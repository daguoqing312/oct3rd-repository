package rt.model.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserInfo
 *
 */
@Entity
public class UserInfo implements Serializable {

	   
	@Id
	private String id;	
	private String name;
	private String address;
	private String phone;
	private String password;
	private String email;
	
	private static final long serialVersionUID = 1L;
	
	
  
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString(){
		String rtnStr= new String("");
		rtnStr += id+", "+name; 
		return rtnStr;
	}
   
}
