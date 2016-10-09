public class Dice {
			
	private DiceValue value;
	
	public Dice() {
		value =  DiceValue.getRandom();
	}
	
	public DiceValue getValue() {
		return value;
	}

	public DiceValue roll() {
		//Kishantha
		//need to set value before returning.
		value =  DiceValue.getRandom();
		return getValue();
	}		
	
	public String toString() {
		return value.toString();
	}
}
