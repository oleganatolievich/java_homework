package Lesson_6;

import java.util.ArrayList;
import java.util.Random;

public class AnimalsDemo {

    private static ArrayList<Animal> animals = new ArrayList<Animal>();

    static
    {
        fillAnimals();
    }

    private static void fillAnimals() {

        animals.add(new Cat("Лео", Genders.Male, 2000, 3000, 4000));
        animals.add(new Cat("Тигрица", Genders.Female, 20, 30, 40));
        animals.add(new Cat("Барсик", Genders.Male));
        animals.add(new Cat("Бусинка", Genders.Female));

        animals.add(new Dog("Федук", Genders.Male, 300, 400, 500));
        animals.add(new Dog("Амина", Genders.Female, 100, 200, 300));
        animals.add(new Dog("Баха", Genders.Male));
        animals.add(new Dog("Зуля", Genders.Female));

        animals.add(new Bird("Курица", Genders.Male, 300, 400, 500));
        animals.add(new Bird("Петухан", Genders.Female, 100, 200, 300));
        animals.add(new Bird("Фазан", Genders.Male));
        animals.add(new Bird("Кря-кря", Genders.Male));

        animals.add(new Horse("Чугунный", Genders.Male, 300, 400, 500));
        animals.add(new Horse("Cкакунья", Genders.Female, 1000, 2000, 3000));
        animals.add(new Horse("Безумный", Genders.Male));
        animals.add(new Horse("Важная", Genders.Female));

    }

    public static void main(String[] args) {

        Random randGen = new Random(100);
        int randInt;

        for (Animal animal: animals) {
            animal.run(randGen.nextInt(100 - 1) + 1);
            animal.swim(randGen.nextInt(200 - 1) + 1);
            animal.jump(randGen.nextInt(300 - 1) + 1);
            System.out.println("\n-----------------------------------------------------------------------\n");
        }

        System.out.printf("Всего животных: %d, котов: %d, собак: %d, птиц: %d и лошадей: %d",
                Animal.length, Cat.length, Dog.length, Bird.length, Horse.length);

    }
}
