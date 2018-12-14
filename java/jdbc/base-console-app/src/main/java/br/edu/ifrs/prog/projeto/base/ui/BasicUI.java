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

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author diego
 */
public abstract class BasicUI {

    protected Scanner input;

    public BasicUI(Scanner input) {
        this.input = input;
    }

    public void clearConsole() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException ex) {

        }

    }

    public Integer requestIntegerValue(String message) {
        System.out.print(message);
        Integer value = this.input.nextInt();
        this.input.nextLine();
        return value;
    }

    public Float requestFloatValue(String message) {
        System.out.print(message);
        do {
            try {
                Float value = this.input.nextFloat();
                this.input.nextLine();
                return value;
            } catch (InputMismatchException ime) {
                System.out.println("\tFormato inválido. Utilize vírgula para separar as casas decimais ");
                this.input.nextLine();
            }
        } while (true);

    }

    public String requestStringValue(String message) {
        System.out.print(message);
        return this.input.nextLine();
    }

    public Date requestDateValue(String message) {
        System.out.print(message);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY");
        do {
            try {
                return sdf.parse(this.input.nextLine());
            } catch (ParseException pe) {
                System.out.println("\tData inválida. Informe no formato dd/mm/yyyy.");

            }
        } while (true);

    }

    public Object requestObjectValueFromAList(String message, List<Object> list) {
        System.out.println(message);

        String criteria = this.requestStringValue("\tButhis.inputar por: ");
        HashMap<Integer, Object> filteredItens = new HashMap<>();
        AtomicInteger ai = new AtomicInteger(0);

        filteredItens.putAll(
                list.stream().filter(
                        obj -> obj.toString().toLowerCase().contains(criteria.toLowerCase()))
                        .collect(Collectors.toMap(obj -> ai.incrementAndGet(), obj -> obj)));

        if (filteredItens.isEmpty()) {
            System.out.println("\tRegistro não encontrado");
            return null;
        }

        filteredItens.forEach((key, value) -> {
            System.out.println(String.format("\t[%4d] %20s", key, value));
        }
        );
        int escolha;

        while (!filteredItens.containsKey(escolha = this.requestIntegerValue("\tInforme o código escolhido [-1 para não encontrado]: ")) && escolha != -1);

        return escolha == -1 ? null : filteredItens.get(escolha);
    }
}
