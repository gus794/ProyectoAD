	package com.gustavo.practicaAd.models.dao;
	
	import java.util.Date;
	import java.util.List;
	
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.CrudRepository;
	import org.springframework.data.repository.query.Param;
	
	import com.gustavo.practicaAd.models.entity.*;
	
	public interface ITrabajoDAO extends CrudRepository<Trabajo, String> {
	    @Query("SELECT t FROM Trabajo t WHERE t.trabajador = :trabajador AND t.fechaFin BETWEEN :fechaInicio AND :fechaFin")
	    List<Trabajo> findTasksByWorkerAndDates(@Param("trabajador") Trabajador trabajador, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	}