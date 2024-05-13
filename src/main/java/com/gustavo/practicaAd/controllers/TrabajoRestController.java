package com.gustavo.practicaAd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.practicaAd.models.entity.Trabajador;
import com.gustavo.practicaAd.models.entity.Trabajo;
import com.gustavo.practicaAd.models.services.ITrabajoService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class TrabajoRestController {
	@Autowired
	private ITrabajoService trabajoService;
	
	@GetMapping("/trabajos")
	public List<Trabajo> index() {
		return trabajoService.findAll();
	}
	
	@GetMapping("/trabajos/unassigned")
	public List<Trabajo> findUnassignedTasks() {
		return trabajoService.findUnassignedTasks();
	}
	
	@GetMapping("/trabajos/unfinished")
	public List<Trabajo> findUnfinishedTasks() {
		return trabajoService.findUnfinishedTasks();
	}
	
	@GetMapping("/trabajos/finished")
	public List<Trabajo> findFinishedTasks() {
		return trabajoService.findFinishedTasks();
	}
	
	@GetMapping("/trabajos/orderedByPriority")
	public List<Trabajo> findTasksOrderedByPriority(@RequestBody Trabajador trabajo) {
		return trabajoService.findTasksOrderedByPriority(trabajo);
	}
	
	@GetMapping("/trabajos/ByPriority/{prioridad}")
	public List<Trabajo> findTasksByPriority(@RequestBody Trabajador trabajo, @PathVariable String prioridad) {
		return trabajoService.findTasksByPriority(trabajo, prioridad);
	}
	
	@GetMapping("/trabajos/{id}")
	public Trabajo show(@PathVariable String id) {
		return this.trabajoService.findById(id);
	}
	
	@PostMapping("/trabajos")
	@ResponseStatus(HttpStatus.CREATED)
	public Trabajo create (@RequestBody Trabajo trabajo) {
		this.trabajoService.save(trabajo);
		return trabajo;
	}
	
	@PutMapping("/trabajos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Trabajo update (@RequestBody Trabajo trabajo, @PathVariable String id) {
		Trabajo currentTrabajo = this.trabajoService.findById(id);
		currentTrabajo.setCategoria(trabajo.getCategoria());
		currentTrabajo.setCodTrabajo(trabajo.getCodTrabajo());
		currentTrabajo.setDescripcion(trabajo.getDescripcion());
		currentTrabajo.setFechaFin(trabajo.getFechaFin());
		currentTrabajo.setFechaInicio(trabajo.getFechaInicio());
		currentTrabajo.setPrioridad(trabajo.getPrioridad());
		currentTrabajo.setTiempo(trabajo.getTiempo());
		this.trabajoService.save(trabajo);
		return trabajo;
	}
	
	@DeleteMapping("/trabajos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete (@PathVariable String id) {
		Trabajo currentTrabajo = this.trabajoService.findById(id);
		this.trabajoService.delete(currentTrabajo);
	}
}