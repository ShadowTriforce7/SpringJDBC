package com.hcl.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hcl.model.Emp;
import com.hcl.model.EmpMapper;
@Component
public class EmpDaoImpl implements EmpDao{
	
	JdbcTemplate template;
	@Autowired
	public EmpDaoImpl(DataSource ds) {
		template = new JdbcTemplate(ds);
	}

	@Override
	public Emp getEmpId(Integer id) {
		return template.queryForObject("select * from employee where eno=?", new Object[] {id}, new EmpMapper());
	}

	@Override
	public List<Emp> getAllEmps() {
		return template.query("select * from employee", new EmpMapper());
	}

	@Override
	public boolean deleteEmp(Emp emp) {
		return template.update("delete from employee where eno=?", emp.getEmpId()) > 0;
	}

	@Override
	public boolean updateEmp(Emp emp) {
		return template.update("update employee set ename=?,esal=? where eno=?", emp.getEmpName(), emp.getEmpSal(), emp.getEmpId())>0;
	}

	@Override
	public boolean createEmp(Emp emp) {
		return template.update("insert into employee(eno,ename,esal) values(?,?,?)",emp.getEmpId(), emp.getEmpName(), emp.getEmpSal())>0;
	}

}
