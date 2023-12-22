package dao;

import dto.CustomerDto;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item,String> {

    public ArrayList<Item> search(String text) throws SQLException, ClassNotFoundException;
//    boolean addItem(ItemDto dto) throws SQLException, ClassNotFoundException;
//    boolean removeItem(String id) throws SQLException, ClassNotFoundException;
//    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
//    ItemDto searchItem(String id) throws SQLException, ClassNotFoundException;
//    List<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;
}
