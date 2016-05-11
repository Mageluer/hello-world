import java.util.ArrayList;

public class AttackAircraftCarrier {
	//declare and initialize some variables 
	private GameHelper helper = new GameHelper();
	private ArrayList<AircraftCarrier> AircarftCarriersList = new ArrayList<AircraftCarrier>();
	private int numOfAttacks = 0;
	
	public void setUpAttack() {
		//create three AircraftCarriers and name them, then put them into the ArrayList<AircraftCarrier>
		AircraftCarrier one = new AircraftCarrier();
		one.setName("Nimitz");
		AircraftCarrier two = new AircraftCarrier();
		two.setName("Queen Elizabeth");
		AircraftCarrier three = new AircraftCarrier();
		three.setName("Gerald");
		AircarftCarriersList.add(one);
		AircarftCarriersList.add(two);
		AircarftCarriersList.add(three);
		
		//prompt, describe the game
		System.out.println("Your goal is to sink three aircraft carriers.");
		System.out.println("Nimitz, Queen Elizabeth, Gerald");
		System.out.println("Try to sink them in the fewest number of attacks.");
		
		//set the locations of the three aircraft carriers
		for(AircraftCarrier AircraftCarrierToSet : AircarftCarriersList){
			ArrayList<String> newLocation = helper.placeAircraftCarrier(3);
			AircraftCarrierToSet.setLocationCells(newLocation);
		}
	}
	
	private void startPlaying(){
		while(!AircarftCarriersList.isEmpty()) {
			String userGuess = helper.getUserInput("Enter a attack.");
			checkUserGuess(userGuess);
		}
		finishGame();
	}	

	private void checkUserGuess(String userGuess){
		numOfAttacks++;
		String result = "miss";
		for(AircraftCarrier AircraftCarrierToTest : AircarftCarriersList){
			result = AircraftCarrierToTest.checkYourself(userGuess);
			if( result.equals("hit") ) {
				break;
			}
			if( result.equals("kill") ) {
				AircarftCarriersList.remove(AircraftCarrierToTest);
				break;
			}
		}
		System.out.println(result);
	}
	
	private void finishGame() {
		System.out.println("You sink them all.You win!");
		if(numOfAttacks<=18){
			System.out.println("Only took you "+ numOfAttacks +" attacks.");
			
		}else{
			System.out.println("Took you "+ numOfAttacks +" attacks.");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AttackAircraftCarrier game=new AttackAircraftCarrier();
		game.setUpAttack();
		game.startPlaying();
	}

}




import java.util.*;
public class AircraftCarrier {
	private ArrayList<String> locationCells;
	private String name;
	
	public void setLocationCells(ArrayList<String> loc){
		locationCells=loc;
	}
	
	public void setName(String n){
		name=n;
	}
	
	public String checkYourself(String userInput){
		String result = "miss";
		int index=locationCells.indexOf(userInput);
		if(index>=0){
			locationCells.remove(index);
			
			if(locationCells.isEmpty()){
				result="kill";
			}else{
				result="miss";
			}
		}
		return result;
	}
}




import java.io.*;
import java.util.*;
public class GameHelper {
	private static final String Alphabet = "abcdefg";
	private int gridLength = 7;
	private int gridSize = 49;
	private int [] grid = new int[gridSize];
	private int AircraftCarrierCount = 0;
	
	public String getUserInput(String prompt){
		String inputLine = null;
		System.out.print(prompt + " ");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			inputLine =br.readLine();
			if(inputLine.length()==0)
				return null;
		} catch (IOException e){
			System.out.println("IOException "+e);
		}
		return inputLine.toLowerCase();
	}
	
	public ArrayList<String> placeAircraftCarrier(int AircraftCarrierSize){
		ArrayList<String> alphaCells = new ArrayList<String>();
		String [] alphacoords = new String [AircraftCarrierSize];
		String temp = null ;
		int [] coords = new int[AircraftCarrierSize];
		int attempts = 0;
		boolean success = false;
		int location = 0;
		
		AircraftCarrierCount++;
		int incr = 1;
		if( (AircraftCarrierCount % 2) == 1 ){
			incr = gridLength;
		}
		
		while ( !success & attempts++ < 200 ) {
			location = (int) (Math.random()*gridSize);
			System.out.println("try "+ location);///////////
			int x = 0;
			success = true ;
			while (success && x < AircraftCarrierSize){
				if (grid[location] == 0) {
					coords[x++] = location;
					location += incr;
					if (location >= gridSize) {
						success = false;
					}
				}
			}
			
			int y = 0;
			int column = 0;
			int row = 0;
			
			while (y < AircraftCarrierSize){
				grid[coords[y]] = 1;
				row = (int)(coords[y] / gridLength) ;
				column = coords[y] % gridLength;
				temp = String.valueOf(Alphabet.charAt(column));
				alphaCells.add(temp.concat(Integer.toString(row)));
				y++;
			}
		}
		return alphaCells;
	}
}

