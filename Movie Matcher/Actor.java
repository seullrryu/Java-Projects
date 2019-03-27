package project6;
/**
 * Store the name of an Actor and provide a one parameter constructor
 * that throws an IllegalArgumentException if it's called with 
 * null or empty string parameter. 
 * @author Seulmin Ryu 
 */
public class Actor {
	private String name; 
	
	/**
	 * Constructs a new Actor object with name value.
	 * @param name name to be used to create an Actor object. 
	 * @throws IllegalArgumentException if name is null or empty
	 */
	public Actor(String name) throws IllegalArgumentException {
		//Validate name parameter. 
		if ((name == null) || (name == "")) {
			throw new IllegalArgumentException("Error! Name is empty."); 
		}
		else {
			this.name = name;
		}	
	}
	/**
	 * Returns the name of the Actor (Getter) 
	 * @return the name of the Actor. 
	 */
	public String getName() {
		return name;
	}
	
	@Override 
	public String toString() {
		String actors = getName(); 
		return actors; 
	}
}
