import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class D3 {
    private static BufferedReader dataReader() {
        File file = new File(
            "pathToYourPuzzle");
 
        try {
            BufferedReader data = new BufferedReader(new FileReader(file));
            return data;
        } catch (FileNotFoundException e) {
             System.out.println("The file does not exist.");
             return null;
        }
    }
    
    private static HashMap<Integer, HashMap<Integer, Integer>> bitInfo() throws IOException {
        BufferedReader inputData = dataReader();
        
        // bitInfo = {0: {0: 5, 1: 7}}
        HashMap<Integer, HashMap<Integer, Integer>> bitInfo = new HashMap<>();
        String depth;
        while ((depth = inputData.readLine()) != null) {
            String[] splited = depth.split("");
            for(int i = 0; i < splited.length; i++) {
                if (bitInfo.containsKey(i)) {
                    if (bitInfo.get(i).containsKey(Integer.parseInt(splited[i]))) {
                        bitInfo.get(i).put(Integer.parseInt(splited[i]), bitInfo.get(i).get(Integer.parseInt(splited[i])) + 1);
                    } else {
                        bitInfo.get(i).put(Integer.parseInt(splited[i]), 1);
                    }
                } else {
                    HashMap<Integer, Integer> inner = new HashMap<>();
                    inner.put(Integer.parseInt(splited[i]), 1);
                    bitInfo.put(i, inner);
                }
            }
        }
        return bitInfo;
    }

    private static void part1() throws IOException {
        HashMap<Integer, HashMap<Integer, Integer>> bitInfo = bitInfo();
        
        ArrayList<Integer> gammaRateList = new ArrayList<Integer>();
        ArrayList<Integer> epsilonRateList = new ArrayList<Integer>();
        for (Integer key : bitInfo.keySet()) {
            if (bitInfo.get(key).get(0) > bitInfo.get(key).get(1)) {
                gammaRateList.add(key, 0);
                epsilonRateList.add(key, 1);
            } else {
                gammaRateList.add(key, 1);
                epsilonRateList.add(key, 0);
            }
        }
        String gammaRateBinary = gammaRateList.stream().map(String::valueOf).collect(Collectors.joining(""));
        String epsilonRateBinary = epsilonRateList.stream().map(String::valueOf).collect(Collectors.joining(""));

        System.out.println(String.format("part 1: %s", Integer.parseInt(gammaRateBinary, 2)*Integer.parseInt(epsilonRateBinary, 2)));        
    }

    private static void part2() throws IOException {
        ArrayList<Integer> oxygenGeneratorRatingList = new ArrayList<Integer>();
        ArrayList<Integer> co2ScrubberRatingList = new ArrayList<Integer>();

        ArrayList<String> zeroList = new ArrayList<String>();
        ArrayList<String> oneList = new ArrayList<String>();
        
        BufferedReader inputData = dataReader();
        String depth;
        String countString = "";
        // first bit and store the data into zeroList & oneList
        while ((depth = inputData.readLine()) != null) {
            if (depth.charAt(0) == '0') {
                zeroList.add(depth);
            } else {
                oneList.add(depth);
            }

            if (countString.equals("")) {
                countString = depth;
            }
        }
        
        if (zeroList.size() > oneList.size()) {
            oxygenGeneratorRatingList.add(0);
            co2ScrubberRatingList.add(1);
        } else if (zeroList.size() < oneList.size()) {
            oxygenGeneratorRatingList.add(1);
            co2ScrubberRatingList.add(0);
        } else {
            oxygenGeneratorRatingList.add(1);
            co2ScrubberRatingList.add(0);
        }

        // for the rest of the bits except for the first bit for oxygenGeneratorRatingList
        int countTime = countString.length();
        int idx = 1;
        while (countTime > idx) {
            ArrayList<String> tempOneList = new ArrayList<String>();
            ArrayList<String> tempZeroList = new ArrayList<String>();
            for (int i = 0; i < oneList.size(); i ++) { 	
                if (oneList.get(i).charAt(idx) == '0') {
                    tempZeroList.add(oneList.get(i));
                } else {
                    tempOneList.add(oneList.get(i));
                }	
            }
            oneList = tempOneList.size() >= tempZeroList.size() ? tempOneList : tempZeroList;
            if (tempOneList.size() >= tempZeroList.size()) {
                oxygenGeneratorRatingList.add(1);
            } else {
                oxygenGeneratorRatingList.add(0);
            }
      
            if (oneList.size() == 1) {
                while (countTime > idx + 1) {
                    idx += 1;
                    oxygenGeneratorRatingList.add(Integer.parseInt(String.valueOf(oneList.get(0).charAt(idx))));
                }
                break;
            }
            idx += 1;
        }
        
        // for the rest of the bits except for the first bit for co2ScrubberRatingList (opposite of oxygenGeneratorRatingList)
        idx = 1;
        while (countTime > idx) {
            ArrayList<String> tempOneList = new ArrayList<String>();
            ArrayList<String> tempZeroList = new ArrayList<String>();
            for (int i = 0; i < zeroList.size(); i ++) { 	
                if (zeroList.get(i).charAt(idx) == '0') {
                    tempZeroList.add(zeroList.get(i));
                } else {
                    tempOneList.add(zeroList.get(i));
                }
            }

            zeroList = tempZeroList.size() > tempOneList.size() ? tempOneList : tempZeroList;
            if (tempZeroList.size() > tempOneList.size()) {
                co2ScrubberRatingList.add(1);
            } else {
                co2ScrubberRatingList.add(0);
            }

            if (zeroList.size() == 1) {
                while (countTime > idx + 1) {
                    idx += 1;
                    co2ScrubberRatingList.add(Integer.parseInt(String.valueOf(zeroList.get(0).charAt(idx))));
                }
                break;
            }
            idx += 1;
        }

        String oxygenGeneratorRatingBinary = oxygenGeneratorRatingList.stream().map(String::valueOf).collect(Collectors.joining(""));
        String co2ScrubberRatingBinary = co2ScrubberRatingList.stream().map(String::valueOf).collect(Collectors.joining(""));
        
        System.out.println(String.format("part 2: %s", Integer.parseInt(oxygenGeneratorRatingBinary, 2)*Integer.parseInt(co2ScrubberRatingBinary, 2)));       
    }

    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }
}