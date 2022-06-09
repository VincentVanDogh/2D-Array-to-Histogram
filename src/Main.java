import java.awt.*;
import java.util.Arrays;

public class Main   {
    private static int[][] genArray(int numLines) {
        int columnLength = 10;
        int[][] array = new int[numLines][columnLength];
        for (int i = 0; i < numLines; i++) {
            int randomLines = ((int) (Math.random() * 10)) + 1;
            array[i] = Arrays.copyOfRange(array[i], 0, randomLines);
            for (int j = 0; j < array[i].length; j++) {
                int randomNumber = ((int) (Math.random() * 40)) + 1;
                array[i][j] = randomNumber;
            }
        }
        return array;
    }

    private static int getMaxValue(int[] workArray) {
        int max = 0;
        for (int i = 0; i < workArray.length; i++) {
            max = Math.max(max, workArray[i]);
        }
        return max;
    }

    private static int getMaxValue(int[][] workArray) {
        int max = 0;
        for (int i = 0; i < workArray.length; i++) {
            for (int j = 0; j < workArray[i].length; j++) {
                max = Math.max(max, workArray[i][j]);
            }
        }
        return max;
    }

    private static int[] genHistogram(int[][] workArray) {
        int max = getMaxValue(workArray);
        int[] temp = new int[max];
        int counter = 0;
        for (int i = 1; i <= max; i++) {
            for (int j = 0; j < workArray.length; j++) {
                for (int k = 0; k < workArray[j].length; k++) {
                    if(workArray[j][k] == i){
                        counter++;
                    }
                }
            }
            temp[i - 1] = counter;
            counter = 0;
        }
        return temp;
    }

    private static void drawHistogram(int[] workArray, int width, int height) {
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        double max = getMaxValue(workArray);
        double x = width / (workArray.length * 1.0);
        double halfWidth = width / (workArray.length * 2.0);
        for (int i = 0; i < workArray.length; i++) {
            if(i == 0 || i == workArray.length - 1) {
                StdDraw.setPenColor(Color.black);
            } else if(workArray[i] > workArray[i - 1] && workArray[i] > workArray[i + 1]){
                StdDraw.setPenColor(Color.red);
            } else if(workArray[i] < workArray[i - 1] && workArray[i] < workArray[i + 1]){
                StdDraw.setPenColor(Color.green);
            } else {
                StdDraw.setPenColor(166, 166, 157);
            }
            StdDraw.filledRectangle(halfWidth + (i * x), (workArray[i] / max) * (height / 2.0), halfWidth,(workArray[i] / max) * (height / 2.0));
            StdDraw.setPenColor(Color.black);
            StdDraw.rectangle(halfWidth + (i * x), (workArray[i] / max) * (height / 2.0), halfWidth,(workArray[i] / max) * (height / 2.0));
        }
    }

    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int[][] myArray = genArray(100);
        printArray(myArray);
        System.out.println();
        int[] myHistogram = genHistogram(myArray);
        System.out.println(Arrays.toString(myHistogram));
        drawHistogram(myHistogram, 600, 400);
        StdDraw.save("histogram.jpg");
    }
}
