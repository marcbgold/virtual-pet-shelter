package virtualpetshelter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest {

	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private VirtualPetShelter underTest;

	@Before
	public void setup() {
		underTest = new VirtualPetShelter(3);
		underTest.admitNewPet(NAME, DESCRIPTION);
	}

	@Test
	public void shouldAdmitNewPet() {
		VirtualPet cat = underTest.getPet(NAME);

		assertThat(cat.getName(), is(NAME));
	}

	@Test
	public void shouldRemovePetWhenAdopted() {
		underTest.adoptOutPet(NAME);

		assertThat(underTest.checkIfPetExists(NAME), is(false));
	}

	@Test
	public void putOutFoodShouldRaiseFoodBowlLevelToPetCountTimes2AndAddFoodType() {
		underTest.putOutFood(1);

		assertThat(underTest.getFoodBowlLevel(), is(4));
		assertThat(underTest.getFoodType(), is(1));
	}

	@Test
	public void putOutWaterShouldRaiseWaterBowlLevelToPetCountTimes2() {
		underTest.putOutWater();

		assertThat(underTest.getWaterBowlLevel(), is(4));
	}

	@Test
	public void scoopLitterBoxShouldLowerLitterBoxLevelToZero() {
		underTest.scoopLitterBox(0);

		assertThat(underTest.getLitterBoxLevel(0), is(0));
	}

	@Test
	public void shouldAddNewLitterBox() {
		underTest.addLitterBox();

		assertThat(underTest.getLitterBoxLevel(2), is(0));
	}

	@Test
	public void shouldFindCleanLitterBox() {
		underTest.scoopLitterBox(1);
		int boxNum = underTest.findCleanLitterBox();

		assertThat(boxNum, is(1));
	}

	@Test
	public void petsShouldTakeCareOfSelves() {
		underTest.admitNewPetWithSpecialValues("Extra", DESCRIPTION, 60, 60, 60, 60, 60);
		underTest.putOutFood(1);
		underTest.putOutWater();
		underTest.scoopLitterBox(1);
		underTest.petsTakeCareOfSelves();

		VirtualPet testPet = underTest.getPet("Extra");
		assertThat(testPet.getHungerLevel(), is(30));
		assertThat(testPet.getThirstLevel(), is(40));
		assertThat(testPet.getWasteLevel(), is(0));
		assertThat(underTest.getFoodBowlLevel(), is(5));
		assertThat(underTest.getWaterBowlLevel(), is(5));
		assertThat(underTest.getLitterBoxLevel(1), is(1));
	}

}
