package giadatonni.CONSEGNA_S18L4.controllers;

import giadatonni.CONSEGNA_S18L4.entities.Blog;
import giadatonni.CONSEGNA_S18L4.exceptions.ValidationException;
import giadatonni.CONSEGNA_S18L4.payload.BlogDTO;
import giadatonni.CONSEGNA_S18L4.services.BlogsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    private final BlogsService blogsService;

    public BlogsController(BlogsService blogsService) {
        this.blogsService = blogsService;
    }

    @GetMapping
    public Page<Blog> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "titolo") String orderBy){
        return blogsService.findAll(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog addBlog(@RequestBody @Validated BlogDTO body, BindingResult validationResults){
        if(validationResults.hasErrors()){
            List<String> errors = validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        } else {
            return blogsService.postaBlog(body);
        }
    }

    @GetMapping("/{blogId}")
    public Blog findById(@PathVariable UUID blogId){
        return blogsService.trovaBlog(blogId);
    }

    @PutMapping("/{blogId}")
    public Blog putBlog(@PathVariable UUID blogId, @RequestBody @Validated BlogDTO body, BindingResult validationResults){
        if(validationResults.hasErrors()){
            List<String> errors = validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errors);
        } else {
            return blogsService.modificaBlog(blogId, body);
        }
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable UUID blogId){
        blogsService.eliminaBlog(blogId);
    }

    @PatchMapping("/picture")
    public void upload(@RequestParam("picture") MultipartFile file){
        System.out.println(file.getName());
    }
}
