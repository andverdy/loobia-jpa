package it.objectmethod.loobia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.loobia.dto.MunicipalityDto;
import it.objectmethod.loobia.service.MunicipalityService;

@RestController
@CrossOrigin
@RequestMapping("/api/municipality")
public class MunicipalityController {
	
	@Autowired
	MunicipalityService municipService;
	
	@GetMapping("/list")
	public List<MunicipalityDto> getMunicipalities(@RequestParam(value = "municipSearched") String municipSearched) {
		
		List<MunicipalityDto> municipListDto = municipService.findByNomeStartingWith(municipSearched);
		return municipListDto;
	}
	
}
