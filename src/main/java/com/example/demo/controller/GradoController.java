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

import com.example.demo.entity.Grado;
import com.example.demo.service.GradoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/grados")
public class GradoController {
	@Autowired
	private GradoService gradoService;
	
	@GetMapping
	public ResponseEntity<List<Grado>> readAll(){
		try {
			List<Grado> Grados = gradoService.readAll();
			if(Grados.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Grados, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Grado> crear(@Valid @RequestBody Grado cat){
		try {
			Grado c = gradoService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Grado> getAutorId(@PathVariable("id") Long id){
		try {
			Grado c = gradoService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Grado> delAutor(@PathVariable("id") Long id){
		try {
			gradoService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateGrado(@PathVariable("id") Long id, @Valid @RequestBody Grado gradoDetalles) {

	    Optional<Grado> gradoExistente = gradoService.read(id);
	    
	    
	    if (gradoExistente.isPresent()) {
	       
	        Grado gradoActual = gradoExistente.get();

	      
	        gradoActual.setGrado_sec(gradoDetalles.getGrado_sec());
	        gradoActual.setGrado_nivel(gradoDetalles.getGrado_nivel());
	        gradoActual.setEstado(gradoDetalles.getEstado());

	      
	        Grado gradoActualizado = gradoService.update(gradoActual);
	        
	        
	        return new ResponseEntity<>(gradoActualizado, HttpStatus.OK);
	    } else {
	        
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}


