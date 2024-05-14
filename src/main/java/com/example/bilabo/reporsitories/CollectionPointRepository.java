package com.example.bilabo.reporsitories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.example.bilabo.model.CollectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CollectionPointRepository {

    @Autowired
    private DataSource dataSource;

    public CollectionPoint save(CollectionPoint collectionPoint) {
        String sql = "INSERT INTO afhentning (adresse) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, collectionPoint.getAddress());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating collection point failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    collectionPoint.setCollectionPointID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating collection point failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception
        }
        return collectionPoint;
    }

    public CollectionPoint findById(int collectionPointId) {
        String sql = "SELECT * FROM afhentning WHERE afhentning_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, collectionPointId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCollectionPoint(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception
        }
        return null;
    }

    public List<CollectionPoint> findAll() {
        List<CollectionPoint> collectionPoints = new ArrayList<>();
        String sql = "SELECT * FROM afhentning";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                collectionPoints.add(mapResultSetToCollectionPoint(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception
        }
        return collectionPoints;
    }

    public void deleteById(int collectionPointId) {
        String sql = "DELETE FROM afhentning WHERE afhentning_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, collectionPointId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception
        }
    }

    private CollectionPoint mapResultSetToCollectionPoint(ResultSet resultSet) throws SQLException {
        CollectionPoint collectionPoint = new CollectionPoint();
        collectionPoint.setCollectionPointID(resultSet.getInt("afhentning_id"));
        collectionPoint.setAddress(resultSet.getString("adresse"));
        return collectionPoint;
    }

    public void update(CollectionPoint collectionPoint) {
        String sql = "UPDATE afhentning SET adresse = ? WHERE afhentning_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, collectionPoint.getAddress());
            statement.setInt(2, collectionPoint.getCollectionPointID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception
        }

    }
}


