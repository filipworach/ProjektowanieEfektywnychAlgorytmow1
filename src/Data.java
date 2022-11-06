import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    private int[][] distances;
    private int howManyVertexes;

    public int getDistance(int i, int j) {
        return distances[i][j];
    }

    public int getHowManyVertexes() {
        return howManyVertexes;
    }

    public Data(String pathName) {
        try {
            File file = new File(pathName);
            Scanner scanner = new Scanner(file);
            String text = scanner.nextLine();
            int howManyCities = Integer.parseInt(text);
            howManyVertexes = howManyCities;
            int[][] distancesArray = new int[howManyCities][howManyCities];
            String[] distance;
            for (int i = 0; i < howManyCities; i++) {
                text = scanner.nextLine();
                distance = text.split("\\s+");
                if(distance[0].equals("")) System.arraycopy(distance,1,distance, 0,distance.length-1);
                for (int j = 0; j < howManyCities; j++) {
                    distancesArray[i][j] = Integer.parseInt(distance[j]);
                }
            }
            distances = distancesArray;
        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //oczekiwany wynik 10
       Data data = new Data("C:\\Users\\filip\\OneDrive\\Pulpit\\dystanse.txt");
        /*DynamicBruteForce dynamicBruteForce = new DynamicBruteForce(data);
        dynamicBruteForce.findTheShortestPath();*/

        BruteForce bruteForce = new BruteForce(data);
        bruteForce.runBruteForce();

    }

}
