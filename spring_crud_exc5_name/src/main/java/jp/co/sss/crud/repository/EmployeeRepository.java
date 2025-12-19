package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Employee findByEmpIdAndEmpPass(Integer empId, String empPass);

	List<Employee> findAllByOrderByEmpIdAsc();
	//List<Employee> findByEmpNameContaining(String searchString);
	List<Employee> findByDepartmentDeptIdOrderByEmpIdAsc(Integer deptId);
	
	Employee findByEmpId(Integer empId);
	
	Employee findByEmpIdAndEmpPassAndDeletedFlg(Integer empId, String empPass, Integer deletedFlg);
	
	List<Employee> findByDeletedFlgOrderByEmpIdAsc(Integer deletedFlg);
	List<Employee> findByEmpNameContainingAndDeletedFlgOrderByEmpIdAsc(String searchString, Integer deletedFlg);
	List<Employee> findByDepartmentDeptIdAndDeletedFlgOrderByEmpIdAsc(Integer deptId, Integer deletedFlg);

}
