package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDB {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		DBConnection db = new DBConnection();
		ArrayList<Rom> r = new ArrayList<Rom>();
		r = db.getAvailableRooms();
		System.out.println(r.toString());
		
	}
}
