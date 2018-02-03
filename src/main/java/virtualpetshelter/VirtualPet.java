package virtualpetshelter;

import java.util.Random;

public class VirtualPet {

	private String name;
	private String description;
	private int hungerLevel;
	private int thirstLevel;
	private int boredomLevel;
	private int tirednessLevel;
	private int wasteLevel;

	int dislikedFoodType;
	int hatedFoodType;
	int minFoodType = 1;
	int maxFoodType = 4;
	Random randomGen = new Random();

	// TODO add nature modififer (normal/hyperactive/lazy)

	public VirtualPet(String name, String description) {
		this(name, description, 20, 20, 50, 10, 0);
	}

	public VirtualPet(String name, String description, int hunger, int thirst, int boredom, int tiredness, int waste) {
		this.name = name;
		hungerLevel = hunger;
		thirstLevel = thirst;
		boredomLevel = boredom;
		tirednessLevel = tiredness;
		wasteLevel = waste;

		dislikedFoodType = randomGen.nextInt((maxFoodType - minFoodType) + 1) + minFoodType;
		do {
			hatedFoodType = randomGen.nextInt((maxFoodType - minFoodType) + 1) + minFoodType;
		} while (dislikedFoodType == hatedFoodType);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getStats() {
		return name + "\t\t|" + hungerLevel + "\t|" + thirstLevel + "\t|" + boredomLevel + "\t |" + tirednessLevel + "\t\t|" + wasteLevel;
	}

	public int getHungerLevel() {
		return hungerLevel;
	}

	public int getThirstLevel() {
		return thirstLevel;
	}

	public int getBoredomLevel() {
		return boredomLevel;
	}

	public int getTirednessLevel() {
		return tirednessLevel;
	}

	public int getWasteLevel() {
		return wasteLevel;
	}

	public int getDislikedFoodType() {
		return dislikedFoodType;
	}

	public int getHatedFoodType() {
		return hatedFoodType;
	}

	public void eat() {
		hungerLevel -= 40;
		thirstLevel += 10;
		tirednessLevel += 10;
		wasteLevel += 20;

		checkForValuesOver100();
	}

	public void drink() {
		thirstLevel -= 40;
		wasteLevel += 20;

		checkForValuesOver100();
	}

	public void play() {
		boredomLevel -= 40;
		hungerLevel += 10;
		thirstLevel += 10;
		tirednessLevel += 30;

		checkForValuesOver100();
	}

	public void sleep() {
		hungerLevel += 20;
		thirstLevel += 20;
		boredomLevel += 20;
		tirednessLevel -= tirednessLevel;

		checkForValuesOver100();
	}

	public void useBathroom() {
		wasteLevel -= wasteLevel;
	}

	public void tick() {
		hungerLevel += 10;
		thirstLevel += 10;
		boredomLevel += 10;
		tirednessLevel += 10;
		wasteLevel += 10;

		checkForValuesOver100();
	}

	public void checkForValuesOver100() {
		if (hungerLevel > 100)
			hungerLevel = 100;

		if (thirstLevel > 100)
			thirstLevel = 100;

		if (boredomLevel > 100)
			boredomLevel = 100;

		if (tirednessLevel > 100)
			tirednessLevel = 100;

		if (wasteLevel > 100)
			wasteLevel = 100;
	}

	@Override
	public String toString() {
		return name + ": " + description;
	}
}
