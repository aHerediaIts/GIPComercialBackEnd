//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.jwt;

import com.backendgip.imp.UserDetailsServiceImp;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private UserDetailsServiceImp userDetailsService;

	public JwtTokenFilter() {
	}

	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = this.getToken(req);
			if (token != null && this.jwtProvider.validateToken(token)) {
				String nombreUsuario = this.jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
						(Object) null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception var8) {
			logger.error("Fail en el metodo doFilter" + var8);
		}

		filterChain.doFilter(req, res);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		return header != null && header.startsWith("Bearer") ? header.replace("Bearer ", "") : null;
	}
}
