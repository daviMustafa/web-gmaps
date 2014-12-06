package br.com.trixmaps_v2.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.model.Tag;
import br.com.trixmaps_v2.service.LocationService;
import br.com.trixmaps_v2.service.TagService;

@WebServlet("/locationActionAjax")
public class LocationActionAjax extends HttpServlet {

	private static final long serialVersionUID = -7174101257872838549L;
	
	private ApplicationContext ctx = null; 
	
	private TagService tagService;
	
	private LocationService locationService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String method = request.getParameter("method");
		
		// Carregando contexto Spring		
		if(ctx == null){
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		}
				
		tagService = ctx.getBean(TagService.class);
		locationService = ctx.getBean(LocationService.class);
		
		if(method != null){
			
			switch(method.toLowerCase()){
			case "loadtags":{
				loadTags(request, response);
				break;
			}
			}
		}
	}
	
	private void loadTags(HttpServletRequest request, HttpServletResponse response){
		JSONObject rt = null;
		
		try(PrintWriter out = response.getWriter();){
			
				rt = new JSONObject();
				
				JSONArray all = loadAll(request, response);
				rt.put("allTags", all);
				
				JSONArray locationTags = loadLocationTags(request, response);
				rt.put("locationTags", locationTags);
				
				response.setContentType("application/json");
				
				out.write(rt.toString());
				out.flush();
				
		} catch (JSONException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Fazer carregamento de tags
	 */
	
	private JSONArray loadAll(HttpServletRequest request, HttpServletResponse response){
		JSONArray jsn = null;
		
		try{
			List<Tag> tags = tagService.list();
			JSONObject obj = null;
				
			jsn = new JSONArray();
			
			for(Tag t: tags){
				obj = new JSONObject();
				obj.put("id", t.getId());
				obj.put("name", t.getName());
				jsn.put(obj);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return jsn;
	}
	
	/**
	 * Fazer carregamento de Tags associadas a uma Location
	 */
	private JSONArray loadLocationTags(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{ 
		
		JSONArray jsn = null;
		
		String locationId = request.getParameter("locationId");
		
		if(locationId != null){
			try{
				Location location = locationService.findById(Long.parseLong(locationId));
				JSONObject obj = null;
				jsn = new JSONArray();
				
				List<Tag> locationTags = location.getTags();
				
				for(Tag t : locationTags){
					obj = new JSONObject();
					obj.put("locationTags", t);
					obj.put("id", t.getId());
					obj.put("name", t.getName());
					jsn.put(obj);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return jsn;
	}
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	
	
}
