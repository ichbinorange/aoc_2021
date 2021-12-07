import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;

public class D5 {
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

    private static int isPlusOne(Integer yValue) {
        return yValue > 1 ? 1 : 0;
    }

    private static void part1() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        ArrayList<HashMap<String, Integer>> data = new ArrayList<>();
             
        while ((depth = inputData.readLine()) != null) {
            String[] splited = depth.split("->");
            List<String> splitedList = new ArrayList<String>(Arrays.asList(splited));
            int beforeAfter = 1;
            HashMap<String, Integer> temp = new HashMap<>();
            for (String coordinate : splitedList) {
                List<String> coordinateList = new ArrayList<String>(Arrays.asList(coordinate.split(",")));
                temp.put(String.format("x%s", beforeAfter), Integer.parseInt(coordinateList.get(0).replaceAll("\\s", "")));
                temp.put(String.format("y%s", beforeAfter), Integer.parseInt(coordinateList.get(1).replaceAll("\\s", "")));

                beforeAfter += 1;
            }
            data.add(temp);
            // check the coordinates form in lines
            if (beforeAfter == 3 && data.size() > 0) {
                HashMap<String, Integer> lastElement = data.get(data.size() - 1);
                boolean isLine = lastElement.get("x1").equals(lastElement.get("x2")) || lastElement.get("y1").equals(lastElement.get("y2"));
                if (!isLine) data.remove(data.size() - 1);
            }
        } 

        // collect the overlap check results
        // results = {x : {y: times}}
        HashMap<Integer, HashMap<Integer, Integer>> results = new HashMap<>();
        for (HashMap<String, Integer> coordinate : data) {
            // if x1 = x2
            if (coordinate.get("x1").equals(coordinate.get("x2"))) {
                int xValue = coordinate.get("x1");
                int largeY = coordinate.get("y1") >= coordinate.get("y2") ? coordinate.get("y1") : coordinate.get("y2");
                int smallY = largeY == coordinate.get("y1") ? coordinate.get("y2") : coordinate.get("y1");
                while (smallY <= largeY) {
                    // this coordinate is in results
                    if (results.containsKey(xValue)) {
                        if (results.get(xValue).containsKey(smallY)) {
                            results.get(xValue).put(smallY, results.get(xValue).get(smallY) + 1);
                        } else {
                            results.get(xValue).put(smallY, 1);
                        }
                    } else {
                        HashMap<Integer, Integer> tempY = new HashMap<>();
                        tempY.put(smallY, 1);
                        results.put(xValue, tempY);
                    }
                    smallY += 1;
                }
            } else {
                // if y1 = y2
                int yValue = coordinate.get("y1");
                int largeX = coordinate.get("x1") >= coordinate.get("x2") ? coordinate.get("x1") : coordinate.get("x2");
                int smallX = largeX == coordinate.get("x1") ? coordinate.get("x2") : coordinate.get("x1");
                while (smallX <= largeX) {
                    // this coordinate is in results
                    if (results.containsKey(smallX)) {
                        if (results.get(smallX).containsKey(yValue)) {
                            results.get(smallX).put(yValue, results.get(smallX).get(yValue) + 1);
                        } else {
                            results.get(smallX).put(yValue, 1);
                        }
                    } else {
                        HashMap<Integer, Integer> tempY = new HashMap<>();
                        tempY.put(yValue, 1);
                        results.put(smallX, tempY);
                    }
                    smallX += 1;
                }
            }
        }      

