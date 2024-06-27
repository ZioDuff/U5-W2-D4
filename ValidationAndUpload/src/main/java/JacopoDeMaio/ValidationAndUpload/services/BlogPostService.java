package JacopoDeMaio.ValidationAndUpload.services;



import JacopoDeMaio.ValidationAndUpload.entities.Autore;
import JacopoDeMaio.ValidationAndUpload.entities.BlogPost;
import JacopoDeMaio.ValidationAndUpload.entities.BlogPostPayload;
import JacopoDeMaio.ValidationAndUpload.exceptions.NotFoundException;
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
    public BlogPost saveBlogPost(BlogPostPayload body){

        Autore autore = autoreService.findAutoreById(body.getAutoreId());

        BlogPost newBlogPost= new BlogPost(body.getCategoria(), body.getTitolo(), body.getCover(), body.getContenuto(), body.getTempoDiLettura(),autore);


        return   blogPostRepository.save(newBlogPost);

    }
//
////    metodo per tornare unb singolo elemento tramite id
    public BlogPost findById(UUID blogPostId){
        return blogPostRepository.findById(blogPostId).orElseThrow(()-> new NotFoundException(blogPostId));
    }
//
    public BlogPost findByIdAndUpdate(UUID blogPostId, BlogPostPayload blogPostPayloadUpdate){
        BlogPost found = blogPostRepository.findById(blogPostId).orElseThrow(()-> new NotFoundException(blogPostId));
        found.setCover(blogPostPayloadUpdate.getCover());
        found.setCategoria(blogPostPayloadUpdate.getCategoria());

        return blogPostRepository.save(found);

    }
//
    public void findByIdAndDelete(UUID blogPostId){
       BlogPost found = blogPostRepository.findById(blogPostId).orElseThrow(()-> new NotFoundException(blogPostId));
       blogPostRepository.delete(found);

    }
}
