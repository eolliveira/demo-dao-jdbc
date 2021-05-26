package model.dao;

import db.DB;
import model.entities.impl.DepartmentDaoJdbc;
import model.entities.impl.SellerDaoJdbc;

public class DaoFactory {

	public static SellerDAO createSellerDao() {
		return new SellerDaoJdbc(DB.getConnection());
	}
	
	public static DepartmentDAO createDepartmentDao() {
		return new DepartmentDaoJdbc(DB.getConnection());
	}
}
