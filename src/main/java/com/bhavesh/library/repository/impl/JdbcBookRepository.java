package com.bhavesh.library.repository.impl;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookRepository implements BookRepository {

    @Override
    public boolean save(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, quantity) VALUES (?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthor());
            statement.setString(3,book.getIsbn());
            statement.setInt(4,book.getQuantity());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to save book" + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        String sql = "SELECT title, author, isbn, quantity FROM books WHERE isbn = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,isbn);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"),
                            resultSet.getInt("quantity")
                    );
                    return Optional.of(book);
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            System.out.println("Failed to find book: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT title, author, isbn, quantity FROM books;";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Book> books = new ArrayList<>();
                while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"),
                            resultSet.getInt("quantity")
                    );
                    books.add(book);
                }
                return books;
            }

        } catch (SQLException e) {
            System.out.println("Failed to find books: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<Book> findByTitle(String keyword) {
        String sql = "SELECT title, author, isbn, quantity FROM books WHERE title ILIKE ?;";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Book> books = new ArrayList<>();
                while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"),
                            resultSet.getInt("quantity")
                    );
                    books.add(book);
                }
                return books;
            }

        } catch (SQLException e) {
            System.out.println("Failed to find books: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public boolean update(Book book){
        String sql = "UPDATE books SET quantity = ? WHERE isbn = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,book.getQuantity());
            statement.setString(2,book.getIsbn());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to update book" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String isbn) {

        String sql = "DELETE FROM books WHERE isbn = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, isbn);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
            } catch (SQLException e) {
                System.out.println("Failed to delete book" + e.getMessage());
                return false;
        }
    }


}
