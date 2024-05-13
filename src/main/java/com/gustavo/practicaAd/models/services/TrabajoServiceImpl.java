	package com.gustavo.practicaAd.models.services;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;
	
	import com.gustavo.practicaAd.models.dao.ITrabajoDAO;
	import com.gustavo.practicaAd.models.entity.Trabajador;
	import com.gustavo.practicaAd.models.entity.Trabajo;
	
	import jakarta.persistence.EntityManager;
	import jakarta.persistence.PersistenceContext;
	
	@Service
	public class TrabajoServiceImpl implements ITrabajoService {
		
		@Autowired
		private ITrabajoDAO trabajoDao;
		
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
	    public List<Trabajo> findTasksOrderedByPriority(Trabajador trabajador) {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador = :trabajador AND fechaFin IS NULL ORDER BY t.prioridad", Trabajo.class)
		            .setParameter("trabajador", trabajador)
		            .getResultList();
		}
		
		@Override
	    public List<Trabajo> findTasksByPriority(Trabajador trabajador, String prioridad) {
			return entityManager.createQuery("SELECT t FROM Trabajo t WHERE t.trabajador = :trabajador AND t.prioridad = :prioridad", Trabajo.class)
		            .setParameter("trabajador", trabajador)
		            .setParameter("prioridad", prioridad)
					.getResultList();
		}
	}