package br.com.gastronomia.util;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerator {
	 public static String issueToken(String json) {
		 Key key = SimpleKeyGenerator.generateKey();
	        String jwtToken = Jwts.builder()
	                .setSubject(json)	               
	                .setIssuedAt(new Date())
	                .setExpiration(toDate(LocalDateTime.now().plusDays(4L)))
	                .signWith(SignatureAlgorithm.HS512, key)
	                .compact();
	        return jwtToken;
	    }

	 private static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
