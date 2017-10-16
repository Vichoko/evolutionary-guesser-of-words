package darwin;

import java.util.Arrays;

/**
 * 
 * @author Vicente Oyanedel M.
 *
 */
public class Individual {
	public char[] genes; // in this case a word of 5 characters

	public String getGenesStr() {
		return String.valueOf(genes);
	}

	int fitness;

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	/**
	 * Instance by existing array of genes
	 * 
	 * @param genes
	 */
	public Individual(char[] genes) {
		this.genes = genes;
	}

	/**
	 * Instance by random array of genes
	 * 
	 * @param genesNumber
	 *            Number of genes to be generated
	 */
	public Individual(int genesNumber) {
		this.genes = new char[genesNumber];
		for (int i = 0; i < genesNumber; i++) {
			genes[i] = Tools.getRandomChar();
		}
	}

	/**
	 * Pairs two individuals creating a Child. First it does a cross-over of the
	 * genes, then generate random mutations and finally returning the son.
	 * 
	 * @param peer
	 *            individual which will be peared with the actual one.
	 * @param mutationRate
	 *            probability of generate mutation in one gene.
	 * @return son.
	 */
	public Individual sex(Individual peer, double mutationRate) {
		char[] cell = this.crossover(peer);
		char[] fetus = this.mutate(cell, mutationRate);
		Individual son = new Individual(fetus);
		return son;
	}

	/**
	 * Generate mutation in the genes of 'mutatingGenes' according to
	 * 'mutationRate'.
	 * 
	 * @param mutatingGenes
	 *            Genes that will be mutated.
	 * @param mutationRate
	 *            probability of generate mutation in one gene.
	 * @return Genes after the mutation procedure.
	 */
	public char[] mutate(char[] mutatingGenes, double mutationRate) {
		for (int genIndex = 0; genIndex < mutatingGenes.length; genIndex++) {
			if (Math.random() < mutationRate) {
				char mutation = Tools.getRandomChar(); // get random char
														// between 'a' and 'z'
														// in ascii.
				mutatingGenes[genIndex] = mutation;
			}
		}
		return mutatingGenes;
	}

	/**
	 * cross-over genes of the father/mother with equal probabilities per gene.
	 * 
	 * @param mommy
	 *            peer gene container.
	 * @return son genes after cross over between father (this) and mother
	 *         (param).
	 */
	public char[] crossover(Individual mommy) {
		Individual daddy = this;
		assert (daddy.genes.length == mommy.genes.length); // same-species check
		char[] sonGenes = new char[daddy.genes.length];

		for (int genIndex = 0; genIndex < daddy.genes.length; genIndex++) {
			if (Math.random() < 0.5) {
				// chose father gen
				sonGenes[genIndex] = daddy.genes[genIndex];
			} else {
				// chose mother one
				sonGenes[genIndex] = mommy.genes[genIndex];
			}
		}
		return sonGenes;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individual other = (Individual) obj;
		if (!Arrays.equals(genes, other.genes))
			return false;
		return true;
	}

}
