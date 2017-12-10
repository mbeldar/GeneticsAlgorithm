package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Gene {
    int[] sequence;
    float fitScore;
    float weight;
    int generation;
    String species;

    public Gene(int[] sequence) {
        this.sequence = sequence;
    }

    public Gene(int n) {
        sequence = new int[n];
        for(int i=0;i<n;i++){
            sequence[i] = -1;
        }
    }

    @Override
    public String toString() {
        return "Gene{" +
                "sequence=" + Arrays.toString(sequence) +
                ", fitScore=" + fitScore +
                ", weight=" + weight +
                ", generation=" + generation +
                ", species='" + species + '\'' +
                '}';
    }

    public Gene(int[] sequence, float fitScore, float weight, int generation, String species) {
        this.sequence = sequence;
        this.fitScore = fitScore;
        this.weight = weight;
        this.generation = generation;
        this.species = species;
    }

    public int[] getSequence() {
        return sequence;
    }

    public void setSequence(int[] sequence) {
        this.sequence = sequence;
    }

    public float getFitScore() {
        return fitScore;
    }

    public void setFitScore(float fitScore) {
        this.fitScore = fitScore;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isValidGene() {

            Set<Integer> set = new HashSet<Integer>();
            for(int i : sequence)
                if(!set.add(i))
                    return false;
            return true;

    }
}
