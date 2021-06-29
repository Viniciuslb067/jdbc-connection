package br.com.contato.main;

import br.com.contato.dao.ContatoDAO;
import br.com.contato.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();

        Contato contato = new Contato();

        contato.setName("");
        contato.setAge(19);
        contato.setCreatedAt(new Date());

        contatoDAO.save(contato);

    }
}
