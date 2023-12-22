package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {

    //Create an instance of CustomerDaoImpl
    CustomerDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getSalary()));

    }

    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customers = new ArrayList<>();
        for(Customer c:dao.getAll()){
            customers.add(new CustomerDto(c.getCId(),c.getName(),c.getAddress(),c.getSalary()));
        }
        return customers;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getSalary()));

    }

    @Override
    public CustomerDto getCustomer(String id) {
        return null;
    }

    @Override
    public ArrayList<CustomerDto> search(String text) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> dots = new ArrayList<>();
        for(Customer d:dao.search(text)){
            dots.add(new CustomerDto(d.getCId(),d.getName(),d.getAddress(),d.getSalary()));
        }
        return dots;
    }
}
