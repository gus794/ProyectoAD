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
import com.gustavo.practicaAd.models.services.ITrabajadorService;
import com.gustavo.practicaAd.models.services.ITrabajoService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class TrabajadorRestController {
	@Autowired
	private ITrabajadorService trabajadorService;
	
	@GetMapping("/trabajadores")
	public List<Trabajador> index() {
		return trabajadorService.findAll();
	}
	
	@GetMapping("/trabajadores/{id}")
	public Trabajador show(@PathVariable Long id) {
		return this.trabajadorService.findById(id);
	}
	
	@PostMapping("/trabajadores")
	@ResponseStatus(HttpStatus.CREATED)
	public Trabajador create (@RequestBody Trabajador trabajo) {
		this.trabajadorService.save(trabajo);
		return trabajo;
	}
	
	@PutMapping("/trabajadores/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Trabajador update (@RequestBody Trabajador trabajo, @PathVariable Long id) {
		Trabajador currentTrabajo = this.trabajadorService.findById(id);
		this.trabajadorService.save(trabajo);
		return trabajo;
	}
	
	@DeleteMapping("/trabajadores/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete (@PathVariable Long id) {
		Trabajador currentTrabajo = this.trabajadorService.findById(id);
		this.trabajadorService.delete(currentTrabajo);
	}
}