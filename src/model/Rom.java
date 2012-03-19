package model;

public class Rom {
	
	
	private int romNr;
	private String sted;
	private int kapasitet;
	private String beskrivelse;
	
	public Rom(int romNR, String sted, int kapasitet,String beskrivelse){
		this.romNr = romNR;
		this.sted = sted;
		this.kapasitet = kapasitet;
		this.beskrivelse = beskrivelse;
	}
	
	public String getSted() {
		return sted;
	}

	public void setSted(String sted) {
		this.sted = sted;
	}

	public int getKapasitet() {
		return kapasitet;
	}

	public void setKapasitet(int kapasitet) {
		this.kapasitet = kapasitet;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	@Override
	public String toString() {
		return "RomNR: " + romNr + " Kap: " + kapasitet  +" Sted: "+ sted;
	}
	public int getRomNr() {
		return romNr;
	}
	
	public void setRomNr(int romNr) {
		this.romNr = romNr;
	}

}
