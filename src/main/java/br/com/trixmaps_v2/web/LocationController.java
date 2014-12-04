package br.com.trixmaps_v2.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
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
			
			
			location.setName(name);
			location.setLatitude(Double.parseDouble(latitude));
			location.setLongitude(Double.parseDouble(longitude));
			location.setCreated(new Date());
			
			try{
				locationService.create(location);
				request.setAttribute("msg", "Location successfully added.");
			} catch (Exception e){
				e.printStackTrace();
				request.setAttribute("errorMsg", "Error trying to add location.");
			}
			
		} else if ("delete".equalsIgnoreCase(action)){
			
			String id = request.getParameter("id");
			
			location.setId(Long.parseLong(id));
			
			try{
				locationService.delete(location);
				request.setAttribute("msg", "Location successfully deleted.");
			} catch (Exception e){
				e.printStackTrace();
				request.setAttribute("errorMsg", "Error trying to delete location.");
			}
			
		} else if ("edit".equalsIgnoreCase(action)){
			
		}
		
		request.setAttribute("locations", locationService.list());
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/location/create.jsp");
		rd.forward(request, response);
		
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
