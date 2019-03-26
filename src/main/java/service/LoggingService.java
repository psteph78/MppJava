package service;

import model.Employee;
import repository.EmployeeRepository;

public class LoggingService {
    private EmployeeRepository repository = new EmployeeRepository();

    public boolean loginUser(Employee employee){return repository.loginUser(employee);}
}
