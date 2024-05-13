package com.gustavo.practicaAd.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gustavo.practicaAd.models.entity.*;

public interface ITrabajoDAO extends CrudRepository<Trabajo, String> {	
}