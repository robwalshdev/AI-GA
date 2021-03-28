package com.robert.B;

import java.util.Random;

public class Driver {

  /**
   * Size of the population
   */
  private static int size = 100;

  /**
   * Population of Knapsacks
   */
  private static Knapsack[] population;

  /**
   * Mating pool of Knapsacks
   */
  private static Knapsack[] matingPool;

  public static void main(String[] args) {
    System.out.println("Knapsack size: 103");
    knapsack(20, 103);
    System.out.println("Knapsack size: 156");
    knapsack(20, 156);
  }

  public static void knapsack(int generations, int capacity) {
    // Setup
    population = new Knapsack[size];
    matingPool = new Knapsack[size];

    int[] values = {78, 35, 89, 36, 94, 75, 74, 79, 80, 16};
    int[] weights = {18, 9, 23, 20, 59, 61, 70, 75, 76, 30};

    // Initialise Population
    for (int i = 0; i < size; i++) {
      population[i] = new Knapsack(values, weights, values.length, capacity);
    }

    int generation = 0;

    while (generation < generations) {
      // Tournament Selection
      for (int i = 0; i < size; i++) {
        int fittestIdx = -1;
        int fittestVal = -1;
        for (int j = 0; j < 2; j++) {
          Random r = new Random();
          int randomIdx = r.nextInt(size);
          if(population[randomIdx].getFitness() > fittestVal) {
            fittestVal = population[randomIdx].getFitness();
            fittestIdx = randomIdx;
          }
        }
        matingPool[i] = population[fittestIdx];
      }

      int maxFitness = 0;
      int averageFitness = 0;

      Knapsack maxKnapsack = null;

      // Crossover & Mutation of Child
      for (int i = 0; i < size; i++) {
        Random r = new Random();
        int idx1 = r.nextInt(size);
        int idx2 = r.nextInt(size);

        Knapsack parentA = matingPool[idx1];
        Knapsack parentB = matingPool[idx2];

        int[] parentAInKnapsack = parentA.getInKnapsack();
        int[] parentBInKnapsack = parentB.getInKnapsack();

        int[] childInKnapsack = new int[parentAInKnapsack.length];

        int crossOverPoint =  (int)(Math.random() * (parentAInKnapsack.length + 1));

        for (int j = 0; j < crossOverPoint; j++) {
          childInKnapsack[j] = parentAInKnapsack[j];
        }

        for (int j = crossOverPoint; j < parentBInKnapsack.length; j++) {
          childInKnapsack[j] = parentBInKnapsack[j];
        }

        Knapsack child = new Knapsack(values, weights, values.length, 103);
        child.setInKnapsack(childInKnapsack);
        child.mutate(0.01);
        child.setFitness();

        int childFitness = child.getFitness();
        averageFitness += childFitness;
        if (childFitness > maxFitness) {
          maxFitness = childFitness;
          maxKnapsack = child;
        }

        population[i] = child;
      }

      ++generation;
      System.out.println("Generation: " + generation +
          "\t\tAverage: " + Math.ceil((double) averageFitness / (double) size) +
          "\t\tMax: " + maxFitness);
      System.out.println("Max Knapsack: " + maxKnapsack);
    }
  }
}
