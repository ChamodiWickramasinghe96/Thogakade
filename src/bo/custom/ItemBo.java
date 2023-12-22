package bo.custom;

import dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo {
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    public ItemDto getItem(String id);

    public ArrayList<ItemDto> search(String text) throws SQLException, ClassNotFoundException;
}
