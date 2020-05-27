package Lesson_2;

import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        fillInvertArray();
        floodArrayWith3();
        makeSixesGreatAgain();
        fillMatrixDiagonally(1);
        findExtremumsInArray();
        int arrSize = getUserInt();
        if (arrSize < 2) {
            System.out.println("Вы, батенька, юный хакер, не иначе.");
            scan.close();
            System.exit(1);
        }
        int arr[] = getRandomArray(arrSize);
        System.out.println((isArraySymmetric(arr) ? "Симметрия есть" : "Симметрии нет"));
        scan.close();
    }

    //1 задание
    //Задать целочисленный массив, состоящий из элементов 0 и 1.
    //Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;

    private static void fillInvertArray() {
        System.out.println("Введите размер массива: ");
        int arrSize = scan.nextInt();
        if (arrSize < 1) {
            System.out.println("Вы, батенька, юный хакер, не иначе.");
            scan.close();
            System.exit(1);
        }
        int arr[] = new int[arrSize];
        for(int i = 0; i < arrSize; i++) {
            int randNumber = (int)(Math.random() * 10);
            arr[i] = (randNumber < 5 ? 0 : 1);
        }
        String Info = "";
        for(int i = 0; i < arrSize; i++) {
            Info = "до инвертации №" + i + " = " + arr[i];
            arr[i] = 1 - arr[i];
            Info += ", после инвертации = " + arr[i];
            System.out.println(Info);
        }
    }

    //2 задание
    //Задать пустой целочисленный массив размером 8.
    //С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;

    private static void floodArrayWith3() {
        int arr[] = new int[8];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
    }

    //3 задание
    //Задать массив [  ]
    //Пройти по нему циклом, и числа меньшие 6 умножить на 2;

    private static void makeSixesGreatAgain() {
        int arr[] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    //4 задание
    //Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    //и с помощью цикла(-ов) заполнить его диагональные элементы единицами;

    private static void fillMatrixDiagonally(int filler) {
        System.out.println("Введите размер матрицы: ");
        int arrSize = scan.nextInt();
        if (arrSize < 2) {
            System.out.println("Вы, батенька, юный хакер, не иначе.");
            scan.close();
            System.exit(1);
        }
        int arr[][] = new int[arrSize][arrSize];
        for(int i = 0; i < arrSize; i++) {
            arr[i][i] = filler;
        }
        for(int i = 0; i < arrSize; i++) {
            String row = "";
            for(int j = 0; j < arrSize; j++) {
                row = row + arr[i][j] + " ";
            }
            System.out.println(row);
        }
    }

    //5-е задание
    //Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);

    private static int getUserInt() {
        System.out.println("Введите размер массива: ");
        return scan.nextInt();
    }

    private static int[] getRandomArray(int arrSize) {
        int arr[] = new int[arrSize];
        for(int i = 0; i < arrSize; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    private static void findExtremumsInArray() {
        int arrSize = getUserInt();
        if (arrSize < 2) {
            System.out.println("Вы, батенька, юный хакер, не иначе.");
            scan.close();
            System.exit(1);
        }
        int arr[] = getRandomArray(arrSize);

        System.out.println("Массив: ");
        int minValue = arr[0];
        int minValueIndex = 0;
        int maxValue = arr[0];
        int maxValueIndex = 0;
        for(int i = 0; i < arrSize; i++) {
            System.out.print((i + 1) + ") " + arr[i] + (i == (arrSize - 1) ? "\n" : "; "));
            if (i == 0) {
                continue;
            }
            if (arr[i] > maxValue && maxValue != arr[i]) {
                maxValueIndex = i + 1;
                maxValue = arr[i];
            }
            if (arr[i] < minValue && minValue != arr[i]) {
                minValueIndex = i + 1;
                minValue = arr[i];
            }
        }
        System.out.println("Мин. значение, индекс: " + minValueIndex + ", значение = " + minValue);
        System.out.println("Макс. значение, индекс: " + maxValueIndex + ", значение = " + maxValue);
    }

    //6-е задание
    //Написать метод, в который передается не пустой одномерный целочисленный массив,
    //Метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
    //Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    //граница показана символами ||, эти символы в массив не входят.

    private static boolean isArraySymmetric(int[] arr) {
        int arrSize = arr.length;
        int wholeSum = 0;
        int currentSum = 0;
        for(int i = 0; i < arrSize; i++) {
            wholeSum += arr[i];
        }
        boolean thereIsSymmetry = false;
        for(int i = 0; i < arrSize; i++) {
            currentSum += arr[i];
            if ((wholeSum / 2 == currentSum) && (wholeSum % 2) == 0) {
                thereIsSymmetry = true;
                break;
            }
        }
        return thereIsSymmetry;
    }

    //7-е задание
    //Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
    //при этом метод должен сместить все элементы массива на n позиций.
    //Для усложнения задачи нельзя пользоваться вспомогательными массивами.

    private static void shiftArray(int[] arr, int step) {
        //храним текущее значение по индексу во временной переменной
        //сдвигаем в цикле на +1 или -1 значения, подставляем заранее сохраненное по индексу + 1 или -1
        //в целом так.
    }
}
