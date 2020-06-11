package Lesson_6;

public class Horse extends Animal {

    public static long length;

    public Horse(String name, Genders gender) {
        super(name, gender, 1500, 100, 3);
        commonInitActions();
    }

    public Horse(String name, Genders gender, double maxRunDistance, double maxSwimDistance, double maxJumpDistance) {
        super(name, gender, maxRunDistance, maxSwimDistance, maxJumpDistance);
        commonInitActions();
    }

    private void commonInitActions() {
        initSpeciesNames("Конь", "Лошадь", "Лошади");
        initActions("run", "пробежал", "пробежала", "бегать");
        initActions("swim", "проплыл", "проплыла", "плавать");
        initActions("jump", "прыгнул на", "прыгнула на", "прыгать");
        this.length++;
    }

    void run(double obsLength) {
        defaultBehaviour("run", obsLength);
    }
    void swim(double obsLength) {
        defaultBehaviour("swim", obsLength);
    }
    void jump(double obsLength) {
        defaultBehaviour("jump", obsLength);
    }

}
