import java.util.ArrayList;
import java.util.Arrays;

public class BruteForce {
    private Data data;
    private ArrayList<int[]> arrayOfPermutations = new ArrayList<>();
    private int[] vertexes;

    public BruteForce(Data data) {
        this.data = data;
        vertexes = new int[data.getHowManyVertexes()-1];
        for(int i = 0; i < vertexes.length; i++) {
            vertexes[i] = i + 1;
        }

    }

    public void printAllRecursive(int n, int[] elements) {

        if(n == 1) {
            printArraystoArray(elements);
        } else {
            for(int i = 0; i < n-1; i++) {
                printAllRecursive(n - 1, elements);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            printAllRecursive(n - 1, elements);
        }
    }

    private void swap(int[] elements, int a, int b) {
        int i = elements[a];
        elements[a] = elements[b];
        elements[b] = i;
    }


    private void printArraystoArray(int[] input) {
        arrayOfPermutations.add(input.clone());
    }

    public ArrayList<int[]> getArrayOfPermutations() {
        return arrayOfPermutations;
    }


    public void runBruteForce() {
        printAllRecursive(vertexes.length, vertexes);
        System.out.println(findTheShortestPath());
    }

    private int findTheShortestPath() {
        int distance = Integer.MAX_VALUE;
        int actualDistance = 0;
        int [] path = new int[vertexes.length + 1];
        for(int i = 0; i < arrayOfPermutations.size(); i++) {
            actualDistance = 0;
            for(int j = 0; j < vertexes.length; j++) {
                if(j==0) {
                    actualDistance += data.getDistance(0,arrayOfPermutations.get(i)[j]);
                }
                else actualDistance += data.getDistance(arrayOfPermutations.get(i)[j-1], arrayOfPermutations.get(i)[j]);
                if(j == vertexes.length - 1) actualDistance += data.getDistance(arrayOfPermutations.get(i)[j], 0);
            }
            if(actualDistance < distance) {
                distance = actualDistance;
                path = arrayOfPermutations.get(i);
            }
        }
        for (int i = 0; i < path.length; i++) {
            System.out.println(path[i]);
        }
        return distance;
    }
}
