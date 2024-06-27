package JacopoDeMaio.ValidationAndUpload.controller;



import JacopoDeMaio.ValidationAndUpload.entities.Autore;
import JacopoDeMaio.ValidationAndUpload.exceptions.BadRequestException;
import JacopoDeMaio.ValidationAndUpload.payloads.AutoreDTO;
import JacopoDeMaio.ValidationAndUpload.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

//    METODI

    @GetMapping // <-- GET di tutta la lista
    public Page<Autore> getAutoreList(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "2")int size, @RequestParam(defaultValue = "id")String sortedBy){
        return  autoreService.getAutoreList(page,size,sortedBy);
    }
//
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore saveAutore(@RequestBody @Validated AutoreDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            throw  new BadRequestException(validationResult.getAllErrors());
        }else return autoreService.saveAutore(body);
    }
//
    @GetMapping("/{autoreId}")
    public Autore findAutoreById(@PathVariable UUID autoreId){
        return autoreService.findAutoreById(autoreId);
    }
//
    @PutMapping("/{autoreId}")
    public Autore findAutoreByIdAndUpdate(@PathVariable UUID autoreId,@RequestBody Autore body){
        return autoreService.findAutoreByIdAndUpdate(autoreId,body);
    }
//
    @DeleteMapping("/{autoreId}")
    public void findAutoreByIdAndDelete(@PathVariable UUID autoreId){
        autoreService.findAutoreByIdAndDelete(autoreId);
    }

//    Come prima prova per l'upload dell'immagine ho provato una POST ma ragionandoci su in teoria non è propriamente corretto.
//    questo perche noi settiamo un avatar di default alla creazione dell'autore quindi sarebbe + corretto fare una PUT o PATCH
//    detto cio comunque il codice sembra funzionare

//    per prima cosa la nostra operazione avra esattamente questo tipo di endpoint altrimenti non funzionerà
    @PostMapping("/{autoreId}/avatar")
//    il requestParam deve essere esattamente quello dell'endpoint.
//    tra l'atro deve essere per forza avatar altrimenti non funzionerà
//    va gestita l'eccezione IOException
    public String uploadAvatar(@RequestParam("avatar")MultipartFile image, @PathVariable UUID autoreId) throws IOException {
        return autoreService.uploadImage(image,autoreId);
    }

}
