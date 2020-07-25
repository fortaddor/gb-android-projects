package lesson5;

import java.util.Locale;

/**
 * Class employee.
 *
 * <author>Valerij Krauter</author>
 * <date>12.07.2020</date>
 */
public class Employee
{
    private String firstName;
    private String lastName;
    private String function;
    private String email;
    private String phone;
    private double salary;
    private int age;

    public Employee(String firstName, String lastName, String function, String email, String phone, double salary, int age)
    {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setFunction(function);
        this.setEmail(email);
        this.setPhone(phone);
        this.setSalary(salary);
        this.setAge(age);
    }

    public void print()
    {
        System.out.print(this);
    }

    public String toString()
    {
        StringBuilder emplInfo = new StringBuilder();

        emplInfo.append("Name: ").append(this.getFirstName()).append(" ").append(this.getLastName());
        emplInfo.append("\nFunction: ").append(this.getFunction());
        emplInfo.append("\nE-Mail: ").append(this.getEmail());
        emplInfo.append("\nPhone: ").append(this.getPhone());
        emplInfo.append("\nSalary: ").append(String.format(Locale.GERMAN,"%1$,.2f Euro", this.getSalary()));
        emplInfo.append("\nAge: ").append(String.format("%d", this.getAge()));
        emplInfo.append("\n\n");

        return emplInfo.toString();
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFunction()
    {
        return function;
    }

    public void setFunction(String function)
    {
        this.function = function;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
