package com.gustavo.practicaAd.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "trabajos")
public class Trabajo {
    @Id
    @Column(name = "cod_trab")
    private String codTrabajo;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "fec_ini", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio = new Date();

    @Column(name = "fec_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    private Double tiempo;

    @Column(name = "prioridad", nullable = false)
    private Integer prioridad;

    @ManyToOne
    @JoinColumn(name = "id_trab")
    private Trabajador trabajador;

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCodTrabajo() {
		return codTrabajo;
	}

	public void setCodTrabajo(String codTrabajo) {
		this.codTrabajo = codTrabajo;
	}
	
	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}
}
