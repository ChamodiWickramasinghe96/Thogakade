package modal.impl;

import db.DBConnection;
import dto.CustomerDto;
import modal.CustomerModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModalImpl implements CustomerModal {

    @Override
    public boolean addCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
       String sql="INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
        stmt.setString(1,dto.getId());
        stmt.setString(2,dto.getName());
        stmt.setString(3,dto.getAddress());
        stmt.setString(4,String.valueOf(dto.getSalary()));
        return stmt.executeUpdate()>0;
    }

    @Override
    public boolean removeCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM customer WHERE id=?";
        PreparedStatement stmt=DBConnection.getInstance().getConnection().prepareStatement(sql);
        stmt.setString(1,id);
        return stmt.executeUpdate()>0;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2,dto.getAddress());
        pstm.setDouble(3,dto.getSalary());
        pstm.setString(4,dto.getId());

        return pstm.executeUpdate()>0;
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        List<CustomerDto> list = new ArrayList<>();

        String sql="SELECT * FROM customer WHERE id=?";
        PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            list.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return (CustomerDto) list;
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<CustomerDto> list = new ArrayList<>();

        String sql="SELECT * FROM customer";
        PreparedStatement stmt=DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()){
            list.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)

            ));
        }
        return list;
    }
}
