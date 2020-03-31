package it.objectmethod.loobia.controller.beans;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.objectmethod.loobia.dto.UserDto;

@Component
public class LoggedUsers {

	private Map<Long, UserDto> mapLoggedUsers;

	public Map<Long, UserDto> getMapLoggedUsers() {
		if (this.mapLoggedUsers == null) {
			this.mapLoggedUsers = new HashMap<Long, UserDto>();
		}
		return mapLoggedUsers;
	}

}
