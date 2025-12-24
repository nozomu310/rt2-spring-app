package jp.co.sss.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.form.EmployeesForm;
import jp.co.sss.crud.repository.EmployeeRepository;
import jp.co.sss.crud.util.BeanManager;

@Service
public class BulkRegisterEmployeeService {
	
	@Autowired
	private EmployeeRepository repo;
	
	@Transactional
	public void execute(EmployeesForm empsForm) {
		
		for(EmployeeForm empForm : empsForm.getEmps()) {
			Employee emp = new Employee();
			
			emp = BeanManager.copyFormToEntity(empForm);
	        emp.setDeletedFlg(0);
	        
	        emp = repo.save(emp);	
		}
	}

}
