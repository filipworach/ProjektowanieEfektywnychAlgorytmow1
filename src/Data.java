import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Data {
    private int[][] distances;
    private int howManyVertices;

    public int getDistance(int i, int j) {
        return distances[i][j];
    }

    public int getHowManyVertices() {
        return howManyVertices;
    }

    public void printMatrixOfDistances() {
        System.out.println("Wierzcholkow w grafie jest: " + howManyVertices);
        for(int i = 0; i < howManyVertices; i++) {
            for(int j = 0; j < howManyVertices; j++) {
                System.out.print(distances[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public Data(int howManyVertices, int range) {
        this.howManyVertices = howManyVertices;
        distances = new int[howManyVertices][howManyVertices];
        Random random = new Random();
        for(int i = 0; i < howManyVertices; i++) {
            for(int j = 0; j < howManyVertices; j++) {
                if(i!=j)distances[i][j] = random.nextInt(range) + 1;
                else distances[i][j] = -1;
            }
        }
    }

    public Data(String pathName) {
        try {
            File file = new File(pathName);
            Scanner scanner = new Scanner(file);
            String text = scanner.nextLine();
            int howManyCities = Integer.parseInt(text);
            howManyVertices = howManyCities;
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
       /*Data data = new Data("C:\\Users\\filip\\OneDrive\\Pulpit\\dystanse.txt");
        *//*DynamicBruteForce dynamicBruteForce = new DynamicBruteForce(data);
        dynamicBruteForce.findTheShortestPath();*//*

        BruteForce bruteForce = new BruteForce(data);
        bruteForce.runBruteForce();*/
        Menu menu = new Menu();
        menu.mainMenu();

    }

}
