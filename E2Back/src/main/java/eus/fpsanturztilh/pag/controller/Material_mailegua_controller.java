package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/material_mailegua")
public class Material_mailegua_controller {

	@Autowired
	Material_mailegu_ServiceImpl mailegua_Service; 
	
    @GetMapping("")
    public ResponseEntity<List<Material_mailegua>> getAllMaileguak() {	
        List<Material_mailegua> mailegua_list = mailegua_Service.getAll();
        return ResponseEntity.ok(mailegua_list);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Material_mailegua> findMaileguak(@PathVariable Long id) {
    	Optional<Material_mailegua> mailegua_list = mailegua_Service.find(id);
    	if(mailegua_list.isPresent()) {
    		return ResponseEntity.ok(mailegua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PutMapping("")
    public ResponseEntity<String> terminarMugimenduak(@RequestBody List<Material_mailegua> mugimenduak) {
        try {
        	mailegua_Service.terminarMovimientos(mugimenduak);

            return new ResponseEntity<>("Mugimenduak ondo erregistratu dira", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PostMapping("")
    public ResponseEntity<String> registrarMovimientos(@RequestBody List<Material_mailegua> movimientos) {
        try {
        	mailegua_Service.registrarMovimientos(movimientos);

            return new ResponseEntity<>("Mugimenduak ondo erregistratu dira", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

