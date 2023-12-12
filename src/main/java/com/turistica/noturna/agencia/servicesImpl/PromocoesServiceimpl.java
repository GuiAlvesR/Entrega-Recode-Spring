package com.turistica.noturna.agencia.servicesImpl;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turistica.noturna.agencia.model.Destinos;
import com.turistica.noturna.agencia.model.Promocoes;
import com.turistica.noturna.agencia.repository.PromocoesRepository;
import com.turistica.noturna.agencia.services.DestinosService;
import com.turistica.noturna.agencia.services.PromocoesService;

@Service
public class PromocoesServiceimpl implements PromocoesService {

	@Autowired
	private PromocoesRepository promocoesRepository;
	@Autowired
	private DestinosService destinosService;

	public List<Promocoes> getAllPromocoes() {
		return promocoesRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Promocoes getPromocaoById(Long id) {
		return promocoesRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public Promocoes savePromocao(Promocoes promocao, Set<Long> destinoIds) {
		Set<Destinos> destinos = destinoIds.stream().map(destinosService::getDestinoById).filter(Objects::nonNull)
				.collect(Collectors.toSet());

		promocao.setDestinos(destinos);

		return promocoesRepository.save(promocao);
	}

	public Promocoes updatePromocao(Long idPromocao, Promocoes promocaoAtualizada) {
		Promocoes promocaoExistente = promocoesRepository.findById(idPromocao).orElse(null);
		if (promocaoExistente != null) {
			promocaoExistente.setNome(promocaoAtualizada.getNome());
			promocaoExistente.setDescricao(promocaoAtualizada.getDescricao());
			promocaoExistente.setPrecoPromocional(promocaoAtualizada.getPrecoPromocional());

			promocaoExistente.setDataDeInicio(promocaoAtualizada.getDataDeInicio());
			promocaoExistente.setDataDeTermino(promocaoAtualizada.getDataDeTermino());

			Set<Destinos> destinosAtualizados = promocaoAtualizada.getDestinos();
			for (Destinos destino : destinosAtualizados) {
				destino.getPromocoes().add(promocaoExistente);
			}

			promocaoExistente.setDestinos(destinosAtualizados);

			return promocoesRepository.save(promocaoExistente);
		} else {
			throw new RuntimeException("Promoção com o ID" + idPromocao + "não encontrada.");
		}
	}

	public void deletePromocao(Long idPromocao) {
		promocoesRepository.deleteById(idPromocao);
	}

}
