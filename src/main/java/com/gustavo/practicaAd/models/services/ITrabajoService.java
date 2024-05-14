package com.gustavo.practicaAd.models.services;

import java.util.Date;
import java.util.List;

import com.gustavo.practicaAd.models.entity.Trabajador;
import com.gustavo.practicaAd.models.entity.Trabajo;

public interface ITrabajoService {
	
	public List<Trabajo> findAll();
	
	public void save (Trabajo trabajo);
	public void saveWithWorker (Trabajo trabajo, Long idTrabajador);
	public void finishTask(String id);
	
	public Trabajo findById (String id);
	
	public void delete (Trabajo trabajo);
	
    public List<Trabajo> findUnassignedTasks();

    public List<Trabajo> findUnfinishedTasks();
    
    public List<Trabajo> findFinishedTasks();
    
    public List<Trabajo> findTasksOrderedByPriority(Trabajador t);
    
    public List<Trabajo> findTasksByPriority(Trabajador trabajador, String prioridad);
    
    public List<Trabajo> findFinishedTasksByWorkerAndDates(Trabajador trabajador, Date fechaInicio, Date fechaFin);
	
    public List<Trabajo> getPendingTasks(String idTrabajador, String contraseña);
    
    public List<Trabajo> getFinishedTasks(String idTrabajador, String contraseña);
    
}