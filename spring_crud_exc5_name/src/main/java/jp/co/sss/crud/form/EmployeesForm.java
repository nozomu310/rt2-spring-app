package jp.co.sss.crud.form;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

public class EmployeesForm {
	
	@Valid
	private List<EmployeeForm> emps = new ArrayList<>();

	public List<EmployeeForm> getEmps() {
		return emps;
	}

	public void setEmps(List<EmployeeForm> emps) {
		this.emps = emps;
	}
	
	

}
