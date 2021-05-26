package application;

import java.util.ArrayList;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		System.out.println("==========================");

		Department dep = new Department(1, null);

		SellerDAO sd = DaoFactory.createSellerDao();

		List<Seller> list = new ArrayList<>();

		list = sd.findByDepartment(dep);

		for (Seller s : list) {
			System.out.println(s);
		}

	}

}
