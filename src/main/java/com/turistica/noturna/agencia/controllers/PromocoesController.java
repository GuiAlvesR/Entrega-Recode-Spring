package com.turistica.noturna.agencia.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.turistica.noturna.agencia.model.Destinos;
import com.turistica.noturna.agencia.model.Promocoes;
import com.turistica.noturna.agencia.services.DestinosService;
import com.turistica.noturna.agencia.services.PromocoesService;

@Controller
@RequestMapping("/promocoes")
public class PromocoesController {

	@Autowired
	private PromocoesService promocoesService;
	
	@Autowired
	private DestinosService destinosService;
	
	@GetMapping
	public String listarPromocoes(Model model) { 
		List<Promocoes> promocoes = promocoesService.getAllPromocoes();
		model.addAttribute("promocoes", promocoes);
		return "listarPromocoes";
	}
	
	@GetMapping("/novo")
	public String showForm(Model model) { 
		Promocoes promocao = new Promocoes();
		List<Destinos> destinos = destinosService.getAllDestinos();
		model.addAttribute("promocao", promocao);
		model.addAttribute("destinos", destinos);
		return "promocoes.html";
	}
	
	@PostMapping("/save")
	public String savePromocoes(@ModelAttribute("promocao") Promocoes promocao, @RequestParam Set<Long> destinos) { 
		promocao.setDestinos(destinos.stream()
				.map(destinosService::getDestinoById)
				.collect(Collectors.toSet()));
		promocoesService.savePromocao(promocao, destinos);
		return "redirect:/promocoes";
	}
	
	@GetMapping("/editar/{idPromocao}")
	public String ShowUpdateForm(@PathVariable("idPromocao") Long idPromocao, Model model) { 
		Promocoes promocao = promocoesService.getPromocaoById(idPromocao);
		model.addAttribute("promocao", promocao);
		model.addAttribute("destinos", destinosService.getAllDestinos());
		return "editarPromocao";
	}
	
	@PostMapping("/editar/{idPromocao}")
	public String updatePromocoes(@PathVariable("idPromocao") Long idPromocao, @ModelAttribute("promocao") Promocoes promocao) { 
		promocoesService.updatePromocao(idPromocao, promocao);
		return "redirect:/promocoes";
	}
	
	@GetMapping("/deletar/{idPromocao}")
	public String deletePromocoes(@PathVariable Long idPromocao) { 
		promocoesService.deletePromocao(idPromocao);
		return "redirect:/promocoes";
		
	}
}
