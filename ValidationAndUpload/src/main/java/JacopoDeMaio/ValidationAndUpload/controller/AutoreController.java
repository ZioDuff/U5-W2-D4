package JacopoDeMaio.ValidationAndUpload.controller;



import JacopoDeMaio.ValidationAndUpload.entities.Autore;
import JacopoDeMaio.ValidationAndUpload.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Autore saveAutore(@RequestBody Autore body){
        return autoreService.saveAutore(body);
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

}
