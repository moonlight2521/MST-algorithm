import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Zun Lin on 11/28/17.
 * CMSC 401
 * Assignment 4
 */
public class cmsc401 {
    public static void main(String[] args){
        int i, k, x, y;
        Scanner in = new Scanner(System.in);
        int cities = in.nextInt();
        int highways = in.nextInt();
        int motel[]= new int [cities];
        int gasPrice [][] = new int [cities][cities];
        //home and L.A cost is zero.
        motel[0]=0;
        motel[1]=0;
        for(i=0; i < cities-2; i++){
            k = in.nextInt()-1;
            motel[k] = in.nextInt();
        }

        for(i=0; i<highways; i++){
             x = in.nextInt()-1;
             y = in.nextInt()-1;
             gasPrice[x][y] = in.nextInt();
             gasPrice[y][x] = gasPrice[x][y];
        }

        //key values used to pick minimum weight edges
        int key [] = new int [cities];
        //represent the set of vertices that is not in the MST
        Boolean mstSet [] = new Boolean[cities];
        //Initialize all keys as infinite
        for (i=0; i<cities; i++){
            key[i]= Integer.MAX_VALUE;
            mstSet[i] =false;
        }
        //get key[0] to 0 so vertex is it is pick first
        key[0]= 0;
        //the vertices between the cities:
       for(i=0;i<cities-1;i++){
           //pick the minimum key vertex for the mstSet
           x = minKey(key, mstSet, cities);
           //add the minimum key to mstSet
           mstSet[x]=true;

           //Pick the less cost path:
           for ( y = 0; y < gasPrice.length; y++)
           {
               if (!mstSet[y] && gasPrice[x][y]!= 0 && key[x] != Integer.MAX_VALUE)
               {
                   if (key[x]+gasPrice[x][y]+motel[x] < key[y])
                   {
                       key[y] = key[x] + gasPrice[x][y] + motel[x];
                   }
               }
           }
        }
        System.out.println(key[1]);
    }

    //find the vertex with minimum key value
    public static int minKey(int key[], Boolean mstSet[], int length){
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        int i;
        for(i=0; i<length; i++ ){
            if(mstSet[i] == false && key[i] < min){
                min = key[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}

