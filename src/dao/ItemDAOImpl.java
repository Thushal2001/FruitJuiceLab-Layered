package dao;

import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl {

    public ArrayList<ItemDTO> loadAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection().getConnection();
        String sql = "SELECT * FROM item";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            ItemDTO itemDTO = new ItemDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getBigDecimal(4));
            allItems.add(itemDTO);
        }
        return allItems;
    }

    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        String sql = "INSERT INTO item (itemCode, description, qtyOnHand, unitPrice) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, itemDTO.getCode());
        preparedStatement.setString(2, itemDTO.getDescription());
        preparedStatement.setInt(3, itemDTO.getQtyOnHand());
        preparedStatement.setBigDecimal(4, itemDTO.getUnitPrice());
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET description=?, qtyOnHand=?, unitPrice=? WHERE itemCode=?");
        preparedStatement.setString(1, itemDTO.getDescription());
        preparedStatement.setInt(2, itemDTO.getQtyOnHand());
        preparedStatement.setBigDecimal(3, itemDTO.getUnitPrice());
        preparedStatement.setString(4, itemDTO.getCode());
        return preparedStatement.executeUpdate() > 0;
    }

    /*public boolean delete(String id) throws SQLException, ClassNotFoundException {

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {

    }

    public ResultSet generateNextId() throws SQLException, ClassNotFoundException {

    }*/
}
