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

import eus.fpsanturztilh.pag.model.Material_kategoria;
import eus.fpsanturztilh.pag.service.MaterialKategoriaServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/material_kategoria")
public class Material_kategoria_controller {

	@Autowired
	MaterialKategoriaServiceImpl materialKatService;
	
    @GetMapping("")
    public ResponseEntity<List<Material_kategoria>> getAllMaterialak() {
    	
        List<Material_kategoria> materialKatList = materialKatService.getAll();
        return ResponseEntity.ok(materialKatList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Material_kategoria> findMaterial(@PathVariable Long id) {
    	Optional<Material_kategoria> materialKatList = materialKatService.find(id);
    	if(materialKatList.isPresent()) {
    		return ResponseEntity.ok(materialKatList.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PutMapping("/id/{id}")
    public ResponseEntity<Material_kategoria> editarMaterialKategoria(@RequestBody Material_kategoria kategoria, @PathVariable Long id) {
        Optional<Material_kategoria> kategoriaExistente = materialKatService.find(id);
        if (kategoriaExistente.isPresent()) {
        	Material_kategoria categoriaActualizado = kategoriaExistente.get();
            categoriaActualizado.setIzena(kategoria.getIzena());
            materialKatService.save(categoriaActualizado);
            return ResponseEntity.status(HttpStatus.OK).body(categoriaActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @PostMapping("")
	public ResponseEntity<Material_kategoria> createMaterialKategoria(@RequestBody Material_kategoria material) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(materialKatService.save(material));
	}
	
	@DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteMateriala(@PathVariable Long id) {
    	Optional<Material_kategoria> Kategoria = materialKatService.find(id);
    	if (Kategoria.isPresent()) {
    		Material_kategoria existingKategori = Kategoria.get();
    		existingKategori.setEzabatzeData(LocalDateTime.now());
    		materialKatService.save(existingKategori);
    	    return ResponseEntity.status(HttpStatus.OK).build();
    	} else {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
}

