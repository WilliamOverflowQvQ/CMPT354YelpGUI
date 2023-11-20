package com.williamoverflow.cmpt354yelpgui;

import java.sql.*;



public class YelpDBHelper {
    public static YelpDBHelper ydbh;


    private String url;
    private String username;
    private String password;
    private String dbname;
    private boolean encrypt = false;
    public Connection connection;


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
            return connection.isClosed();
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

    public void connect() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(getConnStr());
                // ...
            }
        }catch (ClassNotFoundException ex){
            System.err.println("WZE: JDBC for sql server DNE!");
            System.err.println(ex);
            return;
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public UserYelp getUserYelpById(String userId) throws SQLException {
        String sql = "SELECT * FROM user_yelp WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToUserYelp(resultSet);
            } else {
                return null; // 或者抛出一个异常，取决于你的需求
            }
        }
    }

    private UserYelp mapResultSetToUserYelp(ResultSet resultSet) throws SQLException {
        String user_id = resultSet.getString("user_id");
        String name = resultSet.getString("name");
        int review_count = resultSet.getInt("review_count");
        Timestamp yelping_since = resultSet.getTimestamp("yelping_since");
        int useful = resultSet.getInt("useful");
        int funny = resultSet.getInt("funny");
        int cool = resultSet.getInt("cool");
        int fans = resultSet.getInt("fans");
        double average_stars = resultSet.getDouble("average_stars");

        return new UserYelp(user_id, name, review_count, yelping_since, useful, funny, cool, fans, average_stars);
    }

    public class UserYelp {
        public String userId;
        public String name;
        public int reviewCount;
        public Timestamp yelpingSince;
        public int useful;
        public int funny;
        public int cool;
        public int fans;
        public double averageStars;

        public UserYelp(String userId, String name, int reviewCount, Timestamp yelpingSince, int useful, int funny, int cool, int fans, double averageStars){
            this.userId = userId;
            this.name = name;
            this.reviewCount = reviewCount;
            this.yelpingSince = yelpingSince;
            this.useful = useful;
            this.funny = funny;
            this.cool = cool;
            this.fans = fans;
            this.averageStars = averageStars;
        }
    }


}
