package virtualpetshelter;

import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {

	private Map<String, VirtualPet> roster = new HashMap<>();
	private int foodBowlLevel;
	private int foodType;
	private int waterBowlLevel;
	private Map<Integer, Integer> litterBoxes = new HashMap<>();

	public VirtualPetShelter() {
		litterBoxes.put(1, 0);
	}

	public VirtualPetShelter(int litterBoxLevel) {
		litterBoxes.put(1, litterBoxLevel);
	}

	public void admitNewPet(String name, String description) {
		roster.put(name, new VirtualPet(name, description));
	}

	public int getFoodBowlLevel() {
		return foodBowlLevel;
	}

	public int getFoodType() {
		return foodType;
	}

	public int getWaterBowlLevel() {
		return waterBowlLevel;
	}

	public int getLitterBoxLevel(int litterBoxNum) {
		return litterBoxes.get(litterBoxNum);
	}

	public VirtualPet getPet(String name) {
		return roster.get(name);
	}

	public void putOutFood(int foodType) {
		foodBowlLevel = roster.size() * 2;
		this.foodType = foodType;
	}

	public void putOutWater() {
		waterBowlLevel = roster.size() * 2;
	}

	public void scoopLitterBox(int litterBoxNumber) {
		litterBoxes.put(litterBoxNumber, 0);
	}

	public void addLitterBox() {
		int currentSize = litterBoxes.size();
		litterBoxes.put(currentSize + 1, 0);
	}
}
