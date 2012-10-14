package rt.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import rt.model.entity.Employee;
import rt.service.api.IDatabaseService;

@Model
public class EmployeeController {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private IDatabaseService employeeService;
	
	@Inject
	private Logger log;
	
	
	private Employee newEmployee;
	private boolean registerResult = false;
	
	
	@Produces
	@Named
	public Employee getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(Employee newEmployee){
		this.newEmployee = newEmployee;
	}
	
	
	public String register(){
		log.info("EmployeeController-register:"+ newEmployee.toString());
		
		try{
			setRegisterResult(employeeService.findEmployee(newEmployee));
			if (isRegisterResult()){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
			}else{
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "failed to Register!", "Registration failed"));
			}
			initNewEmployee();
		}catch (Exception e){
			log.severe("Error:"+e.getMessage());
		}
		return "registerResult";
	}
	
	@PostConstruct
	public void initNewEmployee()
	{
		newEmployee = new Employee();
	}

	public boolean isRegisterResult() {
		return registerResult;
	}

	public void setRegisterResult(boolean registerResult) {
		this.registerResult = registerResult;
	}
}
