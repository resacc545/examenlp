package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Grado;



public interface GradoDao {
	Grado create(Grado a);
	Grado update(Grado a);
	void delete(Long id);
	Optional<Grado> read(Long id);
	List<Grado> readAll();
}
