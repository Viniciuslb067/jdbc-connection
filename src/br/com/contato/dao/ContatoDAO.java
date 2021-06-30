package br.com.contato.dao;

import br.com.contato.factory.ConnectionFactory;
import br.com.contato.model.Contato;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void save(Contato contato)  {
        String add = "INSERT INTO contatos(name, age, createdAt) VALUES (?, ?, ?)";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(add);

            pstm.setString(1, contato.getName());
            pstm.setInt(2, contato.getAge());
            pstm.setDate(3, new Date(contato.getCreatedAt().getTime()));

            pstm.execute();

            System.out.println("Contato inserido com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null)
                    pstm.close();
                if(conn != null)
                    conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public List<Contato> getContato() {
        String read = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        JdbcPreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(read);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Contato contato = new Contato();

                contato.setId(rset.getInt("id"));
                contato.setName(rset.getString("name"));
                contato.setAge(rset.getInt("age"));
                contato.setCreatedAt(rset.getDate("createdAt"));

                contatos.add(contato);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null)
                    rset.close();

                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contatos;
    }
}
