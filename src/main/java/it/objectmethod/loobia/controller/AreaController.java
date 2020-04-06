package it.objectmethod.loobia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.loobia.dto.AreaDto;
import it.objectmethod.loobia.service.AreaService;

@RestController
@CrossOrigin
@RequestMapping("/api/zona")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@PutMapping("/save")
	public AreaDto censusAgents(@RequestParam(value = "codZona") String codZona,
			@RequestParam(value = "idAgente") Integer idAgente) {

		AreaDto areaDto = areaService.censusAgents(codZona, idAgente);

		return areaDto;
	}
}
