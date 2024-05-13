package com.gustavo.practicaAd.models.services;

import java.util.List;

import com.gustavo.practicaAd.models.entity.Trabajador;
import com.gustavo.practicaAd.models.entity.Trabajo;

public interface ITrabajoService {
	
	public List<Trabajo> findAll();
	
	public void save (Trabajo trabajo);
	
	public Trabajo findById (String id);
	
	public void delete (Trabajo trabajo);
	
    public List<Trabajo> findUnassignedTasks();

    public List<Trabajo> findUnfinishedTasks();
    
    public List<Trabajo> findFinishedTasks();
    public List<Trabajo> findTasksOrderedByPriority(Trabajador t);
    public List<Trabajo> findTasksByPriority(Trabajador trabajador, String prioridad);
}