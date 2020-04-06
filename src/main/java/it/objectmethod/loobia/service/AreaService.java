package it.objectmethod.loobia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.loobia.dto.AreaDto;
import it.objectmethod.loobia.entity.Area;
import it.objectmethod.loobia.entity.User;
import it.objectmethod.loobia.mapper.AreaMapper;
import it.objectmethod.loobia.repository.AreaRepository;
import it.objectmethod.loobia.repository.UserRepository;

@Service
public class AreaService {

	@Autowired
	private AreaRepository areaRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AreaMapper areaMapper;

	public AreaDto censusAgents(String codZona, Integer idAgente) {
		Area area = areaRepo.findByCodZona(codZona);
		User user = userRepo.findById(idAgente).get();
		if (area != null) {
			area.setUser(user);
		} else {
			area = new Area();
			area.setCodZona(codZona);
			area.setUser(user);
		}
		areaRepo.save(area);
		return areaMapper.toDto(area);
	}
}
