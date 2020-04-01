//package it.objectmethod.loobia.controller.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import it.objectmethod.loobia.service.TokenService;
//
//@Component
//public class AuthenticationFilter implements Filter {
//
//	private static Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);
//
//	@Autowired
//	private TokenService tokenService;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		HttpServletRequest req = (HttpServletRequest) request;
//
//		LOG.info("Starting a transaction for req : {}" + req.getRequestURI());
//		// url.endsWith("jpadto/api/user/login")
//		String url = req.getRequestURI();
//		if (url.endsWith("/login")) {
//			chain.doFilter(req, response);
//		} else {
//			String token = req.getHeader("authorization");
//			if (tokenService.getEmail(token) != null) {
//				String email = tokenService.getEmail(token);
//				LOG.info(String.format("Utente autorizzato %s a procedere verso %s", email, req.getRequestURI()));
//
//				req.setAttribute("email", email);
//				chain.doFilter(req, response);
//			} else {
//
//				LOG.info("Denied access to " + req.getRequestURI());
//				((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
//			}
//		}
//
//		LOG.info("Committing a transaction for req : {}" + req.getRequestURI());
//	}
//
//}
