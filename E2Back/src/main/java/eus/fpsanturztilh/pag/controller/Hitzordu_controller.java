package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")  // Permite solicitudes desde Ionic
@RequestMapping("/api/hitzorduak")
public class Hitzordu_controller {

	@Autowired
	Hitzordu_ServiceImpl hitzorduService; 
	
	@Autowired
	Langile_ServiceImpl langileService; 
	
    @GetMapping("")
    public ResponseEntity<List<Hitzorduak>> getAllHitzorduak() {
    	
        List<Hitzorduak> hitzorduakList = hitzorduService.getAll();
        return ResponseEntity.ok(hitzorduakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Hitzorduak> findHitzorduak(@PathVariable Long id) {
    	Optional<Hitzorduak> hitzordua_list = hitzorduService.find(id);
    	if(hitzordua_list.isPresent()) {
    		return ResponseEntity.ok(hitzordua_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @GetMapping("/ticket")
    public ResponseEntity<List<Hitzorduak>> getHitzorduakConPrecio() {
        List<Hitzorduak> hitzorduakList = hitzorduService.getAll();
        List<Hitzorduak> citasConPrecio = new ArrayList<>();
        for (Hitzorduak hitzordu : hitzorduakList) {
            if (hitzordu.getPrezioTotala() != null) {
                citasConPrecio.add(hitzordu);
            }
        }
        return ResponseEntity.ok(citasConPrecio);
    }
    
    @PostMapping("")
    public ResponseEntity<Hitzorduak> createHitzorduak(@RequestBody Hitzorduak hitzordu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(hitzorduService.save(hitzordu));
	}
    
    @PutMapping("")
    public ResponseEntity<Hitzorduak> actualizarCita(@RequestBody Hitzorduak hitzordu) {
        Optional<Hitzorduak> citaExistente = hitzorduService.find(hitzordu.getId());

        if (citaExistente.isPresent()) {
            Hitzorduak cita = citaExistente.get();
            
            cita.setEserlekua(hitzordu.getEserlekua());
            cita.setData(hitzordu.getData());
            cita.setHasieraOrdua(hitzordu.getHasieraOrdua());
            cita.setAmaieraOrdua(hitzordu.getAmaieraOrdua());
            cita.setIzena(hitzordu.getIzena());
            cita.setTelefonoa(hitzordu.getTelefonoa());
            cita.setDeskribapena(hitzordu.getDeskribapena());
            cita.setEtxekoa(hitzordu.getEtxekoa());

            Hitzorduak citaActualizada = hitzorduService.save(cita);
            return ResponseEntity.ok(citaActualizada);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    @PutMapping("/asignar/{id_langile}")
    public ResponseEntity<Hitzorduak> asignarCita(@RequestBody Hitzorduak hitzordu, @PathVariable Long id_langile) {
        Optional<Hitzorduak> citaExistente = hitzorduService.find(hitzordu.getId());
        if (citaExistente.isPresent()) {
            Hitzorduak cita = citaExistente.get();
            Optional<Langileak> langileExistente = langileService.find(id_langile);
            if(langileExistente.isPresent()) {
            	Langileak langile = langileExistente.get();
            	cita.setLangilea(langile);
            	cita.setHasieraOrduaErreala(LocalTime.now());
            	Hitzorduak citaActualizada = hitzorduService.save(cita);
                return ResponseEntity.ok(citaActualizada);
            }
            return ResponseEntity.notFound().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    @DeleteMapping("")
    public ResponseEntity<Hitzorduak> deleteCita(@RequestBody Hitzorduak hitzordu) {
        Optional<Hitzorduak> citaExistente = hitzorduService.find(hitzordu.getId());
        if (citaExistente.isPresent()) {
            Hitzorduak cita = citaExistente.get();
            cita.setEzabatzeData(LocalDateTime.now());
            Hitzorduak citaActualizada = hitzorduService.save(cita);
            return ResponseEntity.ok(citaActualizada);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}

