package model;

import java.util.ArrayList;

public class Melding {
	private String beskjed;
	private Ansatt sender;
	private ArrayList<Ansatt> mottakere;
	private Mote mote;
	
	public Melding(String beskjed, Ansatt sender, ArrayList<Ansatt> mottakere){
		this.beskjed = beskjed;
		this.sender = sender;
		this.mottakere = mottakere;
	}

	public String getBeskjed() {
		return beskjed;
	}

	public void setBeskjed(String beskjed) {
		this.beskjed = beskjed;
	}

	public Ansatt getSender() {
		return sender;
	}

	public void setSender(Ansatt sender) {
		this.sender = sender;
	}

	public ArrayList<Ansatt> getMottakere() {
		return mottakere;
	}

	public void setMottakere(ArrayList<Ansatt> mottakere) {
		this.mottakere = mottakere;
	}

	public Mote getMote() {
		return mote;
	}

	public void setMote(Mote mote) {
		this.mote = mote;
	}
}
