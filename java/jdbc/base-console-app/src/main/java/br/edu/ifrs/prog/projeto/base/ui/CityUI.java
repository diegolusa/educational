/*
 * The MIT License
 *
 * Copyright 2018 diego.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.edu.ifrs.prog.projeto.base.ui;

import br.edu.ifrs.prog.projeto.base.connection.DatabaseConnection;
import br.edu.ifrs.prog.projeto.base.dao.impl.CityDaoImpl;
import br.edu.ifrs.prog.projeto.base.entities.City;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author diego
 */
public class CityUI extends BasicUI {

    private Connection dbConnection;

    public CityUI(Connection dbConnection, Scanner input) {
        super(input);
        this.dbConnection = dbConnection;

    }

    public void buildMainMenu() {
        super.clearConsole();
        1
        System.out.println("    CADASTRO DE CIDADES     ");
        System.out.print("\n1 - Adicionar\n2 - Editar\n3 - Excluir\n4 - Listar\n");
        int option;
        do {
            option = super.requestIntegerValue("-> ");
        } while (!Arrays.asList(1, 2, 3, 4).contains(option));

        switch (option) {
            case 1:
                this.addNewCity();
                break;
        }

    }

    public City addNewCity() {

        System.out.println("\nCadastrar novo registro de cidade\n");

        try {
            CityDaoImpl cdi = new CityDaoImpl(this.dbConnection);
            City city = new City();
            city.setName(super.requestStringValue("Nome:"));
            cdi.addRow(city);

            return city;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
