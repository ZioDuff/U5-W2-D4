package JacopoDeMaio.ValidationAndUpload.controller;



import JacopoDeMaio.ValidationAndUpload.entities.BlogPost;
import JacopoDeMaio.ValidationAndUpload.entities.BlogPostPayload;
import JacopoDeMaio.ValidationAndUpload.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {
//
    @Autowired
    private BlogPostService blogPostService;
//
////    GET che ritorna la lista di blogPost
    @GetMapping // <-- qui andiamo a dirgli che con il metodo get ci ritorna tutta la lista dei blog post
    public Page<BlogPost> getAllBlogPost(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortedBy){
        return  blogPostService.getBlogPostList(page,size,sortedBy);
    }
//
////    GET che ritorna un blogPost singolo
    @GetMapping("/{blogPostId}")
    public BlogPost findBlogPostById(@PathVariable UUID blogPostId){
        return blogPostService.findById(blogPostId);
    }
//
////    POST che serve per generare un singolo blogPost
    @PostMapping // <-- annotazione che viene intercettata durante una post
    @ResponseStatus(HttpStatus.CREATED) // <-- cambiamo il messaggio di status in questo caso 201
    public BlogPost saveBlogPost(@RequestBody BlogPostPayload body){ // <-- per poter generare una post abbiamo bisogno di un body questo viene settato grazie all'apposita annotazione nel parametro
        return this.blogPostService.saveBlogPost(body);
    }
//
    @PutMapping("/{blogPostId}")
    public BlogPost findBlogPostByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody BlogPostPayload body){
        return blogPostService.findByIdAndUpdate(blogPostId,body);
    }
//
    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findBlogPostByIdAndDelete(@PathVariable UUID blogPostId){
        blogPostService.findByIdAndDelete(blogPostId);
    }
}
