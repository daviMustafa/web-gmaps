package br.com.trixmaps_v2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.trixmaps_v2.dao.TagDao;
import br.com.trixmaps_v2.model.Tag;

@Service(value="tagService")
public class TagService {

	@Autowired(required=false)
	private TagDao tagDao;
	
	@Transactional
	public void create(Tag tag){
		tagDao.insert(tag);
	}
	
	@Transactional
	public void save(Tag tag){
		tagDao.update(tag);
	}
	
	@Transactional
	public List<Tag> list(){
		return tagDao.listAll();
	}
	
	@Transactional
	public void delete(Tag tag){
		tagDao.remove(tag);
	}
	
	@Transactional
	public Tag listById(Long id){
		return tagDao.searchById(id);
	}
	
}
