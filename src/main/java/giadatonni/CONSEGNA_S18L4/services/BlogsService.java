package giadatonni.CONSEGNA_S18L4.services;

import giadatonni.CONSEGNA_S18L4.entities.Blog;
import giadatonni.CONSEGNA_S18L4.entities.User;
import giadatonni.CONSEGNA_S18L4.exceptions.NotFoundException;
import giadatonni.CONSEGNA_S18L4.payload.BlogDTO;
import giadatonni.CONSEGNA_S18L4.repositories.BlogsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@Setter
@ToString
@AllArgsConstructor
public class BlogsService {

    private final BlogsRepository blogsRepository;
    private final UsersService usersService;

    public Page<Blog> findAll(int page, int size, String orderBy){
        if (size > 100) size = 100;
        if (size < 0) size = 0;
        if (page > 30) page = 30;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.blogsRepository.findAll(pageable);
    }

   public Blog postaBlog(BlogDTO body) {
        User found = this.usersService.trovaUtente(UUID.fromString(body.utenteId()));
        Blog nuovoBlog = new Blog(body.categoria(), body.titolo(), body.contenuto(), body.tempoLettura(), found);
        this.blogsRepository.save(nuovoBlog);
        System.out.println("Blog salvato");
        return nuovoBlog;
    }

    public Blog trovaBlog(UUID blogId){
        Blog found = null;
        found = this.blogsRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
        return found;
    }

    public Blog modificaBlog(UUID blogId, BlogDTO body){
        Blog found = this.trovaBlog(blogId);
        User userFound = this.usersService.trovaUtente(UUID.fromString(body.utenteId()));
        found.setCategoria(body.categoria());
        found.setTitolo(body.titolo());
        found.setContenuto(body.contenuto());
        found.setTempoLettura(body.tempoLettura());
        found.setUtente(userFound);
        this.blogsRepository.save(found);
        System.out.println("Blog aggiornato correttamente");
        return found;
    }

    public void eliminaBlog(UUID blogId){
        Blog found = this.trovaBlog(blogId);
        this.blogsRepository.delete(found);
        System.out.println("Blog eliminato");
    }


}
