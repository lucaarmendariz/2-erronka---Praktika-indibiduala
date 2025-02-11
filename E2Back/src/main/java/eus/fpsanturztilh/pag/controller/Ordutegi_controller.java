package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/api/ordutegiak")
public class Ordutegi_controller {

	@Autowired
	Ordutegi_ServiceImpl ordutegiService;
	
	@Autowired
	Talde_service taldeService; 
	
	// Obtener todos los horarios
    @GetMapping("")
    public ResponseEntity<List<Ordutegiak>> getAllOrdutegiak() {
        List<Ordutegiak> ordutegiList = ordutegiService.getAll();
        if (ordutegiList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay horarios, devolver 204 No Content
        }
        return ResponseEntity.ok(ordutegiList); // Si hay horarios, devolverlos con código 200 OK
    }
    
 // Obtener un horario por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Ordutegiak> getOrdutegiById(@PathVariable Long id) {
        Optional<Ordutegiak> ordutegi = ordutegiService.find(id);
        if (ordutegi.isPresent()) {
            return ResponseEntity.ok(ordutegi.get()); // Si lo encontramos, devolver el horario
        }
        return ResponseEntity.notFound().build(); // Si no se encuentra, devolver 404 Not Found
    }
    
 // Método adicional para actualizar un horario si fuera necesario
    @PutMapping("/id/{id}")
    public ResponseEntity<Ordutegiak> updateOrdutegi(@PathVariable Long id, @RequestBody Ordutegiak ordutegi) {
        Optional<Ordutegiak> existingOrdutegi = ordutegiService.find(id);
        
        if (existingOrdutegi.isPresent()) {
            // Actualizar los campos del horario
            ordutegi.setId(id);  // Asegurarse de mantener el ID para la actualización
            Ordutegiak updatedOrdutegi = ordutegiService.save(ordutegi);
            return ResponseEntity.ok(updatedOrdutegi); // Devolver el horario actualizado
        }
        
        return ResponseEntity.notFound().build(); // Si no se encuentra el horario, devolver 404 Not Found
    }
    
    @PostMapping("")
    public ResponseEntity<?> createOrdutegiak(@RequestBody Ordutegiak ordutegiak) {
        try {
            if (ordutegiak == null || ordutegiak.hasInvalidFields()) {
                return new ResponseEntity<>("Datos de entrada no válidos", HttpStatus.BAD_REQUEST);
            }
            
            Ordutegiak createdOrdutegiak = ordutegiService.save(ordutegiak);
            return new ResponseEntity<>(createdOrdutegiak, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Valores incorrectos en la petición", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // Eliminar un horario por ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteOrdutegi(@PathVariable Long id) {
        Optional<Ordutegiak> ordutegi = ordutegiService.find(id);
        
        if (ordutegi.isPresent()) {
            Ordutegiak horario = ordutegi.get();
            // Puedes agregar la lógica de establecer la fecha de eliminación aquí
            // Por ejemplo, si tu entidad tiene un campo 'ezabatzeData', lo puedes actualizar:
            horario.setEzabatzeData(LocalDateTime.now());  // Establece la fecha y hora actuales
            ordutegiService.save(horario); // Guardamos el horario con la fecha de eliminación
            
            // O si prefieres eliminarlo completamente:
            ordutegiService.save(horario); // Llamamos al método de eliminación en el servicio
            return ResponseEntity.ok("Horario eliminado correctamente"); // Retornar un mensaje de éxito
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario no encontrado"); // Si no existe el horario
    }

}

