package br.com.trixmaps_v2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name="location")
public class Location extends AbstractEntity{

	
	@Column(nullable=false)
	private Double latitude;
	
	@Column(nullable=false)
	private Double longitude;
	
	@Column(length=30, nullable=false)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@ManyToMany(cascade= {  CascadeType.PERSIST , CascadeType.MERGE , CascadeType.REFRESH }, fetch = FetchType.EAGER )
	@JoinTable(name="LOCATION_TAGS", joinColumns=@JoinColumn(name="location_id", nullable=false), 
	inverseJoinColumns=@JoinColumn(name="tag_id", nullable=false) )
	private List<Tag> tags = new ArrayList<Tag>();
	
	public Location() {
		super();
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [latitude=").append(latitude)
				.append(", longitude=").append(longitude).append(", name=")
				.append(name).append(", created=").append(created)
				.append(", tags=").append(tags).append("]");
		return builder.toString();
	}

}
