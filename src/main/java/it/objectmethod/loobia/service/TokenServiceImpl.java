package it.objectmethod.loobia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class TokenServiceImpl implements TokenService {

	private static Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

	@Value("${secret}")
	private String SECRET = "secret";

	@Override
	public String getToken(String email) {
		log.info("Genero token con chiave " + SECRET);
		// HMAC
		String token = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			token = JWT.create().withIssuer(email).sign(algorithm);
		} catch (JWTCreationException exception) {

			log.error("Errore generando il token", exception);
			// Invalid Signing configuration / Couldn't convert Claims.
		}

		return token;
	}

//	public boolean isValid(String token) {
//		
//		if (StringUtils.isEmpty(token)) return false;
//		try {
//		    Algorithm algorithm = Algorithm.HMAC256(SECRET);
//		    JWTVerifier verifier = JWT.require(algorithm).build();
////		        .withIssuer("auth0")
////		        .build(); //Reusable verifier instance
//		    DecodedJWT jwt = verifier.verify(token);
//		    log.info("Fdecoded token ="+jwt.toString());
//		} catch (JWTVerificationException exception){
//			log.error("Errore decodificando il token ", exception);
//			return false;
//		    //Invalid signature/claims
//		}
//		
//		return true;
//	}

	@Override
	public String getEmail(String token) {
		if (StringUtils.isEmpty(token))
			return null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
//		        .withIssuer("auth0")
//		        .build(); //Reusable verifier instance
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getIssuer();
		} catch (JWTVerificationException exception) {
			log.error("Errore decodificando il token ", exception);
			return null;
		}

	}

}
