package bo.custom;

import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    public CustomerDto getCustomer(String id);

    public ArrayList<CustomerDto> search(String text) throws SQLException, ClassNotFoundException;
}
