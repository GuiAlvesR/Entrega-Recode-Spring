package com.turistica.noturna.agencia.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import com.turistica.noturna.agencia.model.Destinos;;

@Entity
public class Promocoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromocao;

    private String nome;
	@Column(columnDefinition = "TEXT")
    private String descricao;
    private LocalDate dataDeInicio;
    private LocalDate dataDeTermino;
    private Double precoPromocional;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "promocaoDestino", 
      joinColumns = @JoinColumn(name = "idPromocao"), 
      inverseJoinColumns = @JoinColumn(name = "idDestino"))
    private Set<Destinos> destinos = new HashSet<>();

	public Promocoes() {
		super();
	}

	public Promocoes(Long idPromocao, String nome, String descricao, LocalDate dataDeInicio, LocalDate dataDeTermino,
			Double precoPromocional, Set<Destinos> destinos) {
		super();
		this.idPromocao = idPromocao;
		this.nome = nome;
		this.descricao = descricao;
		this.dataDeInicio = dataDeInicio;
		this.dataDeTermino = dataDeTermino;
		this.precoPromocional = precoPromocional;
		this.destinos = destinos;
	}

	public Long getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(Long idPromocao) {
		this.idPromocao = idPromocao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(LocalDate dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	public LocalDate getDataDeTermino() {
		return dataDeTermino;
	}

	public void setDataDeTermino(LocalDate dataDeTermino) {
		this.dataDeTermino = dataDeTermino;
	}

	public Double getPrecoPromocional() {
		return precoPromocional;
	}

	public void setPrecoPromocional(Double precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public Set<Destinos> getDestinos() {
		return destinos;
	}

	public void setDestinos(Set<Destinos> destinos) {
		this.destinos = destinos;
	}
    
}

