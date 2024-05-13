package com.gustavo.practicaAd.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.gustavo.practicaAd.models.entity.*;

public interface ITrabajadorDAO extends CrudRepository<Trabajador, Long> {
	
}