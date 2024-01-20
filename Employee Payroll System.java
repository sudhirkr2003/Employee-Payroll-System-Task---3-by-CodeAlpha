package EmployeePayrollSystem;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee{
    private String name;
    private int id;
    public Employee(String name,int id){
        this.name=name;
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public  int getId(){
        return id;
    }

    abstract public double calculateSalary();

    @Override
    public String toString(){
        return "Employee [ Name: " + name + ", id: " + id + ", Salary: "+ calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee{
    private double Salary;

    public  FullTimeEmployee(String name, int id,double Salary){
        super(name,id);
        this.Salary = Salary;
    }

    @Override
    public double calculateSalary(){return Salary;}
}

class PartTimeEmployee extends Employee{
    private int hourworked;
    private int hourlyrate;


    public PartTimeEmployee(String name, int id, int hourworked, int hourlyrate){
        super(name, id);
        this.hourworked = hourworked;
        this.hourlyrate = hourlyrate;
    }

    @Override
    public  double calculateSalary(){
        return hourworked*hourlyrate;
    }
}

class PayrollSystem{
    private ArrayList<Employee> employeeList;

    public PayrollSystem(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(){
        Scanner in = new Scanner(System.in);
        System.out.println("Which type of employee you are : ");
        System.out.println("1. Full time");
        System.out.println("2. Part time");
        System.out.println("select one option : ");
        int type = in.nextInt();

        if (type == 1){

            System.out.println("Enter the ID: ");
            int id = in.nextInt();

            System.out.println("Enter the Salary : ");
            double salary = in.nextDouble();

            in.nextLine();

            System.out.println("Enter the Name : ");
            String name = in.nextLine();

            FullTimeEmployee NewEmp = new FullTimeEmployee(name, id, salary);
            employeeList.add(NewEmp);
            System.out.println("New Full Time Employee Added Successfully!!");

            System.out.println("You want add more employee [Y/N] : ");
            char ch = in.next().charAt(0);
            if(ch == 'y'||ch=='Y'){
                addEmployee();
            }
            else{
                System.out.println("Thank you !!");
            }
        }
        else {
            System.out.println("Enter the ID: ");
            int id = in.nextInt();

            System.out.println("Enter the hour many hour you worked : ");
            int hourworked = in.nextInt();

            System.out.println("Enter the per hour rate : ");
            int hourlyrate = in.nextInt();

            in.nextLine();

            System.out.println("Enter the Name : ");
            String name = in.nextLine();

            PartTimeEmployee NewEmp = new PartTimeEmployee(name, id, hourworked,hourlyrate);
            employeeList.add(NewEmp);
            System.out.println("Part Time Employee Added Successfully!!");

            System.out.println("You want add more employee [Y/N] : ");
            char ch = in.next().charAt(0);
            if(ch == 'y'||ch=='Y'){
                addEmployee();
            }
            else{
                System.out.println("Thank you !!");
            }
        }
    }

    public void removeEmployee(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id : ");
        int id = in.nextInt();
        Employee employeeToremove = null;
        for(Employee employee : employeeList){
            if(employee.getId() == id){
                employeeToremove = employee;
                break;
            }
        }

        if(employeeToremove !=null){
            employeeList.remove(employeeToremove);
        }
    }

    public void displayAllDetails(){
        if(employeeList.isEmpty()){
            System.out.println("No Employee found!!");
            return;
        }
        else{
            for (Employee employee : employeeList){
                System.out.println(employee);
            }
        }
    }

    public  void exit() {System.exit(0);}
}



public class Main {
    public static void main(String[] args) {
        PayrollSystem payroll = new PayrollSystem();

        System.out.println("Welcome to Employee Payroll System");
        System.out.println("1. Display Employee Details");
        System.out.println("2. Removing Employee");
        System.out.println("3. Add new Employee");
        System.out.println("4. Exit");

        Scanner sc = new Scanner(System.in);
        System.out.println("Select Option : ");
        int choice = sc.nextInt();
        switch (choice){
            case 1 : payroll.displayAllDetails();
            break;
            case 2 : payroll.removeEmployee();
            break;
            case 3 : payroll.addEmployee();
            break;
            case 4 : payroll.exit();
            break;
            default: System.out.println("Invalid Option!!");
        }

    }
}
