import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class mirrorGame{

  private Scanner input;
  private Scanner input2;
  private Random generator;
  
  char [][] gameBoard = new char [10][10];
  int [][] mirrors = new int [10][2];
  int foundMirrors = 0;
  int guesses = 0;
  int lazerCount = 0;
  
  public mirrorGame(){
    input = new Scanner(System.in);
    System.out.println("Welcome to the Black Box Game");
    SettingUp();
    Display();
    int function = 1;
    while (function != 0) {
      System.out.print("\n\nYou have found "  + foundMirrors + " of 10 mirrors after " + guesses + " Guesses \nYou have shot " + lazerCount + " Lazers");
      function = Menu.getChoice();
      doOperation(function);
    }
    input.close();
  }

  public void SettingUp() 
  {
    for(int row = 0; row < 10; row ++) 
    {
      for(int col = 0; col < 10; col++) {
        gameBoard[row][col] = '.';
      }
    }
    int randomX, randomY;
    generator = new Random();
    int i = 0;
    while(i < 10)
    {
      randomX = generator.nextInt(9);
      randomY = 10 + generator.nextInt(9);
      for(int j = 0; j < i; j++) {
        if(mirrors[j][0] == randomX && mirrors[j][1] == randomY) 
        {
          break;
        }
      }
      mirrors[i][0] = randomX;
      mirrors[i][1] = randomY;
      i++;
    }
  }

  public void Display() 
  {
    System.out.println();
    int number = 19;
    int change = 11;
    System.out.print("  20 21 22 23 24 25 26 27 28 29  ");
    for(int row = 0; row < 10; row++) 
    {
      System.out.print("\n" + number);
      for(int col = 0; col < 10; col++) {
        System.out.print(" " + gameBoard[row][col] + " ");
      }
      System.out.print(number + change);
      number -= 1;
      change += 2;
    }
    System.out.print("\n   0  1  2  3  4  5  6  7  8  9   ");
  }

  public boolean ConfirmGuess(int x, int y) 
  {
    if(((x>= 0 && x <= 9) || (x>=20 && x<=29)) && ((y>=10 && y <= 19) || (y>=30 && y <= 39))) 
    {
      System.out.println("\nYou have correctly guessed a location. Now checking accuracy of guess.");
      guesses++;
      for(int i = 0; i < 10; i++) 
      {
        if((mirrors[i][0] == x || mirrors[i][0] == x + 20)) 
        {
          int number = 19;
          int change = 11;
          for(int col = 0; col < 10; col++) {
            if(mirrors[i][1] == number || mirrors[i][1] == number + change) 
            {
              System.out.println("Great job! Your guess was right!");
              return true;
            }
            number -= 1;
            change += 2;
          }
          }
      }
    } else {
      System.out.println("\nDid not guess location correctly, try again!");
      return false;
    }
    System.out.println("Incorrect guess, try again!");
    return false;
  }

  public void addMirror(int x, int y) 
  {
    foundMirrors++;
    int correctX = x;
    int correctY = y;
    if(x >= 20) {
      correctX -= 20;
    }
    int number = 19;
    int change = 11;
    while(number >= 10)
    {
      if (y == number + change) 
      {
        correctY = y - 30;
      }
      else if (y == number) 
      {
        correctY = 19 - y;
      }
      number -= 1;
      change += 2;
    }
    int choice = (int)Math.round(Math.random());
    if(choice == 1)
      gameBoard[correctX][correctY] = '/';
    else
      gameBoard[correctX][correctY] = '\\';
  }

   //This functions alongside the menu to run each of the possible options
    public void doOperation(int function) {
      switch (function) {
        case 1: {
          System.out.println("Lazer shot");
          lazerCount++;
        }
          break;

        case 2: {
          System.out.println("\nWhat is the x coordinate of your mirror guess? (0-39)");
          int xCoord = input.nextInt();
          System.out.println("\nWhat is the y coordinate of your mirror guess? (0-39)");
          int yCoord = input.nextInt();
          boolean check = ConfirmGuess(xCoord, yCoord);
          if(check) {
            addMirror(xCoord, yCoord);
            Display();
          }
        }
          break;
        
        case 0: {
          System.out.println("\nThank you for using my program.");
        }
          break;

        case 3: {
          System.out.println("\nYou have found my cheat code! Here are the mirror locations:");
          for(int i = 0; i < mirrors.length; i++) 
          {
            int x = mirrors[i][0];
            int y = mirrors[i][1];
            addMirror(x, y);
          }
          Display();
        }
          break;
      }
    }

