package br.com.trixmaps_v2.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.trixmaps_v2.model.Tag;
import br.com.trixmaps_v2.service.TagService;

@WebServlet("/tagController")
public class TagController extends HttpServlet {

	private static final long serialVersionUID = -2321176125377083325L;

	private ApplicationContext ctx = null;

	private Tag tag;
	private TagService tagService;

	@Override
	@GET
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@DELETE
	protected void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");

		tag.setId(Long.parseLong(id));

		try {
			tagService.delete(tag);
			request.setAttribute("msg", "Tag successfully deleted.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to delete tag.");
		}
		
		request.setAttribute("tags", tagService.list());
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/tag/create.jsp");
		rd.forward(request, response);
		
	}

	@Override
	@POST
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		loadApplicationContext();
		String method = request.getParameter("save");
		
		if("create".equalsIgnoreCase(method)){
			String name = request.getParameter("name");
			addTag(name, request);
		}
			
		request.setAttribute("tags", tagService.list());
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?page=/pages/tag/create.jsp");
		rd.forward(request, response);
	}

	public void addTag(String name, HttpServletRequest request) {

		tag.setName(name);
		tag.setCreated(new Date());

		try {
			tagService.create(tag);
			request.setAttribute("msg", "Tag successfully added.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error trying to add tag.");
		}
	}

	public void loadApplicationContext() {
		// Carregando contexto Spring
		if (ctx == null) {
			ctx = WebApplicationContextUtils.getWebApplicationContext(this
					.getServletContext());
		}

		tagService = ctx.getBean(TagService.class);
		tag = ctx.getBean(Tag.class);
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
