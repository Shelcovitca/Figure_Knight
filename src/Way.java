
import java.awt.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class Way {
    public static void main(String[] args) {

        int[] possibleX = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] possibleY = {2, 2, 1, -1, -2, -2, -1, 1};
        int size = 1000;
        int chessboard[][] = new int[size][size];  // create 2-dimensional array
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
        possibleMoves.add(startPoint); // добавляем точку startPoint в начало очереди
        chessboard[startPoint.getX()][startPoint.getY()] = 0; // записываем в точку число 0
        while (!possibleMoves.isEmpty()) { // покуда очередь не пустая
            currentPoint = possibleMoves.pop(); // забираем точку currentPoint из конца очереди

            if (currentPoint.getX() == finishPoint.getX()) { // если точка currentPoint является точкой finishPoint
                if (currentPoint.getY() == finishPoint.getY()) {
                    System.out.println("Success in " + chessboard[currentPoint.getX()][currentPoint.getY()]); // это успех!
                    System.out.println();
                    break;
                }
            }
            for (int i = 0; i < 8; i++) {

                nextPoint = new Point(currentPoint.getX() + possibleX[i], currentPoint.getY() + possibleY[i]); // создаем следующую точку nextPoint для очереди
                if (correctBorder(nextPoint.getX(), nextPoint.getY(), size)) { // если точка nextPoint не выходит за пределы массива
                    if (chessboard[nextPoint.getX()][nextPoint.getY()] == -1) { // если в точке nextPoint мы еще не были (точка заполнена первоначальным -1)
                        possibleMoves.add(nextPoint); // добавляем эту точку nextPoint в начало очереди
                        chessboard[nextPoint.getX()][nextPoint.getY()] = chessboard[currentPoint.getX()][currentPoint.getY()] + 1; // помечаем точку nextPoint как посещенную, заполнив числом на 1 больше (т.е. 1)
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

    static boolean correctBorder (int x, int y, int size) { // check points validation according to chessboard borders
        if (x < 0 || y < 0)
            return false;
        if (x >= size || y >= size)
            return false;
        return true;
    }

}


