package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Empleado;
import com.example.demo.service.EmpleadoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping
	public ResponseEntity<List<Empleado>> readAll(){
		try {
			List<Empleado> Empleados = empleadoService.readAll();
			if(Empleados.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Empleados, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Empleado> crear(@Valid @RequestBody Empleado cat){
		try {
			Empleado c = empleadoService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> getAutorId(@PathVariable("id") Long id){
		try {
			Empleado c = empleadoService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Empleado> delAutor(@PathVariable("id") Long id){
		try {
			empleadoService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmpleado(@PathVariable("id") Long id, @Valid @RequestBody Empleado empleadoDetalles) {

	    Optional<Empleado> empleadoExistente = empleadoService.read(id);
	    
	   
	    if (empleadoExistente.isPresent()) {
	       
	        Empleado empleadoActual = empleadoExistente.get();

	  
	        empleadoActual.setNombre(empleadoDetalles.getNombre());
	        empleadoActual.setApellidos(empleadoDetalles.getApellidos());
	        empleadoActual.setDni(empleadoDetalles.getDni());
	        empleadoActual.setCargo(empleadoDetalles.getCargo());
	        empleadoActual.setFecha_ingreso(empleadoDetalles.getFecha_ingreso());
	        empleadoActual.setEstado(empleadoDetalles.getEstado());

	       
	        Empleado empleadoActualizado = empleadoService.update(empleadoActual);
	        
	        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
	    } else {
	      
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}


