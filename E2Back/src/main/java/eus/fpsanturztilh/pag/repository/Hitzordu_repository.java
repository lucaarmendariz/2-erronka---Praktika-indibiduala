package eus.fpsanturztilh.pag.repository;

import eus.fpsanturztilh.pag.model.Hitzorduak;

import java.util.List;

import org.springframework.data.jpa.repository.*;

public interface Hitzordu_repository extends JpaRepository<Hitzorduak, Long> {
	public List<Hitzorduak> findByIzena(String Izena);
}
