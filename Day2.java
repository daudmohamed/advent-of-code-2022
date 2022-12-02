import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Day2 {

    private static Map<String, String> winMap = Map.of("A", "Y", "B", "Z", "C", "X");
    private static Map<String, String> losswMap = Map.of("A", "Z", "B", "X", "C", "Y");
    private static Map<String, String> drawMap = Map.of("A", "X", "B", "Y", "C", "Z");

    private static Integer readFile(Function<BufferedReader, Integer> runner) throws FileNotFoundException, IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("input_2.txt"))) {
            return runner.apply(br);
        }
    }

    public static int firstQuestion() throws FileNotFoundException, IOException{
        List<String> elves = List.of("A", "B", "C");
        List<String> mes = List.of("X", "Y", "Z");
        Function<BufferedReader, Integer> test = (br) ->{
            String line;
            try {
                line = br.readLine();
            
                int score = 0;
                while (line != null) {
                    String[] inputs = line.split(" ");
                    if(winMap.get(inputs[0]).equals(inputs[1])){
                        score += (mes.indexOf(inputs[1]) + 1) + 6;
                    } else if(losswMap.get(inputs[0]).equals(inputs[1])){
                        score += (mes.indexOf(inputs[1]) + 1);
                    } else if(drawMap.get(inputs[0]).equals(inputs[1])){
                        score += (mes.indexOf(inputs[1]) + 1) + 3;
                    } else {
                        System.out.println("Something is wrong");
                    }
                    line = br.readLine();
                }

                return score;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        };

        return readFile(test);
    }

    public static int secondQuestion() throws FileNotFoundException, IOException{
        List<String> elves = List.of("A", "B", "C");
        List<String> mes = List.of("X", "Y", "Z");
        Function<BufferedReader, Integer> test = (br) ->{
            String line;
            try {
                line = br.readLine();
            
                int score = 0;
                while (line != null) {
                    String[] inputs = line.split(" ");
                    switch (inputs[1]){
                        case "X" -> {
                            String key = losswMap.get(inputs[0]);
                            score +=  mes.indexOf(key) + 1 + 0;
                        }
                        case "Y" -> {
                            String key = drawMap.get(inputs[0]);
                            score +=  mes.indexOf(key) + 1 + 3);
                        }
                        case "Z" -> {
                            String key = winMap.get(inputs[0]);
                            score += mes.indexOf(key) +1 +6;
                        }
                    }
                    line = br.readLine();
                }

                return score;
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
