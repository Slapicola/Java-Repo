import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//Main class
public class Main {
    void main() {

        //Read from movies.json file
        try {
        
            String json = Files.readString(
                    Paths.get("Java-Repo/Java Projects/movies.json")

            );

            //Declare Gson object
            Gson gson = new Gson();
            
            //Declare Movie array
            ArrayList<Movie> Movies = gson.fromJson(json, new TypeToken<ArrayList<Movie>>() {
            }.getType());

            //------Menu-----------
            System.out.println("Movie Search Menu");

            System.out.println("1. Search by title");
            System.out.println("2. Search by genre");
            System.out.println("3. Search by actor");
            System.out.println("4. Recommend by Favorite Movie");

            //Get user input
            var userChoice = IO.readln("Please enter choice (ie: 1, 2, 3, 4): ");
            
            if (userChoice.equals("1")) {
                var userSearch = IO.readln("Please enter a movie title: ");
                searchByTitle(Movies, userSearch);
            } else if (userChoice.equals("2")) {
                var userSearch = IO.readln("Please enter a movie genre: ");
                searchByGenre(Movies, userSearch);
            } else if (userChoice.equals("3")){
                var userSearch = IO.readln("Please enter an actor name: ");
                searchByActor(Movies, userSearch);
            } else {
                var userSearch = IO.readln("What is your favorite movie? ");
                recommendByFavorite(Movies, userSearch);
            }


            
            // //Loop through the Array list
            // for (int i = 0; i < Movies.size(); i++) {
            //     Movie currentMovie = Movies.get(i);
            //     System.out.println(currentMovie.getTitle());
            //     System.out.println(currentMovie.getGenre());

            // }
            

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //----------Function Declarations----------------
    static void searchByGenre(ArrayList<Movie> movieList, String genre) {
        for (int i = 0; i < movieList.size(); i++) {
            Movie currentMovie = movieList.get(i);
            if (currentMovie.getGenre().equals(genre)) {
                System.out.println(currentMovie.getTitle());
            }
        }
    }
    
    static void searchByActor(ArrayList<Movie> movieList, String actorName) {
        for (int i = 0; i < movieList.size(); i++) {
            Movie currentMovie = movieList.get(i);
            String[] actors = currentMovie.getActorsList();
            for (int j = 0; j < actors.length; j++) {
                if (actors[j].equals(actorName)) {
                    System.out.println(currentMovie.getTitle());
                }
                
            }
        }
    }
    
    static void searchByTitle(ArrayList<Movie> movieList, String title) {
        for (int i = 0; i < movieList.size(); i++) {
            Movie currentMovie = movieList.get(i);
            if (currentMovie.getTitle().equals(title)) {
                System.out.println("Title: " + currentMovie.getTitle());
                System.out.println("Genre: " + currentMovie.getGenre());
                String[] actors = currentMovie.getActorsList();
                for (int j = 0; j < actors.length; j++) {
                    System.out.println(actors[j]);
                }
            }
        }
    }
            
    static void recommendByFavorite(ArrayList<Movie> movieList, String title) {
        boolean foundMovie = false;
        for (int i = 0; i < movieList.size(); i++) {
            Movie currentMovie = movieList.get(i);
            if (currentMovie.getTitle().equalsIgnoreCase(title)) {
                String genre = currentMovie.getGenre();
                for (int j = 0; j < movieList.size(); j++) {
                    Movie recommendedMovie = movieList.get(j);
                    if (genre.equals(recommendedMovie.getGenre())
                            && !recommendedMovie.getTitle().equalsIgnoreCase(title)) {
                        foundMovie = true;
                        System.out
                                .println("Recommended because you liked " + title + ": " + recommendedMovie.getTitle());
                    }
                }
            }
        }
        if (!foundMovie) {
            System.out.println("Movie not found.");
        }
    }
    }