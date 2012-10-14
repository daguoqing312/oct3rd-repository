package rt.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
public class User implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;
   
   @Id
   @GeneratedValue
   private Long id;   

   @NotNull
   @NotEmpty
   @Email
   private String email;
   
   @NotNull
   @Size(min = 4, max = 25)
   @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+", message = "must non-white chars")
   private String password;
   
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
   
   @Override
   public String toString(){
	   String rtnStr = " Email: "+email+" Password: "+password;
	   return rtnStr;
   }
}