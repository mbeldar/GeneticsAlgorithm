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
        int[][] xx = first.firstPopulation(5000);
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
        int[][] x = new int[10][10];
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
        {
            x[i][j] = src.nextInt();
        }
        return x;
    }

    private static int[][] getFromFile() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int x[][] = new int[10][10];;
        int i=0, j=0;

        while(scanner.hasNextInt()){
            while(j<10)
                x[i][j++] = scanner.nextInt();
            j = 0;
            i++;
        }
        return x;
    }
}
