package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Crossover {
    Gene first;
    Gene second;
    int type;

    Gene FinalChild1;
    Gene FinalChild2;

    public Crossover(Gene first, Gene second, int type) {
        this.first = first;
        this.second = second;
        this.type = type;
    }

    public Gene getFirstChild() {
        if(FinalChild1==null)
            System.out.println("NULL");
        return FinalChild1;
    }

    public Gene getSecondChild() {
        if(FinalChild2==null)
            System.out.println("NULL");
        return FinalChild2;
    }

    public void execute() {
        if(type==1){
            Random rand = new Random();
            int windowsSize = rand.nextInt(CONSTANTS.WINDOW_SIZE_LIMIT)+3;
            int startPt = rand.nextInt(first.getSequence().length-windowsSize);

            int[] part1 = Arrays.copyOfRange(first.getSequence(),startPt,startPt+windowsSize);
            int[] part2 = Arrays.copyOfRange(second.getSequence(),startPt,startPt+windowsSize);

            Gene c1 = new Gene(first.getSequence().length);
            Gene c2 = new Gene(second.getSequence().length);



            //Copy the part 1 and part 2
            for(int i=startPt;i<startPt+windowsSize;i++){
                c1.sequence[i] = part2[i-startPt];
            }
            //Copy the part 1 and part 2
            for(int i=startPt;i<startPt+windowsSize;i++){
                c2.sequence[i] = part1[i-startPt];

            }


        //    System.out.println("Crossover half done c1: " + Arrays.toString(c1.getSequence()));
          //  System.out.println("Crossover half done c2: " + Arrays.toString(c2.getSequence()));

            //remaining except duplicate
            for(int i=0;i<c1.getSequence().length;i++){
                if(c1.getSequence()[i]==-1)
                    if(!ArrayHas(c1.getSequence(),first.getSequence()[i])){
                        c1.sequence[i] = first.getSequence()[i];
                    }

                if(c2.getSequence()[i]==-1)
                    if(!ArrayHas(c2.getSequence(),second.getSequence()[i])){
                        c2.sequence[i] = second.getSequence()[i];
                    }
            }
//            System.out.println("Crossover half 2 done c1: " + Arrays.toString(c1.getSequence()));
  //          System.out.println("Crossover half 2 done c2: " + Arrays.toString(c2.getSequence()));

            //remaining
            for (int i=0;i<first.sequence.length;i++){
                if(!ArrayHas(c1.getSequence(),first.getSequence()[i])){
                    int x = getFirstIndexof(-1,c1.getSequence());
                    c1.sequence[x] =  first.getSequence()[i];
                }
                if(!ArrayHas(c2.getSequence(),second.getSequence()[i])){
                    c2.sequence[getFirstIndexof(-1,c2.getSequence())] =  second.getSequence()[i];
                }
            }

            //System.out.println("Crossover done c1: " + Arrays.toString(c1.getSequence()));
            //System.out.println("Crossover done c2: " + Arrays.toString(c2.getSequence()));
            FinalChild1 = c1;
            FinalChild2 = c2;

            if(c1.isValidGene()||c2.isValidGene())
            {}else{
                try {
                    throw new Exception("INVALID GENE CREATED"+c1.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getFirstIndexof(int i, int[] sequence) {
        for(int j =0;j<sequence.length;j++){
            if(sequence[j]==i)
                return j;
        }
        return -1;
    }

    private boolean ArrayHas(int[] sequence, int n) {
        for (int i=0;i<sequence.length;i++){
            if(n==sequence[i])
                return true;
        }
        return false;
    }
}
