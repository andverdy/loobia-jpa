package it.objectmethod.loobia.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.loobia.dto.UserDto;
import it.objectmethod.loobia.service.TokenService;
import it.objectmethod.loobia.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<String> doLogin(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		ResponseEntity<String> resp = null;
		String ret = "";
		log.info("Login Request with name " + email);

		UserDto userDto = userService.findByUsernameAndPassword(email, password);

		if (userDto != null) {
			ret = tokenService.getToken(email);
			resp = new ResponseEntity<>(ret, HttpStatus.OK);
		} else {

			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@RequestMapping("/other")
	public String otherApi(HttpServletRequest req) {

		return "OK " + req.getAttribute("email");
	}
}
