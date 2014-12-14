package br.com.trixmaps_v2.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

public interface Logic {

	void execute(HttpServletRequest request, HttpServletResponse response, ApplicationContext ctx) throws Exception;
}
