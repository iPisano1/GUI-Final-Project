public class Movie {
   private String title;
   private int price;
   private String imagePath;

   public Movie(String title, int price, String imagePath) {
       this.title = title;
       this.price = price;
       this.imagePath = imagePath;
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
}
