package com.gustavo.practicaAd.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.practicaAd.models.dao.ITrabajadorDAO;
import com.gustavo.practicaAd.models.entity.Trabajador;


@Service
public class TrabajadorServiceImpl implements ITrabajadorService {
	
	@Autowired
	private ITrabajadorDAO trabajadorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Trabajador> findAll() {
		return (List<Trabajador>) trabajadorDao.findAll();
	}

	@Override
	public void save(Trabajador trabajo) {
		trabajadorDao.save(trabajo);
	}

	@Override
	public Trabajador findById(Long id) {
		return trabajadorDao.findById(id).orElse(null);
	}
	
	@Override
	public void delete (Trabajador trabajador) {
		trabajadorDao.delete(trabajador);
	}
}