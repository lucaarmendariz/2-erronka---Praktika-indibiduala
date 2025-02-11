package eus.fpsanturztilh.pag.service;

import java.util.*;
import eus.fpsanturztilh.pag.model.*;

public interface Bezero_fitxak_service {
	
	public List<Bezero_fitxak> getAll();
    
    public Optional<Bezero_fitxak> find(Long id);
    
    public Bezero_fitxak save(Bezero_fitxak bezero_fitxa);
    
}
