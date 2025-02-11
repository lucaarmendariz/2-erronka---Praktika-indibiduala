package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/kolore_historiala")
public class Kolore_historiala_controller {

	@Autowired
	Kolore_historiala_ServiceImpl kolore_historialaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Kolore_historiala>> getAllKoloreHistoriala() {
    	
        List<Kolore_historiala> kolore_historiala_list = kolore_historialaService.getAll();
        return ResponseEntity.ok(kolore_historiala_list);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Kolore_historiala> findKoloreHistoriala(@PathVariable Long id) {
    	Optional<Kolore_historiala> kolore_historiala_list = kolore_historialaService.find(id);
    	if(kolore_historiala_list.isPresent()) {
    		return ResponseEntity.ok(kolore_historiala_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Kolore_historiala> createKoloreHistoriala(@RequestBody Kolore_historiala kolore_historiala) {
		return ResponseEntity.status(HttpStatus.CREATED).body(kolore_historialaService.save(kolore_historiala));
	}
}

