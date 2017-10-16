# evolutionary-guesser-of-words
A simple Evolutionary Algorithm to guess any word.

## General

Dev'd with Eclipse 4.6.2 in Windows 10.

Repo: https://github.com/Vichoko/evolutionary-guesser-of-words

## Dependencies

1. JavaSE-1.8 (or similar)
2. JUnit 4 (optional; for tests)

## Usage
1. (Optional) Customize word and other variables in darwin.Evolution.java.
2. Run darwin.Evolution.main method.
3. Watch evolution in console. At the end, shows the generations counter and final product.

## Customize
```
	static String targetWord = "supercalifragilisticoespiralidoso"; // word that represents the max fitness
	static int populationSize = 1000; // quantity of individuals at each generation; constant
	static double selectionRate = 0.5; // fraction of individuals that survive to natural selection
	static double mutationRate = 0.05; // probability of mutation for each gene
```