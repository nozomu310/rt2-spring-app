package jp.co.sss.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;

@Service
public class DeletedFlgService {
	
	@Autowired
	EmployeeRepository repo;

	/**
	 * 指定された従業員IDの従業員情報を削除メソッド。
	 * データベースから該当する従業員レコードを物理削除します。
	 * 
	 * @param forDeleteEmpId 削除対象の従業員ID
	 */
	//TODO ここに記述
	public void execute(Integer empId) {
		
		Employee emp = new Employee();
	    emp = repo.findByEmpId(empId);
		
		emp.setDeletedFlg(1);
		
		emp = repo.save(emp);
		
	}

}


