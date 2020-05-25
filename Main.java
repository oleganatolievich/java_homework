package javastudy.homeworklesson1;

//1 задание
//Создать пустой проект в IntelliJ IDEA и прописать метод main().

public class Main {

    //2 задание - объявление переменных
    //Создать переменные всех пройденных типов данных и инициализировать их значения.

    private static byte byteVar = 2;
    private static short shortVar = 10;
    private static int intVar = 10_000;
    private static long longVar = 100_000_000L;
    private static float floatVar = 123456.1234f;
    private static double doubleVar = 123456.12345678;
    private static char charVar = 'A';
    private static boolean truth = true;
    private static String phraseOfTheDay = "Выбери сейчас, не выбирай не выбирать (с) Артем Маслов";

    public static void main(String[] args) {
        showVariables();
        System.out.println("10 * (20 + (30 / 40)) = " + evalExpression(10, 20, 30, 40));
        System.out.println("Сумма 10 и 20 входит в интервал {10..20}: " +
                (sumIsBetween10And20(10, 20) ? "да" : "нет"));
        printNumberSign(-5);
        printNumberSign(0);
        printNumberSign(5);
        greetAVictim("великий воен диванных войск");
        greetAVictim("жертва режима");
        greetAVictim("junior Java magician");
        for (int year = 1600; year <= 2020; year ++) {
            printLeapYear(year);
        }
    }

    //2 задание - метод, выводящий переменные
    //Создать переменные всех пройденных типов данных и инициализировать их значения.

    private static void showVariables() {
        System.out.println("byteVar = " + byteVar);
        System.out.println("shortVar = " + shortVar);
        System.out.println("intVar = " + intVar);
        System.out.println("longVar = " + longVar);
        System.out.println("floatVar = " + floatVar);
        System.out.println("doubleVar = " + doubleVar);
        System.out.println("charVar = " + charVar);
        System.out.println("truth = " + truth);
        System.out.println("phraseOfTheDay = " + phraseOfTheDay);
    }

    //3 задание
    //Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    //где a, b, c, d – аргументы этого метода, имеющие тип float.

    private static float evalExpression(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    //4 задание
    //Написать метод, принимающий на вход два целых числа и проверяющий,
    //что их сумма лежит в пределах от 10 до 20 (включительно),
    //если да – вернуть true, в противном случае – false.

    private static boolean sumIsBetween10And20(int a, int b) {
        int sum = a + b;
        return (sum >=10 && sum <= 20);
    }

    //5 задание
    //Написать метод, которому в качестве параметра передается целое число,
    //метод должен напечатать в консоль, положительное ли число передали или отрицательное.
    //Замечание: ноль считаем положительным числом.

    private static void printNumberSign(int n) {
        System.out.println("Число " + n + " является " + (n >= 0 ? "положительным" : "отрицательным"));
    }

    //6 задание
    //Написать метод, которому в качестве параметра передается целое число.
    //Метод должен вернуть true, если число отрицательное.

    private static boolean isNegativeNumber(int n) {
        return (n < 0);
    }

    //7 задание
    //Написать метод, которому в качестве параметра передается строка, обозначающая имя.
    //Метод должен вывести в консоль сообщение «Привет, указанное_имя!».

    private static void greetAVictim(String victim) {
        System.out.println("Привет, " + victim);
    }

    //8 задание
    //Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
    //Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

    private static void printLeapYear(int year) {
        boolean isLeapYear = false;

        if (year % 400 == 0) {
            isLeapYear = true;
        } else if (year % 100 == 0) {
            isLeapYear = false;
        } else if (year % 4 == 0) {
            isLeapYear = true;
        }

        System.out.println("Год " + year + " " + (!isLeapYear ? "не " : "") + "является високосным");
    }
}