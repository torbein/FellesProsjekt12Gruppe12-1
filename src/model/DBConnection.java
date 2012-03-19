package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This class establish the connection to the database 
 * and handles all the queries to the database. 
 * @author Torbein, Erik.
 */
public class DBConnection {
	
	private String mysqlDriver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://mysql.stud.ntnu.no/torbein_gruppe12_db";
	private Connection conn;
	
	private PreparedStatement makeMeetingStmt, makeAppointmentStmt, fetchUserStmt, 
							  fetchMeesageStmt, sendMessageStmt, getAvailableRomStmt,
							  legalPassword,myAppointments, getRoom, getEmployeeStmt,
							  getEmployeesStmt, searchEmployeesStmt, removeAppointment;
	
	/**
	 * Creates the connection to the database.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */	
	public DBConnection() throws ClassNotFoundException, SQLException{
		Class.forName(mysqlDriver);
		String username = "torbein_gruppe12"; 
		String password = "gruppe12";
		conn = DriverManager.getConnection(url,username,password);
		preparedStatement();
		System.out.println("connection!");
	}
	
	/**
	 * Prepares the statements that is needed for the api, 
	 * queries go faster and secures against SQL-Injections.
	 */
	private void preparedStatement(){
		try {
			//For employee
            getEmployeesStmt = conn.prepareStatement("SELECT * FROM Ansatt");
            searchEmployeesStmt = conn.prepareStatement("SELECT * FROM Ansatt WHERE Fnavn LIKE ? OR Enavn LIKE ?");
			//meetings & appointment
			makeMeetingStmt = conn.prepareStatement("INSERT INTO Mote VALUES(?,?,?,?,?,?,?)");
			makeAppointmentStmt = conn.prepareStatement("INSERT INTO Mote VALUES(?,?,?,?,?,?,?,?,?,?)");
			fetchUserStmt = conn.prepareStatement("SELECT * FROM Ansatt WHERE Brukernavn=?");
			fetchMeesageStmt = conn.prepareStatement("SELECT * Melding");
			sendMessageStmt= conn.prepareStatement("");
			getAvailableRomStmt = conn.prepareStatement("SELECT * FROM MoteRom");
			legalPassword = conn.prepareStatement("SELECT * FROM Ansatt WHERE Brukernavn=?");
			myAppointments = conn.prepareStatement("SELECT * FROM Mote WHERE ErAvtale = 1 AND AnsattNR = ?");
			getRoom = conn.prepareStatement("SELECT * FROM MoteRom WHERE RomNR = ?");
			removeAppointment = conn.prepareStatement("DELETE FROM Mote WHERE AvtaleID = ?");
		} catch (Exception e) {

		}
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 * Returns all the employees that match one the strings.
	 */
    public ArrayList<Ansatt> getEmplyoees(String text) {
        ArrayList<Ansatt> employees = new ArrayList<Ansatt>();
        try {
            text = "%" + text + "%";
            searchEmployeesStmt.setString(1, text);
            searchEmployeesStmt.setString(2, text);
            ResultSet rs = searchEmployeesStmt.executeQuery();
            while (rs.next()) {
                int ansattID = rs.getInt("AnsattNR");
            	String brukernavn = rs.getString("Brukernavn");
            	String fnavn = rs.getString("FNavn");
                String enavn = rs.getString("ENavn");
                String epost = rs.getString("Epost");
                int telefon = rs.getInt("Telefon");
                String passord = rs.getString("Passord");
                employees.add(new Ansatt(ansattID, brukernavn, fnavn, enavn, epost,telefon,passord));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    /**
     * 
     * @return
     * Returns all the employees in the database
     */
	public ArrayList<Ansatt> getEmployees(){
        ArrayList<Ansatt> ansatte = new ArrayList<Ansatt>();
        try {
            ResultSet rs = getEmployeesStmt.executeQuery();
            while (rs.next()) {
                int ansattID = rs.getInt("AnsattNR");
            	String brukernavn = rs.getString("Brukernavn");
            	String fnavn = rs.getString("FNavn");
                String enavn = rs.getString("ENavn");
                String epost = rs.getString("Epost");
                int telefon = rs.getInt("Telefon");
                String passord = rs.getString("Passord");
                
                ansatte.add(new Ansatt(ansattID, brukernavn, fnavn, enavn, epost,telefon,passord));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ansatte;
	}
	/**
	 * Returns the employee matching the given id.
	 * @param id
	 * @return
	 */
	public Ansatt getEmployee(String username) {
        Ansatt ansatt = null;
        try {
            fetchUserStmt.setString(1,username);
            ResultSet rs = fetchUserStmt.executeQuery();
            while (rs.next()) {
                int ansattID = rs.getInt("AnsattNR");
            	String brukernavn = rs.getString("Brukernavn");
            	String fnavn = rs.getString("FNavn");
                String enavn = rs.getString("ENavn");
                String epost = rs.getString("Epost");
                int telefon = rs.getInt("Telefon");
                String passord = rs.getString("Passord");
                ansatt = new Ansatt(ansattID,brukernavn,fnavn,enavn,epost,telefon,passord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ansatt;
    }
	
	/**
	 * Gets a list of all the available rooms.
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Rom> getAvailableRooms() throws SQLException{
		ArrayList<Rom> availableRooms = new ArrayList<Rom>();
		ResultSet rs = getAvailableRomStmt.executeQuery();
		Rom rom = null;
		while(rs.next()){
			if(rs.getInt("Ledig") == 1){
				int romNR = rs.getInt("RomNR");
				String sted = rs.getString("Sted");
				String beskrivelse = rs.getString("Beskrivelse");
				int kapasitet = rs.getInt("Kapasitet");
				rom = new Rom(romNR, sted, kapasitet, beskrivelse);
				availableRooms.add(rom);
			}
		}
		return availableRooms;
	}
	
	/**
	 * Checks if the given username and password is legal.
	 * @param userName
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean checkIfPasswordIsLegal(String userName, String password) throws SQLException{
		legalPassword.setString(1, userName);
		ResultSet rs = legalPassword.executeQuery();
		String passw = "";
		while (rs.next()) {
			passw = rs.getString("Passord");
		}
		if(passw.equals(password)){
			return true;
		}
		return false;
	}
	
	/**
	 * Makes an appointment for an employee. 
	 * @param avtale
	 * @throws SQLException
	 */
	public void makeAppointment(Avtale avtale) throws SQLException{
		String startTid;
		if (avtale.getStartTime() < 10){
			startTid = "0"+avtale.getStartTime();			
		}
		else{
			startTid = ""+avtale.getStartTime();
		}
		if(avtale.getStartMinutt()<10){
			startTid += ":0" + avtale.getStartMinutt() + ":00"; 
		}else{
			startTid += ":" + avtale.getStartMinutt() + ":00"; 
		}
		
		String sluttTid;
		if (avtale.getSluttTime() < 10){
			sluttTid = "0"+avtale.getSluttTime();			
		}
		else{
			sluttTid = ""+avtale.getSluttTime();
		}
		if(avtale.getSluttMinutt()<10){
			sluttTid += ":0" + avtale.getSluttMinutt() + ":00"; 
		}else{
			sluttTid += ":" + avtale.getSluttMinutt() + ":00"; 
		}
		
		String startDato;
		
		String maaned;
		if (avtale.getMaaned() < 10){
			maaned = "0"+avtale.getMaaned();			
		}
		else
		{
			maaned = ""+avtale.getMaaned();
		}
		
		String dag = "";
		if (avtale.getDag() < 10){
			dag = "0"+avtale.getDag();			
		}
		else
		{
			dag = ""+avtale.getDag();
		}
		
		startDato = "" + avtale.getAar() + "-" + maaned + "-" + dag + "";
		System.out.println(startTid);
		makeAppointmentStmt.setInt(1,0);
		makeAppointmentStmt.setString(2,startTid);
		makeAppointmentStmt.setString(3,sluttTid);
		makeAppointmentStmt.setString(4,avtale.getTittel());
		makeAppointmentStmt.setString(5,avtale.getBeskrivelse());
		makeAppointmentStmt.setString(6,avtale.getSted());
		makeAppointmentStmt.setInt(7,avtale.getMoteRom().getRomNr());
		makeAppointmentStmt.setInt(8, avtale.getAnsattNR());		
		makeAppointmentStmt.setString(9,startDato);
		makeAppointmentStmt.setInt(10, 1);
		
		makeAppointmentStmt.executeUpdate();
	}
	/**
	 * Gets all appointments for this employee.
	 * @param ansatt
	 * @return ArrayList<Avtale>
	 * @throws SQLException
	 */
	public ArrayList<Avtale> getMyAppointments(Ansatt ansatt) throws SQLException{
		ArrayList<Avtale> myAppointmentsList = new ArrayList<Avtale>();
		myAppointments.setInt(1, ansatt.getAnsattID());
		ResultSet rs = myAppointments.executeQuery();
		ResultSet rsRom;
		while(rs.next()){
			
			getRoom.setInt(1,rs.getInt("MoteRom"));
			rsRom = getRoom.executeQuery(); 
			rsRom.next();
			
			int romNR = rsRom.getInt("RomNR");
			String sted = rsRom.getString("Sted");
			String beskrivelse = rsRom.getString("Beskrivelse");
			int kapasitet = rsRom.getInt("Kapasitet");
			Rom rom = new Rom(romNR, sted, kapasitet, beskrivelse);
			
			myAppointmentsList.add(new Avtale(rs.getString("Beskrivelse"),
			rom,rs.getString("Tittel"), rs.getInt("AnsattNR"), rs.getString("StartDato"), rs.getString("StartTid"), rs.getString("SluttTid")));
		}
		return myAppointmentsList;
	}
}
