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

import br.com.trixmaps_v2.model.Tag;
import br.com.trixmaps_v2.service.TagService;

@WebServlet("/tagController")
public class TagController extends HttpServlet {

	private static final long serialVersionUID = -2321176125377083325L;
	
	ApplicationContext ctx = null; 
	
	private Tag tag;
	private TagService tagService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// Carregando contexto Spring		
		if(ctx == null){
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		}
		
		tagService = ctx.getBean(TagService.class);
		String action = request.getParameter("action");
		
		if("".equalsIgnoreCase(action) || action != null){
			if("create".equalsIgnoreCase(action)){
				
				String name = request.getParameter("name");
				addTag(name, request);
			
			} else if ("delete".equalsIgnoreCase(action)){
				
				String id = request.getParameter("id");
				deleteTag(id, request);
			}
		}	
		
		request.setAttribute("tags", tagService.list());
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/tag/create.jsp");
		rd.forward(request, response);
	}
	
	public void addTag(String name, HttpServletRequest request){
		
		tag = new Tag();
		
		tag.setName(name);
		tag.setCreated(new Date());
		
		try{
			tagService.create(tag);
			request.setAttribute("msg", "Tag successfully added.");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to add tag.");
		}
	}
	
	public void deleteTag(String id, HttpServletRequest request){
		tag = new Tag();
		
		tag.setId(Long.parseLong(id));
		
		try{
			tagService.delete(tag);
			request.setAttribute("msg", "Tag successfully deleted.");
		} catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to delete tag.");
		}
	}
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}


	
	
}
