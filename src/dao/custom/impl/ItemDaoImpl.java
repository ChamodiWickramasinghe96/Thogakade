package dao.custom.impl;

import dao.CrudUtil;
import dao.DaoFactory;
import dto.CustomerDto;
import dto.ItemDto;
import dao.ItemDao;
import entity.Customer;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean save(Item itd) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO item VALUES (?,?,?,?)",
                itd.getICode(), itd.getDesc(), itd.getUnitPrice(), itd.getQty());
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM item");
        ArrayList<Item> items = new ArrayList();
        while (set.next()) {
            items.add(new Item(set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4))

            );
        }
        return items;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE code=?",id);
    }

    @Override
    public boolean update(Item itd) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item SET description=?," +
                " unitPrice=?, qtyOnHand=? WHERE code=?",itd.getDesc(),itd.getUnitPrice(),itd.getQty(),itd.getICode());
    }

    @Override
    public Item get(String s) {
        return null;
    }

    @Override
    public ArrayList<Item> search(String text) throws SQLException, ClassNotFoundException {
        String tempText = "%"+text+"%";
        ResultSet set = CrudUtil.execute("SELECT * FROM item WHERE description LIKE ? ",
                tempText,tempText);
        ArrayList<Item> items = new ArrayList();
        while (set.next()) {
            items.add(new Item(
                    set.getString(1), set.getString(2),
                    set.getDouble(3), set.getInt(4)));
        }
        return items;
    }

//    @Override
//    public boolean addItem(ItemDto dto) throws SQLException, ClassNotFoundException {
//        String sql="INSERT INTO item VALUES(?,?,?,?)";
//        PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        stmt.setString(1,dto.getCode());
//        stmt.setString(2,dto.getDesc());
//        stmt.setDouble(3,dto.getUnitPrice());
//        stmt.setInt(4,dto.getQty());
//        return stmt.executeUpdate()>0;
//    }
//
//    @Override
//    public boolean removeItem(String code) throws SQLException, ClassNotFoundException {
//        String sql="DELETE FROM item WHERE code=?";
//        PreparedStatement stmt=DBConnection.getInstance().getConnection().prepareStatement(sql);
//        stmt.setString(1,code);
//        return stmt.executeUpdate()>0;
//    }
//
//    @Override
//    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
//        String sql = "UPDATE item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?";
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        pstm.setString(1,dto.getDesc());
//        pstm.setDouble(2,dto.getUnitPrice());
//        pstm.setInt(3,dto.getQty());
//        pstm.setString(4,dto.getCode());
//
//        return pstm.executeUpdate()>0;
//    }
//
//    @Override
//    public ItemDto searchItem(String id) throws SQLException, ClassNotFoundException {
//        List<ItemDto> list = new ArrayList<>();
//
//        String sql="SELECT * FROM item WHERE id=?";
//        PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = stmt.executeQuery();
//
//        while (resultSet.next()){
//            list.add(new ItemDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDouble(3),
//                    resultSet.getInt(4)
//            ));
//        }
//        return (ItemDto) list;
//    }
//
//    @Override
//    public List<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
//        List<ItemDto> list = new ArrayList<>();
//
//        String sql="SELECT * FROM item";
//        PreparedStatement stmt=DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = stmt.executeQuery();
//        while (resultSet.next()){
//            list.add(new ItemDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDouble(3),
//                    resultSet.getInt(4)
//
//            ));
//        }
//        return list;
//    }
}
