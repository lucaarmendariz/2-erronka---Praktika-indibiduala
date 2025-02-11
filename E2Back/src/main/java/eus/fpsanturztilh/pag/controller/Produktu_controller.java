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
import eus.fpsanturztilh.pag.service.ProduktuKategoriaServiceImpl;
import eus.fpsanturztilh.pag.service.ProduktuServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/produktuak")
public class Produktu_controller {

	@Autowired
	ProduktuServiceImpl produktuService;
	
	@Autowired
	ProduktuKategoriaServiceImpl produktuKategoriaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Produktuak>> getAllProduktuak() {
    	
        List<Produktuak> produktuakList = produktuService.getAll();
        return ResponseEntity.ok(produktuakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Produktuak> findProduktu(@PathVariable Long id) {
    	Optional<Produktuak> produktua_list = produktuService.find(id);
    	if(produktua_list.isPresent()) {
    		return ResponseEntity.ok(produktua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
	public ResponseEntity<Produktuak> createProduktua(@RequestBody Produktuak produktu) {
    	Long id = produktu.getProduktuKategoria().getId();
    	Optional<Produktu_Kategoria> kategoria_list = produktuKategoriaService.find(id);
    	if(kategoria_list.isPresent()) {
    		produktu.setProduktuKategoria(kategoria_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(produktuService.save(produktu));
	}
    
    @PutMapping("")
    public ResponseEntity<Produktuak> editarProducto(@RequestBody Produktuak producto) {
        Optional<Produktuak> productoExistente = produktuService.find(producto.getId());
        if (productoExistente.isPresent()) {
            Produktuak productoActualizado = productoExistente.get();
            productoActualizado.setIzena(producto.getIzena());
            productoActualizado.setDeskribapena(producto.getDeskribapena());
            productoActualizado.setMarka(producto.getMarka());
            productoActualizado.setStock(producto.getStock());
            productoActualizado.setStockAlerta(producto.getStockAlerta());
            Long kategoriaId = producto.getProduktuKategoria().getId();
            Optional<Produktu_Kategoria> categoria = produktuKategoriaService.find(kategoriaId);
            if (categoria.isPresent()) {
                productoActualizado.setProduktuKategoria(categoria.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            produktuService.save(productoActualizado);
            return ResponseEntity.status(HttpStatus.OK).body(productoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @DeleteMapping("")
    public ResponseEntity<Void> deleteProduktua(@RequestBody Produktuak produkto) {
    	Optional<Produktuak> produktua = produktuService.find(produkto.getId());
    	if (produktua.isPresent()) {
    	    Produktuak existingProduct = produktua.get();
    	    existingProduct.setEzabatzeData(LocalDateTime.now());
    	    produktuService.save(existingProduct);
    	    return ResponseEntity.status(HttpStatus.OK).build();
    	} else {
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
}

