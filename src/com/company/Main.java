package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    static Scanner src = new Scanner(System.in);
    public static void main(String[] args) {
	// write your code here
        int[][] problemGrid = getProblem();
        printGrid(problemGrid);
        ProblemSolver first;
        Gene[] newGenFinal = null;
        boolean isFirstTime = true;
        first = new ProblemSolver(problemGrid);
        int[][] xx = first.firstPopulation(2000);
        //printGrid(xx);


        while(true) {
            if(isFirstTime) {
                first.generateGenes();
                isFirstTime=false;
            }
            else {
                first.generateGenes(newGenFinal);
            }
            first.getFitnessScore();


            first.selection();


            Gene[] newGen = first.mating();
            newGenFinal = first.mutateEveryone(newGen);
        }


    }

    private static void printGrid(int[][] problemGrid) {
        System.out.println("\n***\nThe Grid:");
        for(int i=0;i<problemGrid.length;i++)
        {
            for(int j=0;j<problemGrid[1].length;j++)
                System.out.print(problemGrid[i][j]+"\t");
            System.out.println();
        }
    }

    private static int[][] getProblem() {

        System.out.println("Option 1: Read file.\nOption 2: I will type row wise.");
        switch (src.nextLine()){
            case "1":
                return getFromFile();
            case "2":
                return getFromKeyboard();
            default: return getProblem();
        }
    }

    private static int[][] getFromKeyboard() {
        int[][] x = new int[CONSTANTS.MAX_SIZE][CONSTANTS.MAX_SIZE];
        for(int i=0;i<CONSTANTS.MAX_SIZE;i++)
            for(int j=0;j<CONSTANTS.MAX_SIZE;j++)
        {
            x[i][j] = src.nextInt();
        }
        return x;
    }

    private static int[][] getFromFile() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input30.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int x[][] = new int[CONSTANTS.MAX_SIZE][CONSTANTS.MAX_SIZE];;
        int i=0, j=0;

        while(scanner.hasNextInt()){
            while(j<CONSTANTS.MAX_SIZE)
                x[i][j++] = scanner.nextInt();
            j = 0;
            i++;
        }
        return x;
    }
}
