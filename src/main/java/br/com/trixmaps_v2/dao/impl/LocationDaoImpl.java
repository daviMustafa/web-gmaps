
package br.com.trixmaps_v2.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.trixmaps_v2.dao.LocationDao;
import br.com.trixmaps_v2.model.Location;


@Repository
public class LocationDaoImpl implements LocationDao, Serializable{
	
	private static final long serialVersionUID = 3698621543099996350L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public LocationDaoImpl(){}
	
	public LocationDaoImpl(EntityManager manager){
		this.manager = manager;
	}
	
	@Transactional
	public void insert(Location location){
		this.manager.persist(manager.merge(location));
	}
	
	@Transactional(readOnly=true)
	public Location searchById(Long id){
		return this.manager.find(Location.class, id);
	}
	
	@Transactional(readOnly=true)
	public List<Location> listAll(){
		return this.manager.createQuery("Select l from Location l", Location.class).getResultList();
	}
	
	@Transactional
	public void remove(Location location){
		this.manager.remove(manager.merge(location));
	}
	
	@Transactional
	public void update(Location location){
		this.manager.merge(manager.merge(location));
	}
}
