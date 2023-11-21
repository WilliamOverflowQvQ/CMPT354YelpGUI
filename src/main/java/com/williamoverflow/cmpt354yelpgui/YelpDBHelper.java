package com.williamoverflow.cmpt354yelpgui;

import com.williamoverflow.cmpt354yelpgui.entities.YelpUser;

import java.sql.*;



public class YelpDBHelper {
    public static YelpDBHelper ydbh = new YelpDBHelper();


    public String url = "";
    public String username = "";
    private String password = "";
    public String dbname = "";
    public boolean encrypt = false;
    public Connection connection = null;

    public YelpUser sceneUser = null;

    // full
    public YelpDBHelper(String url, String username, String password, String dbname, boolean encrypt) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.dbname = dbname;
        this.encrypt = encrypt;
    }

    public YelpDBHelper() {
        this.url = "cypress.csil.sfu.ca;";
        this.username = "s_zza198";
        this.password = "d4rHQtH4tM76En64";
        this.dbname = "zza198354";
        this.encrypt = false;
    }

    public boolean isClosed(){
        try {
            return connection == null || connection.isClosed();
        }catch (SQLException ex){
            System.err.println(ex);
            return true;
        }
    }

    public String getConnStr(){
        String result = "jdbc:sqlserver://{url};databaseName={dbname};user={username};password={password};encrypt={encrypt}";
        result = result.replace("{url}", url);
        result = result.replace("{dbname}", dbname);
        result = result.replace("{username}", username);
        result = result.replace("{password}", password);
        result = result.replace("{encrypt}", String.valueOf(encrypt));

        return result;
    }

    public void connect() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  // for db driver set up DO NOT TOUCH
        if (isClosed()) {
            connection = DriverManager.getConnection(getConnStr());
            // ...
        }
    }

    public void close() throws SQLException {
        if (isClosed()) {
            connection.close();
        }
    }

    public YelpUser getUserYelpById(String userId) throws SQLException {
        String sql = "SELECT * FROM user_yelp WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return YelpUser.map(resultSet);
            } else {
                return null;
            }
        }
    }

    public YelpUser getUserYelpByName(String name) throws SQLException {
        String sql = "SELECT * FROM user_yelp WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return YelpUser.map(resultSet);
            } else {
                return null;
            }
        }
    }




}
