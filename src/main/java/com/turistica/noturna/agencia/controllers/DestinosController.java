package com.turistica.noturna.agencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turistica.noturna.agencia.model.Destinos;
import com.turistica.noturna.agencia.services.DestinosService;


@Controller
@RequestMapping("/destinos")
public class DestinosController {

	@Autowired
	private DestinosService destinosService;

	@GetMapping
	public String listDestinos(Model model) {
		List<Destinos> destinos = destinosService.getAllDestinos();
		model.addAttribute("destinos", destinos);
		return "listarDestinos";
	}

	@GetMapping("/novo")
	public String showFormForAdd(Model model) {
		Destinos destino = new Destinos();
		model.addAttribute("destino", destino);
		return "destino.html";
	}

	@PostMapping("/save")
	public String saveDestino(@ModelAttribute("destino") Destinos destino) {
		destinosService.saveDestino(destino);
		return "redirect:/destinos";
	}

	@GetMapping("/editar/{idDestino}")
	public String showFormForUpdate(@PathVariable Long idDestino, Model model) {
		Destinos destino = destinosService.getDestinoById(idDestino);
		model.addAttribute("destino", destino);
		return "editarDestino";
	}

	@PostMapping("/editar/{idDestino}")
	public String updateDestinos(@PathVariable Long idDestino, @ModelAttribute("destino") Destinos destino) {
		destinosService.updateDestino(idDestino, destino);
		return "redirect:/destinos";
	}

	@GetMapping("/deletar/{idDestino}")
	public String deleteDestino(@PathVariable Long idDestino) {
		destinosService.deleteDestino(idDestino);
		return "redirect:/destinos";
	}
	
}
