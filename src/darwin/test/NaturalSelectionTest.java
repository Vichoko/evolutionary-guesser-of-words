package darwin.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import darwin.Individual;
import darwin.NaturalSelection;

public class NaturalSelectionTest {
	NaturalSelection ns;
	Individual pedro;
	Individual maria;
	Individual rorro;
	Individual potro;
	
	@Before
	public void setUp() throws Exception {
		pedro = new Individual("pedro".toCharArray());
		maria = new Individual("maria".toCharArray());
		rorro = new Individual("rorro".toCharArray());
		potro = new Individual("potro".toCharArray());
		List<Individual> population = new ArrayList<Individual>();
		population.add(pedro);
		population.add(maria);		
		population.add(rorro);
		population.add(potro);
		
		ns = new NaturalSelection(population, "perro");
	}
	
	@Test
	public void sortByFitnessTest(){
		ns.sortByFitness();
		assertEquals(ns.getPopulation().get(0), pedro);
		assertEquals(ns.getPopulation().get(ns.getPopulation().size()-1), maria);
		
		assertEquals(pedro.getFitness(), 4);
		assertEquals(maria.getFitness(), 1);
	}
	
	@Test
	public void selectStrongerTest(){
		ns.selectStronger(0.5);
		List<Individual> pop = ns.getPopulation(); // selected 
		assertEquals(pop.size(), 2);	
	}
	
	@Test
	public void matingPhaseTest(){
		ns.matingPhase(0.05);
		List<Individual> pop = ns.getPopulation(); // sons
		assertEquals(pop.size(), ns.populationSize);
	}

	@Test
	public void rouletteTest() {
		//after 100000 roulete runs, pedro should be chose more times than maria and rorro and potro.
		ns.getMaxFitness();
		int pedroCounter = 0;
		int mariaCounter = 0;
		int rorroCounter = 0;
		int potroCounter = 0;
		for (int i = 0; i < 10000000; i++) {
			Individual chosenOne = ns.runRoulette();
			if (chosenOne.getGenesStr().equals("pedro")) {
				pedroCounter++;
			}
			else if (chosenOne.getGenesStr().equals("maria")) {
				mariaCounter++;
			}
			else if (chosenOne.getGenesStr().equals("rorro")) {
				rorroCounter++;
			}
			else if (chosenOne.getGenesStr().equals("potro")) {
				potroCounter++;
			}
		}
		//System.out.println("pedro: " + pedroCounter + " rorro: " + rorroCounter + " potro: " + potroCounter + " maria: " + mariaCounter);
		assertTrue(pedroCounter > mariaCounter);
		assertTrue(pedroCounter > rorroCounter);
		assertTrue(pedroCounter > potroCounter);
		assertTrue(rorroCounter > mariaCounter);
		assertTrue(potroCounter > mariaCounter);
	}
}
