package client_api;

import data_models.Customer;
import data_models.Employee;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.UUID;

@WebService
public interface IAdministrativeApi {
    @WebMethod
    Employee getEmployeeById(UUID uuid);
    @WebMethod
    List<Employee> getEmployees();
    @WebMethod
    boolean createEmployee(Employee employee);
    @WebMethod
    boolean updateEmployee(Employee employee);

    @WebMethod
    Customer getCustomer(UUID customerId);
    @WebMethod
    List<Customer> getCustomers();
    @WebMethod
    boolean createCustomer(Customer customer);
    @WebMethod
    boolean updateCustomer(Customer customer);
}
