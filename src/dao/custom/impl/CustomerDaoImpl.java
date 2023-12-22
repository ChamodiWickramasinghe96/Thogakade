package dao.custom.impl;

import dao.CrudUtil;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer cd) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?)",
                cd.getCId(), cd.getName(), cd.getAddress(), cd.getSalary());
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customers = new ArrayList();
        while (set.next()) {
            customers.add(new Customer(set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4))

            );
        }
        return customers;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public boolean update(Customer cd) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET name=?," +
                " address=?, salary=? WHERE id=?",cd.getName(),cd.getAddress(),cd.getSalary(),cd.getCId());
    }

    @Override
    public Customer get(String s) {
        return null;
    }

    @Override
    public ArrayList<Customer> search(String text) throws SQLException, ClassNotFoundException {
        String tempText = "%"+text+"%";
        ResultSet set = CrudUtil.execute("SELECT * FROM customer WHERE name LIKE ? || address LIKE ?",
                tempText,tempText);
        ArrayList<Customer> customers = new ArrayList();
        while (set.next()) {
            customers.add(new Customer(
                    set.getString(1), set.getString(2),
                    set.getString(3), set.getDouble(4)));
        }
        return customers;
    }

//    @Override
//    public boolean addCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
//       String sql="INSERT INTO customer VALUES(?,?,?,?)";
//        PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        stmt.setString(1,dto.getId());
//        stmt.setString(2,dto.getName());
//        stmt.setString(3,dto.getAddress());
//        stmt.setString(4,String.valueOf(dto.getSalary()));
//        return stmt.executeUpdate()>0;
//    }
//
//    @Override
//    public boolean removeCustomer(String id) throws SQLException, ClassNotFoundException {
//        String sql="DELETE FROM customer WHERE id=?";
//        PreparedStatement stmt=DBConnection.getInstance().getConnection().prepareStatement(sql);
//        stmt.setString(1,id);
//        return stmt.executeUpdate()>0;
//    }
//
//    @Override
//    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
//        String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setString(1,dto.getName());
//        pstm.setString(2,dto.getAddress());
//        pstm.setDouble(3,dto.getSalary());
//        pstm.setString(4,dto.getId());
//
//        return pstm.executeUpdate()>0;
//    }
//
//    @Override
//    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
//        List<CustomerDto> list = new ArrayList<>();
//
//        String sql="SELECT * FROM customer WHERE id=?";
//        PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = stmt.executeQuery();
//
//        while (resultSet.next()){
//            list.add(new CustomerDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4)
//            ));
//        }
//        return (CustomerDto) list;
//    }
//
//    @Override
//    public List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
//        List<CustomerDto> list = new ArrayList<>();
//
//        String sql="SELECT * FROM customer";
//        PreparedStatement stmt=DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = stmt.executeQuery();
//        while (resultSet.next()){
//            list.add(new CustomerDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4)
//
//            ));
//        }
//        return list;
//    }
}
