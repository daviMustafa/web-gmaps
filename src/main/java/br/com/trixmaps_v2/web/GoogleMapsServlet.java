package br.com.trixmaps_v2.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.service.LocationService;

@WebServlet("/googleMapsController")
public class GoogleMapsServlet extends HttpServlet {

	private static final long serialVersionUID = 4481103553228677322L;

	private LocationService locationService;
	private ApplicationContext ctx;
	private Location location;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		loadApplicationContext();
		
		String action = request.getParameter("method");
		
		if("loadMap".equalsIgnoreCase(action)){
			
			String locationId = request.getParameter("locationId");
			
			switch(action.toLowerCase()){
			case "loadmap":{
				loadMap(request, response, locationId);
				break;
			}
			}
		}
		
		List <Location> locations = locationService.list();
		request.setAttribute("listLocations", locations);
		
		if(!response.isCommitted()){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/map/map.jsp");
			rd.forward(request, response);
		}	
		
	}
	
	private void loadMap(HttpServletRequest request, HttpServletResponse response, String locationId){
		
		JSONObject obj = null;
		
		try(PrintWriter out = response.getWriter();){
			
			obj = new JSONObject();
			location = locationService.findById(Long.parseLong(locationId));
			
			obj.put("name", location.getName());
			obj.put("latitude", location.getLatitude());
			obj.put("longitude", location.getLongitude());
			obj.put("latlng", location.getLatitude()+", "+location.getLongitude());
			
			response.setContentType("application/json");
			
			out.write(obj.toString());
			out.flush();
			System.out.println(obj);
			
		} catch (JSONException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void loadApplicationContext(){
		// Carregando contexto Spring		
		if(ctx == null){
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		}
			
		locationService = ctx.getBean(LocationService.class);
		location = ctx.getBean(Location.class);
	}
	
}
