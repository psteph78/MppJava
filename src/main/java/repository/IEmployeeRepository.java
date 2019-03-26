package repository;

import model.Employee;

public interface IEmployeeRepository extends IRepository<Employee> {
    boolean loginUser(Employee employee);
}
