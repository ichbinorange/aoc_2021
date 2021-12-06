import java.io.*;
import java.util.HashMap;

public class D1 {
    private static BufferedReader dataReader() {
        // File path is passed as parameter
        File file = new File("pathToYourPuzzle");
 
        // Creating an object of BufferedReader class
        // could generate FileNotFoundException (checked)
        try {
            BufferedReader data = new BufferedReader(new FileReader(file));
            return data;
        } catch (FileNotFoundException e) {
             System.out.println("The file does not exist.");
             return null;
        }
    }

    private static HashMap<String, Integer> location(Integer  value) {
        HashMap<String, Integer> position = new HashMap<>();
        // Add keys and values 
        position.put("value", value);
        return position;
    } 

    private static void part1() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        int measurement1 = 0;
        int current1 = 0;
        int previous1 = 0;
        
        while ((depth = inputData.readLine()) != null) {
            int depthNum = Integer.parseInt(depth);
            current1 = depthNum;
            if (current1 > previous1) {
                measurement1 += 1;
            }
            previous1 = current1;
        }
        System.out.println(String.format("part 1: %s", measurement1-1));        
    }

    private static void part2() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        int current2 = 0;
        int previous2 = 0;
        int measurement2 = 0;
        Integer pointer = 0;
        HashMap<String, Integer> first = location(0);
        HashMap<String, Integer> second = location(0);
        HashMap<String, Integer> third = location(0);
    
        while ((depth = inputData.readLine()) != null) {
            int depthNum = Integer.parseInt(depth);
            // update three values
            if (pointer == 0) {
                first = location(depthNum);
            } else if (pointer == 1) {
                second = location(depthNum);
            } else {
                third = location(depthNum);
            }

            // reset pointer
            if (pointer < 2) {
                pointer += 1;
            } else {
                pointer = 0;
            }

            // calculate the sum of the three values
            if (first.get("value") != 0 && second.get("value") != 0 && third.get("value") != 0) {
                current2 = first.get("value") + second.get("value") + third.get("value");
                if (current2 > previous2) {
                    measurement2 += 1;
                }
                previous2 = current2;
            }
        }
        System.out.println(String.format("part 2: %s", measurement2-1));       
    }

    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }
}