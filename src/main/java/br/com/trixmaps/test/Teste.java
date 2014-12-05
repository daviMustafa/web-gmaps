package br.com.trixmaps.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import br.com.trixmaps_v2.dao.LocationDao;
import br.com.trixmaps_v2.model.Location;
import br.com.trixmaps_v2.model.Tag;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;

//import br.com.trixmaps.dao.LocationDao;
//import br.com.trixmaps.dao.LocationDaoImpl;
//import br.com.trixmaps.model.Location;
//import br.com.trixmaps.model.Tag;

// TESTE APLICAÇÂO
public class Teste {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/applicationContext.xml");
		
		
		LocationDao bean = ctx.getBean(LocationDao.class);
		 
		Location l = new Location();
		l.setName("Unifor");
		l.setLatitude(-3.769785);
		l.setLongitude(-38.478131);
		l.setCreated(new Date());
		
		Tag t = new Tag();
		t.setName("North");
		t.setCreated(new Date());

		Tag t2 = new Tag();
		t2.setName("Brazil");
		t2.setCreated(new Date());
		
		l.getTags().add(t);
		l.getTags().add(t2);
		
		bean.insert(l);
		
//		Location location = bean.listAll().get(0);
//		bean.remove(location);
		System.out.println(l);
		
//		TagDao bean = ctx.getBean(TagDao.class);
//		
//		String id = "2";
//		
//		Tag tag = new Tag();
//		
//		tag.setId(Long.parseLong(id));
//		bean.remove(tag);
	}
}
