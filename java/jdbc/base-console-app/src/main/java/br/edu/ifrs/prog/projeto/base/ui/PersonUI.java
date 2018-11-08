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
import br.edu.ifrs.prog.projeto.base.dao.impl.PersonDaoImpl;
import br.edu.ifrs.prog.projeto.base.dao.impl.mapper.CityMapper;
import br.edu.ifrs.prog.projeto.base.entities.City;
import br.edu.ifrs.prog.projeto.base.entities.Person;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author diego
 */
public class PersonUI extends BasicUI {

    public void addNewPerson(Scanner sc) {
        System.out.println("\nCadastrar novo registro de pessoa\n");
        try (Connection conn = DatabaseConnection.getNewDefaultConnection()) {
            PersonDaoImpl pdi = new PersonDaoImpl(conn);
            CityDaoImpl cdi = new CityDaoImpl(conn);

            Person p = new Person();
            p.setName(super.requestStringValue("Nome:", sc));
            p.setBirthDate(new Date(super.requestDateValue("Data de Nascimento:", sc).getTime()));
            p.setHeigth(super.requestFloatValue("Altura:", sc));

            City city = (City) super.requestObjectValueFromAList("Escolha a cidade de residência", sc, cdi.getAllRows((new CityMapper())::map).stream().map(item -> (Object) item).collect(Collectors.toList())
            );
            if (city != null) {
                p.setCity(city);
            }

            pdi.addRow(p);
            conn.commit();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PersonUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
