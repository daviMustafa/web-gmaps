package br.com.trixmaps_v2.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.trixmaps_v2.model.Tag;

@Repository
public class TagDaoImpl implements TagDao, Serializable{
	
	private static final long serialVersionUID = 6349010375889069302L;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired(required=false)
	private TagDao tagDao;
	
	public TagDaoImpl(){
	}
	
	public TagDaoImpl(EntityManager manager){
		this.manager = manager;
	}
	
	@Transactional
	public void insert(Tag tag){
		this.manager.persist(manager.merge(tag));
	}
	
	@Transactional(readOnly=true)
	public Tag searchById(Integer id){
		return this.manager.find(Tag.class, id);
	}
	
	@Transactional(readOnly=true)
	public List<Tag> listAll(){
		return this.manager.createQuery("SELECT t from Tag t", Tag.class).getResultList();
	}
	
	@Transactional
	public void remove(Tag tag){
		this.manager.remove(manager.merge(tag));
	}
	
	@Transactional
	public void update(Tag tag){
		this.manager.merge(tag);
	}
	
	@Autowired(required=false)
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	public TagDao getTagDao() {
		return tagDao;
	}
}
