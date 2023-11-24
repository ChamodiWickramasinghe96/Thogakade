package modal;

import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemModal {
    boolean addItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean removeItem(String id) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    ItemDto searchItem(String id) throws SQLException, ClassNotFoundException;
    List<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;
}
