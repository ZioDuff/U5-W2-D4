package JacopoDeMaio.ValidationAndUpload.services;


import JacopoDeMaio.ValidationAndUpload.entities.Autore;
import JacopoDeMaio.ValidationAndUpload.exceptions.BadRequestException;
import JacopoDeMaio.ValidationAndUpload.exceptions.NotFoundException;
import JacopoDeMaio.ValidationAndUpload.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;


//    METODI

//    metodo getList
    public Page<Autore> getAutoreList(int page, int size, String sorteBy){
        if(size> 20) size = 20;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sorteBy));
        return autoreRepository.findAll(pageable);
    }
//
//    metodo save autore
    public Autore saveAutore(Autore body){
        autoreRepository.findByEmail(body.getEmail()).ifPresent(
                autore -> {
                    throw new BadRequestException("Attenzione l'email: "+ body.getEmail() + " è gia in uso!");
                }
        );

        body.setAvatar("https://ui-avatars.com/api/?name="+ body.getNome()+ "+"+ body.getCognome());
        autoreRepository.save(body);
        return body;
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
    }

