import java.io.*;
import java.util.ArrayList;

public class D7 {
    private static BufferedReader dataReader() {
        File file = new File("/Users/tingyi/code/AoC/2021/d7-data.txt");

        try {
            BufferedReader data = new BufferedReader(new FileReader(file));
            return data;
        } catch (FileNotFoundException e) {
             System.out.println("The file does not exist.");
             return null;
        }
    }

    private static void part1() throws IOException {
        BufferedReader inputData = dataReader();
        ArrayList<Integer> crabSubmarines = new ArrayList<>();
        int largestX = 0;
        int smallestX = 0;
        int idx = 0;
        for (String crabLocation : inputData.readLine().split(",")) {
            int convertCrabLocationToNum = Integer.parseInt(crabLocation);
            crabSubmarines.add(convertCrabLocationToNum);
            largestX = convertCrabLocationToNum > largestX ? convertCrabLocationToNum : largestX;
            if (idx == 0) {
                smallestX = convertCrabLocationToNum;
                idx += 1;
            } else {
                smallestX = convertCrabLocationToNum < smallestX ? convertCrabLocationToNum : smallestX;
            }
        }
        
        int cheapestFuelCost = 0;
        while (smallestX <= largestX) {
            int tempCost = 0;
            for (Integer crabLocation : crabSubmarines) {
                tempCost += Math.abs(crabLocation - smallestX);
            }
            if (idx == 1) {
                cheapestFuelCost = tempCost;
                idx += 1;
            } else {
                cheapestFuelCost = cheapestFuelCost < tempCost ? cheapestFuelCost : tempCost;
            }
            smallestX += 1;
        }
        System.out.println(String.format("part 1: %s", cheapestFuelCost));        
    }

    private static void part2() throws IOException {
        BufferedReader inputData = dataReader();
        ArrayList<Integer> crabSubmarines = new ArrayList<>();
        int largestX = 0;
        int smallestX = 0;
        int idx = 0;
        for (String crabLocation : inputData.readLine().split(",")) {
            int convertCrabLocationToNum = Integer.parseInt(crabLocation);
            crabSubmarines.add(convertCrabLocationToNum);
            largestX = convertCrabLocationToNum > largestX ? convertCrabLocationToNum : largestX;
            if (idx == 0) {
                smallestX = convertCrabLocationToNum;
                idx += 1;
            } else {
                smallestX = convertCrabLocationToNum < smallestX ? convertCrabLocationToNum : smallestX;
            }
        }
        
        int cheapestFuelCost = 0;
        while (smallestX <= largestX) {
            int tempCost = 0;
            for (Integer crabLocation : crabSubmarines) {
                tempCost += fuelCalculation(smallestX, crabLocation);
            }
            if (idx == 1) {
                cheapestFuelCost = tempCost;
                idx += 1;
            } else {
                cheapestFuelCost = cheapestFuelCost < tempCost ? cheapestFuelCost : tempCost;
            }
            smallestX += 1;
        }
        System.out.println(String.format("part 2: %s", cheapestFuelCost));        
    }

    private static int fuelCalculation(Integer aimX, Integer crabLocation) {
        int sum = 0;
        int addUp = 1;
        int largerX = crabLocation > aimX ? crabLocation : aimX;
        int smallerX = largerX == crabLocation ? aimX : crabLocation;
        while (largerX > smallerX) {
            smallerX += 1;
            sum += addUp;
            addUp += 1;
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }
}