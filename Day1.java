import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day1 {
    

    private static int findElfWithMaxCalories() throws IOException{
        List<Integer> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("input_1.txt"))) {
            String line = br.readLine();
            int current = 0;
            while (line != null) {
                if (!line.equals("")){
                    current += Integer.parseInt(line);
                } else {
                    list.add(current);
                    current = 0;
                }
                line = br.readLine();
            }
            int max = Collections.max(list);
            return max;

        }
        
    }

    private static int findTop3ElfsWithMaxCalories() throws IOException{
        List<Integer> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("input_1.txt"))) {
            String line = br.readLine();
            int current = 0;
            while (line != null) {
                if (!line.equals("")){
                    current += Integer.parseInt(line);
                } else {
                    list.add(current);
                    current = 0;
                }
                line = br.readLine();
            }
            int max = Collections.max(list);
            list.remove(list.indexOf(max));
            int maxTwo = Collections.max(list);
            list.remove(list.indexOf(maxTwo));
            int maxThree = Collections.max(list);
            System.out.println(max);
            System.out.println(maxTwo);
            System.out.println(maxThree);
            return max + maxTwo + maxThree;

        }
        
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Elf with top number of calories: " + findElfWithMaxCalories());
        System.out.println("Top 3 elfs number of calories: " + findTop3ElfsWithMaxCalories());

    }
}
