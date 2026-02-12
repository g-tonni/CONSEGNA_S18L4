package giadatonni.CONSEGNA_S18L4.services;

import giadatonni.CONSEGNA_S18L4.entities.Blog;
import giadatonni.CONSEGNA_S18L4.entities.User;
import giadatonni.CONSEGNA_S18L4.exceptions.NotFoundException;
import giadatonni.CONSEGNA_S18L4.exceptions.BadRequestException;
import giadatonni.CONSEGNA_S18L4.payload.UserDTO;
import giadatonni.CONSEGNA_S18L4.repositories.BlogsRepository;
import giadatonni.CONSEGNA_S18L4.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final BlogsRepository blogsRepository;

    public Page<User> findAll(int page, int size, String orderBy){
        if (size > 100) size = 100;
        if (size < 0) size = 0;
        if (page > 30) page = 30;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.usersRepository.findAll(pageable);
    }

   public User postaUtente(UserDTO body) {
        if(this.usersRepository.existsByEmail(body.email())) throw new BadRequestException("Email già in uso");
        User nuovoUtente = new User(body.nome(), body.cognome(), body.email(), body.dataNascita());
        this.usersRepository.save(nuovoUtente);
        System.out.println("Utente salvato");
        return nuovoUtente;
   }

    public User trovaUtente(UUID userId){
        User found = this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
        return found;
    }

    public User modificaUtente(UUID userId, UserDTO body){
        User found = this.trovaUtente(userId);
        if(!found.getEmail().equals(body.email())){
           if(this.usersRepository.existsByEmail(body.email())) throw new BadRequestException("Email già in uso");
        }
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());
        found.setDataNascita(body.dataNascita());
        this.usersRepository.save(found);
        System.out.println("L'utente con id " + userId + " è stato aggiornato");
        return found;
    }

    public List<Blog> findAllBlogByUtenteId(UUID utenteId){
        return this.blogsRepository.findAllBlogByUtenteId(utenteId);
    }

    public void deleteAllPostByUtenteId(UUID utenteId){
        List<Blog> blogsFound = this.findAllBlogByUtenteId(utenteId);
        blogsFound.forEach(blog -> this.blogsRepository.delete(blog));
        System.out.println("Elenco blog eliminato");
    }

    public void eliminaUtente(UUID userId){
        this.deleteAllPostByUtenteId(userId);
        User found = this.trovaUtente(userId);
        this.usersRepository.delete(found);
        System.out.println("L'utente e tutti i suoi post sono stati eliminati");
    }

}
