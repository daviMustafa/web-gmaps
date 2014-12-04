package br.com.trixmaps_v2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.trixmaps_v2.dao.LocationDao;
import br.com.trixmaps_v2.model.Location;


@Service(value="locationService")
public class LocationService {
	
	@Autowired(required=false)
	private LocationDao locationDao;
	
	@Transactional
	public void create(Location location){
		locationDao.insert(location);
	}
	
	@Transactional
	public void save(Location location){
		locationDao.update(location);
	}
	
	@Transactional
	public List<Location> list(){
		return locationDao.listAll();
	}
	
	@Transactional
	public void delete(Location location){
		locationDao.remove(location);
	}
}
