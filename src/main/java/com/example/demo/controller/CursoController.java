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

import com.example.demo.entity.Curso;

import com.example.demo.service.CursoService;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/cursos")
public class CursoController {
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<Curso>> readAll(){
		try {
			List<Curso> Cursos = cursoService.readAll();
			if(Cursos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Cursos, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Curso> create(@Valid @RequestBody Curso obj){
		try {
			Curso objeto = cursoService.create(obj);
			return new ResponseEntity<>(objeto, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	



	@GetMapping("/{id}")
	public ResponseEntity<Curso> getAutorId(@PathVariable("id") Long id){
		try {
			Curso c = cursoService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Curso> delAutor(@PathVariable("id") Long id){
		try {
			cursoService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCurso(@PathVariable("id") Long id, @Valid @RequestBody Curso cursoDetalles) {

	    Optional<Curso> cursoExistente = cursoService.read(id);
	    
	    if (cursoExistente.isPresent()) {
	       
	        Curso cursoActual = cursoExistente.get();

	       
	        cursoActual.setNombre(cursoDetalles.getNombre());
	        cursoActual.setHoras(cursoDetalles.getHoras());
	        cursoActual.setEstado(cursoDetalles.getEstado());

	     
	        if (cursoDetalles.getGrado() != null) {
	            cursoActual.setGrado(cursoDetalles.getGrado());
	        }

	    
	        Curso cursoActualizado = cursoService.update(cursoActual);
	        
	     
	        return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
	    } else {
	        
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}


}




