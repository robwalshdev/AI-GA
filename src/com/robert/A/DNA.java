package com.robert.A;

public class DNA {

  /**
   * Integer array to store 0's and 1's
   */
  private int[] genes;

  /**
   * Fitness of the DNA
   */
  private int fitness;

  /**
   * Constructor for part i
   * @param length of genes in DNA
   */
  public DNA(int length) {
    this.genes = new int[length];
    for (int i = 0; i < length; i++) {
      genes[i] = (int) Math.round(Math.random());
    }
    setFitness();
  }

  /**
   * Constructor for part ii
   * @param length of genes in DNA
   * @param targetFitness the array to match
   */
  public DNA (int length, int[] targetFitness) {
    this.genes = new int[length];
    for (int i = 0; i < length; i++) {
      genes[i] = (int) Math.round(Math.random());
    }
    setTargetFitness(targetFitness);
  }

  /**
   * Constructor for part iii
   * @param length of genes in DNA
   * @param lengthOfSolutions required for fitness function
   */
  public DNA (int length, int lengthOfSolutions) {
    this.genes = new int[length];
    for (int i = 0; i < length; i++) {
      genes[i] = (int) Math.round(Math.random());
    }

    setDeceptiveLandscapeFitness(lengthOfSolutions);
  }

  /**
   * Mutate method for DNA
   * @param mutationRate chance to mutate
   */
  public void mutate(double mutationRate) {
    for (int i = 0; i < genes.length; i++) {
      if (Math.random() < mutationRate) {
        genes[i] = (genes[i] == 1 ? 0 : 1);
      }
    }
  }

  /**
   * Fitness method for part i
   */
  public void setFitness() {
    fitness = 0;
    for(int gene: genes) {
      if (gene == 1) {
        fitness++;
      }
    }
  }

  /**
   * Fitness method for part ii
   * @param targetFitness the array to match
   */
  public void setTargetFitness(int[] targetFitness) {
    fitness = 0;
    for (int i = 0; i < 20; i++) {
      if (genes[i] == targetFitness[i]) {
        fitness++;
      }
    }
  }

  /**
   * Fitness method for part iii
   * @param lengthOfSolutions required for zero solutions
   */
  public void setDeceptiveLandscapeFitness(int lengthOfSolutions) {
    fitness = 0;
    for(int gene: genes) {
      if (gene == 1) {
        fitness++;
      }
    }

    if (fitness == 0) {
      fitness = 2 * lengthOfSolutions;
    }
  }

  /**
   * Method to get Fitness
   * @return fitness
   */
  public int getFitness() {
    return fitness;
  }

  /**
   * Method to get genes
   * @return array of genes
   */
  public int[] getGenes() {
    return genes;
  }

  /**
   * Method to set genes
   * @param genes array of genes
   */
  public void setGenes(int[] genes) {
    this.genes = genes;
  }

}
