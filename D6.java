import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class D6 {
    private static BufferedReader dataReader() {
        File file = new File("pathToYourPuzzle");

        try {
            BufferedReader data = new BufferedReader(new FileReader(file));
            return data;
        } catch (FileNotFoundException e) {
             System.out.println("The file does not exist.");
             return null;
        }
    }

    private static long growthCalculation(int totalDays, ArrayList<Integer> originalFishNumbers) {
        HashMap<Integer, Long> fishList = fishLifeCycleList(originalFishNumbers);
        while (totalDays > 0) {
            HashMap<Integer, Long> tempFishLifeCycleList = new HashMap<>();
            for (Integer fishLifeCycle : fishList.keySet()) {
                if (fishLifeCycle > 0 && fishLifeCycle != 7) {
                    tempFishLifeCycleList.put(fishLifeCycle - 1, fishList.get(fishLifeCycle));
                } else if (fishLifeCycle == 0) {
                    tempFishLifeCycleList.put(8, fishList.get(fishLifeCycle));
                    Long originalSeven = fishList.containsKey(7) ? fishList.get(7) : 0;
                    Long totalSix = fishList.get(fishLifeCycle) + originalSeven;
                    tempFishLifeCycleList.put(6, totalSix);
                } else {
                    if (!(tempFishLifeCycleList.containsKey(6))) {
                        tempFishLifeCycleList.put(fishLifeCycle - 1, fishList.get(fishLifeCycle));
                    }
                }
            } 

            fishList = tempFishLifeCycleList;
            totalDays -= 1;
        }

        long totalFishes = 0;
        for (Long fishNumber : fishList.values()) {
            totalFishes += fishNumber;
        }
        return totalFishes;
    }

    private static HashMap<Integer, Long> fishLifeCycleList(ArrayList<Integer> originalFishNumbers) {
        HashMap<Integer, Long> fishLifeCycleList = new HashMap<>();
        for (int i = 0; i < originalFishNumbers.size(); i++) {
            if (fishLifeCycleList.containsKey(originalFishNumbers.get(i))) {
                fishLifeCycleList.put(originalFishNumbers.get(i), fishLifeCycleList.get(originalFishNumbers.get(i)) + 1);
            } else {
                fishLifeCycleList.put(originalFishNumbers.get(i), 1L);
            }
        }
        return fishLifeCycleList;
    }

    private static void part1() throws IOException {
        BufferedReader inputData = dataReader();
        String[] splited = inputData.readLine().split(",");
        ArrayList<Integer> fishNumbers = new ArrayList<>();
        for (String num : splited) {
            fishNumbers.add(Integer.parseInt(num));
        }
        
        int totalDays = 80;
        long result = growthCalculation(totalDays, fishNumbers);
        System.out.println(String.format("part 1: %s", result));        
    }

    private static void part2() throws IOException {
        BufferedReader inputData = dataReader();
        String[] splited = inputData.readLine().split(",");
        ArrayList<Integer> fishNumbers = new ArrayList<>();
        for (String num : splited) {
            fishNumbers.add(Integer.parseInt(num));
        }
        
        int totalDays = 256;
        long result = growthCalculation(totalDays, fishNumbers);
        System.out.println(String.format("part 1: %s", result));        
    }

    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }
}