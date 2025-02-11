package eus.fpsanturztilh.pag.service;

import java.util.*;
import eus.fpsanturztilh.pag.model.*;

public interface Hitzordu_service {
	
	public List<Hitzorduak> getAll();
    
    public Optional<Hitzorduak> find(Long id);
    
    public Hitzorduak save(Hitzorduak hitzordu);
    
}
