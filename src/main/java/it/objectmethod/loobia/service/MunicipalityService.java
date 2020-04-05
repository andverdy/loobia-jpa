package it.objectmethod.loobia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.dto.MunicipalityDto;
import it.objectmethod.loobia.entity.Municipality;
import it.objectmethod.loobia.mapper.MunicipalityMapper;
import it.objectmethod.loobia.repository.MunicipalityRepository;

@Component
public class MunicipalityService {

	@Autowired
	private MunicipalityRepository municipRepo;

	@Autowired
	private MunicipalityMapper municipMapper;

	public List<MunicipalityDto> findByNomeStartingWith(String municipSearched) {
		List<Municipality> listMunicip = municipRepo.searchByNomeStartsWith(municipSearched);
		List<MunicipalityDto> municipalitiesListDto = new ArrayList<MunicipalityDto>();

		for (int i = 0; i < listMunicip.size(); i++) {
			MunicipalityDto munDto = municipMapper.toDto(listMunicip.get(i));
			municipalitiesListDto.add(munDto);
		}

		return municipalitiesListDto;
	}

}
