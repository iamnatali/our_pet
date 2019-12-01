package com.company;

import org.apache.logging.log4j.core.appender.rolling.action.IfNot;

import java.util.HashMap;

import java.sql.*;


public class PetDB implements DataStorage {
    //возможно стоит внести в константы
    private String database="ipet.db";
    private String table="infopet";
    private String insertString = "INSERT INTO "+
            table+
            " VALUES (?, ?, ?, ?)";
    private String getString ="SELECT * FROM "+table+" WHERE id=?";
    private String updateString="UPDATE "+table+" SET name=?, gender=? WHERE id=?";
    private String deleteString="DELETE FROM "+table+" WHERE id=?";

    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        }
        catch (SQLException e){
            System.out.println("Database exception(connection): "+e.getMessage());
        }
        return connection;
    }

    @Override
    public void deleteData(Long index){
        Connection con = this.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(deleteString)) {
            pstmt.setLong(1, index);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
    }

    @Override
    public void updateData(Long index, PetBot pet){
        Connection con = this.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(updateString)) {
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.learnGender());
            pstmt.setLong(3, index);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Database exception: "+e.getMessage());
        }
    }


    //Boolean-найден ли индекс
    @Override
    public HashMap<PetBot, Boolean> getData(Long index){
        PetBot pet=new PetBot();
        boolean flag=false;
        Connection con = this.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(getString);) {
            pstmt.setLong(1, index);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                flag = true;
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                Integer health=rs.getInt("health");
                pet.giveName(name);
                pet.chooseGender(gender);
                pet.setWealth(health);
                System.out.println("DB health"+health);
            }
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        HashMap<PetBot, Boolean> result = new HashMap<>();
        result.put(pet, flag);
        return result;
    }

    @Override
    public void setData(Long index, PetBot pet){
        Connection con = this.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(insertString)) {
            pstmt.setLong(1,index);
            pstmt.setString(2,pet.getName());
            pstmt.setString(3,pet.learnGender());
            pstmt.setInt(4, pet.getWealth());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Database exception: "+e.getMessage());
        }
    }
}
