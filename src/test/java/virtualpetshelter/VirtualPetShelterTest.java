package virtualpetshelter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class VirtualPetShelterTest {

	private static final String NAME = "Name";
	private static final String OTHER_NAME = "Other Name";
	private static final String DESCRIPTION = "Description";
	private VirtualPetShelter underTest;

	@Before
	public void setup() {
		underTest = new VirtualPetShelter(3);
		underTest.admitNewPet(NAME, DESCRIPTION);
		underTest.admitNewPet(OTHER_NAME, DESCRIPTION);
	}

	@Test
	public void shouldAdmitTwoNewPets() {
		VirtualPet cat = underTest.getPet(NAME);
		VirtualPet otherCat = underTest.getPet(OTHER_NAME);

		assertThat(cat.getName(), is(NAME));
		assertThat(otherCat.getName(), is(OTHER_NAME));
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

}
