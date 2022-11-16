import java.util.ArrayList;
import java.util.LinkedList;

public class BruteForce {
    private Data data;
    private ArrayList<ArrayList<Integer>> arrayOfPermutations = new ArrayList<>();
    private ArrayList<Integer> vertices = new ArrayList<>();
    private int shortestPath = Integer.MAX_VALUE;
    private ArrayList<Integer> path = new ArrayList<>();

    public BruteForce(Data data) {
        this.data = data;
        int verticesSize = data.getHowManyVertices() - 1;
        for (int i = 0; i < verticesSize; i++) {
            vertices.add(i + 1);
        }

    }

    public int getShortestPath() {
        return shortestPath;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public void generatePermutations(int n, ArrayList<Integer> elements) {

        if (n == 1) {
            findTheShortest(elements);
        } else {
            for (int i = 0; i < n - 1; i++) {
                generatePermutations(n - 1, elements);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            generatePermutations(n - 1, elements);
        }
    }

    private void swap(ArrayList<Integer> elements, int a, int b) {
        int i = elements.get(a);
        elements.set(a, elements.get(b));
        elements.set(b, i);
    }


    private void printArraysToArray(ArrayList<Integer> input) {
        arrayOfPermutations.add((ArrayList<Integer>) input.clone());
    }

    /*public ArrayList<int[]> getArrayOfPermutations() {
        return arrayOfPermutations;
    }*/


    public void runBruteForce() {
        generatePermutations(vertices.size(), vertices);
        System.out.println("Koszt: " + this.getShortestPath());
        printPath(this.getPath());
    }

    private void printPath(ArrayList<Integer> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer arrayList1 : arrayList) {
            stringBuilder.append(arrayList1);
            stringBuilder.append("->");
        }
        System.out.println(stringBuilder.substring(0,stringBuilder.length()-2));
    }

    private void findTheShortest(ArrayList<Integer> elements) {
        int actualDistance = 0;
        int elementsSize = elements.size();
        actualDistance += data.getDistance(0, elements.get(0));
        for(int i = 0; i < elementsSize - 1; i++) {
            actualDistance += data.getDistance(elements.get(i), elements.get(i+1));
        }
        actualDistance += data.getDistance(elements.get(elementsSize-1), 0);
        if(this.shortestPath > actualDistance){
            this.shortestPath = actualDistance;
            this.path = (ArrayList<Integer>) elements.clone();
            this.path.add(0, 0);
            this.path.add(path.size(),0);
        }
    }

   /* private int findTheShortestPath() {
        int distance = Integer.MAX_VALUE;
        int actualDistance = 0;
        ArrayList<Integer> path = new ArrayList<>();
        int arrayOfPermutationsSize = arrayOfPermutations.size();
        int verticesSize = vertices.size();
        for (int i = 0; i < arrayOfPermutationsSize; i++) {
            actualDistance = 0;
            for (int j = 0; j < verticesSize; j++) {
                    if (j == 0) {
                        actualDistance += data.getDistance(0, arrayOfPermutations.get(i).get(j));
                    } else
                        actualDistance += data.getDistance(arrayOfPermutations.get(i).get(j - 1), arrayOfPermutations.get(i).get(j));
                    if (j == verticesSize - 1)
                        actualDistance += data.getDistance(arrayOfPermutations.get(i).get(j), 0);
            }
            if (actualDistance < distance) {
                distance = actualDistance;
                path = arrayOfPermutations.get(i);
            }
        }
        path.add(0, 0);
        path.add(path.size(), 0);
        printPath(path);

        return distance;
    }*/
}
