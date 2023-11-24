package modal;

import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerModal {

boolean addCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
boolean removeCustomer(String id) throws SQLException, ClassNotFoundException;
boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;
List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

}
