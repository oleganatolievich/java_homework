package Lesson_5;


import java.time.LocalDate;
import java.time.Period;

//1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
public class Employee {

    private String fullName;
    private Positions position;
    private String email;
    private String phone;
    private int salary;
    private LocalDate birthDate;
    private long personnelNumber;

    //2. Конструктор класса должен заполнять эти поля при создании объекта.
    public Employee(String fullName, Positions position, String email, String phone, int salary, LocalDate birthDate) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.birthDate = birthDate;
        //SQL.insert into Employees value {bla, bla, bla}
        //SQL.select personnelnumber from Employees, this.personnelNumber = recordset.personnelNumber;
    }

    //3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
    public void showInfo() {
        System.out.println("ФИО: " + fullName);
        System.out.println("Таб. номер: " + getPN());
        System.out.println("Должность: " + position);
        System.out.println("E-mail: " + email);
        System.out.println("Телефон: " + phone);
        System.out.println("ЗП (EUR): " + salary);
        System.out.println("Возраст (в годах): " + getAge());
    }

    public long getPN() {
        return personnelNumber;
    }

    public long getAge() {
        Period period = Period.between(birthDate, LocalDate.now());
        return period.getYears();
    }
}
