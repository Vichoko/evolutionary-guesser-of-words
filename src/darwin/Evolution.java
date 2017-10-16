package darwin;

import java.util.ArrayList;

public class Evolution {
	static String target = "supercalifragilisticoespiralidoso"; // natural gen perfection
	static int populationSize = 1000;
	static double selectionRate = 0.5;
	static double mutationRate = 0.05;

	int genCounter;
	ArrayList<Individual> population;
	
	public Evolution(){
		genCounter = 0;
		initPopulation(populationSize);
	}
	
	/**
	 * Generate initial population with random genetics.
	 * @param poblation size of population
	 */
	private void initPopulation(int poblation){
		population = new ArrayList<Individual>();
		for (int i = 0; i < poblation; i++){
			population.add(new Individual(target.length()));
		}
	}
	

	/**
	 * Executes the evolution algorith to learn the 'target' string.
	 * @param args Nothing
	 */
	public static void main(String[] args){
		Evolution evo = new Evolution();
		NaturalSelection ns = new NaturalSelection(evo.population, target);
		ns.sortByFitness();

		while(!ns.perfectIndividualExistence){
			// compute new generation
			ns.selectStronger(selectionRate);
			ns.matingPhase(mutationRate);
			ns.sortByFitness();
			evo.genCounter++;
		}
		Individual perfectOne = ns.perfectIndividual;
		System.out.println("Found secret word my evolving in " + evo.genCounter + " generations.");
		System.out.println("Secret word is: " + perfectOne.getGenesStr());
	}

}
