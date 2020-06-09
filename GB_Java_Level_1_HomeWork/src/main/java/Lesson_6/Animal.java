package Lesson_6;

import java.util.HashMap;
import java.util.Map;

//1. Создать классы Собака и Кот с наследованием от класса Животное.

abstract public class Animal {

    //Выбрал имя "length" для счетчика, т. к. это свойство есть у всех объектов java.lang.Object

    public static long length;
    private String speciesSingularMale;
    private String speciesSingularFemale;
    private String speciesPlural;

    //3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).

    private Genders gender;
    private String name;
    private Map<String, String> maleActions = new HashMap<String, String>();
    private Map<String, Double> actionCapacities = new HashMap<String, Double>();
    private Map<String, String> femaleActions = new HashMap<String, String>();
    private Map<String, String> pluralActions = new HashMap<String, String>();
    //в 1С хеш-таблица тоже называется HashMap или Соответствие (в русском синтаксисе)

    public Animal() {
        this.length++;
    }

    public Animal(String name, Genders gender) {
        this();
        this.name = name;
        this.gender = gender;
    }

    public Animal(String name, Genders gender, double maxRunDistance) {
        this(name, gender);
        actionCapacities.put("run", maxRunDistance);
        actionCapacities.put("swim", new Double(0));
        actionCapacities.put("jump", new Double(0));
    }

    public Animal(String name, Genders gender, double maxRunDistance, double maxSwimDistance) {
        this(name, gender, maxRunDistance);
        actionCapacities.put("swim", maxSwimDistance);
    }

    public Animal(String name, Genders gender, double maxRunDistance, double maxSwimDistance, double maxJumpDistance) {
        this(name, gender, maxRunDistance, maxSwimDistance);
        actionCapacities.put("jump", maxJumpDistance);
    }

    public String getSpeciesSingular() {
        return (gender == Genders.Female ? speciesSingularFemale : speciesSingularMale);
    }

    public String getSpeciesPlural() { return speciesPlural; }

    public void initSpeciesNames(String speciesSingularMale, String speciesSingularFemale, String speciesPlural) {
        this.speciesSingularMale = speciesSingularMale;
        this.speciesSingularFemale = speciesSingularFemale;
        this.speciesPlural = speciesPlural;
    }

    public void initActions(String actionId, String actionMale, String actionFemale, String actionPlural) {
        maleActions.put(actionId, actionMale);
        femaleActions.put(actionId, actionFemale);
        pluralActions.put(actionId, actionPlural);
    }

    public double getMaxDistance(String actionId) {
        Double capacity = actionCapacities.get(actionId);
        return (capacity instanceof Double? capacity.doubleValue() : 0);
    }

    public Genders getGender() { return gender; }
    public String getName() { return name; }
    public String getActionSingular(String actionId)
    {
        return (getGender() == Genders.Male ? maleActions.get(actionId) : femaleActions.get(actionId));
    }
    public String getActionPlural(String actionId)
    {
        return pluralActions.get(actionId);
    }

    //2. Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
    //   Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');

    public void defaultBehaviour(String actionId, double obsLength) {
        double maxDistance = getMaxDistance(actionId);
        String actionPlural = getActionPlural(actionId);
        if (maxDistance == 0) {
            System.out.printf("%s не умеют %s\n", getSpeciesPlural(), actionPlural);
        } else if (obsLength > maxDistance) {
            System.out.printf("%s по кличке %s не умеет %s дальше %.1f м.\n", getSpeciesSingular(), getName(), actionPlural, maxDistance);
        } else {
            System.out.printf("%s по кличке %s %s %.1f м.\n", getSpeciesSingular(), getName(), getActionSingular(actionId), obsLength);
        }
    }

    abstract void run(double obsLength);

    abstract void swim(double obsLength);

    abstract void jump(double obsLength);

}