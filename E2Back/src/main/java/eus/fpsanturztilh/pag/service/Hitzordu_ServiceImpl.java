package eus.fpsanturztilh.pag.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.repository.*;

@Service
public class Hitzordu_ServiceImpl implements Hitzordu_service {
	@Autowired
	Hitzordu_repository hitzorduRepository; 
	
	@Override
	public List<Hitzorduak> getAll()
	{
		List<Hitzorduak> hitzorduakList = hitzorduRepository.findAll();
        return hitzorduakList;
	}
	
	@Override
    public Optional<Hitzorduak> find(Long id)
    {
    	Optional<Hitzorduak> hitzordua_list = hitzorduRepository.findById(id);
    	return hitzordua_list;
    }
	
	@Override
    public Hitzorduak save(Hitzorduak hitzordu)
    {
		return hitzorduRepository.save(hitzordu);
    }
}
