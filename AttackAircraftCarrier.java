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
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
