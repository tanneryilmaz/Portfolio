import java.util.Scanner;

public class PlayerRoster {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] jerseyNum = new int[5]; 
		int[] playerRating = new int[5]; 
		boolean cont = true; //should I put this initialization here, or directly above the while loop?
		
		for(int i = 1; i <= 5; i++) {
			System.out.println("Enter player " + i + "'s jersey number:");
			jerseyNum[i-1] = sc.nextInt();
			
			System.out.println("Enter player " + i + "'s rating:");
			playerRating[i-1] = sc.nextInt();
			System.out.println(""); 
		}
		
		outputRoster(jerseyNum, playerRating);
				
		while(cont == true) {
			printMenu();
			System.out.println("\nChoose an option:");
			String userSelection = sc.next().toLowerCase();
			
			if(userSelection.equals("u")) {
				System.out.println("Enter a jersey number: ");
				int jersNumber = sc.nextInt();
				System.out.println("Enter a new rating for player: ");
				int newRating = sc.nextInt();
				updateRating(jersNumber, newRating, jerseyNum, playerRating);
			}
			
			else if(userSelection.equals("a")){
				System.out.println("Enter a rating: ");
				int rating = sc.nextInt();
				outputAboveRating(rating, jerseyNum, playerRating);
			}
			
			else if(userSelection.equals("r")) {
				System.out.println("Enter a jersey number: ");
				int replaceNumber = sc.nextInt();
				
				if(inRoster(replaceNumber, jerseyNum)) {
					System.out.println("Enter a new jersey number: ");
					int newJerseyNum = sc.nextInt();
					
					System.out.println("Enter a rating for the new player: ");
					int newPlayerRating = sc.nextInt();
					
					replacePlayer(replaceNumber, newJerseyNum, newPlayerRating, jerseyNum, playerRating);
				}
			}
			
			else if(userSelection.equals("o")) {
				outputRoster(jerseyNum, playerRating);
			}
			
			else if(userSelection.equals("q")) {
				cont = false;
			}			
		}
	}
	
	public static void updateRating(int jerseyNumber, int newRating, int[] jerseyNums, int[] playerRating) { 
		//Is it bad that I have named the parameters for this method the same as the variables initialized in lines 42 and 44?
		//Did I really need to pass the integer arrays to this method? Or is there another way to modify their content from within this scope?
		
		boolean jerseyNumExists = false;
		
		for(int i = 0; i < jerseyNums.length; i++) {
			if(jerseyNums[i] == jerseyNumber) {
				playerRating[i] = newRating;
				jerseyNumExists = true; //jerseyNumExists is true if the user entered a jersey number that is in the jerseyNums array
			}
		}
		
		if(!jerseyNumExists) {
			System.out.println("That jersey number does not exist");
		}
	}
	
	public static void outputAboveRating(int rating, int[] jerseyNums, int[] playerRating) {
		
		System.out.println("ABOVE " + rating);
		
		for(int i = 0; i < playerRating.length; i++) {
			
			if(playerRating[i] > rating) {
				System.out.println("Player " + (i + 1) + " -- Jersey number: " + jerseyNums[i] + ", Rating: " + playerRating[i]);
			}
		}
	}
	
	public static void replacePlayer(int replaceNumber, int newJerseyNum, int newPlayerRating, int[] jerseyNum, int[] playerRating) {
		
		for(int i = 0; i < jerseyNum.length; i++) {
			if(jerseyNum[i] == replaceNumber) {
				jerseyNum[i] = newJerseyNum;
				playerRating[i] = newPlayerRating;
			}
		}
	}
	
	public static void outputRoster(int[] jersey, int[] rating) {
		
		System.out.println("ROSTER");
		
		for(int j = 1; j <= 5; j++) {
			System.out.println("Player " + j + " -- Jersey number: " + jersey[j-1] + ", Rating: " + rating[j-1]);
		}
	}

	public static boolean inRoster(int playerJerseyNum, int[] jerseyNum) {
		boolean playerExists = false;
		
		for(int i = 0; i < jerseyNum.length; i++) {
			if(jerseyNum[i] == playerJerseyNum) {
				playerExists = true;
			}
		}
		return playerExists; //returns whether or not the player exists in the roster
	}
	
	public static void printMenu() {
		System.out.println("\nMENU");	//should I have implemented the printing of this menu as a separate method?
		System.out.println("u - Update player rating");
		System.out.println("a - Output players above a rating");
		System.out.println("r - Replace player");
		System.out.println("o - Output roster");
		System.out.println("q - Quit");
	}
}
