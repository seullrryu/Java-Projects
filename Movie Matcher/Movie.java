package project6;
import java.util.ArrayList; 
/**
 * Stores the title, year of release, list of SF locations in which the movie was filmed (together with associated fun facts), director
 * writer, list of up to three actors.
 * Inherits all of its properties from Comparable<Movie>
 * @author Seulmin Ryu 
 */
public class Movie implements Comparable<Movie> {
		/**
		 * Store title, year of release, list of SF locations in which the movie was filmed, director, writer, list of up to three actors 
		 * Store objects in ArrayList so MovieList can use it 
		 */
		private String title; 
		private int year; 
		ArrayList <Location> Locationlist = new ArrayList<Location>(); 
		String director; 
		String writer; 
		Actor ActorList [] = new Actor[3]; 
		
		/**
		 * Constructs Movie objects with title and year values
		 * @param title title of the movie
		 * @param year year of the release of the movie
		 * @throws IllegalArgumentException if title is empty, or year values are not in range. 
		 */
		public Movie(String title, int year) throws IllegalArgumentException {
			//Validate Parameters
			if (title == null || title == "") {
				throw new IllegalArgumentException("Error! Title is empty!"); 
			}
			if (year < 1915) {
				throw new IllegalArgumentException("Error! Year is too small"); 
			}
			else if (year > 2018) {
				throw new IllegalArgumentException("Error! Year is too big"); 
			}
			Locationlist = new ArrayList<Location>(); 
			this.title = title; 
			this.year = year; 
		}
		
		/**
		 * Constructs Movie objects with title, year, director, writer, and three actors starring in the movie. 
		 * @param title title of the movie 
		 * @param year year of release
		 * @param director director of the movie
		 * @param writer writer of the script of the movie
		 * @param actor1 actor1 of the movie
		 * @param actor2 actor2 of the movie 
		 * @param actor3 actor3 of the movie 
		 * @throws IllegalArgumentException if title is empty, year values are not in range, or first actor value is empty. 
		 */
		public Movie(String title, int year, String director, String writer, Actor actor1, Actor actor2, Actor actor3) throws IllegalArgumentException {
			//Validation
			if (title == null || title == "") {
				throw new IllegalArgumentException("Error! Title is empty!"); 
			}
			if (year < 1915) {
				throw new IllegalArgumentException("Error! Year is too old"); 
			}
			else if (year > 2018) {
				throw new IllegalArgumentException("Error! Year is too big"); 
			}
		
			if (actor1 == null) {
				throw new IllegalArgumentException("Error! There is no actor given."); 
			}
			
			//Assign values 
			Locationlist = new ArrayList<Location>(); 
			this.title = title; 
			this.year = year; 
			this.director = director; 
			this.writer = writer; 
			ActorList[0] = actor1; 
			ActorList[1] = actor2; 
			ActorList[2] = actor3;
		}
		
		/**
		 * Returns the title of movie. 
		 * @return title of movie object. 
		 */
		public String getTitle() {
			return title;
		}
		
		/**
		 * Returns the year of the movie release
		 * @return year of the movie object. 
		 */
		public int getYear() {
			return year;
		}
		
		
		/**
		 * Adds a given location to the list of filming locations for the current Movie Object.
		 * @param loc location of the movie object
		 * @throws IllegalArgumentException if location is null. 
		 */
		public void addLocation(Location loc) throws IllegalArgumentException {
			if (loc == null) {
				throw new IllegalArgumentException("Error!");
			}
			else {
				//Add location objects to the ArrayList LocationList
				Locationlist.add(loc);
			}
		}
		
		/**
		 * Gets the name of Actors in ActorList array, formats the name of Actors 
		 * @return string allActors
		 */
		public String formatActors() {
			String allActors = ""; 
			if (ActorList[0] != null) {
				allActors +=  "" + ActorList[0].getName();; 
			}
			if (ActorList[1] != null) {
				allActors +=  ", " + ActorList[1].getName(); 
			}
			if (ActorList[2] != null) {
				allActors +=  ", " + ActorList[2].getName(); 
			}
			return allActors; 
		}
		
		/**
		 * Gets the name of locations in Location object, formats location names and fun facts 
		 * @return string allLocations 
		 */
		public String formatLocations() {
			String allLocations = "";
			for (Location l : this.Locationlist) {
				if (l.getFunFact() == null || l.getFunFact().trim().isEmpty()) {
					allLocations = allLocations + l.getlocation() + "\n"; 
				}
				else {
					allLocations = allLocations + l.getlocation() + " " + "(" + l.getFunFact() + ")" + "\n";
				}
			}
			return allLocations; 
		}
		
		/**
		 * Compares the year value of the Movie object constructed above and 
		 * Movie object thrown in the method. 
		 * @param Movie movie Movie object to compare
		 * @return -1 if parameter movie object is newer 
		 * @return 1 if parameter movie object is older
		 * @return the comparison value if they are released in the same year. 
		 */
		@Override
		public int compareTo(Movie movie) {
			if (this.getYear() < movie.getYear()) {
				return -1; 
			}
			else if (this.getYear() > movie.getYear()) {
				return 1; 
			}
			else {
				return (this.getTitle().compareToIgnoreCase(movie.getTitle()));
			}
		}
		
		@Override
		public boolean equals(Object mov) {
			if (!(mov instanceof Movie)) {
				return false; 
			}
			else {	
				if ((this.title.compareToIgnoreCase(((Movie)mov).title) == 0) && ((this.year) == ((Movie)mov).year)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		public String toString() 
		{	
			String total = getTitle() + " " + "(" + year + ")" + "\n" + "------------------------------------" + "\n" + "Director: \t" + director + "\n" + "Writer: \t" + writer + "\n" + 
		"Starring: \t"  + formatActors() + "\n" + 
		"Filmed on location at:" + "\n" +  formatLocations() + "\n";
			return total; 
		}
}
