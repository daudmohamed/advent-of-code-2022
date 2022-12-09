package day9;

import java.util.List;

public class Pos {
    int x;
    int y;
    int prevX;
    int prevY;
    Pos related;

    Pos(int x, int y){
        this(x, y, null);
    }

    Pos(int x, int y, Pos related){
        this.x = x;
        this.y = y;
        this.related = related;
    }

    public boolean isAdjacent() {
        if (related == null){
            return false;
        } else {
            List<Integer> xTangent = List.of(x, x-1, x+1);
            List<Integer> yTangent = List.of(y, y-1, y+1);

            return xTangent.contains(related.x) && yTangent.contains(related.y);
        }
    }


    public Integer[] nextPosition(String direction){
        if (related == null) {
            switch(direction){
                case "U" -> {
                    this.prevY = this.y--;
                    this.prevX = x;
                }
                case "D" -> {
                    this.prevY = this.y++;
                    this.prevX = x;
                }
                case "R" -> {
                    this.prevX = this.x++;
                    this.prevY = y;
                }
                case "L" -> {
                    this.prevX = this.x--;
                    this.prevY = y;
                }
            }    
        } else if (related != null && !isAdjacent()){
            this.y = related.prevY;
            this.x = related.prevX;
            
        }

        return new Integer[]{x, y};
    } 

}