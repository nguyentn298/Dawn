package com.dante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dante.db.entity.Client;
import com.dante.db.repository.ClientRepository;

@Controller
public class DatabaseController {

	@Autowired
	private ClientRepository clientRepository;

	@RequestMapping(value = "/findClientById", method = RequestMethod.GET)
	public String findClientById(Model model) {
		model.addAttribute("client", new Client());
		return "database-view";
	}

	@RequestMapping(value = "/findClientById", method = RequestMethod.POST)
	public String findClientById(Model model, @ModelAttribute("client") Client client) {
//		Client clientObject = clientRepository.findClientByCardDetailId(client.getId());// khoan, cai unit test cua em chua xai bao gio, nen co the do unit test sai
//		model.addAttribute("clientObject", clientObject);
		return "database-view";
	}
}
