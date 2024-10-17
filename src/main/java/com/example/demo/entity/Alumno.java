package com.example.demo.entity;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "apellidos", length = 50)
	private String apellidos;
	@Column(name = "codigo", length = 10)
	private String codigo;
	@Column(name = "correo", length = 50)
	private String correo;
	@Column(name = "fecha_nac")
	@Temporal(TemporalType.DATE)
	private Date fecha_nac;
	@Column(name = "estado", length = 1)
	private String estado;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alumno")
	@JsonIgnore
	private Set<Matricula> matriculas;
}
