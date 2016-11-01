package patterns.clone.company;

import java.util.ArrayList;
import java.util.List;

public class Company implements Cloneable {
    private String name;
    private List<Employee> employees;

    public Company(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public int getSize() {
        return employees.size();
    }

    public void addEmployee(Employee p) {
        this.employees.add(p);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Company) {
            Company c = (Company) o;
            return name.equals(c.name)
                    && employees.equals(c.employees);
        } else {
            return false;
        }
    }

    @Override
    public Company clone() {
        try {
            Company c = (Company) super.clone();
            List<Employee> newList = new ArrayList<>();
            for (Employee e : c.employees) {
                newList.add(e.clone());
            }
            c.employees = newList;
            return c;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }
}
