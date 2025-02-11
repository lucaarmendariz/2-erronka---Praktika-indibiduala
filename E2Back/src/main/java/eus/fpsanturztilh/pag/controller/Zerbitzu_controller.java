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
import eus.fpsanturztilh.pag.service.ZerbitzuServiceImpl;
import eus.fpsanturztilh.pag.service.Zerbitzu_kategoria_service;


@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/zerbitzuak")
public class Zerbitzu_controller {

	@Autowired
	ZerbitzuServiceImpl zerbitzuService;
	
	@Autowired
	Zerbitzu_kategoria_service zerbitzuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Zerbitzuak>> getAllZerbitzuak() {
    	
        List<Zerbitzuak> zerbitzuakList = zerbitzuService.getAll();
        return ResponseEntity.ok(zerbitzuakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Zerbitzuak> findZerbitzuak(@PathVariable Long id) {
    	Optional<Zerbitzuak> zerbitzua_list = zerbitzuService.find(id);
    	if(zerbitzua_list.isPresent()) {
    		return ResponseEntity.ok(zerbitzua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Zerbitzuak> createZerbitzua(@RequestBody Zerbitzuak zerbitzu) {
    	Long id = zerbitzu.getZerbitzuKategoria().getId();
    	Optional<Zerbitzu_kategoria> kategoria_list = zerbitzuKategoriaService.find(id);
    	if(kategoria_list.isPresent()) {
    		zerbitzu.setZerbitzuKategoria(kategoria_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(zerbitzuService.save(zerbitzu));
	}
    
    @PutMapping("")
    public ResponseEntity<Zerbitzuak> editarZerbitzua(@RequestBody Zerbitzuak zerbitzua) {
        Optional<Zerbitzuak> zerbitzuExistente = zerbitzuService.find(zerbitzua.getId());
        if (zerbitzuExistente.isPresent()) {
            Zerbitzuak zerbitzuActualizado = zerbitzuExistente.get();
            zerbitzuActualizado.setIzena(zerbitzua.getIzena());
            zerbitzuActualizado.setEtxekoPrezioa(zerbitzua.getEtxekoPrezioa());
            zerbitzuActualizado.setKanpokoPrezioa(zerbitzua.getKanpokoPrezioa());
          //  productoActualizado.setStock(producto.getStock());
          //  productoActualizado.setStockAlerta(producto.getStockAlerta());
            Long kategoriaId = zerbitzua.getZerbitzuKategoria().getId();
            Optional<Zerbitzu_kategoria> categoria = zerbitzuKategoriaService.find(kategoriaId);
            if (categoria.isPresent()) {
                zerbitzuActualizado.setZerbitzuKategoria(categoria.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            zerbitzuService.save(zerbitzuActualizado);
            return ResponseEntity.status(HttpStatus.OK).body(zerbitzuActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduktua(@PathVariable Long id) {
    	Optional<Zerbitzuak> zerbitzua = zerbitzuService.find(id);
    	if (zerbitzua.isPresent()) {
    		Zerbitzuak existingZerbitzua = zerbitzua.get();
    		existingZerbitzua.setEzabatzeData(LocalDateTime.now());
    		zerbitzuService.save(existingZerbitzua);
    	    return ResponseEntity.status(HttpStatus.OK).build();
    	} else {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
}

