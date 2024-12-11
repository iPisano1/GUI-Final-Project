public class Movie {
    
    private int movieId;
    private String title;
    private int price;
    private String imagePath;
    private String genre;
    
    // Constructor
    public Movie(int movieId, String title, int price, String imagePath, String genre) {
        this.movieId = movieId;
        this.title = title;
        this.price = price;
        this.imagePath = imagePath;
        this.genre = genre;
    }

    // Getters
    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getGenre() {
        return genre;
    }
}
