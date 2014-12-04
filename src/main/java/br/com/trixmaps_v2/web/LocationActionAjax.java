package br.com.trixmaps_v2.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.model.Tag;
import br.com.trixmaps_v2.service.LocationService;
import br.com.trixmaps_v2.service.TagService;

public class LocationActionAjax extends HttpServlet {

	private static final long serialVersionUID = -7174101257872838549L;
	
	private TagService tagService;
	
	private LocationService locationService;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String method = request.getParameter("method");
		
		if(method != null){
			
			switch(method.toLowerCase()){
			case "loadTags":{
				loadTags(request, response);
				break;
			}
			}
		}
	}
	
	private void loadTags(HttpServletRequest request, HttpServletResponse response){
		JSONObject rt = null;
		
		try(PrintWriter out = response.getWriter();){
			final String location = request.getParameter("location");
			
			if(location != null){
				rt = new JSONObject();
				
				JSONArray all = loadAll(request, response);
				rt.put("all", all);
				
				JSONArray locationTags = loadLocationTags(request, response);
				rt.put("locationTags", locationTags);
				
				response.setContentType("application/json");
				
				out.write(rt.toString());
				out.flush();
			}
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
	 * Fazer carregamento de Tags associadas a uma location
	 */
	private JSONArray loadLocationTags(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{ 
		
		JSONArray jsn = null;
		
		try{
			List<Location> locationTags = locationService.list();
			JSONObject obj = null;
			jsn = new JSONArray();
			
			for(Location l : locationTags){
				obj = new JSONObject();
				obj.put("locationTags", l.getTags());
				jsn.put(obj);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return jsn;
	}
	
	
	
	
	
}
