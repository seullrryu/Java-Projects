package project6;

import java.io.File;
import java.util.ArrayList; 
import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.lang.IllegalArgumentException; 

/**
 * Class used to opening and reading the data file
 * It is responsible for opening and reading the data files, obtaining user input, performing some data validation 
 * and handling all errors that may occur.
 * @author Seulmin Ryu
 */
public class SFMovieData {
	/**
	 * The main() method of this program. 
	 * @param args array of Strings provided on the command line when the program is started. 
	 * First string should be the name of the input file containing all the movie data. 
	 */
	public static void main(String[] args)  {
		MovieList data = new MovieList();	
		
		//Verify that command line argument is not empty. 
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		
		//verify that command line argument contains a name of an existing file.
		File file = new File(args[0]); //"Film_Locations_in_San_Francisco (1).csv"); 

		//If file doesn't exist, print error message and exit program.
		if (!(file.exists())) {
			System.err.println("Error: the file does not exist.");
			System.exit(1);
		}
		
		//If file can't be read, print error message and exit program. 
		if (!(file.canRead())) {
			System.err.println("Error: the file cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//Open the file for reading 
		try {
			Scanner in = new Scanner(file);
			in.nextLine();
			
			/*
			 * Iterate through the file, store the respective information into the classes. 
			 * First check whether the data set is valid with length of row, and contains title, year of release, location, and actor1.  
			 * Assign each row to their respective data fields. 
			 * If size is 9, only has one actor, size is 10, has two actors, size is 11, has three actors.
			 */ 
			while (in.hasNextLine()) {
				String line = in.nextLine();
				ArrayList<String> list = splitCSVLine(line);
				
				if (list.size() > 5 && !(list.get(0).equals("") || list.get(1).equals("") || list.get(2).equals("") || list.get(8).equals("")) )
				{
					//If there is only one actor
					if (list.size() == 9) {
						//Store information to respective classes.
						String title = list.get(0);
						int year = Integer.parseInt(list.get(1)); 
						Location location = new Location(list.get(2), list.get(3));
						String director = list.get(6); 
						String writer = list.get(7); 
						Actor actor1 = new Actor(list.get(8)); 
						Actor actor2 = null; 
						Actor actor3 = null;
						
						try {
							Movie movie = new Movie(title, year, director, writer, actor1, actor2, actor3);
							movie.Locationlist.add(location);
							
							
							//If the same movie is already there, add on the locations to the most recent movie dataset in MovieList object. 
							if (data.size() != 0 && data.get(movie)!=null) {
								data.get(movie).Locationlist.add(location);
							}
							
							//If first time introducing a movie, add it to the MovieList object. 
							else {
								data.add(movie); 
							}
						}
						catch (IllegalArgumentException nomovie) {
							System.err.print("No movie!");
						}
					}
					
					//Two actors
					else if (list.size() == 10) {
						//Store information to respective classes.
						String title = list.get(0);
						int year = Integer.parseInt(list.get(1)); 
						Location location = new Location(list.get(2), list.get(3));
						String director = list.get(6); 
						String writer = list.get(7); 
						Actor actor1 = new Actor(list.get(8)); 
						Actor actor2 = new Actor(list.get(9)); 
						Actor actor3 = null; 
						
						try {
							Movie movie = new Movie(title, year, director, writer, actor1, actor2, actor3);
							movie.Locationlist.add(location);
							
							//If the same movie is already there, add on the locations to the most recent movie dataset in MovieList object. 
							if (data.size() != 0 && data.get(movie)!=null) {
								data.get(movie).Locationlist.add(location);
							}
							//If first time introducing a movie, add it to the MovieList object. 
							else {
								data.add(movie); 
							}
						}
						catch (IllegalArgumentException nomovie) {
							System.err.print("No movie!");
						}
					}
					
					//Three actors
					else {
						//Store information to respective classes.
						String title = list.get(0);
						int year = Integer.parseInt(list.get(1)); 
						Location location = new Location(list.get(2), list.get(3));
						String director = list.get(6); 
						String writer = list.get(7); 
						Actor actor1 = new Actor(list.get(8)); 
						Actor actor2 = new Actor(list.get(9)); 
						Actor actor3 = new Actor(list.get(10)); 
						
						try {
							Movie movie = new Movie(title, year, director, writer, actor1, actor2, actor3);
							movie.Locationlist.add(location);
							
							//If the same movie is already there, add on the locations to the most recent movie dataset in MovieList object. 							
							if (data.size() != 0 && data.get(movie)!=null) {
								data.get(movie).Locationlist.add(location);
							}
							//If first time introducing a movie, add it to the MovieList object. 
							else {
								data.add(movie); 
							}
						}
						catch (IllegalArgumentException nomovie) {
							System.err.print("No movie!");
						}
					}
				}
			} 	
		}
		catch (FileNotFoundException e) {
			System.err.println("Error: The file cannot be opened");
			System.exit(1); 
		}
		
		
		//Interactive mode
		Scanner scan = new Scanner(System.in);
		System.out.println("Search the database by matching keywords to titles or actor names."); 
		System.out.println("To search for matching titles, enter \n title KEYWORD");
		System.out.println("To search for matching actor names, enter \n actor KEYWORD");
		System.out.println("To finish the program, enter \n (Q)quit");
		

		String input="";
		
		//Try to find all the matching movies by its respective keywords. 
		while (input.equalsIgnoreCase("quit") == false) {
			input = scan.nextLine();
			/**
			 * If user wants to find movies with the same title, find them all from the matchingTitles MovieList object 
			 * using the method getMatchingTitles.
			 * If user wants to find movies with the same actors, find them all from the matchingActors MovieList object 
			 * using the method getMatchingActor.
			 */
			if (!(input.equalsIgnoreCase("quit")) && input.length() < 5) {
				System.err.println("Please Enter a valid category."); 
				continue;
			}
			else if (input.equalsIgnoreCase("quit")) {
				System.out.println("Good Bye!");
			}
			else {
				//Comparing Titles
				if (input.substring(0,5).equalsIgnoreCase("title")) {
				    MovieList matchingTitles = data.getMatchingTitles(input.substring(6));
				    
				    //If movie with the same title query entered by user doesn't exist, print out error message. 
				    if (!(matchingTitles == null)) {
				    		for (Movie m : matchingTitles) {
				    			System.out.println(m.toString());
				    		}
				    }
				    else {
				    		System.out.println("No movies with that title.");
				    		continue;
				    }
				}
				
				//Comparing actors
				else if (input.substring(0,5).equalsIgnoreCase("actor")) {
					MovieList matchingActors = data.getMatchingActor(input.substring(6));

				    //If movie with the same actor query entered by user doesn't exist, print out error message. 
					if (!(matchingActors == null)) {
						for (Movie m : matchingActors) {
				    			System.out.println(m.toString());
						}
					}
					else {
				    		System.out.println("No movies with that actor.");
				    		continue;
					}
				}
				else {
					System.err.println("Please Enter a valid category."); 
					continue;
				}
			} 
		}
		scan.close();
		
	}
	
	/**
	  * Splits the given line of a CSV file according to commas and double quotes
	  * (double quotes are used to surround multi-word entries so that they may contain commas)
	  * @author Joanna Klukowska
	  * @param textLine a line of text to be passed
	  * @return an ArrayList object containing all individual entries found on that line
	  */
	public static ArrayList<String> splitCSVLine(String textLine) {

		 if (textLine == null ) return null;
		
		 ArrayList<String> entries = new ArrayList<String>();
		 int lineLength = textLine.length();
		 StringBuffer nextWord = new StringBuffer();
		 char nextChar;
		 boolean insideQuotes = false;
		 boolean insideEntry= false;
		
		 // iterate over all characters in the textLine
		 for (int i = 0; i < lineLength; i++) {
				 nextChar = textLine.charAt(i);
			
			 // handle smart quotes as well as regular quotes
			 if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D')  {
			
			 // change insideQuotes flag when nextChar is a quote
				 if (insideQuotes) {
					 insideQuotes = false;
					 insideEntry = false;
				 }
				 else {
					 insideQuotes = true;
					 insideEntry = true;
				 }
				 
			 } 
			 else if (Character.isWhitespace(nextChar)) {
			 
				   if ( insideQuotes || insideEntry ) {
					   // add it to the current entry
					   nextWord.append( nextChar );
				   }
				   else { // skip all spaces between entries
					 continue;
				   } 
			 }
			 else if (nextChar == ',') {
				 if (insideQuotes){ // comma inside an entry
					 nextWord.append(nextChar);
				 } 
				 else { // end of entry found
					 insideEntry = false;
					 entries.add(nextWord.toString());
					 nextWord = new StringBuffer();
				 }
			 } 
			 else {
			 // add all other characters to the nextWord
				nextWord.append(nextChar); 
				insideEntry = true;
			 }
		
		 }
		 // add the last word ( assuming not empty )
		 // trim the white space before adding to the list
		 if (!nextWord.toString().equals("")) {
			 entries.add(nextWord.toString().trim());
		}
			return entries;
	}

}
