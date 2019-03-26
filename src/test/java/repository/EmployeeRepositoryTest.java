package repository;

import model.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeRepositoryTest {

    @Test
    public void loginUser() {
        EmployeeRepository repo = new EmployeeRepository();
        Employee employee = new Employee("steph", "1234");
        Employee employee1 = new Employee("steph", "1");
        boolean check = repo.loginUser(employee);
        assertTrue(check);
        check = repo.loginUser(employee1);
        assertFalse(check);
    }
}