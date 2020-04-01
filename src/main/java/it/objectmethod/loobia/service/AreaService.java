package it.objectmethod.loobia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.loobia.entity.Area;
import it.objectmethod.loobia.repository.AreaRepository;

@Service
public class AreaService {

	@Autowired
	AreaRepository areaRepo;

	public void censusAgents(String codzona, Integer idagent) {
		Area area = areaRepo.findByCodzona(codzona);

		if (area != null) {
			// se il cod_zona esiste aggiorno solo l'id_agente
			area.setIdagente(idagent);
			areaRepo.save(area);
		} else {
			// se non esiste inserisco sia cod_zona che id_agente
			area = new Area();
			area.setCodzona(codzona);
			area.setIdagente(idagent);

			areaRepo.save(area);
		}
	}
}
