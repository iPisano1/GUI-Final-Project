public class Movie {
   private String title;
   private int price;
   private String imagePath;
   private String genre;

   public Movie(String title, int price, String imagePath, String genre){
       this.title = title;
       this.price = price;
       this.imagePath = imagePath;
       this.genre = genre;
   }

   public String getTitle(){
       return title;
   }

   public int getPrice(){
       return price;
   }

   public String getImagePath(){
       return imagePath;
   }

   public String getGenre(){
        return genre;
   }
}
