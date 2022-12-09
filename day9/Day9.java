package day9;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

/*
 * == Initial State ==

......
......
......
......
H.....  (H covers T, s)

*/

public class Day9 {

    private static <T> T readFile(Function<BufferedReader, T> runner) throws FileNotFoundException, IOException{
        File wd = new File(".");
        System.out.println("working dir: " + wd.getAbsolutePath());
        try(BufferedReader br = new BufferedReader(new FileReader("day9/input_9.txt"))) {
            return runner.apply(br);
        }
    }
    private static <T> T readTest(Function<BufferedReader, T> runner) throws FileNotFoundException, IOException{
        File wd = new File(".");
        System.out.println("working dir: " + wd.getAbsolutePath());
        try(BufferedReader br = new BufferedReader(new FileReader("day9/test_9.txt"))) {
            return runner.apply(br);
        }
    }

    public static Integer firstQuestion() throws FileNotFoundException, IOException{
        Function<BufferedReader, Integer> test = (br) -> {
            Matrix matrix = new Matrix(6,5, 0);
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    String[] inputs = line.split(" ");
                    matrix.move(inputs[0], Integer.parseInt(inputs[1]));
                    line = br.readLine();
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return matrix.numberOfVisitedPos();
        };

        return readTest(test);
    }

    //public static Integer secondQuestion() throws FileNotFoundException, IOException{}


    public static void main(String[] args) throws IOException {
        System.out.println("Score: " + firstQuestion());
        //System.out.println("Score: " + secondQuestion());

    }
}
