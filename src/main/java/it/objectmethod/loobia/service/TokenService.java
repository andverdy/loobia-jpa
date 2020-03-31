package it.objectmethod.loobia.service;

public interface TokenService {

	String getToken(String email);

	String getEmail(String token);

}