package model.entities.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
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
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				
				return seller;
			}
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		
		// nenhum usu�rio encontrado
		return null;
	}
	
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(dep);
		
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
	
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT \r\n" + 
					"	s.*,\r\n" + 
					"    d.Name as DepName\r\n" + 
					"FROM \r\n" + 
					"	seller s\r\n" + 
					"INNER JOIN department d\r\n" + 
					"	ON s.DepartmentId = d.Id\r\n" + 
					"ORDER BY Name");
			rs = ps.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				//verfica se a chave(Id) solicitado ja existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
				
			}
			return list;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSEt(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT \r\n" + 
					"	s.*,\r\n" + 
					"    d.Name as DepName\r\n" + 
					"FROM \r\n" + 
					"	seller s\r\n" + 
					"INNER JOIN department d\r\n" + 
					"	ON s.DepartmentId = d.Id\r\n" + 
					"WHERE d.Id = ?\r\n" + 
					"ORDER BY DepName");
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				//verfica se a chave(Id) solicitado ja existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
				
			}
			return list;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSEt(rs);
		}
	}

	
}
