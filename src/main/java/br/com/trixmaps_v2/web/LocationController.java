package br.com.trixmaps_v2.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.trixmaps_v2.logica.Logic;
import br.com.trixmaps_v2.service.LocationService;
import br.com.trixmaps_v2.service.TagService;

@WebServlet("/locationController")
public class LocationController extends HttpServlet {

	private static final long serialVersionUID = 181756908569087888L;
	
	ApplicationContext ctx = null;
	
	private LocationService locationService;
	private TagService tagService;
	
	@Override
	@GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	@POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		loadApplicationContext();
		
		String action = request.getParameter("action");
		
		String nameClass = "br.com.trixmaps_v2.logica."+action;
		
		Class<?> classe;
		
		if(action != null){
			try{
				
				classe = Class.forName(nameClass);
				Object obj = classe.newInstance();
				
				Logic logic = (Logic) obj;
				logic.execute(request, response, ctx);
			
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			} catch (InstantiationError e){
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
		} 
			
			request.setAttribute("tags", tagService.list());
			request.setAttribute("locations", locationService.list());
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/location/create.jsp");
			rd.forward(request, response);
		
	}
	
	public void loadApplicationContext(){
		
		// Carregando contexto Spring		
		if (ctx == null) {
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		}

		tagService = ctx.getBean(TagService.class);
		locationService = ctx.getBean(LocationService.class);
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	
}
