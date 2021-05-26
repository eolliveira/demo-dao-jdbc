package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDAO sd = DaoFactory.createSellerDao();
		Seller seller = sd.findById(3);
		
		System.out.println(seller);
		
		
		DepartmentDAO depDAO = DaoFactory.createDepartmentDao();
		Department dep = depDAO.findById(2);
		
		System.out.println(dep);
		
	}

}
