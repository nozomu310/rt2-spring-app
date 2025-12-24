package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.form.EmployeesForm;
import jp.co.sss.crud.service.BulkRegisterEmployeeService;
import jp.co.sss.crud.util.Constant;

@Controller
public class BulkRegistrationController {
	
	@Autowired
	private BulkRegisterEmployeeService bRS;
	
	@GetMapping("/regist/bulk/input")
	public String bulkInput(@ModelAttribute EmployeesForm employeesForm){
		
		EmployeeForm emp = new EmployeeForm();
		emp.setGender(Constant.DEFAULT_GENDER);
		emp.setAuthority(Constant.DEFAULT_AUTHORITY);
		emp.setDeptId(Constant.DEFAULT_DEPT_ID);
		
		employeesForm.getEmps().add(emp);
		
		return "bulkRegist/regist_input";
	}
	
	@PostMapping("/regist/bulk/complete")
	public String bulkComplete(@Valid @ModelAttribute EmployeesForm employeesForm, BindingResult result) {
	
	if(result.hasErrors()) {
		return "bulkRegist/regist_input";
	}
	
	bRS.execute(employeesForm);
	
	return "redirect:/regist/bulk/complete";
}
	
	@GetMapping("/regist/bulk/complete")
	public String completeResist() {
		return "bulkRegist/regist_complete";
	}

}
