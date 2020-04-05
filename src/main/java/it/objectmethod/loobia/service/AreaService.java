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

	public AreaDto censusAgents(String codzona, Integer idagent) {
		Area area = areaRepo.findByCodzona(codzona);
		User user = userRepo.findById(idagent).get();
		if (area != null) {

			area.setUser(user);
			areaRepo.save(area);
		} else {
			// se non esiste inserisco sia cod_zona che id_agente
			area = new Area();

			area.setCodzona(codzona);
			area.setUser(user);

			areaRepo.save(area);
		}
		return areaMapper.toDto(area);
	}
}
