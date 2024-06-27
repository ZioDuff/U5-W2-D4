package JacopoDeMaio.ValidationAndUpload.services;


import JacopoDeMaio.ValidationAndUpload.entities.Autore;
import JacopoDeMaio.ValidationAndUpload.exceptions.BadRequestException;
import JacopoDeMaio.ValidationAndUpload.exceptions.NotFoundException;
import JacopoDeMaio.ValidationAndUpload.payloads.AutoreDTO;
import JacopoDeMaio.ValidationAndUpload.repository.AutoreRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;




//    METODI

//    metodo getList
    public Page<Autore> getAutoreList(int page, int size, String sorteBy){
        if(size> 20) size = 20;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sorteBy));
        return autoreRepository.findAll(pageable);
    }
//
//    metodo save autore
    public Autore saveAutore(AutoreDTO body){
        autoreRepository.findByEmail(body.email()).ifPresent(
                autore -> {
                    throw new BadRequestException("Attenzione l'email: "+ body.email() + " è gia in uso!");
                }
        );

        Autore autore= new Autore(body.nome(), body.cognome(), body.email(), body.dataDiNascita());

        autore.setAvatar("https://ui-avatars.com/api/?name="+ body.nome()+ "+"+ body.cognome());
        return autoreRepository.save(autore);


    }
//
////    metodo GET singolo elemento
    public Autore findAutoreById(UUID autoreId){
        Autore found =autoreRepository.findById(autoreId).orElseThrow(()->new NotFoundException(autoreId));
        return found;
    }
//
////    metodo PUT per le modifiche
    public Autore findAutoreByIdAndUpdate(UUID autoreId, Autore body){
        Autore found = autoreRepository.findById(autoreId).orElseThrow(()->new NotFoundException(autoreId));
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setDataDiNascita(body.getDataDiNascita());



        return autoreRepository.save(found);

    }

////    metodo DELETE
    public void findAutoreByIdAndDelete(UUID autoreId){
        Autore found = autoreRepository.findById(autoreId).orElseThrow(()->new NotFoundException(autoreId));
        autoreRepository.delete(found);


        }

        public String uploadImage(MultipartFile file, UUID autoreId) throws IOException {
        String img = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        Autore found = autoreRepository.findById(autoreId).orElseThrow(()->new NotFoundException(autoreId));
        found.setAvatar(img);
        autoreRepository.save(found);
        return img;
        }
    }

