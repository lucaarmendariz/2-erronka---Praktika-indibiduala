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
import eus.fpsanturztilh.pag.service.ProduktuKategoriaServiceImpl;
import eus.fpsanturztilh.pag.service.ProduktuServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/produktu_kategoria")
public class Produktu_kategoria_controller {

	@Autowired
	ProduktuKategoriaServiceImpl produktuKatService;
	
	//@Autowired
	//Produktu_kategoria_service produktuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Produktu_Kategoria>> getAllProduktuak() {
    	
        List<Produktu_Kategoria> produktuakKatList = produktuKatService.getAll();
        return ResponseEntity.ok(produktuakKatList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Produktu_Kategoria> findProduktu(@PathVariable Long id) {
    	Optional<Produktu_Kategoria> produktuakKatList = produktuKatService.find(id);
    	if(produktuakKatList.isPresent()) {
    		return ResponseEntity.ok(produktuakKatList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PutMapping("/id/{id}")
    public ResponseEntity<Produktu_Kategoria> editarProduktuKategoria(@RequestBody Produktu_Kategoria kategoria, @PathVariable Long id) {
        Optional<Produktu_Kategoria> kategoriaExistente = produktuKatService.find(id);
        if (kategoriaExistente.isPresent()) {
            Produktu_Kategoria categoriaActualizado = kategoriaExistente.get();
            categoriaActualizado.setIzena(kategoria.getIzena());
            produktuKatService.save(categoriaActualizado);
            return ResponseEntity.status(HttpStatus.OK).body(categoriaActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @PostMapping("")
	public ResponseEntity<Produktu_Kategoria> createProduktua(@RequestBody Produktu_Kategoria produktu) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(produktuKatService.save(produktu));
	}
	
	@DeleteMapping("")
    public ResponseEntity<Void> deleteProduktua(@RequestBody Produktu_Kategoria kat) {
    	Optional<Produktu_Kategoria> Kategoria = produktuKatService.find(kat.getId());
    	if (Kategoria.isPresent()) {
    		Produktu_Kategoria existingKategori = Kategoria.get();
    		existingKategori.setEzabatzeData(LocalDateTime.now());
    	    produktuKatService.save(existingKategori);
    	    return ResponseEntity.status(HttpStatus.OK).build();
    	} else {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
}

