public class Movie{
   
   private int movieId;
   private String title;
   private int price;
   
   public Movie(int movieId, String title, int price)
   {
      this.movieId = movieId;
      this.title = title;
      this.price = price;
   }
   
   public int getMovieId()
   {
      return movieId;
   }
   
   public String getTitle()
   {
      return title;
   }
   
   public int getPrice()
   {
      return price;
   }
   
}