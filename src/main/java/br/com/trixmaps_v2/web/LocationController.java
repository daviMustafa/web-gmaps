package br.com.trixmaps_v2.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.service.LocationService;

@WebServlet("/locationController")
public class LocationController extends HttpServlet {

	private static final long serialVersionUID = 181756908569087888L;
	
	ApplicationContext ctx = null;
	
	private Location location;
	private LocationService locationService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("acao"); 
		location = new Location();
		
		if(ctx == null){
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		}
		
		locationService = ctx.getBean(LocationService.class);
		
		if("save".equalsIgnoreCase(action)){
			String name = request.getParameter("name");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			
			
		} else if ("delete".equalsIgnoreCase(action)){
			
		} else if ("edit".equalsIgnoreCase(action)){
			
		}
		
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
}
