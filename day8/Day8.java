package day8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;


public class Day8 {

    private static <T> T readFile(Function<BufferedReader, T> runner) throws FileNotFoundException, IOException{
        File wd = new File(".");
        System.out.println("working dir: " + wd.getAbsolutePath());
        try(BufferedReader br = new BufferedReader(new FileReader("day8/input_8.txt"))) {
            return runner.apply(br);
        }
    }
    private static <T> T readTest(Function<BufferedReader, T> runner) throws FileNotFoundException, IOException{
        File wd = new File(".");
        System.out.println("working dir: " + wd.getAbsolutePath());
        try(BufferedReader br = new BufferedReader(new FileReader("day8/test_8.txt"))) {
            return runner.apply(br);
        }
    }

    public static Integer firstQuestion() throws FileNotFoundException, IOException{
        Function<BufferedReader, Integer> test = (br) -> {
            List<List<Integer>> matrix = new ArrayList<>();
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    System.out.println(line);
                    List<Integer> row = new ArrayList<>();
                    for (int i = 0; i < line.toCharArray().length; i++) {
                        String c = String.valueOf(line.charAt(i));
                        row.add(Integer.parseInt(c));
                    }
                    line = br.readLine();
                    matrix.add(row);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            int visibleTrees = 0;
            visibleTrees += (matrix.size() * 2) + (matrix.get(0).size() * 2) - 4;
            
            for (int i = 1; i < matrix.size() - 1; i++) {
                List<Integer> row = matrix.get(i);
                for (int j = 1; j < row.size() - 1; j++) {
                    final int matIndex = j;
                    int current = row.get(j);
                    boolean topVis = matrix.subList(0, i)
                        .stream()
                        .map(m -> m.get(matIndex))
                        .allMatch(t -> t < current);
                    boolean botVis = matrix.subList(i+1, matrix.size())
                        .stream()
                        .map(m -> m.get(matIndex))
                        .allMatch(t -> t < current);
                    boolean leftVis = row.subList(0, j)
                        .stream()
                        .allMatch(t -> t < current);
                    boolean rightVis = row.subList(j+1, row.size())
                        .stream()
                        .allMatch(t -> t < current);
                    
                    if (topVis || botVis || leftVis || rightVis){
                        visibleTrees++;
                    }
                }
            }

            return visibleTrees;
        };

        return readFile(test);
    }

    public static Integer secondQuestion() throws FileNotFoundException, IOException{
        Function<BufferedReader, Integer> test = (br) -> {
            List<List<Integer>> matrix = new ArrayList<>();
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    System.out.println(line);
                    List<Integer> row = new ArrayList<>();
                    for (int i = 0; i < line.toCharArray().length; i++) {
                        String c = String.valueOf(line.charAt(i));
                        row.add(Integer.parseInt(c));
                    }
                    line = br.readLine();
                    matrix.add(row);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            int score = 0;
            
            for (int i = 1; i < matrix.size() - 1; i++) {
                List<Integer> row = matrix.get(i);
                for (int j = 1; j < row.size() - 1; j++) {
                    final int matIndex = j;
                    int current = row.get(j);
                    List<Integer> topList = new ArrayList<>(matrix.subList(0, i)
                        .stream()
                        .map(m -> m.get(matIndex))
                        .toList());
                    Collections.reverse(topList);
                    int top = giveDirection(
                        topList, 
                        current
                    );
                    int bot = giveDirection(
                        matrix.subList(i+1, matrix.size())
                            .stream()
                            .map(m -> m.get(matIndex))
                            .toList(), 
                        current
                    );

                    List<Integer> leftList = new ArrayList<>(row.subList(0, j));
                    Collections.reverse(leftList);


                    int left = giveDirection(leftList, current);

                    int right = giveDirection(row.subList(j+1, row.size()), current);


                    int total = top * bot * left * right;
                    
                    if (total > score){
                        score = total;
                    }
                }
            }

            return score;
        };

        return readFile(test);
    }

    private static int giveDirection(List<Integer> nums, Integer current) {                        
        OptionalInt rightInt = IntStream.range(0, nums.size())
            .filter(t -> nums.get(t) >= current)
            .findFirst();

        return rightInt.isPresent() ? rightInt.getAsInt() + 1 : nums.size();
    }


    public static void main(String[] args) throws IOException {
        //System.out.println("Score: " + firstQuestion());
        System.out.println("Score: " + secondQuestion());

    }
}
