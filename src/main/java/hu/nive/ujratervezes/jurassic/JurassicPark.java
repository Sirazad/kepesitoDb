package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> dinosaurs = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)){
            String SQL = "SELECT breed FROM dinosaur WHERE actual > expected GROUP BY breed";
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                dinosaurs.add(result.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("A problem occurred during the process.");
            e.printStackTrace();
        }
        return dinosaurs;
    }


}
