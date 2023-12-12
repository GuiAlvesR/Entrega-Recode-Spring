package com.turistica.noturna.agencia.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turistica.noturna.agencia.model.Destinos;
import com.turistica.noturna.agencia.repository.DestinosRepository;
import com.turistica.noturna.agencia.services.DestinosService;

@Service
public class DestinosServiceImpl implements DestinosService {

	@Autowired
	private DestinosRepository destinosRepository;

	@Override
	public List<Destinos> getAllDestinos() {
		return destinosRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Destinos getDestinoById(Long idDestino) {
		return destinosRepository.findById(idDestino).orElse(null);
	}
	
	@Override
	@Transactional
	public Destinos saveDestino(Destinos destinos) {
		return destinosRepository.save(destinos);
	}
	
	@Override
	public Destinos updateDestino(Long idDestino, Destinos destinoAtualizado) {
		Destinos destinoExistente = destinosRepository.findById(idDestino).orElse(null);
		if (destinoExistente != null) { 
			destinoExistente.setNome(destinoAtualizado.getNome());
			destinoExistente.setDescricao(destinoAtualizado.getDescricao());
			destinoExistente.setPreco(destinoAtualizado.getPreco());
			return destinosRepository.save(destinoExistente);
		} else { 
			throw new RuntimeException("Destino com o ID" + idDestino + "não encontrado.");
		}
	}

	@Override
	public void deleteDestino(Long idDestino) {
		destinosRepository.deleteById(idDestino);
	}
}
