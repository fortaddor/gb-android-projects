package lesson5;

/**
 * Home work to lesson 5.
 *
 * <author>Valerij Krauter</author>
 * <date>12.07.2020</date>
 */
public class Lesson5
{
    public static final int EMPLOYEE_NUMBER = 5;

    private Employee[] employees = new Employee[EMPLOYEE_NUMBER];

    public static void main(String[] args)
    {
        Lesson5 lesson = new Lesson5();

        lesson.createEmployees();
        lesson.printEmployeesOlderFourty();
    }

    private void createEmployees()
    {
        employees[0] = new Employee("Michael", "Schmidt", "CEO", "m.schmidt@company.com",
                "+49755321456", 6536.80, 55);
        employees[1] = new Employee("Michael", "Smith", "CIO", "m.smith@company.com",
                "+49755321465", 5065.80, 43);
        employees[2] = new Employee("Michail", "Kuznecov", "Senior Manager", "m.kuznecov@company.com",
                "+49755321474", 4998.60, 44);
        employees[3] = new Employee("Michal", "Kovac", "Project manager", "m.kovac@company.com",
                "+49755321444", 4995.98, 39);
        employees[4] = new Employee("Miquel", "Ferres", "Trainee", "m.ferres@company.com",
                "+49755321422", 2110.20, 22);
    }

    private void printEmployeesOlderFourty()
    {
        for (Employee employee : this.employees)
        {
            if (employee.getAge() > 40)
            {
                employee.print();
            }
        }
    }
}
