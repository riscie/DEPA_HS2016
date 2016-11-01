package patterns.clone.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CloningTest {
	private Company c1, c2;
	private Employee p1, p2, p3;
	private ArrayList<Employee> employees;

	@Before
	public void setUp() throws Exception {
		p1 = new Employee("P1", 1990);
		p2 = new Employee("P2", 1989);
		p3 = new Employee("P3", 1993);
		employees = new ArrayList<>();
		employees.add(p1);
		employees.add(p2);
		employees.add(p3);
		c1 = new Company("C1", employees);
	}

	@Test
	public void testCompanyClone() {
		c2 = c1.clone();
		assertTrue(c1 != c2);
		assertEquals(c1.getClass(), c2.getClass());
		assertEquals(c1.getName(), c2.getName());
	}

	@Test
	public void testEmployeeClone() {
		p2 = p1.clone();
		assertTrue(p1 != p2);
		assertEquals(p1.getClass(), p2.getClass());
		assertEquals(p1.getName(), p2.getName());
		assertEquals(p1.getYearOfBirth(), p2.getYearOfBirth());
	}

	@Test
	public void testShallow() {
		c2 = c1.clone();
		assertEquals(c1, c2);
	}

	@Test
	public void testDeep1() {
		c2 = c1.clone();
		assertEquals(c1, c2);
		c1.addEmployee(new Employee("new", 1993));
		assertFalse(c1.equals(c2));
	}

	@Test
	public void testDeep2() {
		c2 = c1.clone();
		assertEquals("this is not even a shallow copy", c1, c2);
		c1.addEmployee(new Employee("new", 1993));
		assertFalse("this is only a shallow copy", c1.equals(c2));
		c2 = c1.clone();
		p1.setName("Changed");
		assertFalse("you didn't copy deep enough", c1.equals(c2));
	}

	@Test
	public void testDeepWithPartTimeEmployee() {
		PartTimeEmployee ppe = new PartTimeEmployee("new", 1993, 42);
		c1.addEmployee(ppe);
		c2 = c1.clone();
		assertEquals("this is not even a shallow copy", c1, c2);
		c2 = c1.clone();
		ppe.setWorkload(10);
		assertFalse("you didn't copy deep enough", c1.equals(c2));
	}
}
