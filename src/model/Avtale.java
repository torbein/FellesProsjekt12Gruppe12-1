package model;

import java.awt.image.DataBufferFloat;

public class Avtale {
	private String beskrivelse;
	private String sted;
	private String tittel;
	private int ansattNR;
	private Rom moteRom;
	private int dag;
	private int maaned;
	private int aar;
	private int startTime;
	private int startMinutt;
	private int sluttTime;
	private int sluttMinutt;
	
	/**
	 * This constructor is used when making an appointment without a designated meeting room.  
	 * @param beskrivelse
	 * @param sted
	 * @param tittel
	 * @param ansattNR
	 * @param dag
	 * @param maaned
	 * @param aar
	 * @param startTime
	 * @param startMinutt
	 * @param sluttTime
	 * @param sluttMinutt
	 */
	public Avtale(String beskrivelse, String sted, String tittel, int ansattNR,
			int dag, int maaned, int aar,int startTime,int startMinutt,int sluttTime,int sluttMinutt){
		this.beskrivelse = beskrivelse;
		this.sted = sted;
		this.tittel = tittel;
		this.ansattNR = ansattNR;
		this.dag = dag;
		this.maaned = maaned;
		this.aar = aar;
		this.startTime = startTime;
		this.startMinutt = startMinutt;
		this.sluttTime = sluttTime;
		this.sluttMinutt = sluttMinutt;
		
	}
	
	public Avtale(String beskrivelse, Rom moteRom, String tittel, int ansattNR,String dato, String startTid,String sluttTid){
		this.beskrivelse = beskrivelse;
		this.moteRom = moteRom;
		this.tittel = tittel;
		this.ansattNR = ansattNR;
		this.aar = Integer.parseInt(dato.substring(0,4));
		this.maaned = Integer.parseInt(dato.substring(5,7));
		this.dag = Integer.parseInt(dato.substring(8,10));
		this.startTime =  Integer.parseInt(startTid.substring(0,2));
		this.startMinutt =  Integer.parseInt(startTid.substring(3,5));
		this.sluttTime = Integer.parseInt(sluttTid.substring(0,2));
		this.sluttMinutt = Integer.parseInt(sluttTid.substring(3,5));
	}
	/**
	 * This constructor is used when making an appointment with a designated meeting room.
	 * @param beskrivelse
	 * @param moteRom
	 * @param tittel
	 * @param ansattNR
	 * @param dag
	 * @param maaned
	 * @param aar
	 * @param startTime
	 * @param startMinutt
	 * @param sluttTime
	 * @param sluttMinutt
	 */
	public Avtale(String beskrivelse, Rom moteRom, String tittel, int ansattNR,
			int dag, int maaned, int aar,int startTime,int startMinutt,int sluttTime,int sluttMinutt){
		this.beskrivelse = beskrivelse;
		this.sted = sted;
		this.tittel = tittel;
		this.ansattNR = ansattNR;
		this.dag = dag;
		this.maaned = maaned;
		this.aar = aar;
		this.startTime = startTime;
		this.startMinutt = startMinutt;
		this.sluttTime = sluttTime;
		this.sluttMinutt = sluttMinutt;
		this.moteRom = moteRom;
	}
	
	public String getBeskrivelse() {
		return beskrivelse;
	}
	
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
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
		if(maaned > 0 && maaned < 13){
			this.maaned = maaned;
		}
	}
	
	public int getAar() {
		
		return aar;
	}
	
	public void setAar(int aar) {
		this.aar = aar;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int startTime) {
		if(startTime > 0 && startTime < 24){
			this.startTime = startTime;
		}
	}
	
	public int getStartMinutt() {
		return startMinutt;
	}
	
	public void setStartMinutt(int startMinutt) {
		if(startMinutt > 0 && startMinutt < 61){
			this.startMinutt = startMinutt;
		}
	}
	
	public int getSluttTime() {
		return sluttTime;
	}
	
	public void setSluttTime(int sluttTime) {
		if(startTime > 0 && startTime < 24){
			this.sluttTime = sluttTime;
		}
	}	
	
	public int getSluttMinutt() {
		return sluttMinutt;
	}
	
	public void setSluttMinutt(int sluttMinutt) {
		if(startMinutt > 0 && startMinutt < 61){
			this.sluttMinutt = sluttMinutt;
		}
	}
	
	public void setTittel(String tittel){
		this.tittel = tittel;
	}
	public String getTittel(){
		return tittel;
	}

	public String getSted() {
		return sted;
	}

	public void setSted(String sted) {
		this.sted = sted;
	}

	public Rom getMoteRom() {
		return moteRom;
	}

	public void setMoteRom(Rom moteRom) {
		this.moteRom = moteRom;
	}

	public int getAnsattNR() {
		return ansattNR;
	}

	public void setAnsattNR(int ansattNR) {
		this.ansattNR = ansattNR;
	}
	
	@Override
	public String toString() {
		return dag + "/" + maaned +"/"+ aar + " - " + tittel +
		" - " + startTime +":"+ startMinutt + " - " + sluttTime +":"+ sluttMinutt ;
	}
	
}
