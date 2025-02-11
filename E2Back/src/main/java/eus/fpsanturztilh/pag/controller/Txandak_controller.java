package eus.fpsanturztilh.pag.controller;

import eus.fpsanturztilh.pag.model.Txandak;
import eus.fpsanturztilh.pag.service.Txandak_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/txandak")
public class Txandak_controller {

    @Autowired
    private Txandak_service txandakService;

    @GetMapping
    public ResponseEntity<List<Txandak>> getAllTxandak() {
        List<Txandak> txandakList = txandakService.getAll();
        if (txandakList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(txandakList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Txandak> getTxandakById(@PathVariable Long id) {
        Optional<Txandak> txandakOptional = txandakService.find(id);
        return txandakOptional.map(txandak -> new ResponseEntity<>(txandak, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Txandak> createTxandak(@RequestBody Txandak txandak) {
        try {
            Txandak createdTxandak = txandakService.create(txandak);
            return new ResponseEntity<>(createdTxandak, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Txandak> updateTxandak(@PathVariable Long id, @RequestBody Txandak txandak) {
        Optional<Txandak> txandakOptional = txandakService.find(id);
        if (txandakOptional.isPresent()) {
            txandak.setId(id);
            Txandak updatedTxandak = txandakService.update(txandak);
            return new ResponseEntity<>(updatedTxandak, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Txandak> deleteTxandak(@PathVariable Long id) {
        Optional<Txandak> txandakOptional = txandakService.find(id);
        if (txandakOptional.isPresent()) {
            Txandak txandak = txandakOptional.get();
            txandak.setEzabatzeData(LocalDateTime.now()); // Asignar la fecha de eliminación
            Txandak updatedTxandak = txandakService.update(txandak);
            return new ResponseEntity<>(updatedTxandak, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mota/{mota}")
    public ResponseEntity<List<Txandak>> getTxandakByMota(@PathVariable String mota) {
        List<Txandak> txandakList = txandakService.findByMota(mota);
        if (txandakList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(txandakList, HttpStatus.OK);
    }
}
