package RangierbahnhofMVC;

/**
 * Die Klasse Zug repräsentiert einen Zug. Jeder Zug hat eine ID.
 * 
 * @author katerina milenkovski und daniel biedermann
 *
 */
public class Zug {
	private int id;

	/**
	 * Konstruktor
	 * 
	 * @param id
	 */
	public Zug(int id) {
		this.id = id;
	}

	/**
	 * Getter der ID
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}
}
