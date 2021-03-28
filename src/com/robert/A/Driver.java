package com.robert.A;

import java.util.Random;

public class Driver {

    /**
     * Size of the population
     */
    private static int size = 100;

    /**
     * Population of DNA
     */
    private static DNA[] population;

    /**
     * Mating pool of DNA
     */
    private static DNA[] matingPool;

    public static void main(String[] args) {
        System.out.println("Part a - i\n");
        partA1(20);
        System.out.println("\nPart a - ii\n");
        partA2(20);
        System.out.println("\nPart a - iii\n");
        partA3(20);
    }

    public static void partA1(int generations) {
        // Setup
        population = new DNA[size];
        matingPool = new DNA[size];

        // Initialise Population
        for (int i = 0; i < size; i++) {
            population[i] = new DNA(20);
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

            // Crossover
            for (int i = 0; i < size; i++) {
                Random r = new Random();
                int idx1 = r.nextInt(size);
                int idx2 = r.nextInt(size);

                DNA parentA = matingPool[idx1];
                DNA parentB = matingPool[idx2];

                int[] parentAGenes = parentA.getGenes();
                int[] parentBGenes = parentB.getGenes();

                int[] childGenes = new int[20];

                int crossOverPoint =  (int)(Math.random() * (20 + 1));

                for (int j = 0; j < crossOverPoint; j++) {
                    childGenes[j] = parentAGenes[j];
                }

                for (int j = crossOverPoint; j < 20; j++) {
                    childGenes[j] = parentBGenes[j];
                }

                DNA child = new DNA(20);
                child.setGenes(childGenes);
                child.mutate(0.01);
                child.setFitness();

                int childFitness = child.getFitness();
                averageFitness += childFitness;
                maxFitness = (childFitness > maxFitness ? childFitness : maxFitness);

                population[i] = child;
            }

            ++generation;
            System.out.println("Generation: " + generation +
                "\t\tAverage: " + Math.ceil((double) averageFitness / (double) size) +
                "\t\tMax: " + maxFitness);
        }
    }

    public static void partA2(int generations) {
        // Setup
        population = new DNA[size];
        matingPool = new DNA[size];
        int[] targetFitness = {0,1,0,1,1,1,1,0,0,0,1,0,0,1,0,1,1,1,0,0};
        System.out.println(targetFitness.length);

        // Initialize Population
        for (int i = 0; i < size; i++) {
            population[i] = new DNA(20, targetFitness);
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

            // Crossover & Mutation of Child
            for (int i = 0; i < size; i++) {
                Random r = new Random();
                int idx1 = r.nextInt(size);
                int idx2 = r.nextInt(size);

                DNA parentA = matingPool[idx1];
                DNA parentB = matingPool[idx2];

                int[] parentAGenes = parentA.getGenes();
                int[] parentBGenes = parentB.getGenes();

                int[] childGenes = new int[20];

                int crossOverPoint =  (int)(Math.random() * (20 + 1));

                for (int j = 0; j < crossOverPoint; j++) {
                    childGenes[j] = parentAGenes[j];
                }

                for (int j = crossOverPoint; j < 20; j++) {
                    childGenes[j] = parentBGenes[j];
                }

                DNA child = new DNA(20);
                child.setGenes(childGenes);
                child.mutate(0.01);
                child.setTargetFitness(targetFitness);

                int childFitness = child.getFitness();
                averageFitness += childFitness;
                maxFitness = (childFitness > maxFitness ? childFitness : maxFitness);

                population[i] = child;
            }

            ++generation;
            System.out.println("Generation: " + generation +
                "\t\tAverage: " + Math.ceil((double) averageFitness / (double) size) +
                "\t\tMax: " + maxFitness);
        }
    }

    public static void partA3(int generations) {
        // Setup
        population = new DNA[size];
        matingPool = new DNA[size];

        int[] zeroSolution = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        // Initialize Population
        for (int i = 0; i < size; i++) {
            population[i] = new DNA(20, 20);
            if (i < size / 4) {
                population[i].setGenes(zeroSolution);
                population[i].setDeceptiveLandscapeFitness(size);
            }
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

            // Crossover
            for (int i = 0; i < size; i++) {
                Random r = new Random();
                int idx1 = r.nextInt(size);
                int idx2 = r.nextInt(size);

                DNA parentA = matingPool[idx1];
                DNA parentB = matingPool[idx2];

                int[] parentAGenes = parentA.getGenes();
                int[] parentBGenes = parentB.getGenes();

                int[] childGenes = new int[20];

                int crossOverPoint =  (int)(Math.random() * (20 + 1));

                for (int j = 0; j < crossOverPoint; j++) {
                    childGenes[j] = parentAGenes[j];
                }

                for (int j = crossOverPoint; j < 20; j++) {
                    childGenes[j] = parentBGenes[j];
                }

                DNA child = new DNA(20);
                child.setGenes(childGenes);
                child.mutate(0.01);
                child.setDeceptiveLandscapeFitness(20);

                int childFitness = child.getFitness();
                averageFitness += childFitness;
                maxFitness = (childFitness > maxFitness ? childFitness : maxFitness);

                population[i] = child;
            }

            ++generation;
            System.out.println("Generation: " + generation +
                "\t\tAverage: " + Math.ceil((double) averageFitness / (double) size) +
                "\t\tMax: " + maxFitness);
        }
    }



}
