
import java.awt.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class Way {
    public static void main(String[] args) {

        int[] possibleX = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] possibleY = {2, 2, 1, -1, -2, -2, -1, 1};
        int size = 1000;
        int chessboard[][] = new int[size][size];
        int currentPointValue;

        Point startPoint = new Point(0, 0);
        Point finishPoint = new Point(999, 999);
        Point currentPoint;
        Point nextPoint;


        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                chessboard[x][y] = -1;
            }
        }

        LinkedList<Point> possibleMoves = new LinkedList<Point>();
        possibleMoves.add(startPoint);
        chessboard[startPoint.getX()][startPoint.getY()] = 0;
        while (!possibleMoves.isEmpty()) {
            currentPoint = possibleMoves.pop();

            if (currentPoint.getX() == finishPoint.getX()) {
                if (currentPoint.getY() == finishPoint.getY()) {
                    System.out.println("Success in " + chessboard[currentPoint.getX()][currentPoint.getY()]);
                    System.out.println();
                    break;
                }
            }
            for (int i = 0; i < 8; i++) {

                nextPoint = new Point(currentPoint.getX() + possibleX[i], currentPoint.getY() + possibleY[i]);
                if (correctBorder(nextPoint.getX(), nextPoint.getY(), size)) {
                    if (chessboard[nextPoint.getX()][nextPoint.getY()] == -1) {
                        possibleMoves.add(nextPoint);
                        chessboard[nextPoint.getX()][nextPoint.getY()] = chessboard[currentPoint.getX()][currentPoint.getY()] + 1;
                    }
                }

            }
        }

        currentPointValue = chessboard[finishPoint.getX()][finishPoint.getY()];
        while (currentPointValue != 0) {
            for (int i = 0; i < 8; i++) {
                    nextPoint = new Point(finishPoint.getX() + possibleX[i], finishPoint.getY() + possibleY[i]);
                    if (correctBorder(nextPoint.getX(), nextPoint.getY(), size)) {
                        if (chessboard[nextPoint.getX()][nextPoint.getY()] == chessboard[finishPoint.getX()][finishPoint.getY()] - 1) {
                            finishPoint = nextPoint;
                            currentPointValue = currentPointValue - 1;
                            System.out.println("Return path: x= " + finishPoint.getX() + " y= " + finishPoint.getY());
                            }
                    }
                }
            }
        }

    static boolean correctBorder (int x, int y, int size) {
        if (x < 0 || y < 0)
            return false;
        if (x >= size || y >= size)
            return false;
        return true;
    }

}


