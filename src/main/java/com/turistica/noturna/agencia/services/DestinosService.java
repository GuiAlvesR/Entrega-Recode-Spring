package com.turistica.noturna.agencia.services;

import java.util.List;

import com.turistica.noturna.agencia.model.Destinos;

public interface DestinosService {

	List<Destinos> getAllDestinos();

	Destinos getDestinoById(Long idDestino);

	Destinos saveDestino(Destinos destinos);

	Destinos updateDestino(Long idDestino, Destinos destinoAtualizado);

	void deleteDestino(Long idDestino);
}
