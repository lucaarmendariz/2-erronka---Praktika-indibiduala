package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.service.*;
import eus.fpsanturztilh.pag.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/langileak")
public class Langile_controller {

	@Autowired
	Langile_ServiceImpl langileService;
	
	@Autowired
	Talde_service taldeService; 
	
	@Autowired
	Txandak_service txandakService; 
	 
    @GetMapping("")
    public ResponseEntity<List<Langileak>> getAllLangileak() {    	
        List<Langileak> langileakList = langileService.getAll();
        return ResponseEntity.ok(langileakList);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Langileak> findLangilea(@PathVariable Long id) {
    	Optional<Langileak> langile_list = langileService.find(id);
    	if(langile_list.isPresent()) {
    		return ResponseEntity.ok(langile_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Langileak> createLangilea(@RequestBody Langileak langile) {
    	String kodea = langile.getTaldea().getKodea();
    	Optional<Taldeak> talde_list = taldeService.find(kodea);
    	if(talde_list.isPresent()) {
    		langile.setTaldea(talde_list.get());
    	}
		return ResponseEntity.status(HttpStatus.CREATED).body(langileService.save(langile));
	}
    
    @PutMapping("/{id}")
    public ResponseEntity<Langileak> updateLangilea(@PathVariable Long id, @RequestBody Langileak langileUpdated) {
        // Buscar el Langilea por su ID
        Optional<Langileak> existingLangileaOpt = langileService.find(id);
        
        if (existingLangileaOpt.isPresent()) {
            // Si el Langilea existe, obtenemos el objeto actual
            Langileak existingLangilea = existingLangileaOpt.get();
            
            // Actualizamos los campos del Langilea con los nuevos valores
            existingLangilea.setIzena(langileUpdated.getIzena());
            existingLangilea.setAbizenak(langileUpdated.getAbizenak());

            // Actualizamos la relación con Taldea si se proporciona un nuevo Taldea (grupo)
            if (langileUpdated.getTaldea() != null) {
                String newKodea = langileUpdated.getTaldea().getKodea();
                Optional<Taldeak> newTalde = taldeService.find(newKodea);
                if (newTalde.isPresent()) {
                    existingLangilea.setTaldea(newTalde.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Si el código del grupo no es válido
                }
            }
            
            // Actualización de las listas de relaciones (aunque no modificamos sus valores en este ejemplo)
            // Aquí se pueden actualizar si es necesario:
            // - Productos relacionados (Produktu_mugimenduak)
            // - Transacciones relacionadas (Txandak)
            
            // Si se desea actualizar las listas de Produktu_mugimenduak y Txandak, se puede hacerlo de forma similar.
            // Suponiendo que esas listas no deben modificarse directamente, no las actualizamos explícitamente.

            // Actualizamos las fechas correspondientes
            existingLangilea.setEguneratzeData(LocalDateTime.now());  // Fecha de actualización
            
            // Guardamos el Langilea actualizado
            Langileak updatedLangilea = langileService.save(existingLangilea);
            
            // Devolvemos el Langilea actualizado con un código de estado 200 OK
            return ResponseEntity.ok(updatedLangilea);
        } else {
            // Si el Langilea no se encuentra, respondemos con un 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    
    @DeleteMapping("/{id}")
    public ResponseEntity<Langileak> deleteLangilea(@PathVariable Long id) {
        Optional<Langileak> langileOpt = langileService.find(id);

        if (langileOpt.isPresent()) {
            Langileak existingLangilea = langileOpt.get();

            // Obtener las txandak asociadas al langile
            List<Txandak> txandakList = txandakService.findByLangile(existingLangilea);

            // Asignar la fecha de eliminación a las txandak
            LocalDateTime now = LocalDateTime.now();
            for (Txandak txanda : txandakList) {
                txanda.setEzabatzeData(now);
                txandakService.update(txanda);
            }

            // Asignar la fecha de eliminación al langile
            existingLangilea.setEzabatzeData(now);
            langileService.save(existingLangilea);

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}