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

import com.example.demo.entity.Alumno;
import com.example.demo.service.AlumnoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<List<Alumno>> readAll() {
		try {
			List<Alumno> lista = service.readAll();
			if(lista.isEmpty()){
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(lista, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping
	public ResponseEntity<Alumno> create(@Valid @RequestBody Alumno obj){
		try {
			Alumno objeto = service.create(obj);
			return new ResponseEntity<>(objeto, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Alumno> getAlumnoId(@PathVariable Long id){
		try {
			Alumno objeto = service.read(id).get();
			return new ResponseEntity<>(objeto, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Alumno> delAlumno(@PathVariable Long id){
		try {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAcceso(@PathVariable Long id, @Valid @RequestBody Alumno obj) {

	    Optional<Alumno> objeto = service.read(id);
	    
	    if (objeto.isPresent()) {
	        
	        Alumno alumnoExistente = objeto.get();

	      
	        alumnoExistente.setNombre(obj.getNombre());
	        alumnoExistente.setApellidos(obj.getApellidos());
	        alumnoExistente.setCodigo(obj.getCodigo());
	        alumnoExistente.setCorreo(obj.getCorreo());
	        alumnoExistente.setFecha_nac(obj.getFecha_nac());
	        alumnoExistente.setEstado(obj.getEstado());

	       
	        Alumno updatedAlumno = service.update(alumnoExistente);
	        return new ResponseEntity<>(updatedAlumno, HttpStatus.OK); 
	    } else {
	       
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	}

