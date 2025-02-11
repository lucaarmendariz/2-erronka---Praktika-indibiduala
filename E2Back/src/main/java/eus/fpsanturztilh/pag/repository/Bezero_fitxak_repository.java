package eus.fpsanturztilh.pag.repository;

import eus.fpsanturztilh.pag.model.Bezero_fitxak;

import java.util.List;

import org.springframework.data.jpa.repository.*;

public interface Bezero_fitxak_repository extends JpaRepository<Bezero_fitxak, Long> {
	public List<Bezero_fitxak> findByIzena(String izena);
}
