package day7;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class Day7 {

    private static <T> T readFile(Function<BufferedReader, T> runner) throws FileNotFoundException, IOException{
        File wd = new File(".");
        System.out.println("working dir: " + wd.getAbsolutePath());
        try(BufferedReader br = new BufferedReader(new FileReader("day7/input_7.txt"))) {
            return runner.apply(br);
        }
    }
    private static <T> T readTest(Function<BufferedReader, T> runner) throws FileNotFoundException, IOException{
        File wd = new File(".");
        System.out.println("working dir: " + wd.getAbsolutePath());
        try(BufferedReader br = new BufferedReader(new FileReader("day7/test_7.txt"))) {
            return runner.apply(br);
        }
    }

    public static Integer firstQuestion() throws FileNotFoundException, IOException{
        Function<BufferedReader, Integer> test = (br) -> {
            Dir root = new Dir(null, "/");
            Dir current = root;
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    System.out.println(line);
                    if(line.contains("cd")){
                        if(line.equals("$ cd ..")){
                            current = current.getParent();
                        } else if(!line.contains("/")){
                            current = current.getDir(line.split(" ")[2]);
                        }
                        line = br.readLine();
                    } else if(line.equals("$ ls")){
                        line = br.readLine();
                        List<String> dirNames = new ArrayList<>();
                        while(line != null && !line.contains("$ cd") && !line.equals("$ ls") ){
                            System.out.println(line);
                            String[] parts = line.split(" ");
                            if(parts[0].equals("dir")){
                                dirNames.add(parts[1]);
                            } else {
                                current.addFile(Integer.parseInt(parts[0]), parts[1]);
                            }
                            line = br.readLine();
                        }
                        current.setDirs(dirNames);
                    }

                }

                root.printDirs(0);
                List<Dir> smallDirs = new ArrayList<>();
                root.calculateQ1(smallDirs);
                return smallDirs.stream()
                    .mapToInt(Dir::calculateSize)
                    .sum();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        };

        return readFile(test);
    }

    public static String secondQuestion() throws FileNotFoundException, IOException{
        Function<BufferedReader, String> test = (br) -> {
            Dir root = new Dir(null, "/");
            Dir current = root;
            String line;
            try {
                line = br.readLine();
                while (line != null) {
                    if(line.contains("cd")){
                        if(line.equals("$ cd ..")){
                            current = current.getParent();
                        } else if(!line.contains("/")){
                            current = current.getDir(line.split(" ")[2]);
                        }
                        line = br.readLine();
                    } else if(line.equals("$ ls")){
                        line = br.readLine();
                        List<String> dirNames = new ArrayList<>();
                        while(line != null && !line.contains("$ cd") && !line.equals("$ ls") ){
                            String[] parts = line.split(" ");
                            if(parts[0].equals("dir")){
                                dirNames.add(parts[1]);
                            } else {
                                current.addFile(Integer.parseInt(parts[0]), parts[1]);
                            }
                            line = br.readLine();
                        }
                        current.setDirs(dirNames);
                    }

                }

                root.printDirs(0);
                int sysSize = 70000000;
                int sysSizeUsed = root.calculateSize();
                int sizeNeeded = 30000000 - (sysSize - sysSizeUsed);
                System.out.println(sysSize);
                System.out.println(sysSizeUsed);
                System.out.println(sizeNeeded);
                List<Dir> smallDirs = new ArrayList<>();
                root.calculateQ2(smallDirs, sizeNeeded);
                Optional<Dir> o = smallDirs.stream()
                    .min(Comparator.comparing(Dir::calculateSize));
                    
                return o.get().name;
                    
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
