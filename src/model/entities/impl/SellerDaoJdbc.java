package model.entities.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJdbc implements SellerDAO {
	
	private Connection conn;
	

	public SellerDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT \r\n" + 
					"	s.*,\r\n" + 
					"    dp.Name as DepName\r\n" + 
					"FROM \r\n" + 
					"	seller s\r\n" + 
					"JOIN department dp\r\n" + 
					"ON s.DepartmentId = dp.Id\r\n" + 
					"WHERE s.Id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			//se retornar um valor na consulta
			if(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setDepartment(dep);
				
				return seller;
			}
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		
		// nenhum usuário encontrado
		return null;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
