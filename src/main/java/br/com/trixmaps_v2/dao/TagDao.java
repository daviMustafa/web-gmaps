package br.com.trixmaps_v2.dao;

import java.util.List;

import br.com.trixmaps_v2.model.Tag;

public interface TagDao {
	
	public void insert(Tag tag);
	
	public Tag searchById(Integer id);
	
	public List<Tag> listAll();
	
	public void remove(Tag tag);
	
	public void update(Tag tag);
}
