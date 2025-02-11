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
import eus.fpsanturztilh.pag.model.Materialak;
import eus.fpsanturztilh.pag.service.MaterialKategoriaServiceImpl;
import eus.fpsanturztilh.pag.service.MaterialaServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/materialak")
public class Materiala_controller {

	@Autowired
	MaterialaServiceImpl materialaService;
	
	@Autowired
	MaterialKategoriaServiceImpl materialKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Materialak>> getAllMaterialak() {
    	
        List<Materialak> materialakList = materialaService.getAll();
        return ResponseEntity.ok(materialakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Materialak> findMateriala(@PathVariable Long id) {
    	Optional<Materialak> materiala_list = materialaService.find(id);
    	if(materiala_list.isPresent()) {
    		return ResponseEntity.ok(materiala_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Materialak> createMateriala(@RequestBody Materialak materiala) {
    	Long id = materiala.getMaterialKategoria().getId();
    	Optional<Material_kategoria> kategoria_list = materialKategoriaService.find(id);
    	if(kategoria_list.isPresent()) {
    		materiala.setMaterialKategoria(kategoria_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(materialaService.create(materiala));
	}
    
    @PutMapping("/id/{id}")
    public ResponseEntity<Materialak> editarMaterial(@PathVariable Long id, @RequestBody Materialak materiala) {
        Optional<Materialak> materialExistente = materialaService.find(id);
        if (materialExistente.isPresent()) {
            Materialak materialActualizado = materialExistente.get();
            materialActualizado.setEtiketa(materiala.getEtiketa());
            materialActualizado.setIzena(materiala.getIzena());
            Long kategoriaId = materiala.getMaterialKategoria().getId();
            Optional<Material_kategoria> categoria = materialKategoriaService.find(kategoriaId);
            if (categoria.isPresent()) {
            	materialActualizado.setMaterialKategoria(categoria.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            materialaService.save(materialActualizado);
            return ResponseEntity.status(HttpStatus.OK).body(materialActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteMateriala(@PathVariable Long id) {
    	Optional<Materialak> material = materialaService.find(id);
    	if (material.isPresent()) {
    	    Materialak existingMaterial = material.get();
    	    existingMaterial.setEzabatzeData(LocalDateTime.now());
    	    materialaService.save(existingMaterial);
    	    return ResponseEntity.status(HttpStatus.OK).build();
    	} else {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
}

