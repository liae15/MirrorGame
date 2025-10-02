import java.util.Scanner;

public class Menu 
{
  private static Scanner input;

  //menu program
  public static int getChoice(){
    input = new Scanner(System.in);
    int choice = 0;
    do{
      System.out.println("\nChoose:\n"); 
      System.out.println("(1) Shoot a Laser");
      System.out.println("(2) Guess at a mirror location");
      System.out.println("(0) Quit the game");
      choice = input.nextInt();
    } while(choice < 0 && choice > 3);
    return choice; 
  }
}
