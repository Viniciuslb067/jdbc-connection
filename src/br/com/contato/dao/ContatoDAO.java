package br.com.contato.dao;

import br.com.contato.factory.ConnectionFactory;
import br.com.contato.model.Contato;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;

public class ContatoDAO {

    public void save(Contato contato) {
        String add = "INSERT INTO contatos(name, age, createdAt) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(add);

            pstm.setString(1, contato.getName());
            pstm.setInt(2, contato.getAge());
            pstm.setDate(3, new Date(contato.getCreatedAt().getTime()));

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
