package it.objectmethod.loobia.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import it.objectmethod.loobia.dto.UserDto;
import it.objectmethod.loobia.entity.Area;
import it.objectmethod.loobia.entity.User;
import it.objectmethod.loobia.mapper.UserMapper;
import it.objectmethod.loobia.repository.AreaRepository;
import it.objectmethod.loobia.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AreaRepository areRepo;

	@Autowired
	private UserMapper userMapper;

	public UserDto findByUsernameAndPassword(String email, String password) {

		User user = userRepo.findByEmailAndPassword(email, password);
		UserDto userDto = userMapper.toDto(user);
		return userDto;
	}

	public void censusAgents(String codzona, Integer idagent) {
		Area area = areRepo.findByCodzona(codzona);

		if (area != null) {
			// se il cod_zona esiste aggiorno solo l'id_agente
			area.setIdagente(idagent);
			areRepo.save(area);
		} else {
			// se non esiste inserisco sia cod_zona che id_agente
			area = new Area();
			area.setCodzona(codzona);
			area.setIdagente(idagent);

			areRepo.save(area);
		}

	}
}
