package virtualpetshelter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VirtualPetShelter {

	private Map<String, VirtualPet> roster = new HashMap<>();
	private int foodBowlLevel;
	private int foodType;
	private int waterBowlLevel;
	private Map<Integer, Integer> litterBoxes = new HashMap<>();

	public VirtualPetShelter() {
		this(0);
	}

	public VirtualPetShelter(int litterBoxLevel) {
		litterBoxes.put(1, litterBoxLevel);
		admitNewPet("Widget", "4 YOF, gray striped tabby.  Pretty normal cat, aside from the weird name.");
	}

	public Collection<VirtualPet> getAllPets() {
		return roster.values();
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

	public void admitNewPet(String name, String description) {
		roster.put(name, new VirtualPet(name, description));
	}

	public void admitNewPetWithSpecialValues(String name, String description, int hunger, int thirst, int boredom, int tiredness, int waste) {
		roster.put(name, new VirtualPet(name, description, hunger, thirst, boredom, tiredness, waste));
	}

	public void adoptOutPet(String name) {
		roster.remove(name);
	}

	public boolean checkIfPetExists(String name) {
		return roster.containsKey(name);
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

	public int findCleanLitterBox() {
		for (Entry<Integer, Integer> entry : litterBoxes.entrySet()) {
			if (entry.getValue() < 3) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public void petsTakeCareOfSelves() {
		for (VirtualPet currentPet : getAllPets()) {
			currentPet.tick();

			if (currentPet.getHungerLevel() >= 50 && foodBowlLevel > 0) {
				// if (foodType != dislikedFoodType)
				// eat();
				// else if (hungerLevel >= 70)
				currentPet.eat();
				foodBowlLevel--;
			}

			if (currentPet.getThirstLevel() >= 50 && waterBowlLevel > 0) {
				currentPet.drink();
				waterBowlLevel--;
			}

			int cleanBoxNum = findCleanLitterBox();
			if (currentPet.getWasteLevel() >= 70 && cleanBoxNum > 0) {
				currentPet.useBathroom();
				int boxLevel = litterBoxes.get(cleanBoxNum);
				litterBoxes.put(cleanBoxNum, boxLevel + 1);
			}
		}
	}
}
