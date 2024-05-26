	package com.gustavo.practicaAd.models.services;
	
	import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

import com.gustavo.practicaAd.models.dao.ITrabajadorDAO;
import com.gustavo.practicaAd.models.dao.ITrabajoDAO;
	import com.gustavo.practicaAd.models.entity.Trabajador;
	import com.gustavo.practicaAd.models.entity.Trabajo;
	
	import jakarta.persistence.EntityManager;
	import jakarta.persistence.PersistenceContext;
	
	@Service
	public class TrabajoServiceImpl implements ITrabajoService {
		
		@Autowired
		private ITrabajoDAO trabajoDao;
		
		@Autowired
		private ITrabajadorDAO trabajadorDao;

		
	    @PersistenceContext
	    private EntityManager entityManager;
	
		@Override
		@Transactional(readOnly = true)
		public List<Trabajo> findAll() {
			return (List<Trabajo>) trabajoDao.findAll();
		}
	
		@Override
		public void save(Trabajo trabajo) {
			trabajoDao.save(trabajo);
		}
		
		@Override
	    public void finishTask(String id) {
	        Optional<Trabajo> optionalTrabajo = trabajoDao.findById(id);
	        if (optionalTrabajo.isPresent()) {
	            Trabajo trabajo = optionalTrabajo.get();
	            if (trabajo.getFechaFin() == null) {
	                trabajo.setFechaFin(new Date());
	                trabajoDao.save(trabajo);
	            }
	        }
	    }
		
		@Override
		public void saveWithWorker(Trabajo trabajo, Long idTrabajador) {
			Trabajador trabajador = trabajadorDao.findById(idTrabajador).orElse(null);
			trabajo.setTrabajador(trabajador);
			trabajoDao.save(trabajo);
		}
	
		@Override
		public Trabajo findById(String id) {
			return trabajoDao.findById(id).orElse(null);
		}
	
		@Override
		public void delete(Trabajo trabajo) {
			trabajoDao.delete(trabajo);
		}
		
		@Override
	    public List<Trabajo> findUnassignedTasks() {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador IS NULL", Trabajo.class)
		            .getResultList();
		}
	
		@Override
	    public List<Trabajo> findUnfinishedTasks() {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador IS NOT NULL AND fechaFin IS NULL", Trabajo.class)
		            .getResultList();
		}
		
		@Override
	    public List<Trabajo> findFinishedTasks() {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador IS NOT NULL AND fechaFin IS NOT NULL", Trabajo.class)
		            .getResultList();
		}
		
		@Override
	    public List<Trabajo> findTasksOrderedByPriority(String idTrabajador) {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador = (SELECT tr FROM Trabajador tr WHERE tr.idTrabajador = :idTrabajador) AND fechaFin IS NULL ORDER BY t.prioridad", Trabajo.class)
		            .setParameter("idTrabajador", idTrabajador)
		            .getResultList();
		}
		
		@Override
	    public List<Trabajo> findTasksByPriority(Trabajador trabajador, String prioridad) {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador = :trabajador AND t.prioridad = :prioridad AND fechaFin IS NULL", Trabajo.class)
		            .setParameter("trabajador", trabajador)
		            .setParameter("prioridad", prioridad)
					.getResultList();
		}
		
		@Override
	    @Transactional(readOnly = true)
	    public List<Trabajo> findFinishedTasksByWorkerAndDates(Trabajador trabajador, Date fechaInicio, Date fechaFin) {
	        return trabajoDao.findTasksByWorkerAndDates(trabajador, fechaInicio, fechaFin);
	    }
		
		@Override
		public List<Trabajo> getPendingTasks(String idTrabajador, String contraseña){
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.fechaFin"
					+ " IS NULL AND t.trabajador = ("
					+ "SELECT tr FROM Trabajador tr WHERE tr.idTrabajador = :idTrabajador AND "
					+ "tr.contraseña = :contraseña)"
					,Trabajo.class)
					.setParameter("idTrabajador", idTrabajador)
					.setParameter("contraseña", contraseña)
					.getResultList();
		}
		
		@Override
		public List<Trabajo> getFinishedTasks(String idTrabajador, String contraseña){
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.fechaFin"
					+ " IS NOT NULL AND t.trabajador = ("
					+ "SELECT tr FROM Trabajador tr WHERE tr.idTrabajador = :idTrabajador AND "
					+ "tr.contraseña = :contraseña)"
					,Trabajo.class)
					.setParameter("idTrabajador", idTrabajador)
					.setParameter("contraseña", contraseña)
					.getResultList();
		}

		@Override
		public List<Trabajo> findByTrabajador(Trabajador trabajador) {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador = ("
					+ "SELECT tr FROM Trabajador tr WHERE tr.idTrabajador = :idTrabajador)"
					,Trabajo.class)
					.setParameter("idTrabajador", trabajador.getIdTrabajador())
					.getResultList();
		}
	}