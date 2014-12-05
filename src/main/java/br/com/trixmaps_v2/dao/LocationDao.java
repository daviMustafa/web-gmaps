package br.com.trixmaps_v2.dao;

import java.util.List;

import br.com.trixmaps_v2.model.Location;

public interface LocationDao {
	
	public void insert(Location location);
	
	public Location searchById(Long id);
	
	public List<Location> listAll();
	
	public void remove(Location location);
	
	public void update(Location location);
}
