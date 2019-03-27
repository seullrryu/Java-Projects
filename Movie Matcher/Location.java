package project6;
/**
 * Stores the name of the location and fun facts associated with them. 
 * Provides a constructor that takes two string parameters that represent
 * the location and fun fact. 
 * Constructor throws an IllegalArgumentException if the location string is 
 * null or empty. 
 * @author Seulmin Ryu 
 */
public class Location implements Comparable<Location> {
	private String loc; 
	private String funfact;
	
	/**
	 * Constructs an Location object with location and fun fact values.  
	 * @param loc location used to create Location object.
	 * @param funfact fun fact used to create Location object. 
	 * @throws IllegalArgumentException if location parameter is null or empty
	 */
	public Location(String loc, String funfact) throws IllegalArgumentException {
		this.funfact = funfact;
		
		//Validate loc parameter
		if (loc == null) {
			throw new IllegalArgumentException("Error! Location is null."); 
		}
		else if (loc == "") {
			throw new IllegalArgumentException("Error! Location is blank."); 
		}
		else {
			this.loc = loc;
		}	
	}
	
	/**
	 * Returns the location (Getter) 
	 * @return the location name. 
	 */
	public String getlocation() {
		return loc;
	}
	
	/**
	 * Returns a fun fact (Getter) 
	 * @return the fun fact. 
	 */
	public String getFunFact() {
		return funfact; 
	}
	
	@Override
	public String toString() {
		String locations = ""; 
		locations = locations + "\n" + getlocation();
		return locations; 
	}
	
	@Override 
	public int compareTo(Location l) {
		return this.loc.compareTo(l.loc); 
	}
}
