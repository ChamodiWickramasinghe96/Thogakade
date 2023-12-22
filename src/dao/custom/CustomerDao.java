package dao.custom;

import dao.CrudDao;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer,String> {
    public ArrayList<Customer> search(String text) throws SQLException, ClassNotFoundException;

//boolean addCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
//boolean removeCustomer(String id) throws SQLException, ClassNotFoundException;
//boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
//CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;
//List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

}
