package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
	private int dag = 0;
	private int maaned = 0;
	private int aar = 0;
	private int dagerImaaned = 0;
	private SimpleDateFormat curDate;
	private static final String DATE_FORMAT_NOW ="yyyyMMdd";
	

	
	public Date(){
		getDaysInMonth();
		getCurrentDate();
		
	}
	public int getDag() {
		return dag;
	}
	public void setDag(int dag) {
		this.dag = dag;
	}
	public int getMaaned() {
		return maaned;
	}
	public void setMaaned(int maaned) {
		this.maaned = maaned;
	}
	public int getAar() {
		return aar;
	}
	public void setAar(int aar) {
		this.aar = aar;
	}
	
	public boolean isLeapYear(int aar) {
		if (aar < 0) {
			return false;
		}
		if (aar % 400 == 0) {
			return true;
		} else if (aar % 100 == 0) {
			return false;
		} else if (aar % 4 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isLeapYear() {
		if (aar < 0) {
			return false;
		}
		if (aar % 400 == 0) {
			return true;
		} else if (aar % 100 == 0) {
			return false;
		} else if (aar % 4 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void getDaysInMonth(){
		if (maaned == 1 || maaned == 3 || maaned == 5 || maaned == 7 || maaned == 8 || maaned == 10 || maaned == 12){
			dagerImaaned = 31;
		}
		else if (maaned == 4 || maaned == 6 || maaned == 9 || maaned == 11){
			dagerImaaned = 30;
		}
		else if (maaned == 2){
			if(isLeapYear()){
				dagerImaaned = 28;
			}
			else {
				dagerImaaned = 29;
			}
		}
		else {
			dagerImaaned =-1;
			}
	}
	
	public int getDaysInMonth(int maaned){
		if (maaned == 1 || maaned == 3 || maaned == 5 || maaned == 7 || maaned == 8 || maaned == 10 || maaned == 12){
			return dagerImaaned = 31;
		}
		else if (maaned == 4 || maaned == 6 || maaned == 9 || maaned == 11){
			return dagerImaaned = 30;
		}
		else if (maaned == 2){
			if(isLeapYear()){
				return dagerImaaned = 28;
			}
			else {
				return dagerImaaned = 29;
			}
		}
		else{
			return dagerImaaned =-1;
		}
	}
	
	public boolean checkDate() {
		if(dagerImaaned== -1){
			return false;
		}
		if(dag >0 && dag <= dagerImaaned) {
			return true;
		}
		return false;
	}
	
	public void getCurrentDate(){
		String date;
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    date = sdf.format(cal.getTime());
	    System.out.println(date);
	    String s= "";
	    for (int i = 0; i < date.length(); i++) {
	    	if(i == 4){
	    		setAar(Integer.parseInt(s));
	    		System.out.println(s);
	    		s = "";
	    	}
	    	if(i == 6){
	    		setMaaned(Integer.parseInt(s));
	    		System.out.println(s);
	    		s="";
	    	}
	    	s+= date.charAt(i);
		}
	    setDag(Integer.parseInt(s));
	    
	}
	
	public static void main(String[] args) {
		new Date();
		
		
	}
}

