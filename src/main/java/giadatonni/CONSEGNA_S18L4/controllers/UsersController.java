package giadatonni.CONSEGNA_S18L4.controllers;

import giadatonni.CONSEGNA_S18L4.entities.Blog;
import giadatonni.CONSEGNA_S18L4.entities.User;
import giadatonni.CONSEGNA_S18L4.exceptions.ValidationException;
import giadatonni.CONSEGNA_S18L4.payload.UserDTO;
import giadatonni.CONSEGNA_S18L4.services.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "surname") String orderBy){
        return usersService.findAll(page,size,orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Validated UserDTO body, BindingResult validationResults){
        if(validationResults.hasErrors()){
            List<String> errors = validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        } else {
            return usersService.postaUtente(body);
        }
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId){
        return usersService.trovaUtente(userId);
    }

    @PutMapping("/{userId}")
    public User putUser(@PathVariable UUID userId, @RequestBody @Validated UserDTO body, BindingResult validationResults){
        if(validationResults.hasErrors()){
            List<String> errors = validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        } else {
            return usersService.modificaUtente(userId, body);
        }
    }

    @GetMapping("/trovaBlog/{utenteId}")
    public List<Blog> findByUtenteId(@PathVariable UUID utenteId){
        return this.usersService.findAllBlogByUtenteId(utenteId);
    }

    @DeleteMapping("/eliminaBlogByUtente/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteListaBlogs(@PathVariable UUID utenteId){
        this.usersService.deleteAllPostByUtenteId(utenteId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId){
        usersService.eliminaUtente(userId);
    }

  /*  @PatchMapping("/{userId}/avatar")
    public void uploadImage(@RequestParam("profile_picture") MultipartFile file, @PathVariable UUID userId) {
        // profile_picture deve corrispondere ESATTAMENTE al campo del Form Data dove viene inserito il file
        // se così non è il file non verrà trovato
        System.out.println(file.getName());
        // return this.usersService.uploadAvatar(userId, file);
    }*/

    /*@PatchMapping("/picture")
    public void upload(@RequestParam("picture") MultipartFile file){
        System.out.println(file.getName());
    }*/
}
