package model;

public class Ansatt {
	
	private int ansattID;
	private String forNavn;
	private String etterNavn;
	private String epost;
	private int telefon;
	private String brukerNavn;
	private String passord;
	
	public Ansatt(int ansattID,String brukerNavn,String forNavn,String etterNavn,String epost, int telefon, String passord){
		this.setAnsattID(ansattID);
		this.brukerNavn = brukerNavn;
		this.forNavn = forNavn;
		this.etterNavn = etterNavn;
		this.epost = epost;
		this.telefon = telefon;
		this.passord = passord;
	}

	public String getForNavn() {
		return forNavn;
	}

	public void setForNavn(String forNavn) {
		this.forNavn = forNavn;
	}

	public String getEtterNavn() {
		return etterNavn;
	}

	public void setEtterNavn(String etterNavn) {
		this.etterNavn = etterNavn;
	}

	public String getEpost() {
		return epost;
	}

	public void setEpost(String epost) {
		this.epost = epost;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getBrukerNavn() {
		return brukerNavn;
	}

	public void setBrukerNavn(String brukerNavn) {
		this.brukerNavn = brukerNavn;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public int getAnsattID() {
		return ansattID;
	}

	public void setAnsattID(int ansattID) {
		this.ansattID = ansattID;
	}
	public String toString(){
		return getForNavn() + " " + getEtterNavn();
	}

}
