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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String acao = request.getParameter("acao");
		tag = new Tag();
		
		if(ctx == null){
			System.out.println("Carregando contexto.");
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		}
		
		tagService = ctx.getBean(TagService.class);
		
		
		if("save".equalsIgnoreCase(acao)){
			String name = request.getParameter("name");
		
			System.out.println(name);
		
			tag.setName(name);
			tag.setCreated(new Date());
			tagService.create(tag);
		
			request.setAttribute("msgSucesso", "Tag adicionada com sucesso.");
		
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/tag/create.jsp");
			rd.forward(request, response);
			
		} else if ("delete".equalsIgnoreCase(acao)){
			
			String id = request.getParameter("id");
			
			tag.setId(Long.parseLong(id));
			
			tagService.delete(tag);
			
		}
	}
	
	public TagService getTagService() {
		return tagService;
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
