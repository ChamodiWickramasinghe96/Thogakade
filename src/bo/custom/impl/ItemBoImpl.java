package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.ItemDao;
import dao.custom.CustomerDao;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    //Create an instance of ItemDaoImpl
    ItemDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Item(dto.getCode(),dto.getDesc(),dto.getUnitPrice(),dto.getQty()));
    }

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> items = new ArrayList<>();
        for(Item d:dao.getAll()){
            items.add(new ItemDto(d.getICode(),d.getDesc(),d.getUnitPrice(),d.getQty()));
        }
        return items;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Item(dto.getCode(),dto.getDesc(),dto.getUnitPrice(),dto.getQty()));
    }

    @Override
    public ItemDto getItem(String id) {
        return null;
    }

    @Override
    public ArrayList<ItemDto> search(String text) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> items = new ArrayList<>();
        for(Item d:dao.search(text)){
            items.add(new ItemDto(d.getICode(),d.getDesc(),d.getUnitPrice(),d.getQty()));
        }
        return items;
    }
}
