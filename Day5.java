import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* 
First stack

[D]                     [N] [F]    
[H] [F]             [L] [J] [H]    
[R] [H]             [F] [V] [G] [H]
[Z] [Q]         [Z] [W] [L] [J] [B]
[S] [W] [H]     [B] [H] [D] [C] [M]
[P] [R] [S] [G] [J] [J] [W] [Z] [V]
[W] [B] [V] [F] [G] [T] [T] [T] [P]
[Q] [V] [C] [H] [P] [Q] [Z] [D] [W] 
 1   2   3   4   5   6   7   8   9 


 Example input: move 1 from 3 to 9
*/
public class Day5 {

    List<String> l1 = new ArrayList<>();


    private static String readFile(Function<BufferedReader, String> runner) throws FileNotFoundException, IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("input_5.txt"))) {
            return runner.apply(br);
        }
    }

    public static String firstQuestion() throws FileNotFoundException, IOException{
        List<String> l1 = new ArrayList<>(Arrays.asList("Q", "W", "P", "S", "Z", "R", "H", "D"));
        List<String> l2 = new ArrayList<>(Arrays.asList("V", "B", "R", "W", "Q", "H", "F"));
        List<String> l3 = new ArrayList<>(Arrays.asList("C", "V", "S", "H"));
        List<String> l4 = new ArrayList<>(Arrays.asList("G", "F", "H"));
        List<String> l5 = new ArrayList<>(Arrays.asList("P", "G", "J", "B", "Z"));
        List<String> l6 = new ArrayList<>(Arrays.asList("Q", "T", "J", "H", "W", "F", "L"));
        List<String> l7 = new ArrayList<>(Arrays.asList("Z", "T", "W", "D", "L", "V", "J", "N"));
        List<String> l8 = new ArrayList<>(Arrays.asList("D", "T", "Z", "C", "J", "G", "H", "F"));
        List<String> l9 = new ArrayList<>(Arrays.asList("W", "P", "V", "M", "B", "H"));
        Map<Integer, List<String>> map = new LinkedHashMap<Integer, List<String>>() {{
            put(1, l1);
            put(2, l2);
            put(3, l3);
            put(4, l4);
            put(5, l5);
            put(6, l6);
            put(7, l7);
            put(8, l8);
            put(9, l9);
        }};

        Function<BufferedReader, String> test = (br) ->{
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    String[] parts = line.split(" ");
                
                    int num = Integer.parseInt(parts[1]);
                    int from = Integer.parseInt(parts[3]);
                    int to = Integer.parseInt(parts[5]);

                    for (int i = 0; i < num; i++) {
                        List<String> fromList = map.get(from);
                        List<String> toList = map.get(to);
                        toList.add(fromList.remove(fromList.size() -1));
                    }
                    
                    line = br.readLine();
                }


                return map.values()
                    .stream()
                    .map(l -> l.get(l.size()-1))
                    .collect(Collectors.joining(""));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        };

        return readFile(test);
    }

    public static String secondQuestion() throws FileNotFoundException, IOException{ 
        List<String> l1 = new ArrayList<>(Arrays.asList("Q", "W", "P", "S", "Z", "R", "H", "D"));
        List<String> l2 = new ArrayList<>(Arrays.asList("V", "B", "R", "W", "Q", "H", "F"));
        List<String> l3 = new ArrayList<>(Arrays.asList("C", "V", "S", "H"));
        List<String> l4 = new ArrayList<>(Arrays.asList("G", "F", "H"));
        List<String> l5 = new ArrayList<>(Arrays.asList("P", "G", "J", "B", "Z"));
        List<String> l6 = new ArrayList<>(Arrays.asList("Q", "T", "J", "H", "W", "F", "L"));
        List<String> l7 = new ArrayList<>(Arrays.asList("Z", "T", "W", "D", "L", "V", "J", "N"));
        List<String> l8 = new ArrayList<>(Arrays.asList("D", "T", "Z", "C", "J", "G", "H", "F"));
        List<String> l9 = new ArrayList<>(Arrays.asList("W", "P", "V", "M", "B", "H"));
        Map<Integer, List<String>> map = new LinkedHashMap<Integer, List<String>>() {{
            put(1, l1);
            put(2, l2);
            put(3, l3);
            put(4, l4);
            put(5, l5);
            put(6, l6);
            put(7, l7);
            put(8, l8);
            put(9, l9);
        }};

        Function<BufferedReader, String> test = (br) ->{
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    String[] parts = line.split(" ");
                
                    int num = Integer.parseInt(parts[1]);
                    int from = Integer.parseInt(parts[3]);
                    int to = Integer.parseInt(parts[5]);

                    List<String> fromList = map.get(from);
                    List<String> toList = map.get(to);
                    List<String> sub = fromList.subList(fromList.size() - num, fromList.size());
                    toList.addAll(sub);
                    sub.clear();
                    
                    line = br.readLine();
                }

                return map.values()
                    .stream()
                    .map(l -> l.get(l.size()-1))
                    .collect(Collectors.joining(""));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        };

        return readFile(test);
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Score: " + firstQuestion());
        System.out.println("Score: " + secondQuestion());

    }
}
