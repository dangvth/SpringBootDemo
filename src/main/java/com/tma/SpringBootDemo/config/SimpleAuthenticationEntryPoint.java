/**
 * 
 */
package com.tma.SpringBootDemo.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tma.SpringBootDemo.exception.ErrorMessage;

/**
 * @author dangv
 *
 */
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		HashMap<String, String> map = new HashMap<>(3);
		map.put("time", new Date().toString());
		map.put("status", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
		map.put("message", "UNAUTHORIZED-not authentication");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ObjectMapper objectMapper = new ObjectMapper();
		String resBody = objectMapper.writeValueAsString(map);
		logger.error(map.get("message"));
		PrintWriter printWriter = response.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
	}

}