        // calculate overlap
        int overlap = 0;
        for (HashMap<Integer, Integer> xValue : results.values()) {
            for (Integer yValue : xValue.values()) {
                overlap += isPlusOne(yValue);
            }
        }
        System.out.println(String.format("part 1: %s", overlap));        
    }

    
    private static void part2() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        ArrayList<HashMap<String, Integer>> data = new ArrayList<>();
             
        while ((depth = inputData.readLine()) != null) {
            String[] splited = depth.split("->");
            List<String> splitedList = new ArrayList<String>(Arrays.asList(splited));
            int beforeAfter = 1;
            HashMap<String, Integer> temp = new HashMap<>();
            for (String coordinate : splitedList) {
                List<String> coordinateList = new ArrayList<String>(Arrays.asList(coordinate.split(",")));
                temp.put(String.format("x%s", beforeAfter), Integer.parseInt(coordinateList.get(0).replaceAll("\\s", "")));
                temp.put(String.format("y%s", beforeAfter), Integer.parseInt(coordinateList.get(1).replaceAll("\\s", "")));

                beforeAfter += 1;
            }
            data.add(temp);
            // check the coordinates form a line or a diagonal line at exactly 45 degrees
            if (beforeAfter == 3 && data.size() > 0) {
                HashMap<String, Integer> lastElement = data.get(data.size() - 1);
                boolean isLine = lastElement.get("x1").equals(lastElement.get("x2")) || lastElement.get("y1").equals(lastElement.get("y2"));
                boolean is45Degrees = Math.abs(lastElement.get("x1") - lastElement.get("x2")) == Math.abs(lastElement.get("y1") - lastElement.get("y2")); 
                if (!isLine && !is45Degrees) data.remove(data.size() - 1);
            }
        } 
        
        // collect the overlap check results
        // results = {x : {y: times}}
        HashMap<Integer, HashMap<Integer, Integer>> results = new HashMap<>();
        for (HashMap<String, Integer> coordinate : data) {
            // if x1 = x2
            if (coordinate.get("x1").equals(coordinate.get("x2"))) {
                int xValue = coordinate.get("x1");
                int largeY = coordinate.get("y1") >= coordinate.get("y2") ? coordinate.get("y1") : coordinate.get("y2");
                int smallY = largeY == coordinate.get("y1") ? coordinate.get("y2") : coordinate.get("y1");
                while (smallY <= largeY) {
                    // this coordinate is in results
                    if (results.containsKey(xValue)) {
                        if (results.get(xValue).containsKey(smallY)) {
                            results.get(xValue).put(smallY, results.get(xValue).get(smallY) + 1);
                        } else {
                            results.get(xValue).put(smallY, 1);
                        }
                    } else {
                        HashMap<Integer, Integer> tempY = new HashMap<>();
                        tempY.put(smallY, 1);
                        results.put(xValue, tempY);
                    }
                    smallY += 1;
                }
            } else if (coordinate.get("y1").equals(coordinate.get("y2"))) {
                // if y1 = y2
                int yValue = coordinate.get("y1");
                int largeX = coordinate.get("x1") >= coordinate.get("x2") ? coordinate.get("x1") : coordinate.get("x2");
                int smallX = largeX == coordinate.get("x1") ? coordinate.get("x2") : coordinate.get("x1");
                while (smallX <= largeX) {
                    // this coordinate is in results
                    if (results.containsKey(smallX)) {
                        if (results.get(smallX).containsKey(yValue)) {
                            results.get(smallX).put(yValue, results.get(smallX).get(yValue) + 1);
                        } else {
                            results.get(smallX).put(yValue, 1);
                        }
                    } else {
                        HashMap<Integer, Integer> tempY = new HashMap<>();
                        tempY.put(yValue, 1);
                        results.put(smallX, tempY);
                    }
                    smallX += 1;
                }
            } else {
                int largeX = coordinate.get("x1") >= coordinate.get("x2") ? coordinate.get("x1") : coordinate.get("x2");
                int smallX = largeX == coordinate.get("x1") ? coordinate.get("x2") : coordinate.get("x1");
                int yForLargeX = largeX == coordinate.get("x1") ? coordinate.get("y1") : coordinate.get("y2");
                int yForSmallX = yForLargeX == coordinate.get("y1") ? coordinate.get("y2") : coordinate.get("y1");
                while (smallX <= largeX) {
                    // this coordinate is in results
                    if (results.containsKey(largeX)) {
                        if (results.get(largeX).containsKey(yForLargeX)) {
                            results.get(largeX).put(yForLargeX, results.get(largeX).get(yForLargeX) + 1);
                        } else {
                            results.get(largeX).put(yForLargeX, 1);
                        }
                    } else {
                        HashMap<Integer, Integer> tempY = new HashMap<>();
                        tempY.put(yForLargeX, 1);
                        results.put(largeX, tempY);
                    }

                    // move the diagonal way
                    largeX -= 1;
                    if (yForLargeX < yForSmallX) {
                        yForLargeX += 1;
                    } else {
                        yForLargeX -= 1;
                    }
                }
            }
        }      

        // calculate overlap
        int overlap = 0;
        for (HashMap<Integer, Integer> xValue : results.values()) {
            for (Integer yValue : xValue.values()) {
                overlap += isPlusOne(yValue);
            }
        }
        System.out.println(String.format("part 2: %s", overlap));        
    }

    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }
}