package darwin;

import java.util.ArrayList;

/**
 * 
 * @author Vicente Oyanedel M.
 *
 */
public class Evolution {
	static String targetWord = "supercalifragilisticoespiralidoso"; // natural
																	// gene
																	// perfection
	static int populationSize = 1000;
	static double selectionRate = 0.5;
	static double mutationRate = 0.05;

	/**
	 * Instance variables.
	 */
	int genCounter;
	ArrayList<Individual> population;

	/**
	 * Instantiate new evolution system.
	 */
	public Evolution() {
		genCounter = 0;
		initPopulation(populationSize);
	}

	/**
	 * Generate initial population with random genetics.
	 * 
	 * @param poblation
	 *            size of population
	 */
	private void initPopulation(int poblation) {
		population = new ArrayList<Individual>();
		for (int i = 0; i < poblation; i++) {
			population.add(new Individual(targetWord.length()));
		}
	}

	/**
	 * Executes the evolution algorith to learn the 'targetWord' string.
	 * 
	 * @param args
	 *            Nothing
	 */
	public static void main(String[] args) {
		Evolution evo = new Evolution();
		NaturalSelection ns = new NaturalSelection(evo.population, targetWord);
		ns.sortByFitness();

		while (!ns.existPerfectIndividual) {
			// compute new generation
			ns.selectStronger(selectionRate); // select some, kill the rest
			ns.matingPhase(mutationRate); // sex time
			ns.sortByFitness(); // sort by fitness
			evo.genCounter++;
		}
		Individual perfectOne = ns.perfectIndividual;
		System.out.println("Secret word is: " + perfectOne.getGenesStr());
		System.out.println("Found secret word my evolving " + evo.genCounter + " generations.");
	}

}
