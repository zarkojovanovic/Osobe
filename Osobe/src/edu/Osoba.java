package edu;

public class Osoba implements Comparable<Osoba> {
	
	private String ime;
	private String prezime;
	private int godiste;
	static boolean isLeksikografski;
	
	
	public Osoba(String ime, String prezime, int godiste) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.godiste = godiste;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public int getGodiste() {
		return godiste;
	}
	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}
	public static boolean isLeksikografski() {
		return isLeksikografski;
	}
	public static void setLeksikografski(boolean isLeksikografski) {
		Osoba.isLeksikografski = isLeksikografski;
	}
	
	
	
	@Override
	public String toString() {
		return ime +  ", " + prezime + ", " + godiste;
	}

	@Override
	public int compareTo(Osoba o) {
		if(isLeksikografski()) {
			return (this.ime + this.prezime).compareTo(o.ime + o.prezime);
		}
		return Integer.compare(this.godiste, o.godiste);
	}
	
	

}
