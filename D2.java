import java.io.*;

public class D2 {
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

    private static void part1() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        int x = 0;
        int y = 0;
        
        while ((depth = inputData.readLine()) != null) {
            String[] splited = depth.split("\\s+");
            int depthNum = Integer.parseInt(splited[1]);
            if (splited[0].equals("forward")) {
                x += depthNum;
            } else if (splited[0].equals("up")) {
                y += depthNum;
            } else if (splited[0].equals("down")) {
                y -= depthNum;
            }
        }
        System.out.println(String.format("part 1: %s", x*y));        
    }

    private static void part2() throws IOException {
        BufferedReader inputData = dataReader();
        String depth;
        int x = 0;
        int y = 0;
        int aim = 0;
        
        while ((depth = inputData.readLine()) != null) {
            String[] splited = depth.split("\\s+");
            int depthNum = Integer.parseInt(splited[1]);
            if (splited[0].equals("forward")) {
                x += depthNum;
                y += aim* depthNum;
            } else if (splited[0].equals("up")) {
                aim += depthNum;
            } else if (splited[0].equals("down")) {
                aim -= depthNum;
            }
        }
        System.out.println(String.format("part 2: %s", x*y));       
    }

    public static void main(String[] args) throws Exception {
        part1();
        part2();
    }
}