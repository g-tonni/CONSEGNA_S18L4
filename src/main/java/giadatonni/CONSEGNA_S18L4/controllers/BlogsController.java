package giadatonni.CONSEGNA_S18L4.controllers;

import giadatonni.CONSEGNA_S18L4.entities.Blog;
import giadatonni.CONSEGNA_S18L4.payload.BlogPayload;
import giadatonni.CONSEGNA_S18L4.services.BlogsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Blog addBlog(@RequestBody BlogPayload body){
        return blogsService.postaBlog(body);
    }

    @GetMapping("/{blogId}")
    public Blog findById(@PathVariable UUID blogId){
        return blogsService.trovaBlog(blogId);
    }

    @PutMapping("/{blogId}")
    public Blog putBlog(@PathVariable UUID blogId, @RequestBody BlogPayload body){
        return blogsService.modificaBlog(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable UUID blogId){
        blogsService.eliminaBlog(blogId);
    }
}
