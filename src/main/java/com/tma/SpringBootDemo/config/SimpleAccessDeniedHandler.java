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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author dangv
 *
 */
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		HashMap<String, String> map = new HashMap<>(3);
		map.put("time", new Date().toString());
		map.put("status", String.valueOf(HttpStatus.FORBIDDEN.value()));
		map.put("message", "FORBIDDEN-not authentication");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		ObjectMapper objectMapper = new ObjectMapper();
		String resBody = objectMapper.writeValueAsString(map);
		logger.error(map.get("message"));
		PrintWriter printWriter = response.getWriter();
		printWriter.print(resBody);
		printWriter.flush();
		printWriter.close();
	}

}
