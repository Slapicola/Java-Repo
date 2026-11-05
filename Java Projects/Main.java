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

            //Empty watch hitsory Array
            ArrayList<Movie> watchHistory = new ArrayList<Movie>();

            //Declare user input variable
                var userChoice = "";

            while (!userChoice.equals("6")) {
                //------Menu-----------
                System.out.println("===========================");
                System.out.println("MOVIE RECOMMENDER");
                System.out.println("===========================");
                System.out.println();
                
                System.out.println("1. Search by title");
                System.out.println("2. Search by genre");
                System.out.println("3. Search by actor");
                System.out.println("4. Recommend by Favorite Movie");
                System.out.println("5. Add Movies to watch list");
                System.out.println("6. Exit");
                
                //Get user input
                userChoice = IO.readln("Please enter choice (ie: 1, 2, 3): ");
    
                
                if (userChoice.equals("1")) {
                    var userSearch = IO.readln("Please enter a movie title: ");
                    searchByTitle(Movies, userSearch);
                } else if (userChoice.equals("2")) {
                    var userSearch = IO.readln("Please enter a movie genre: ");
                    searchByGenre(Movies, userSearch);
                } else if (userChoice.equals("3")){
                    var userSearch = IO.readln("Please enter an actor name: ");
                    searchByActor(Movies, userSearch);
                } else if (userChoice.equals("4")){
                    var userSearch = IO.readln("What is your favorite movie? ");
                    recommendByFavorite(Movies, userSearch);
                } else if (userChoice.equals(("5"))){
                    populateWatchHistory(Movies, watchHistory);
                    
                    for (Movie movie : watchHistory) {
                        System.out.println(movie.getTitle());
                    }
                    recommendByWatchList(Movies, watchHistory);
                } else if (userChoice.equals("6")) {
                    System.out.println("Goodbye! See you again.");
                    break;
                }

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
        boolean foundGenre = false;
        System.out.println("====================");
        System.out.println(genre + " Movies");
        System.out.println("====================");
        for (int i = 0; i < movieList.size(); i++) {
            Movie currentMovie = movieList.get(i);
            if (currentMovie.getGenre().equalsIgnoreCase(genre)) {
                foundGenre = true;

                System.out.println("- " + currentMovie.getTitle());
            }
        }
        if (!foundGenre) {
            System.out.println("There are no movies in that genre.");
        }
    }
    
    static void searchByActor(ArrayList<Movie> movieList, String actorName) {
        boolean foundActor = false;
        System.out.println("====================");
        System.out.println("Movies starring " + actorName);
        System.out.println("====================");
        for (int i = 0; i < movieList.size(); i++) {
            Movie currentMovie = movieList.get(i);
            String[] actors = currentMovie.getActorsList();
            for (int j = 0; j < actors.length; j++) {
                if (actors[j].equalsIgnoreCase(actorName)) {
                    foundActor = true;
                    System.out.println("- " + currentMovie.getTitle());
                }

            }
        }
        if (!foundActor) {
            System.out.println("There are no movies with that actor.");
        }
    }
    
    static void searchByTitle(ArrayList<Movie> movieList, String title) {
        boolean foundMovie = false;
        System.out.println("====================");
        System.out.println("Movie Information");
        System.out.println("====================");
        for (int i = 0; i < movieList.size(); i++) {
            Movie currentMovie = movieList.get(i);
            if (currentMovie.getTitle().equalsIgnoreCase(title)) {
                foundMovie = true;
                System.out.println("Title: " + currentMovie.getTitle());
                System.out.println("Genre: " + currentMovie.getGenre());
                System.out.println("Actors:");
                String[] actors = currentMovie.getActorsList();
                for (int j = 0; j < actors.length; j++) {
                    System.out.println("- " + actors[j]);
                }
            }
        }
        if (!foundMovie) {
            System.out.println("That movie is not in the database.");
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
            System.out.println("Movie not found in the database.");
        }
    }

    static void populateWatchHistory(ArrayList<Movie> movieList, ArrayList<Movie> watchHistory) {
        watchHistory.clear();

        boolean alreadyAdded = false;

        System.out.println("Enter 3 watched movies: ");
        var askForMovies1 = IO.readln("Movie 1: ");
        var askForMovies2 = IO.readln("Movie 2: ");
        var askForMovies3 = IO.readln("Movie 3: ");

        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(askForMovies1)) {
                alreadyAdded = false;
                for (Movie alreadyWatched : watchHistory) {
                    if (alreadyWatched.equals(movie)) {
                        alreadyAdded = true;
                    }
                }
                if (!alreadyAdded) {
                    watchHistory.add(movie);
                } else {
                    System.out.println("Movie is already in watch history.");
                }
            } 
            if (movie.getTitle().equalsIgnoreCase(askForMovies2)) {
                alreadyAdded = false;
                for (Movie alreadyWatched : watchHistory) {
                    if (alreadyWatched.equals(movie)) {
                        alreadyAdded = true;
                    }
                }
                if (!alreadyAdded) {
                    watchHistory.add(movie);
                } else {
                    System.out.println("Movie is already in watch history.");
                }
            }
            if (movie.getTitle().equalsIgnoreCase(askForMovies3)) {
                alreadyAdded = false;
                for (Movie alreadyWatched : watchHistory) {
                    if (alreadyWatched.equals(movie)) {
                        alreadyAdded = true;
                    }
                }
                if (!alreadyAdded) {
                    watchHistory.add(movie);
                } else {
                    System.out.println("Movie is already in watch history.");
                }
            }
        }
    }

    static void recommendByWatchList(ArrayList<Movie> movieData, ArrayList<Movie> watchHistory) {
        //Declare genre counters
        int scienceFictionCount = 0;
        int crimeCount = 0; 
        int fantasyCount = 0;
        int dramaCount = 0;
        int actionCount = 0;
        int romanceCount = 0;
        int adventureCount = 0;
        int animationCount = 0;

        int highestCount = 0;

        String favoriteGenre = "";

        for (int i = 0; i < watchHistory.size(); i++) {
            Movie currentMovie = watchHistory.get(i);
            
            if (currentMovie.getGenre().equals("Science Fiction")) {
                scienceFictionCount++;
            }
            if (currentMovie.getGenre().equals("Crime")) {
                crimeCount++;
            }
            if (currentMovie.getGenre().equals("Fantasy")) {
                fantasyCount++;
            }
            if (currentMovie.getGenre().equals("Drama")) {
                dramaCount++;
            }
            if (currentMovie.getGenre().equals("Action")) {
                actionCount++;
            }
            if (currentMovie.getGenre().equals("Romance")) {
                romanceCount++;
            }
            if (currentMovie.getGenre().equals("Adventure")) {
                adventureCount++;
            }
            if (currentMovie.getGenre().equals("Animation")) {
                animationCount++;
            }
        }

        //Check what genre is seen the most in the watchlist
        if (scienceFictionCount > highestCount) {
            highestCount = scienceFictionCount;
            favoriteGenre = "Science Fiction";
        }
        if (crimeCount > highestCount) {
            highestCount = crimeCount;
            favoriteGenre = "Crime";
        }
        if (fantasyCount > highestCount) {
            highestCount = fantasyCount;
            favoriteGenre = "Fantasy";
        }
        if (dramaCount > highestCount) {
            highestCount = dramaCount;
            favoriteGenre = "Drama";
        }
        if (actionCount > highestCount) {
            highestCount = actionCount;
            favoriteGenre = "Action";
        }
        if (romanceCount > highestCount) {
            highestCount = romanceCount;
            favoriteGenre = "Romance";
        }
        if (adventureCount > highestCount) {
            highestCount = adventureCount;
            favoriteGenre = "Adventure";
        }
        if (animationCount > highestCount) {
            highestCount = animationCount;
            favoriteGenre = "Animation";
        }

        //testing counters
        System.out.println("Science Fiction: " + scienceFictionCount);
        System.out.println("Crime: " + crimeCount);
        System.out.println("Fantasy: " + fantasyCount);
        System.out.println("Drama: " + dramaCount);
        System.out.println("Action: " + actionCount);
        System.out.println("Romance: " + romanceCount);
        System.out.println("Adventure: " + adventureCount);
        System.out.println("Animation: " + animationCount);
        System.out.println("Favorite Genre: " + favoriteGenre);

        //Loop through movieData
        boolean watchedMovie = false;
        System.out.println();
        System.out.println("Based on your watch history, we recommend: ");
        for (Movie movie : movieData) {
            if (movie.getGenre().equals(favoriteGenre)) {
                watchedMovie = false;
                for (Movie watched : watchHistory) {
                    if (watched.equals(movie)) {
                        watchedMovie = true;
                    }
                }
                if (!watchedMovie) {
                    System.out.println("-" + " " + movie.getTitle());
                }
            }
        }
    }
    }