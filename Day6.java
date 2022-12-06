import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Day6 {

    List<String> l1 = new ArrayList<>();


    private static <T> T readFile(Function<BufferedReader, T> runner) throws FileNotFoundException, IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("input_6.txt"))) {
            return runner.apply(br);
        }
    }

    public static Integer firstQuestion() throws FileNotFoundException, IOException{


        Function<BufferedReader, Integer> test = (br) -> {
            List<Character> list = new ArrayList<>();
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    char[] chars = line.toCharArray();
                    for (int i = 0; i < chars.length; i++) {

                        if (list.contains(chars[i])){
                            System.out.println("cleared: "+ chars[i] + ": " + list.stream().map(String::valueOf).collect(Collectors.joining("")));
                            int index = list.indexOf(chars[i]);
                            list.subList(0, index+1).clear();
                        } 
                        list.add(chars[i]);



                        if (list.size() == 4){
                            System.out.println("Result: " + list.stream().map(String::valueOf).collect(Collectors.joining("")));
                            return i+1;
                        }
                    }
                        
                    line = br.readLine();
                }


                return 0;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        };

        return readFile(test);
    }

    public static Integer secondQuestion() throws FileNotFoundException, IOException{


        Function<BufferedReader, Integer> test = (br) -> {
            List<Character> list = new ArrayList<>();
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    char[] chars = line.toCharArray();
                    for (int i = 0; i < chars.length; i++) {

                        if (list.contains(chars[i])){
                            System.out.println("cleared: "+ chars[i] + ": " + list.stream().map(String::valueOf).collect(Collectors.joining("")));
                            int index = list.indexOf(chars[i]);
                            list.subList(0, index+1).clear();
                        } 
                        list.add(chars[i]);



                        if (list.size() == 14){
                            System.out.println("Result: " + list.stream().map(String::valueOf).collect(Collectors.joining("")));
                            return i+1;
                        }
                    }
                        
                    line = br.readLine();
                }


                return 0;
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
