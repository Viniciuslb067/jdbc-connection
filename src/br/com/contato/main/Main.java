package br.com.contato.main;

import br.com.contato.dao.VendedorDAO;
import br.com.contato.model.Vendedor;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        VendedorDAO vendedorDAO = new VendedorDAO();

        Vendedor vendedor = new Vendedor();

        vendedor.setName("Vinicius");
        vendedor.setEmail("vinicius@gmail.com");
        vendedor.setBaseSalary(1100.00);

        // vendedorDAO.save(vendedor);

        Vendedor vendedorUpdate = new Vendedor();
        vendedorUpdate.setName("Carlos");
        vendedorUpdate.setEmail("carlos@gmail.com");
        vendedorUpdate.setBaseSalary(1500.00);
        vendedorUpdate.setId(8);

        vendedorDAO.update(vendedorUpdate);

        // vendedorDAO.deleteById(5);

        for (Vendedor c: vendedorDAO.getContato()) {
            System.out.println("Nome: " + c.getName() + "\n" + "Email: "
                    + c.getEmail() + "\n" + "Aniversário: " + "\n" + "Salário: " + c.getBaseSalary());
        }

    }
}
