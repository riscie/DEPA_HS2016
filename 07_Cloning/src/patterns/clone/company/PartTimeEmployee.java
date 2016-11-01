package patterns.clone.company;

public class PartTimeEmployee extends Employee implements Cloneable {
    private int workload;

    public PartTimeEmployee(String name, int yearOfBirth, int workload) {
        super(name, yearOfBirth);
        this.workload = workload;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PartTimeEmployee) {
            PartTimeEmployee p = (PartTimeEmployee) o;
            return p.workload == workload;
        }
        return false;
    }

    //Könnte auch entfernt werden
    @Override
    public PartTimeEmployee clone() {
        return (PartTimeEmployee) super.clone();
    }
}
