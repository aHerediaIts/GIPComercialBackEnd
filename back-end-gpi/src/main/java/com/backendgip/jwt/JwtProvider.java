//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.jwt;

import com.backendgip.model.UsuarioPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;

	public JwtProvider() {
	}

	public String generateToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + (long) (this.expiration * 1000)))
				.signWith(SignatureAlgorithm.HS512, this.secret).compact();
	}

	public String getNombreUsuarioFromToken(String token) {
		return ((Claims) Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody()).getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException var3) {
			logger.error("token mal formado");
		} catch (UnsupportedJwtException var4) {
			logger.error("token no soportado");
		} catch (ExpiredJwtException var5) {
			logger.error("token expirado");
		} catch (SignatureException var6) {
			logger.error("token mal firmado");
		}

		return false;
	}
}
