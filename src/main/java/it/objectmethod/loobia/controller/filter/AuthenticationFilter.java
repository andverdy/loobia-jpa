package it.objectmethod.loobia.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.entity.User;
import it.objectmethod.loobia.repository.UserRepository;
import it.objectmethod.loobia.service.TokenService;

@Component
public class AuthenticationFilter implements Filter {

	private static Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepo;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		LOG.info("Starting a transaction for req : {}" + req.getRequestURI());
		String url = req.getRequestURI();
		if (url.endsWith("/login")) {
			chain.doFilter(req, response);
		} else {

			String token = req.getHeader("authorization");
			String email = tokenService.getEmail(token);
			User user = userRepo.findByEmail(email);

			boolean authorized = false;

			if (email != null) {
				if (user.getRole().equals("AGENT")) {
					if (url.contains("api/customer") || url.contains("api/municipality")
							|| url.contains("api/customer_addresses") | url.contains("api/order")
							|| url.contains("api/download")) {
						authorized = true;
					}
				} else if (user.getRole().equals("ADMIN")) {
					if (url.contains("api/zona") || url.contains("api/municipality")) {
						authorized = true;
					}
				}
			}
			// loobia/api/customer_addresses/by_id
			if (authorized) {
				req.setAttribute("email", email);
				LOG.info(String.format("Utente autorizzato %s a procedere verso %s", email, req.getRequestURI()));
				chain.doFilter(req, response);
			} else {
				LOG.info("Denied access to " + req.getRequestURI());
				((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
			}

		}
		LOG.info("Committing a transaction for req : {}" + req.getRequestURI());
	}

}
