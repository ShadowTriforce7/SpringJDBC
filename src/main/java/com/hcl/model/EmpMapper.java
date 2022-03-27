package com.hcl.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmpMapper implements RowMapper<Emp> {

	@Override
	public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
		Emp e1 = new Emp();
		e1.setEmpId(rs.getInt("eno"));
		e1.setEmpName(rs.getString("ename"));
		e1.setEmpSal(rs.getInt("esal"));
		
		return e1;
	}

}
