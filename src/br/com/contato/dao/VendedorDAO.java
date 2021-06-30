package br.com.contato.dao;

import br.com.contato.factory.ConnectionFactory;
import br.com.contato.model.Vendedor;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {

    public void save (Vendedor vendedor)  {
        String add = "INSERT INTO vendedores(name, email, birthDate, baseSalary) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(add);

            pstm.setString(1, vendedor.getName());
            pstm.setString(2, vendedor.getEmail());
            pstm.setDate(3, vendedor.getBirthDate());
            pstm.setDouble(4, vendedor.getBaseSalary());

            pstm.execute();

            System.out.println("Vendedor inserido com sucesso!");

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

    public List<Vendedor> getContato() {
        String read = "SELECT * FROM vendedores";

        List<Vendedor> vendedors = new ArrayList<Vendedor>();

        Connection conn = null;
        JdbcPreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(read);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Vendedor vendedor = new Vendedor();

                vendedor.setId(rset.getInt("id"));
                vendedor.setName(rset.getString("name"));
                vendedor.setEmail(rset.getString("email"));
                vendedor.setBirthDate(rset.getDate("birthDate"));
                vendedor.setBaseSalary(rset.getDouble("baseSalary"));

                vendedors.add(vendedor);
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
        return vendedors;
    }

    public void update (Vendedor vendedor) {
        String update = "UPDATE vendedores SET name = ?, email = ?, baseSalary = ? " + "WHERE id = ?";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(update);

            pstm.setString(1, vendedor.getName());
            pstm.setString(2, vendedor.getEmail());
            pstm.setDouble(3, vendedor.getBaseSalary());
            pstm.setInt(4, vendedor.getId());

            if (vendedor.getName().equals("") || vendedor.getName().equals(null)) {
                System.out.println("PREENCHA TODOS OS CAMPOS");
                System.exit(1);
            }

            if (vendedor.getEmail().equals("") || vendedor.getEmail().equals(null)) {
                System.out.println("PREENCHA TODOS OS CAMPOS");
                System.exit(1);
            }

            if (vendedor.getBaseSalary() == 0 || vendedor.getBaseSalary() == null) {
                System.out.println("PREENCHA TODOS OS CAMPOS");
                System.exit(1);
            }

            pstm.execute();

            System.out.println("Contato atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteById(int id) {
        String delete = "DELETE FROM contatos WHERE id = ? ";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(delete);

            pstm.setInt(1, id);

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
