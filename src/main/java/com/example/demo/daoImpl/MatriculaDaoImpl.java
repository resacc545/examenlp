package com.example.demo.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.MatriculaDao;
import com.example.demo.entity.Matricula;
import com.example.demo.repository.MatriculaRepository;


@Component
public class MatriculaDaoImpl implements MatriculaDao {
	
	@Autowired
	private MatriculaRepository gradoRepository;
	
	@Override
	public Matricula create(Matricula a) {
		// TODO Auto-generated method stub
		return gradoRepository.save(a);
	}

	@Override
	public Matricula update(Matricula a) {
		// TODO Auto-generated method stub
		return gradoRepository.save(a);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		gradoRepository.deleteById(id);
		
	}

	@Override
	public Optional<Matricula> read(Long id) {
		// TODO Auto-generated method stub
		return gradoRepository.findById(id);
	}

	@Override
	public List<Matricula> readAll() {
		// TODO Auto-generated method stub
		return gradoRepository.findAll();
	}

}
