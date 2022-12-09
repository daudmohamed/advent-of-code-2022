package day9;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {
    Set<String> visitedPos = new HashSet<>();
    Pos H;
    Pos T;
    List<List<String>> matrix;


    Matrix(int xLen, int yLen, int startPos){
        H = new Pos(0, yLen-1);
        T = new Pos(0, yLen-1, H);
        
        matrix =  IntStream.range(0, yLen)
        .mapToObj(l -> {
            List<String> row = IntStream.range(0, xLen)
                .mapToObj(n -> ".")
                .collect(Collectors.toList());

            return row;
        }).toList();
        matrix.get(yLen-1).set(startPos, "H");
        
         
    }

    public void move(String direction, int numberOfMoves){
        for (int i = 0; i < numberOfMoves; i++) {
            // Reset
            matrix.get(H.y).set(H.x, ".");
            matrix.get(T.y).set(T.x, ".");


            Integer[] hPos = H.nextPosition(direction);
            matrix.get(hPos[1]).set(hPos[0], "H");

            Integer[] tPos = T.nextPosition(direction);
            matrix.get(tPos[1]).set(tPos[0], "T");
            visitedPos.add(String.format("%d_%d",tPos[0],tPos[1]));

            print();
        }
    }

    private void print(){
        System.out.println("___________________");

        matrix
            .forEach(l -> {
                l.forEach(t -> System.out.print(t));
                System.out.println();
            });

    }

    private void printVisited(){
        List<List<String>> test = IntStream.range(0, matrix.size())
        .mapToObj(l -> {
            List<String> row = IntStream.range(0, matrix.get(0).size())
                .mapToObj(n -> ".")
                .collect(Collectors.toList());

            return row;
        }).toList();

        visitedPos.stream()
            .forEach(vp -> {
                String[] sp = vp.split("_");
                test.get(Integer.parseInt(sp[1])).set(Integer.parseInt(sp[0]), "#");
            });
            System.out.println("___________________");

        test
            .forEach(l -> {
                l.forEach(t -> System.out.print(t));
                System.out.println();
            });

    }

    public int numberOfVisitedPos(){
        printVisited();
        return visitedPos.size();
    }
}
