package com.robert.B;

public class Knapsack {

  /**
   * Integer array to store values
   */
  private int[] values;

  /**
   * Integer array to store weights
   */
  private int[] weights;

  /**
   * Integer array to store 0's and 1's
   * Item is present in Knapsack = 1
   */
  private int[] inKnapsack;

  /**
   * Fitness of the Knapsack
   */
  private int fitness;

  /**
   * Capacity of the Knapsack
   */
  private int capacity;

  /**
   * Constructor for Knapsack.
   * Initialises knapsack to random items by
   * 0's and 1's to represent if item is in
   * knapsack.
   * @param values
   * @param weights
   * @param length
   * @param capacity
   */
  public Knapsack(int[] values, int[] weights, int length, int capacity) {
    this.values = values;
    this.weights = weights;
    this.capacity = capacity;
    this.inKnapsack = new int[length];

    for (int i = 0; i < length; i++) {
      inKnapsack[i] = (int) Math.round(Math.random());
    }

    setFitness();
  }

  /**
   * Mutate method for Knapsack
   * @param mutationRate chance to mutate
   */
  public void mutate(double mutationRate) {
    for (int i = 0; i < inKnapsack.length; i++) {
      if(Math.random() < mutationRate) {
        inKnapsack[i] = (inKnapsack[i] == 1 ? 0 : 1);
      }
    }
  }

  /**
   * Method to set/calculate the fitness of knapsack.
   * If item is in knapsack (1 at index of inKnapsack)
   * add weight at index to sumOfWeights
   * add values at index to sumOfValues
   * If sumOfWeights is less than or equal to capacity
   * set fitness to sumOfValues; else 0.
   */
  public void setFitness() {
    this.fitness = 0;

    int sumOfValues = 0;
    int sumOfWeights = 0;

    for (int i = 0; i < inKnapsack.length; i++) {
      if (inKnapsack[i] == 1) {
        sumOfValues += values[i];
        sumOfWeights += weights[i];
      }
    }

    this.fitness = (sumOfWeights <= capacity ? sumOfValues : 0);
  }

  /**
   * Method to get Fitness
   * @return fitness
   */
  public int getFitness() {
    return fitness;
  }

  /**
   * Method to get values
   * @return array of values
   */
  public int[] getValues() {
    return values;
  }

  /**
   * Method to get weights
   * @return array of weights
   */
  public int[] getWeights() {
    return weights;
  }

  /**
   * Method to get inKnapsack
   * @return array of inKnapsack
   */
  public int[] getInKnapsack() {
    return inKnapsack;
  }

  /**
   * Method to set inKnapsack
   * @param inKnapsack array of inKnapsack
   */
  public void setInKnapsack(int[] inKnapsack) {
    this.inKnapsack = inKnapsack;
  }

  /**
   * Override toString method.
   * Returns String of items (Value & Weight) in knapsack
   * @return
   */
  @Override
  public String toString() {
    String toReturn = "Items in Knapsack:\n";
    for (int i = 0; i < inKnapsack.length; i++) {
      if (inKnapsack[i] == 1) {
        toReturn += "Value: " + values[i] + "\tWeight: " + weights[i] + "\n";
      }
    }

    return toReturn;
  }
}
