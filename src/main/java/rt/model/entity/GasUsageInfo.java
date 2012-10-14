package rt.model.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UnitInfo
 *
 */
@Entity

public class GasUsageInfo implements Serializable {

	   
	@Id
	private int id;
	private String username;
	private String password;
	private String type;
	private String brand_model;
	private String cond_notes;
	private String prob_desc;
	private String rental;
	private String service_tag;
	private String rush;
	private String dust;
	
	private static final long serialVersionUID = 1L;

	  
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand_model() {
		return brand_model;
	}
	public void setBrand_model(String brand_model) {
		this.brand_model = brand_model;
	}
	public String getCond_notes() {
		return cond_notes;
	}
	public void setCond_notes(String cond_notes) {
		this.cond_notes = cond_notes;
	}
	public String getProb_desc() {
		return prob_desc;
	}
	public void setProb_desc(String prob_desc) {
		this.prob_desc = prob_desc;
	}
	public String getRental() {
		return rental;
	}
	public void setRental(String rental) {
		this.rental = rental;
	}
	public String getService_tag() {
		return service_tag;
	}
	public void setService_tag(String service_tag) {
		this.service_tag = service_tag;
	}
	public String getRush() {
		return rush;
	}
	public void setRush(String rush) {
		this.rush = rush;
	}
	public String getDust() {
		return dust;
	}
	public void setDust(String dust) {
		this.dust = dust;
	}
   
}
