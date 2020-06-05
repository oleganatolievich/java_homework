package Lesson_5;

import java.time.LocalDate;
import java.util.Arrays;

public class EmployeeDemo {
    private static Employee[] employees = new Employee[5];
    static
    {
        //4. Создать массив из 5 сотрудников.
        initEmployees();
    }

    private static void initEmployees() {
        employees[0] = new Employee("Абазин Антон Владимирович", Positions.Mesero,
                "sonOfMothersFriend@mail.ru",
                "+7-666-666-66-66",
                3_000,
                LocalDate.of(1978, 12, 12));

        employees[1] = new Employee("Шокин Николай Ефимович", Positions.Policia,
                "shoknik@mail.ru",
                "+7-777-777-77-77",
                7_000,
                LocalDate.of(1928, 1, 1));

        employees[2] = new Employee("Борзыкин Александр Ефремович", Positions.Asesino,
                "asesino@gmail.com",
                "+7-888-888-88-88",
                17_000,
                LocalDate.of(1988, 6, 9));

        employees[3] = new Employee("Алоизов Артур Афанасьевич", Positions.Asesino,
                "myCredentialsAreAAA@gmail.com",
                "+7-654-321-32-43",
                25_000,
                LocalDate.of(1974, 4, 4));

        employees[4] = new Employee("Новиков Максим Евгеньевич", Positions.Mexican,
                "novikov.maksim@gmail.com",
                "+7-911-333-22-11",
                25_000,
                LocalDate.of(1987, 5, 12));
    }

    public static void main(String[] args) {
        //5. *С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
        int counter = 1;
        for (Employee emp: employees) {
            if (emp.getAge() > 40) {
                System.out.printf("%d. ", counter);
                emp.showInfo();
                System.out.println("\n-------------------------------------------------------------\n");
                counter++;
            }
        }
    }
}
