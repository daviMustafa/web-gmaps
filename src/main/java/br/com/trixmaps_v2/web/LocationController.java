package br.com.trixmaps_v2.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.model.Tag;
import br.com.trixmaps_v2.service.LocationService;
import br.com.trixmaps_v2.service.TagService;

@WebServlet("/locationController")
public class LocationController extends HttpServlet {

	private static final long serialVersionUID = 181756908569087888L;
	
	ApplicationContext ctx = null;
	
	private Location location;
	private Tag tag;
	private LocationService locationService;
	private TagService tagService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String action = request.getParameter("action"); 
		
		loadApplicationContext();
		
		if("create".equalsIgnoreCase(action)){
			
			String name = request.getParameter("name");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			String[] tagsSelecionadas = request.getParameterValues("tagsSelecionadas");
			
			addLocation(request, name, latitude, longitude, tagsSelecionadas);
			
		} else if ("del".equalsIgnoreCase(action)){
			
			String id = request.getParameter("id");
			deleteLocation(id, request);
			
		} else if ("edit".equalsIgnoreCase(action)){
			
			String id = request.getParameter("id");
			prepareUpdate(request, id);
			
		} else if("update".equalsIgnoreCase(action)){
			
			String id = request.getParameter("locationId");
			String name = request.getParameter("name");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			String[] tagsSelecionadas = request.getParameterValues("tagsSelecionadas");
			
			updateLocation(request, id, name, latitude, longitude, tagsSelecionadas);
		}
		
		request.setAttribute("tags", tagService.list());
		request.setAttribute("locations", locationService.list());
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/location/create.jsp");
		rd.forward(request, response);
		
	}
	
	public void addLocation(HttpServletRequest request, String name, String latitude, String longitude, String[] tagsSelecionadas){
		
		List<Tag> tags = new ArrayList<Tag>();
		tag = new Tag();
		
		for(String str : tagsSelecionadas){
			tag = tagService.findById(Long.parseLong(str));
			tags.add(tag);
		}
		
		location.setName(name);
		location.setLatitude(Double.parseDouble(latitude));
		location.setLongitude(Double.parseDouble(longitude));
		location.setTags(tags);
		location.setCreated(new Date());
		
		try{
			locationService.create(location);
			request.setAttribute("msg", "Location successfully added.");
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to add location.");
		}
		
	}
	
	public void deleteLocation(String id, HttpServletRequest request){
		
		
		location.setId(Long.parseLong(id));
		
		try{
			locationService.delete(location);
			request.setAttribute("msg", "Location successfully deleted.");
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to delete location.");
		}
	}
	
	public void prepareUpdate(HttpServletRequest request, String id){
		
		location.setId(Long.parseLong(id));
		
		try{
			
			location = locationService.findById(location.getId());
			
			request.setAttribute("location", location);
			
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to edit Location.");
		}
		
	}
	
	public void updateLocation(HttpServletRequest request, String id, String name, String latitude, String longitude, String[] tagsSelecionadas){
		
		List<Tag> tags = new ArrayList<Tag>();
		tag = new Tag();
		
		for(String str : tagsSelecionadas){
			tag = tagService.findById(Long.parseLong(str));
			tags.add(tag);
		}
		
		location.setId(Long.parseLong(id));
		location.setName(name);
		location.setLatitude(Double.parseDouble(latitude));
		location.setLongitude(Double.parseDouble(longitude));
		location.setTags(tags);
		
		try{
			locationService.save(location);
			request.setAttribute("msg", "Location successfully updated");
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to update Location");
		}
		
	}
	
	public void loadApplicationContext(){
		
		// Carregando contexto Spring		
		if (ctx == null) {
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		}

		tagService = ctx.getBean(TagService.class);
		locationService = ctx.getBean(LocationService.class);
		location = ctx.getBean(Location.class);
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	
}
