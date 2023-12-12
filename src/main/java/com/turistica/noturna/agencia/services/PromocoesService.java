package com.turistica.noturna.agencia.services;

import java.util.List;
import java.util.Set;

import com.turistica.noturna.agencia.model.Promocoes;

public interface PromocoesService {

	List<Promocoes> getAllPromocoes();

	Promocoes getPromocaoById(Long idPromocao);

	Promocoes savePromocao(Promocoes promocao, Set<Long> destinoIds);

	Promocoes updatePromocao(Long idPromocao, Promocoes promocaoAtualizada);

	void deletePromocao(Long idPromocao);
}
