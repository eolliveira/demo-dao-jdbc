package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		DepartmentDAO dd = DaoFactory.createDepartmentDao();

		SellerDAO sd = DaoFactory.createSellerDao();
		
		Department dep = dd.findById(1);
		
		Seller seller = new Seller("Matheus Felipe", "matheuso@", sdf.parse("07/07/2020"), 1000.0, dep);
		sd.insert(seller);
		
		

	}

}
