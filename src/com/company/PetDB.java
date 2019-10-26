package com.company;

import javafx.util.Pair;

import java.sql.*;

public class PetDB {
    //возможно стоит внести в константы
    private String database="ipet.db";
    private String table="infopet";
    private String insertString = "INSERT INTO "+
            table+
            " VALUES (?, ?, ?)";
    private String getString ="SELECT * FROM "+table+" WHERE id=?";
    private String updateString="UPDATE "+table+" SET name=?, gender=? WHERE id=?";
    private String deleteString="DELETE FROM "+table+" WHERE id=?";

    private Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        }
        catch (SQLException e){
            System.out.println("Database exception(connection): "+e.getMessage());
        }
        return connection;
    }

    int deleteData(Long index) throws SQLException {
        int a=0;
        Connection con = this.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(deleteString)) {
            pstmt.setLong(1,index);
            a=pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Database exception: "+e.getMessage());
        }
        return a;
    }

    int updateData(Long index, PetBot pet) throws SQLException {
        int a=0;
        Connection con = this.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(updateString)) {
            pstmt.setString(1,pet.getName());
            pstmt.setString(2,pet.learnGender());
            pstmt.setLong(3,index);
            a=pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Database exception: "+e.getMessage());
        }
        return a;
    }

    //Boolean-найден ли индекс
    Pair<PetBot, Boolean> getData(Long index) throws SQLException {
        PetBot pet=new PetBot();
        boolean flag=false;
        Connection con = this.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(getString);) {
            pstmt.setLong(1,index);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                flag=true;
                String name=rs.getString("name");
                String gender=rs.getString("gender");
                pet.giveName(name);
                pet.chooseGender(gender);
            }
        }
        return new Pair<>(pet, flag);
    }

    public int setData(Long index, PetBot pet) throws SQLException {
        int a=0;
        Connection con = this.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(insertString)) {
            pstmt.setLong(1,index);
            pstmt.setString(2,pet.getName());
            pstmt.setString(3,pet.learnGender());
            a=pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Database exception: "+e.getMessage());
        }
        return a;
    }
}
