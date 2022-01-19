package net.slipcor.pvparena.utils;

public class MergeSort {

    public Tuple[] arrayToSort;
    public Tuple[] sortedArray;

    public MergeSort(Tuple[] arrayToSort){
       this.arrayToSort = arrayToSort;
       this.sortedArray = null;
    }

    public void sortByPlayerBal(){
        sortedArray = mergeSort(arrayToSort);
    }

    public Tuple[] getSortedArray(){
        if (sortedArray != null){
            return sortedArray;
        } else {
            System.out.println("sort first");
            return null;
        }
    }

    public String getRichestPlayer(){
        return sortedArray[sortedArray.length-1].getStr();
    }

    private Tuple[] mergeSort(Tuple[] playerBalTuple){
        if (playerBalTuple.length < 2){
            return playerBalTuple;
        }
        int length = playerBalTuple.length;
        Tuple[] lowerSec = newArray(playerBalTuple, 0, length/2);
        Tuple[] upperSec = newArray(playerBalTuple, length/2, length);
        Tuple[] lowerSorted = mergeSort(lowerSec);
        Tuple[] upperSorted = mergeSort(upperSec);
        return sort(lowerSorted, upperSorted);
    }

    private Tuple[] sort(Tuple[] A, Tuple[] B){
        Tuple[] aux = new Tuple[A.length + B.length];

        int i=0;
        int j=0;
        int k=0;
        while (k<A.length + B.length){
            if (A[i].getNum() < B[j].getNum()){
                aux[k] = A[i];
                i++;
            } else if (B[j].getNum() < A[i].getNum()){
               aux[k] = B[j];
               j++;
            } else {
                aux[k] = A[i];
                i++;
            }
            k++;
        }
        return aux;
    }

    // inclusive from, exclusive to [to, from)
    private Tuple[] newArray(Tuple[] origin, int from, int to){
       Tuple[] newTuple =  new Tuple[to-from];
       for (int i=from;i<to;i++){
           newTuple[i] = origin[i];
       }
       return newTuple;
    }
}
