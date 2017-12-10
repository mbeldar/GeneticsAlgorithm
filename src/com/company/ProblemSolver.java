package com.company;

import java.util.Arrays;
import java.util.Random;

public class ProblemSolver {
    int[][] problemGrid;
    int[][] p;
    Gene[] genes;
    Tournament tournaments[];
    public ProblemSolver(int[][] problemGrid) {
        this.problemGrid = problemGrid;
    }


    public int[][] firstPopulation(int n){
        int[][] p = new int[n][10];
        for(int i=0;i<n;i++)
        {
            for (int j=0;j<10;j++){
                p[i][j]=j;
            }
            p[i] = shuffleArray(p[i]);
        }
        this.p = p;
        return p;
    }



    public static int[] shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
        return a;
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    public void generateGenes() {
        genes = new Gene[p.length];
        for (int i=0;i<p.length;i++){
            Gene gene = new Gene(p[i]);
            genes[i] = gene;
        }
    }

    public void getFitnessScore() {
        int smallest=99999;
        int sum=0;
        for(int i=0;i<genes.length;i++){
            int fscore = (int) calculateFitness(genes[i]);
            sum += fscore;
            if(smallest>fscore)
                smallest=fscore;
            genes[i].setFitScore(fscore);
        }
        System.out.println("Smallest fitnest is: "+smallest);
        System.out.println("Avg fitness: "+sum/genes.length);
    }

    private float calculateFitness(Gene gene) {
        float cost = 0;
        int[] seq = gene.getSequence();
        for(int i=0;i<seq.length-1;i++){
           // System.out.println(problemGrid[seq[i]][seq[i+1]]);
            cost += problemGrid[seq[i]][seq[i+1]];
        }
        //System.out.println(cost+"\n------");
        return cost;
    }


    public void selection() {
        int numOfTournaments = getEvenRandomNumber();
        int genesPerTournament = genes.length/numOfTournaments;
       // System.out.println("Number of tournaments: "+numOfTournaments);
       // System.out.println("Genes per tournament: "+genesPerTournament);
        tournaments = new Tournament[numOfTournaments];

        for (int i=0;i<numOfTournaments;i++)
        {
            int from = i*genesPerTournament;
            int to = from+genesPerTournament;
            Gene[] g = Arrays.copyOfRange(genes,from, to);
            Tournament t = new Tournament(g,null,null,i);
           // System.out.println(t.toString());
            tournaments[i] = t;
        }

        //best of half from each TRNMNT

        for (int i=0;i<tournaments.length;i++){
            tournaments[i].parentSelection();
        }


    }

    private int getEvenRandomNumber() {
        Random n = new Random();
        int RandInt = n.nextInt(8)+2;
        //choosing good randint so that we get total even # like 125*4 = 500;
        if((genes.length/RandInt)%2==0&&RandInt%2==0&&genes.length%RandInt==0)
            return RandInt;
        else
            return getEvenRandomNumber();

    }

    public Gene[] mating() {
        int count=0;
        Gene[] newGeneration = new Gene[genes.length];
       // System.out.println("Genes.length: "+genes.length);
        for(int i=0;i<tournaments.length;i++)
        {
          //  System.out.println("Tournament: "+i);
            Gene genesFromTournament[] = tournaments[i].winners;
          //  System.out.println("genesFromTournament.length: "+genesFromTournament.length);
            for(int j=0;j<genesFromTournament.length;j++){
               // System.out.println("Gene num "+j);
                Random rand = new Random();
                int firstParent = rand.nextInt(genesFromTournament.length);
                int secondParent = rand.nextInt(genesFromTournament.length);

                Crossover crossover = new Crossover(genesFromTournament[firstParent],genesFromTournament[secondParent],1);
                crossover.execute();
                Gene child1 = crossover.getFirstChild();
                Gene child2 = crossover.getSecondChild();

                //newGeneration[count++] = genesFromTournament[firstParent];
                //newGeneration[count++] = genesFromTournament[secondParent];
                newGeneration[count++] = child1;
                newGeneration[count++] = child2;
              //  System.out.println(count);

            }
        }

        return newGeneration;
    }


    public Gene[] mutateEveryone(Gene[] newGen) {
       // System.out.println("New generation has "+newGen.length + " elements\nLet's start mutation");
        Random rand = new Random();
        for(int i=0;i<newGen.length;i++){
           // System.out.println(Arrays.toString(newGen[i].sequence));
            int f = rand.nextInt(10);
            int s = rand.nextInt(10);
            int t = newGen[i].sequence[f];
            newGen[i].sequence[f] = newGen[i].sequence[s];
            newGen[i].sequence[s] = t;
          // System.out.println(Arrays.toString(newGen[i].sequence));
        }

        return newGen;
    }

    public void generateGenes(Gene[] newGenFinal) {
        genes = newGenFinal;
    }
}
