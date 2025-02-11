package eus.fpsanturztilh.pag.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturztilh.pag.model.*;
import eus.fpsanturztilh.pag.repository.*;

@Service
public class Bezero_fitxak_ServiceImpl implements Bezero_fitxak_service {
	@Autowired
	Bezero_fitxak_repository BezeroFitxakRepository; 
	
	@Override
	public List<Bezero_fitxak> getAll()
	{
		List<Bezero_fitxak> bezeroFitxaList = BezeroFitxakRepository.findAll();
        return bezeroFitxaList;
	}
	
	@Override
    public Optional<Bezero_fitxak> find(Long id)
    {
    	Optional<Bezero_fitxak> bezeroFitxaList = BezeroFitxakRepository.findById(id);
    	return bezeroFitxaList;
    }
	
	@Override
    public Bezero_fitxak save(Bezero_fitxak bezero_fitxa)
    {
		return BezeroFitxakRepository.save(bezero_fitxa);
    }
}
