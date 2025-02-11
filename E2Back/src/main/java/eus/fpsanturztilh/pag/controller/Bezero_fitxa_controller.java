package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/bezero_fitxak")
@CrossOrigin(origins = "http://localhost:8100")
public class Bezero_fitxa_controller {

	@Autowired
	Bezero_fitxa_ServiceImpl bezero_fitxaService;
	
	@Autowired
	Kolore_historiala_service kolore_historialaService; 
	
	@Autowired
	ProduktuServiceImpl produktuaService; 
	
    @GetMapping("")
    public ResponseEntity<List<Bezero_fitxa>> getAllBezeroFitxak() {
    	
        List<Bezero_fitxa> bezero_fitxa_list = bezero_fitxaService.getAll();
        return ResponseEntity.ok(bezero_fitxa_list);
	}
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Bezero_fitxa> findBezeroFitxak(@PathVariable Long id) {
    	Optional<Bezero_fitxa> bezero_fitxa_list = bezero_fitxaService.find(id);
    	if(bezero_fitxa_list.isPresent()) {
    		return ResponseEntity.ok(bezero_fitxa_list.get());
    	}
        return ResponseEntity.notFound().build();
	}
    
    @PostMapping("")
    public ResponseEntity<Bezero_fitxa> createBezeroFitxak(@RequestBody Bezero_fitxa bezero_fitxa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bezero_fitxaService.save(bezero_fitxa));
	}
    
    @PutMapping("")
    public ResponseEntity<Bezero_fitxa> updateBezero(@RequestBody Bezero_fitxa bezeroFitxa) {
        Optional<Bezero_fitxa> existingBezero = bezero_fitxaService.find(bezeroFitxa.getId());
        if (existingBezero.isPresent()) {
            Bezero_fitxa bezero = existingBezero.get();
            bezero.setIzena(bezeroFitxa.getIzena());
            bezero.setAbizena(bezeroFitxa.getAbizena());
            bezero.setTelefonoa(bezeroFitxa.getTelefonoa());
            bezero.setAzalSentikorra(bezeroFitxa.getAzalSentikorra());

            // Lista para mantener historiales actualizados
            List<Kolore_historiala> updatedHistoriala = new ArrayList<>();

            for (Kolore_historiala hist : bezeroFitxa.getHistoriala()) {
                if (hist.getId() == null) {
                    // Si no tiene ID, es un nuevo historial -> Añadirlo
                    hist.setBezeroa(bezero);
                 // Asignar producto si solo viene el ID
                    //if (hist.getProduktua() == null && hist.getid_produktua() != null) {
                      //  Produktuak produktua = produktuaService.find(hist.getid_produktua())
                        //    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                        //hist.setProduktua(produktua);
                    //}
                    updatedHistoriala.add(hist);
                } else {
                    // Si ya existe, buscarlo y actualizarlo
                    Optional<Kolore_historiala> existingHist = kolore_historialaService.find(hist.getId());
                    if (existingHist.isPresent()) {
                        Kolore_historiala existing = existingHist.get();
                        existing.setData(hist.getData());
                        existing.setKantitatea(hist.getKantitatea());
                        existing.setBolumena(hist.getBolumena());
                        existing.setOharrak(hist.getOharrak());

                        // Si se eliminó, actualizar ezabatze_data
                        if (hist.getEzabatzeData() != null) {
                            existing.setEzabatzeData(LocalDateTime.now());
                        }
                        // Guardar historial actualizado
                        kolore_historialaService.save(existing);
                        updatedHistoriala.add(existing);
                    }
                }
            }

            // Reemplazar historial del bezero
            bezero.getHistoriala().clear();
            bezero.getHistoriala().addAll(updatedHistoriala);

            // Guardar bezero con historial actualizado
            bezero_fitxaService.save(bezero);
            return ResponseEntity.ok(bezero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("")
    public ResponseEntity<Bezero_fitxa> deleteCita(@RequestBody Bezero_fitxa bezero) {
        Optional<Bezero_fitxa> bezeroExistente = bezero_fitxaService.find(bezero.getId());
        if (bezeroExistente.isPresent()) {
            Bezero_fitxa bezeroa = bezeroExistente.get();
            bezeroa.setEzabatzeData(LocalDateTime.now());
            Bezero_fitxa bezeroActualizada = bezero_fitxaService.save(bezeroa);
            return ResponseEntity.ok(bezeroActualizada);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }


}

