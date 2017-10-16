package darwin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Vicente Oyanedel M.
 *
 */
public class NaturalSelection {

	public boolean existPerfectIndividual;
	public Individual perfectIndividual;

	List<Individual> population;

	public List<Individual> getPopulation() {
		return this.population;
	}

	public int populationSize;
	String perfectGenes;

	/**
	 * Instance with an initial 'population' and notion of perfect individual
	 * ('perfectGenes').
	 * 
	 * @param population
	 *            Individuals in the system.
	 * @param perfectGenes
	 *            Genes of the perfect individual (max fitness).
	 */
	public NaturalSelection(List<Individual> population, String perfectGenes) {
		this.population = population;
		this.populationSize = population.size();
		this.existPerfectIndividual = false;
		this.perfectGenes = perfectGenes;
	}

	/**
	 * Selects the 'selectRate' porcent of the top fitness individuals.
	 * 
	 * @param selectRate
	 *            Number between 0 and 1, rate of survivors after genocide.
	 */
	public void selectStronger(double selectRate) {
		int lastIndex = (int) Math.ceil((selectRate * populationSize)); // genocide
		population = new ArrayList<Individual>(population.subList(0, lastIndex));
		System.out.println("Best fitness is " + population.get(0).getGenesStr() + " with " + population.get(0).fitness);
	}

	/**
	 * sorts the population by fitness descending.
	 */
	public void sortByFitness() {
		for (Individual i : population) {
			calculateFitness(i);
		}
		population.sort(new Comparator<Individual>() { // sort by fitness
														// descending

			@Override
			public int compare(Individual o1, Individual o2) {
				return -(o1.getFitness() - o2.getFitness());
			}

		});
		if (population.get(0).getGenesStr().equals(perfectGenes)) {
			existPerfectIndividual = true;
			perfectIndividual = population.get(0);
		}
	}

	/**
	 * Grabs pairs of survivors randomly and mate them to create a new child.
	 * Until the population is recovered; i.e. populationSize childs are
	 * created.
	 * 
	 * @param mutationRate
	 *            Number between 0 and 1, probability of have a random mutation
	 *            in a gene.
	 */
	public void matingPhase(double mutationRate) {
		Random rand = new Random();
		List<Individual> children = new ArrayList<Individual>();
		// mate pairs of 25% top races

		assert (population.size() >= 2); // need at least 2 after selection
		for (int childIndex = 0; childIndex < populationSize;) {
			int firstParent = rand.nextInt(population.size());
			int secondParent = firstParent;
			while (firstParent == secondParent) {
				// parents must not be the same individual
				secondParent = rand.nextInt(population.size());
			}

			children.add(population.get(firstParent).sex(population.get(secondParent), mutationRate));
			childIndex++;
		}
		this.updateGeneration(children);
	}

	/**
	 * calculates the fitness of the individual, saving the result inside the
	 * individual fitness field.
	 * 
	 * @param i
	 *            Individual which fitness will be calculated
	 * @return fitness calculated.
	 */
	private int calculateFitness(Individual i) {
		int fitness = 0;
		assert (perfectGenes.length() == i.genes.length);
		for (int genIndex = 0; genIndex < perfectGenes.length(); genIndex++) {
			if (perfectGenes.toCharArray()[genIndex] == i.genes[genIndex]) {
				fitness++;
			}
		}
		i.setFitness(fitness);
		return fitness;
	}

	/**
	 * replaces the actual population with the new generation of childs.
	 * 
	 * @param newGeneration
	 *            List of child individuals.
	 */
	private void updateGeneration(List<Individual> newGeneration) {
		this.population = newGeneration;

	}
}
