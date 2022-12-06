import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day4 {

    private static Integer readFile(Function<BufferedReader, Integer> runner) throws FileNotFoundException, IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("input_4.txt"))) {
            return runner.apply(br);
        }
    }

    private static boolean check(String first, String second, boolean fullOverlap){
        String[] f = first.split("-");
        List<Integer> fList = IntStream.range(Integer.parseInt(f[0]), Integer.parseInt(f[1])+1)
            .boxed()
            .toList();

        String[] s = second.split("-");
        List<Integer> sList = IntStream.range(Integer.parseInt(s[0]), Integer.parseInt(s[1])+1)
            .boxed()
            .toList();


        boolean result;
        if (fullOverlap) {
            //result = (sList.size() > 0 && sList.containsAll(fList)) || (fList.size() > 0 && fList.containsAll(sList));
            result =  fList.stream().allMatch(n -> sList.contains(n)) || sList.stream().allMatch(n -> fList.contains(n));
        } else {
            result = fList.stream().anyMatch(n -> sList.contains(n));
        }

        return result;
    }

    public static int firstQuestion() throws FileNotFoundException, IOException{
        Function<BufferedReader, Integer> test = (br) ->{
            String line;
            try {
                line = br.readLine();
                int sum = 0;
                while (line != null) {
                    String[] inputs = line.split(",");
                    if (check(inputs[0], inputs[1], true)) {
                        sum++;
                    }
                    line = br.readLine();
                }

                return sum;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        };

        return readFile(test);
    }

    public static int secondQuestion() throws FileNotFoundException, IOException{  
        Function<BufferedReader, Integer> test = (br) ->{
            String line;
            try {
                line = br.readLine();
                int sum = 0;
                while (line != null) {
                    String[] inputs = line.split(",");
                    if (check(inputs[0], inputs[1], false)) {
                        sum++;
                    }
                    line = br.readLine();
                }

                return sum;
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
