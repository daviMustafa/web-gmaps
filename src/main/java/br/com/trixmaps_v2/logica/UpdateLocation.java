package br.com.trixmaps_v2.logica;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.model.Tag;
import br.com.trixmaps_v2.service.LocationService;
import br.com.trixmaps_v2.service.TagService;

/**
 * @author  Davi Mustafa
 * @email   mustafadavi@gmail.com
 * @date    14/12/2014
 */
public class UpdateLocation implements Logic {

	private Location location;
	private LocationService locationService;
	private TagService tagService;
	private Tag tag;
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response, ApplicationContext ctx) throws Exception{
		
		location = ctx.getBean(Location.class);
		tag = ctx.getBean(Tag.class);
		locationService = ctx.getBean(LocationService.class);
		tagService = ctx.getBean(TagService.class);
		
		String id = request.getParameter("locationId");
		String name = request.getParameter("name");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String[] tagsSelecionadas = request.getParameterValues("tagsSelecionadas");
		
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
}
