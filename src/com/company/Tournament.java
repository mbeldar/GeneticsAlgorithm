package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Tournament {
    Gene[] allGenes;
    Gene[] winners;
    Gene[] matingDone;
    int ID;

    public Tournament(Gene[] allGenes, Gene[] winners, Gene[] MatingDone, int ID) {
        this.allGenes = allGenes;
        this.winners = winners;
        this.matingDone = MatingDone;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "allGenes=" + Arrays.toString(allGenes) +
                ", winners=" + Arrays.toString(winners) +
                ", losers=" + Arrays.toString(matingDone) +
                '}';
    }

    public void parentSelection() {
        for (int i=0;i<allGenes.length;i++){
            for (int j=i;j<allGenes.length;j++){
               if(allGenes[i].getFitScore()>allGenes[j].getFitScore()){
                   Gene t = allGenes[i];
                   allGenes[i] = allGenes[j];
                   allGenes[j] = t;
               }
            }
        }
        winners = Arrays.copyOfRange(allGenes,0,allGenes.length/2);
   //     System.out.println(winners.length+" winners selected from the tournament #"+ID);
    }
}
