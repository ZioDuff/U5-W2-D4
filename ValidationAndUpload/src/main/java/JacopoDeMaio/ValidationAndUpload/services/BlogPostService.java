package JacopoDeMaio.ValidationAndUpload.services;



import JacopoDeMaio.ValidationAndUpload.entities.Autore;
import JacopoDeMaio.ValidationAndUpload.entities.BlogPost;
import JacopoDeMaio.ValidationAndUpload.exceptions.NotFoundException;
import JacopoDeMaio.ValidationAndUpload.payloads.BlogPostDTO;
import JacopoDeMaio.ValidationAndUpload.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;



////    METODI
//
////  metodo per tornare una lista
    public Page<BlogPost> getBlogPostList(int page, int size, String sortedBy){
        if(size> 10) size=10;
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortedBy));
        return blogPostRepository.findAll(pageable);
    }
//
////    metodo per creare un nuovo blog post
    public BlogPost saveBlogPost(BlogPostDTO body){

        Autore autore = autoreService.findAutoreById(body.autoreId());

        BlogPost newBlogPost= new BlogPost(body.categoria(), body.titolo(),  body.contenuto(), body.tempoDiLettura(),autore);

        newBlogPost.setCover("https://ui-avatars.com/api/?name="+ body.titolo()+"&autoreId="+body.autoreId());


        return   blogPostRepository.save(newBlogPost);

    }
//
////    metodo per tornare unb singolo elemento tramite id
    public BlogPost findById(UUID blogPostId){
        return blogPostRepository.findById(blogPostId).orElseThrow(()-> new NotFoundException(blogPostId));
    }
//
//    public BlogPost findByIdAndUpdate(UUID blogPostId, BlogPostDTO blogPostPayloadUpdate){
//        BlogPost found = blogPostRepository.findById(blogPostId).orElseThrow(()-> new NotFoundException(blogPostId));
//        found.setCover(blogPostPayloadUpdate.);
//        found.setCategoria(blogPostPayloadUpdate.getCategoria());
//
//        return blogPostRepository.save(found);
//
//    }
//
    public void findByIdAndDelete(UUID blogPostId){
       BlogPost found = blogPostRepository.findById(blogPostId).orElseThrow(()-> new NotFoundException(blogPostId));
       blogPostRepository.delete(found);

    }
}
