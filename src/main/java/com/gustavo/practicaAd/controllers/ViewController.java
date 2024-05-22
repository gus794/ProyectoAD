package com.gustavo.practicaAd.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gustavo.practicaAd.models.entity.Trabajador;
import com.gustavo.practicaAd.models.entity.Trabajo;
import com.gustavo.practicaAd.models.services.TrabajadorServiceImpl;
import com.gustavo.practicaAd.models.services.TrabajoServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("views")
public class ViewController {
	
	@Autowired
	TrabajadorServiceImpl trabajadorService = new TrabajadorServiceImpl();
	@Autowired
	TrabajoServiceImpl trabajoService = new TrabajoServiceImpl();
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titulo", "Práctica 4");
		return "index";
	}
	
	@GetMapping("/crearTrabajador")
	public String addTrabajador(Model model) {
		model.addAttribute("titulo","Añadir trabajador");
		model.addAttribute("dni","DNI:");
		model.addAttribute("nombre","Nombre:");
		model.addAttribute("apellidos","Apellidos:");
		model.addAttribute("especialidad","Especialidad:");
		model.addAttribute("contraseña","Contraseña:");
		model.addAttribute("email","Email:");
		model.addAttribute("trabajador",new Trabajador());
		return "crearTrabajador";
	}
	
	@GetMapping("/crearTrabajo")
	public String addTrabajo(Model model) {
		model.addAttribute("titulo","Añadir trabajo");
		model.addAttribute("categoria","Categoria:");
		model.addAttribute("descripcion","Descripcion:");
		model.addAttribute("prioridad","Prioridad:");
		model.addAttribute("tiempo","Tiempo:");
		List<Trabajador> trabajadores = trabajadorService.findAll();
	    model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("trabajo",new Trabajo());
		return "crearTrabajo";
	}
	
	@GetMapping("/editarTrabajador/{id}")
	public String editTrabajador(@PathVariable Long id, Model model) {
	    Trabajador trabajador = trabajadorService.findById(id);
        model.addAttribute("titulo", "Editar trabajador");
        model.addAttribute("dni", "DNI:");
        model.addAttribute("nombre", "Nombre:");
        model.addAttribute("apellidos", "Apellidos:");
        model.addAttribute("especialidad", "Especialidad:");
        model.addAttribute("contraseña", "Contraseña:");
        model.addAttribute("email", "Email:");
        model.addAttribute("trabajador", trabajador);
        return "editarTrabajador";
	}
	
	@GetMapping("/editarTrabajo/{id}")
	public String editTrabajo(@PathVariable String id, Model model) {
	    Trabajo trabajo = trabajoService.findById(id);
	    model.addAttribute("titulo","Añadir trabajo");
		model.addAttribute("categoria","Categoria:");
		model.addAttribute("descripcion","Descripcion:");
		model.addAttribute("prioridad","Prioridad:");
		model.addAttribute("tiempo","Tiempo:");
		List<Trabajador> trabajadores = trabajadorService.findAll();
	    model.addAttribute("trabajadores", trabajadores);
        model.addAttribute("trabajo", trabajo);
        return "editarTrabajo";
	}

	
	@RequestMapping("/createTrabajador")
	public ModelAndView createTrabajador(@Valid Trabajador trabajador, BindingResult result, Model mod) {
	    ModelAndView model = new ModelAndView();
	    boolean exists = false;

	    model.addObject("trabajador", trabajador);

	    if (!result.hasErrors()) {
	        for (Trabajador t : trabajadorService.findAll()) {
	            if (t.getIdTrabajador() == trabajador.getIdTrabajador()) {
	                exists = true;
	                break;
	            }
	        }

	        model.setViewName("ready");

	        if (!exists) {
	            mod.addAttribute("resultado", "Trabajador creado");
	            trabajadorService.save(trabajador);
	        } else {
	            mod.addAttribute("resultado", "El id ya existe");
	        }
	    } else {
	        model.setViewName("crearTrabajador");
	    }

	    mod.addAttribute("dni","DNI:");
		mod.addAttribute("nombre","Nombre:");
		mod.addAttribute("apellidos","Apellidos:");
		mod.addAttribute("especialidad","Especialidad:");
		mod.addAttribute("contraseña","Contraseña:");
		mod.addAttribute("email","Email:");

	    return model;
	}
	
	@RequestMapping("/createTrabajo")
	public ModelAndView createTrabajo(@Valid Trabajo trabajo, BindingResult result, Model mod) {
	    ModelAndView model = new ModelAndView();
	    boolean exists = false;

	    List<Trabajo> trabajos = trabajoService.findAll();
	    int mayor = 0;
	    for(int i=0; i<trabajos.size();i++) {
	    	if(i==0) {
	    		mayor = Integer.parseInt(trabajos.get(i).getCodTrabajo().substring(1));
	    	}
	    	else{
	    		if(mayor < Integer.parseInt(trabajos.get(i).getCodTrabajo().substring(1))) {
	    			mayor = Integer.parseInt(trabajos.get(i).getCodTrabajo().substring(1));
	    		}
	    	}
	    }
	    mayor = mayor+1;
	    String nuevoCodTrabajo = "T"+mayor;
	    System.out.println(nuevoCodTrabajo);
	    trabajo.setCodTrabajo(nuevoCodTrabajo);
	    model.addObject("trabajo", trabajo);

	    if (!result.hasErrors()) {
	        for (Trabajo t : trabajoService.findAll()) {
	            if (t.getCodTrabajo() == trabajo.getCodTrabajo()) {
	                exists = true;
	                break;
	            }
	        }

	        model.setViewName("ready");

	        if (!exists) {
	            mod.addAttribute("resultado", "Trabajo creado");
	            trabajoService.save(trabajo);
	        } else {
	            mod.addAttribute("resultado", "El id ya existe");
	        }
	    } else {
	        model.setViewName("crearTrabajo");
	    }

	    mod.addAttribute("categoria","Categoria:");
		mod.addAttribute("descripcion","Descripcion:");
		mod.addAttribute("prioridad","Prioridad:");
		mod.addAttribute("tiempo","Tiempo:");
		List<Trabajador> trabajadores = trabajadorService.findAll();
	    mod.addAttribute("trabajadores", trabajadores);

	    return model;
	}
	
	@PostMapping("/updateTrabajador")
	public ModelAndView updateTrabajador(@Valid Trabajador trabajador, BindingResult result, Model mod) {
	    ModelAndView model = new ModelAndView();

	    model.addObject("trabajador", trabajador);

	    if (!result.hasErrors()) {
	    	Long idTrabajador = trabajador.getIdTrabajador();
	        Trabajador existingTrabajador = trabajadorService.findById(idTrabajador);
	        if (existingTrabajador != null) {
	            existingTrabajador.setDni(trabajador.getDni());
	            existingTrabajador.setNombre(trabajador.getNombre());
	            existingTrabajador.setApellidos(trabajador.getApellidos());
	            existingTrabajador.setEspecialidad(trabajador.getEspecialidad());
	            existingTrabajador.setContraseña(trabajador.getContraseña());
	            existingTrabajador.setEmail(trabajador.getEmail());

	            trabajadorService.save(existingTrabajador);
	            
	            model.setViewName("ready");
	            mod.addAttribute("resultado", "Trabajador actualizado");
	        }
	    }

	    mod.addAttribute("dni","DNI:");
	    mod.addAttribute("nombre","Nombre:");
	    mod.addAttribute("apellidos","Apellidos:");
	    mod.addAttribute("especialidad","Especialidad:");
	    mod.addAttribute("contraseña","Contraseña:");
	    mod.addAttribute("email","Email:");

	    return model;
	}
	
	@PostMapping("/updateTrabajo")
	public ModelAndView updateTrabajo(@Valid Trabajo trabajo, BindingResult result, Model mod) {
	    ModelAndView model = new ModelAndView();

	    model.addObject("trabajo", trabajo);

	    if (!result.hasErrors()) {
	    	String codTrabajo = trabajo.getCodTrabajo();
	        Trabajo existingTrabajo = trabajoService.findById(codTrabajo);
	        if (existingTrabajo != null) {
	            existingTrabajo.setCategoria(trabajo.getCategoria());
	            existingTrabajo.setDescripcion(trabajo.getDescripcion());
	            existingTrabajo.setTiempo(trabajo.getTiempo());
	            existingTrabajo.setPrioridad(trabajo.getPrioridad());
	            if(trabajo.getTrabajador() != null) {
	            	existingTrabajo.setTrabajador(trabajo.getTrabajador());
	            }

	            trabajoService.save(existingTrabajo);
	            
	            model.setViewName("ready");
	            mod.addAttribute("resultado", "Trabajo actualizado");
	        }
	    }

	    mod.addAttribute("categoria","Categoria:");
		mod.addAttribute("descripcion","Descripcion:");
		mod.addAttribute("prioridad","Prioridad:");
		mod.addAttribute("tiempo","Tiempo:");
		List<Trabajador> trabajadores = trabajadorService.findAll();
	    mod.addAttribute("trabajadores", trabajadores);

	    return model;
	}
}
