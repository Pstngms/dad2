package JumpMyBallApp.manager;

import JumpMyBallApp.App;
import JumpMyBallApp.entity.ProductEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductEntityManager {
    public static void insert(ProductEntity product) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "INSERT INTO product (Title,ProductType,ArticleNumber,Description,Image,ProductionPersonCount,ProductionWorkshopNumber,MinCostForAgent)VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,product.getTitle());
            ps.setString(2,product.getProductType());
            ps.setInt(3,product.getArticleNumber());
            ps.setString(4,product.getDescription());
            ps.setString(5,product.getImage());
            ps.setInt(6,product.getProductionPersonCount());
            ps.setInt(7,product.getProductionWorkshopNumber());
            ps.setInt(8,product.getMinCostForAgent());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                product.setId(resultSet.getInt(1));
                return;
            }
        }
    }
    public static List<ProductEntity> selectAll() throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "SELECT * FROM product";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            List<ProductEntity> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getInt("ArticleNumber"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getInt("ProductionPersonCount"),
                        resultSet.getInt("ProductionWorkshopNumber"),
                        resultSet.getInt("MinCostForAgent")
                ));
            }
            return list;
        }
    }
    public static void update(ProductEntity product) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "UPDATE product SET Title=?,ProductType=?,ArticleNumber=?,Description=?,Image=?,ProductionPersonCount=?,ProductionWorkshopNumber=?,MinCostForAgent=? WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,product.getTitle());
            ps.setString(2,product.getProductType());
            ps.setInt(3,product.getArticleNumber());
            ps.setString(4,product.getDescription());
            ps.setString(5,product.getImage());
            ps.setInt(6,product.getProductionPersonCount());
            ps.setInt(7,product.getProductionWorkshopNumber());
            ps.setInt(8,product.getMinCostForAgent());
            ps.setInt(9,product.getId());
            ps.executeUpdate();
        }
    }
    public static ProductEntity selectById(int id) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "SELECT * FROM product WHERE ID=?";
            PreparedStatement ps =c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getInt("ArticleNumber"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getInt("ProductionPersonCount"),
                        resultSet.getInt("ProductionWorkshopNumber"),
                        resultSet.getInt("MinCostForAgent")
                );
            }
            return null;
        }
    }
    public static void delete(int id) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "DELETE FROM product WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }
}
