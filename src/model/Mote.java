package model;

import java.util.ArrayList;
/**
 * 
 * @author chrpeter
 * @author staurset
 * @version 1.0.0
 * 
 * Klasse for mote. Denne klassen arver fra superklassen Avtale
 */
public class Mote extends Avtale {
	ArrayList<Ansatt> deltakere;
	public Mote(){
		
	}
	public ArrayList<Ansatt> getDeltakere() {
		return deltakere;
	}
	public void setDeltakere(ArrayList<Ansatt> deltakere) {
		this.deltakere = deltakere;
	}
	

}
