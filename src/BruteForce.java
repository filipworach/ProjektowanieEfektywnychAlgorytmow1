import java.util.LinkedList;
import java.util.Scanner;

public class BruteForce {
    Data data;

    public BruteForce(Data data) {
        this.data = data;
    }

    private int dynamicProgramming(LinkedList<Integer> remainingVertexes, int vertex) {
        int distance = Integer.MAX_VALUE;
        LinkedList<Integer> copyOfRemainingVertexes;
        copyOfRemainingVertexes = (LinkedList<Integer>) remainingVertexes.clone();
        int sizeOfcopyOfRemainingVertexes = copyOfRemainingVertexes.size();
        if (copyOfRemainingVertexes.size() == 1) {
            return data.getDistance(vertex, copyOfRemainingVertexes.get(0)) + data.getDistance(copyOfRemainingVertexes.get(0),0);
        } else if (copyOfRemainingVertexes.size() > 0){
            for(int i = 0; i < sizeOfcopyOfRemainingVertexes; i++) {
                copyOfRemainingVertexes = (LinkedList<Integer>) remainingVertexes.clone();
                int tempVertex = copyOfRemainingVertexes.get(i);
                copyOfRemainingVertexes.remove(i);
                distance = Math.min(data.getDistance(vertex, tempVertex) + dynamicProgramming(copyOfRemainingVertexes,tempVertex), distance);
            }
        }
        return distance;
    }


    public void findTheShortestPath() {
        LinkedList<Integer> remainingVertexes = new LinkedList<>();
        int howManyVertexes = data.getHowManyVertexes();
        for (int i = 1; i < howManyVertexes; i++) remainingVertexes.add(i);
        int result = dynamicProgramming(remainingVertexes, 0);
        System.out.println(result);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
