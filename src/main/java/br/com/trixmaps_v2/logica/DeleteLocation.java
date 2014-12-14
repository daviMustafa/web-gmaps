package br.com.trixmaps_v2.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.service.LocationService;

/**
 * @author  Davi Mustafa
 * @email   mustafadavi@gmail.com
 * @date    14/12/2014
 */

public class DeleteLocation implements Logic{

	private Location location;
	private LocationService locationService;
	
	public void execute(HttpServletRequest request, HttpServletResponse response, ApplicationContext ctx) throws Exception{
		
		location = ctx.getBean(Location.class);
		locationService = ctx.getBean(LocationService.class);
		
		String id = request.getParameter("id");
		
		location.setId(Long.parseLong(id));
		
		try{
			locationService.delete(location);
			request.setAttribute("msg", "Location successfully deleted.");
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to delete location.");
		}
		
	}
}
