import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class D4 {
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

    private static HashMap<String, Integer> score(ArrayList<Integer> drawnNumbers, ArrayList<ArrayList<Integer>> currentBingoCard) {
        HashMap<String, Integer> result = new HashMap<>();
        int bingoCardScore = 0;
        int idx = 0;
        int smallerIdx = 0;
        int minimusBingoNumbers = 5;
        while (idx < drawnNumbers.size()) {
            // first, drawing numbers to match the minimus numbers for bingo requirement
            while (idx < minimusBingoNumbers) {
                currentBingoCard = updateBingoCard(drawnNumbers.get(idx), currentBingoCard);
                idx += 1;
            }
            
            boolean bingo = bingoCheck(currentBingoCard);
            // if bingo, calculate the bingoCardScore
            if (bingo) {
                bingoCardScore = calculateScore(drawnNumbers.get(idx-1), currentBingoCard);
                smallerIdx = idx -1;
                break;
            } else {
                // if no bingo happens, keep drawing a new number
                currentBingoCard = updateBingoCard(drawnNumbers.get(idx), currentBingoCard);
            }
            idx += 1;
        }

        result.put("drawnIdx", smallerIdx);
        result.put("bingoCardScore", bingoCardScore);
        return result;
    }

    private static ArrayList<ArrayList<Integer>> updateBingoCard(Integer drawnNum, ArrayList<ArrayList<Integer>> currentBingoCard) {
        boolean found = false;
        for (ArrayList<Integer> numList : currentBingoCard) {
            if (!found) {
                for (int i = 0; i < numList.size(); i++) {
                    if (numList.get(i).equals(drawnNum)) {
                        numList.set(i, -1);
                        found = true;
                    }
                }
            }
        } 
        return currentBingoCard;
    }

    private static boolean bingoCheck(ArrayList<ArrayList<Integer>> currentBingoCard) {
        HashMap<Integer, Boolean> columnsToCheck = new HashMap<>();
        for (int r = 0; r < currentBingoCard.size(); r++) {
            int bingoRow = 0;
            for (int c = 0; c < currentBingoCard.size(); c++) {
                // check rows
                if (currentBingoCard.get(r).get(c) == -1){
                    bingoRow += 1;
                    if (r == 0) columnsToCheck.put(c, true);
                }
                // check columns
                if ((r != 0) && (!columnsToCheck.containsKey(c) || currentBingoCard.get(r).get(c) != -1)) columnsToCheck.remove(c);
            }
            if (bingoRow == 5) return true;
            bingoRow = 0;
        }
        
        if (!columnsToCheck.isEmpty()) return true;
        return false;
    }

    private static int calculateScore(Integer drawnNumber, ArrayList<ArrayList<Integer>> currentBingoCard) {
        int total = 0;
        for (int r = 0; r < currentBingoCard.size(); r++) {
            for (int c = 0; c < currentBingoCard.size(); c++) {
                // check rows
                if (!(currentBingoCard.get(r).get(c) == -1)) {
                    total += currentBingoCard.get(r).get(c);
                }
            }
        }
        return total * drawnNumber;
    }

    private static void part1() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        ArrayList<Integer> drawnNumbers = new ArrayList<>();
        int numLine = 0;
        int bingoBoardSize = 0;
        int currentScoreForMinDrawnIdx = 0;
        int currentMinIdx = -1;
        ArrayList<ArrayList<Integer>> currentBingoCard = new ArrayList<>();
        while ((depth = inputData.readLine()) != null) {
            // collect the 1st line for drawn numbers
            if (numLine == 0) {
                for (String num : depth.split(",")) {
                    drawnNumbers.add(Integer.parseInt(num));
                }
                numLine += 1;
            } else {
                // collect five lines for each bingo card
                if (!depth.isEmpty() && bingoBoardSize < 5) {
                    // convert string[] to integer[]
                    String[] stringDepth = depth.split(" ");
                    List<String> stringDepthList = new ArrayList<String>(Arrays.asList(stringDepth));
                    stringDepthList.removeAll(Arrays.asList("", null));
                    ArrayList<Integer> numDepthList = new ArrayList<>();
                    for (String num : stringDepthList) {
                        numDepthList.add(Integer.parseInt(num));
                    }

                    currentBingoCard.add(bingoBoardSize, numDepthList);
                    bingoBoardSize += 1;
                } else if (depth.isEmpty() && bingoBoardSize == 5) {
                    // reset for the next bingo card
                    currentBingoCard .clear();
                    bingoBoardSize = 0;
                } 
                
                // calculate each bingo card
                if (bingoBoardSize == 5) {
                    HashMap<String, Integer> currentResult = score(drawnNumbers, currentBingoCard);
                    if (currentMinIdx == -1 || currentMinIdx > currentResult.get("drawnIdx")) {
                        currentMinIdx = currentResult.get("drawnIdx");
                        currentScoreForMinDrawnIdx = currentResult.get("bingoCardScore"); 
                    } 
                }
            }
        }
        System.out.println(String.format("part 1: %s", currentScoreForMinDrawnIdx));        
    }

    private static void part2() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        ArrayList<Integer> drawnNumbers = new ArrayList<>();
        int numLine = 0;
        int bingoBoardSize = 0;
        int currentScoreForMinDrawnIdx = 0;
        int currentMinIdx = -1;
        ArrayList<ArrayList<Integer>> currentBingoCard = new ArrayList<>();
        while ((depth = inputData.readLine()) != null) {
            // collect the 1st line for drawn numbers
            if (numLine == 0) {
                for (String num : depth.split(",")) {
                    drawnNumbers.add(Integer.parseInt(num));
                }
                numLine += 1;
            } else {
                // collect five lines for each bingo card
                if (!depth.isEmpty() && bingoBoardSize < 5) {
                    // convert string[] to integer[]
                    String[] stringDepth = depth.split(" ");
                    List<String> stringDepthList = new ArrayList<String>(Arrays.asList(stringDepth));
                    stringDepthList.removeAll(Arrays.asList("", null));
                    ArrayList<Integer> numDepthList = new ArrayList<>();
                    for (String num : stringDepthList) {
                        numDepthList.add(Integer.parseInt(num));
                    }

                    currentBingoCard.add(bingoBoardSize, numDepthList);
                    bingoBoardSize += 1;
                } else if (depth.isEmpty() && bingoBoardSize == 5) {
                    // reset for the next bingo card
                    currentBingoCard .clear();
                    bingoBoardSize = 0;
                } 
                
                // calculate each bingo card
                if (bingoBoardSize == 5) {
                    HashMap<String, Integer> currentResult = score(drawnNumbers, currentBingoCard);
                    if (currentMinIdx == -1 || currentMinIdx < currentResult.get("drawnIdx")) {
                        currentMinIdx = currentResult.get("drawnIdx");
                        currentScoreForMinDrawnIdx = currentResult.get("bingoCardScore"); 
                    } 
                }
            }
        }
        System.out.println(String.format("part 2: %s", currentScoreForMinDrawnIdx));       
    }

    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }

}
