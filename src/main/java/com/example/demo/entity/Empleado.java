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
@Table(name = "empleados")

public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre", length = 50)
	private String nombre;
	@Column(name = "apellidos", length = 50)
	private String apellidos;
	@Column(name = "dni", length = 8)
	private String dni;
	@Column(name = "cargo", length = 50)
	private String cargo;
	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fecha_ingreso;
	@Column(name = "estado",length = 1)
	private String estado;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empleado")
	@JsonIgnore
	private Set<Matricula> matriculas;
}
