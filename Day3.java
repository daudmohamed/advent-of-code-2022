import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class Day3 {

    private static Integer readFile(Function<BufferedReader, Integer> runner) throws FileNotFoundException, IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("input_3.txt"))) {
            return runner.apply(br);
        }
    }

    private static int getPriority(String letter){
        if (letter.equals(letter.toLowerCase())){
            return (letter.charAt(0) - 96);
        } else {
            return (letter.charAt(0) - 38);
        }
    }

    public static int firstQuestion() throws FileNotFoundException, IOException{
        Function<BufferedReader, Integer> test = (br) ->{
            String line;
            try {
                line = br.readLine();
                int sum = 0;
                while (line != null) {
                    final int mid = line.length() / 2; //get the middle of the String
                    String[] parts = {line.substring(0, mid), line.substring(mid)};
                    for (Character c : parts[0].toCharArray()) {
                        if (parts[1].indexOf(c) != -1){
                            sum += getPriority(String.valueOf(c));
                            break;
                        }
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
            String line1;
            String line2;
            String line3;
            try {
                line1 = br.readLine();
                line2 = br.readLine();
                line3 = br.readLine();
                int sum = 0;
                while (line1 != null && line2 != null && line3 != null ) {
                    for (Character c : line1.toCharArray()) {
                        if (line2.indexOf(c) != -1 && line3.indexOf(c) != -1){
                            sum += getPriority(String.valueOf(c));
                            break;
                        }
                    }
                    line1 = br.readLine();
                    line2 = br.readLine();
                    line3 = br.readLine();
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
