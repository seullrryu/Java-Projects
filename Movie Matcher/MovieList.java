package project6;
/**
* Used to store all the movie objects. 
* Inherits all of its properties from an BST<Movie> class
* @author Seulmin Ryu 
*/
import java.util.Collections; 

/**
 * Class used to store all the Movie objects. 
 * Contain methods that returns the Movie objects with same title and actors. 
 * @author Seulmin Ryu
 */
public class MovieList extends BST<Movie> {
	//This constructor creates an empty MovieList object. 
	public MovieList() 
	{
		
	}
	
	/**
	 * Search through a list of Movie objects to find that matches the given keyword. 
	 * @param keyword the name of title to search 
	 * add the movie object that match to the "matchingTitlelist" MovieList object. 
	 * @return the matchingTitles MovieList object if titles match 
	 * @return null if title parameter is null or empty
	 */
	public MovieList getMatchingTitles(String keyword) throws IllegalArgumentException {		
		
		MovieList matchingTitlelist = new MovieList();
		//Check if keyword is valid
		if (keyword == null || keyword.equals("")) {
			return null; 
		}
		
		//Make it all lower case to make it case insensitive
		String actualKeyword = keyword.toLowerCase();
		
		//Iterate through BST of Movies, if title matches that given in parameter, add it to the MovieList object. 
		for (Movie m: this) {
			if(m.getTitle().toLowerCase().contains(actualKeyword)) {
				matchingTitlelist.add(m);
			}
		} 
		
		//If empty, return null
		if(matchingTitlelist.size() == 0) {
			return null;
		}
		
		//Sorting in natural order
		return matchingTitlelist;
	}
	
	/**Search through a list of Movie objects to find that matches the given keyword. 
	 * @param keyword the name of actors to search 
	 * add the movie object that match to the "matchingActorlist" MovieList object. 
	 * @return null if actor parameter is null or empty, or if 
	 * @return matchingActors MovieList object
	 */
	public MovieList getMatchingActor(String keyword) {
		MovieList matchingActorlist = new MovieList();
		
		//Checking if keyword is valid
		if (keyword == null || keyword.equals("")) {
			return null; 
		}
		
		//System.out.println(this.size());
		//Make it all lower case to make it case insensitive
		String actualKeyword = keyword.toLowerCase();
		
		//Iterate through ArrayList of Movies, if Actor name matches that of given in parameter, add it to the MovieList object matchingActorlist. 
		for (Movie m: this) {
			for(Actor a: m.ActorList) {
				if (a != null) {
					if(a.getName().toLowerCase().contains(actualKeyword)) {				
						matchingActorlist.add(m);
					}
				}
			}
		}
		
		//If empty, return null
		if(matchingActorlist.size() == 0) {
			return null;
		}
		
		//Sorting in natural order		
		return matchingActorlist;
	}
	
	@Override
	public String toString() {
		String string = ""; 
		for (Movie m : this) {
			string = string + m.toString() + ";"; 
		}
		return string; 
	}
}
