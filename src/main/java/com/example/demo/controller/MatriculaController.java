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

import com.example.demo.entity.Matricula;
import com.example.demo.service.MatriculaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
	@Autowired
	private MatriculaService matriculaService;
	
	@GetMapping
	public ResponseEntity<List<Matricula>> readAll(){
		try {
			List<Matricula> Matriculas = matriculaService.readAll();
			if(Matriculas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Matriculas, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Matricula> crear(@Valid @RequestBody Matricula cat){
		try {
			Matricula c = matriculaService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Matricula> getAutorId(@PathVariable("id") Long id){
		try {
			Matricula c = matriculaService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Matricula> delAutor(@PathVariable("id") Long id){
		try {
			matriculaService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMatricula(@PathVariable("id") Long id, @Valid @RequestBody Matricula matriculaDetalles) {

	    Optional<Matricula> matriculaExistente = matriculaService.read(id);
	    
	    
	    if (matriculaExistente.isPresent()) {
	     
	        Matricula matriculaActual = matriculaExistente.get();


	        matriculaActual.setEstado(matriculaDetalles.getEstado());
	        matriculaActual.setFecha_mat(matriculaDetalles.getFecha_mat());
	        matriculaActual.setHoras(matriculaDetalles.getHoras());
	        matriculaActual.setNivel(matriculaDetalles.getNivel());

	        
	        if (matriculaDetalles.getAlumno() != null) {
	            matriculaActual.setAlumno(matriculaDetalles.getAlumno());
	        }

	        if (matriculaDetalles.getEmpleado() != null) {
	            matriculaActual.setEmpleado(matriculaDetalles.getEmpleado());
	        }

	        if (matriculaDetalles.getGrado() != null) {
	            matriculaActual.setGrado(matriculaDetalles.getGrado());
	        }

	        
	        Matricula matriculaActualizada = matriculaService.update(matriculaActual);
	        
	        
	        return new ResponseEntity<>(matriculaActualizada, HttpStatus.OK);
	    } else {
	       
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}

