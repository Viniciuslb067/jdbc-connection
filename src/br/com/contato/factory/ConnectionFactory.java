package br.com.contato.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USERNAME = "root";

    private static final String PASSWORD = "123";

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database?characterEncoding=utf8";

    public static Connection createConnectionToMySql() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySql();

        if(con != null) {
            System.out.println("Conenctado com sucesso!");
            con.close();
        }

    }

}
