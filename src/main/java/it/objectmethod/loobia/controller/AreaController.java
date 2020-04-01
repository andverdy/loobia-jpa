package it.objectmethod.loobia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.loobia.service.AreaService;

@RestController
@CrossOrigin
@RequestMapping("/api/zona")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@PutMapping("/save")
	public void censusAgents(@RequestParam(value = "codzona") String codzona,
			@RequestParam(value = "idagente") Integer idagente) {

		areaService.censusAgents(codzona, idagente);
	}
}
