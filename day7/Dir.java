package day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Dir {
    Dir parent = null;
    String name;
    Integer dirSize;
    Map<String, Dir> dirs = new HashMap<>();
    List<Fil> files = new ArrayList<>();

    Dir (Dir parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public void setDirs(List<String> dirNames){
        dirNames.forEach(d -> {
            Dir dir = new Dir(this, d);
            dirs.put(d, dir);
        });
    }

    public void addFile(int size, String name){
        files.add(new Fil(size, name));
    }

    public Dir getDir(String name){
        return dirs.get(name);
    }

    public int calculateSize() {
        if(dirSize == null){
            int size = 0;
            size += files.stream()
            .mapToInt(Fil::getSize)
            .sum();
            size += dirs.values()
            .stream()
            .mapToInt(Dir::calculateSize)
            .sum();

            dirSize = size;
        }
        
        return dirSize;
    }

    public void calculateQ1(List<Dir> res){
        int size = 0;
        size += files.stream()
            .mapToInt(Fil::getSize)
            .sum();
        size += dirs.values()
            .stream()
            .mapToInt(d -> {
                d.calculateQ1(res);
                return d.calculateSize();
            })
            .sum();
        
        if (size < 100000){
            res.add(this);
        } 
    }

    public void calculateQ2(List<Dir> res, int sysSize){
        int size = 0;
        size += files.stream()
            .mapToInt(Fil::getSize)
            .sum();
        size += dirs.values()
            .stream()
            .mapToInt(d -> {
                d.calculateQ2(res, sysSize);
                return d.calculateSize();
            })
            .sum();
        
        if (size > sysSize){
            res.add(this);
        } 
    }

    private String numberOfSpaces(int num){
        StringBuilder str = new StringBuilder();
        IntStream.range(0, num)
            .forEach(n -> str.append(" "));
        return str.toString();
    }
    public void printDirs(int indent) {
        System.out.println(String.format("%s- %s (dir)", numberOfSpaces(indent), name));
        files.forEach(f -> {
            System.out.println(String.format("%s- %s (file, size=%d)", numberOfSpaces(indent+1), f.name, f.size));
        });
        dirs.values().forEach(d -> d.printDirs(indent+1));
    }

    public Dir getParent() {
        return parent;
    }
}