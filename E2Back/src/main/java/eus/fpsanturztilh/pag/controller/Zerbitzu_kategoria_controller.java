package eus.fpsanturztilh.pag.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eus.fpsanturztilh.pag.model.Produktu_Kategoria;
import eus.fpsanturztilh.pag.model.Produktuak;
import eus.fpsanturztilh.pag.model.Zerbitzu_kategoria;
import eus.fpsanturztilh.pag.model.Zerbitzuak;
import eus.fpsanturztilh.pag.service.ZerbitzuKategoriaServiceImpl;
import eus.fpsanturztilh.pag.service.ZerbitzuServiceImpl;


@RestController
@RequestMapping("/api/zerbitzu_kategoria")
@CrossOrigin(origins = "http://localhost:8100")
public class Zerbitzu_kategoria_controller {

	@Autowired
	ZerbitzuKategoriaServiceImpl zerbitzuKategoriaService;
	
	//@Autowired
	//Zerbitzu_kategoria_service zerbitzuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Zerbitzu_kategoria>> getAllZerbitzuak() {
    	
        List<Zerbitzu_kategoria> zerbitzuKategoriaList = zerbitzuKategoriaService.getAll();
        return ResponseEntity.ok(zerbitzuKategoriaList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Zerbitzu_kategoria> findZerbitzuak(@PathVariable Long id) {
    	Optional<Zerbitzu_kategoria> zerbitzuKategoriaList = zerbitzuKategoriaService.find(id);
    	if(zerbitzuKategoriaList.isPresent()) {
    		return ResponseEntity.ok(zerbitzuKategoriaList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Zerbitzu_kategoria> createZerbitzua(@RequestBody Zerbitzu_kategoria kategoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(zerbitzuKategoriaService.save(kategoria));
	}
    
    @PutMapping("")
    public ResponseEntity<Zerbitzu_kategoria> editarZerbitzua(@RequestBody Zerbitzu_kategoria kategoria) {
        Optional<Zerbitzu_kategoria> kategoriaExistente = zerbitzuKategoriaService.find(kategoria.getId());
        if (kategoriaExistente.isPresent()) {
        	Zerbitzu_kategoria kategoriaActualizado = kategoriaExistente.get();
        	kategoriaActualizado.setIzena(kategoria.getIzena());
        	kategoriaActualizado.setKolorea(kategoria.isKolorea());
        	kategoriaActualizado.setExtra(kategoria.isExtra());
        	zerbitzuKategoriaService.save(kategoriaActualizado);
            return ResponseEntity.status(HttpStatus.OK).body(kategoriaActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduktua(@PathVariable Long id) {
    	Optional<Zerbitzu_kategoria> kategoria = zerbitzuKategoriaService.find(id);
    	if (kategoria.isPresent()) {
    		Zerbitzu_kategoria existingKategoria = kategoria.get();
    		existingKategoria.setEzabatzeData(LocalDateTime.now());
    		zerbitzuKategoriaService.save(existingKategoria);
    	    return ResponseEntity.status(HttpStatus.OK).build();
    	} else {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
}

