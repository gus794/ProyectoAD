package com.gustavo.practicaAd.models.services;

import java.util.List;

import com.gustavo.practicaAd.models.entity.Trabajador;

public interface ITrabajadorService {
	
	public List<Trabajador> findAll();
	
	public void save (Trabajador Trabajador);
	
	public Trabajador findById (Long id);
	
	public void delete (Trabajador Trabajador);
	
}