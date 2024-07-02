package com.lucasrznd.repositories;

import com.lucasrznd.entities.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FuncionarioRepository {

    private List<Funcionario> listaDeFuncionarios = new ArrayList<>();

    public FuncionarioRepository() {
        listaDeFuncionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        listaDeFuncionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
        listaDeFuncionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        listaDeFuncionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        listaDeFuncionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
        listaDeFuncionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        listaDeFuncionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
        listaDeFuncionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        listaDeFuncionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        listaDeFuncionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));
    }

    public List<Funcionario> findAll() {
        return listaDeFuncionarios;
    }

    public Funcionario findByNome(String nome) {
        for (Funcionario funcionario : listaDeFuncionarios) {
            if (funcionario.getNome().equals(nome)) {
                return funcionario;
            }
        }
        return null;
    }

    public List<Funcionario> findByMesAniversario(Month mes) {
        List<Funcionario> novaLista = new ArrayList<>();

        for (Funcionario funcionario : listaDeFuncionarios) {
            if (funcionario.getDataNascimento().getMonth().equals(mes)) {
                novaLista.add(funcionario);
            }
        }

        return novaLista;
    }

    public Funcionario findByDataNascimentoDesc() {
        Funcionario funcionarioMaisVelho = listaDeFuncionarios.get(0);

        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            if (listaDeFuncionarios.get(i).getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                funcionarioMaisVelho = listaDeFuncionarios.get(i);
            }
        }

        return funcionarioMaisVelho;
    }

    public List<Funcionario> findAllByOrdemAlfabetica() {
        listaDeFuncionarios.sort(new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario f1, Funcionario f2) {
                return f1.getNome().compareTo(f2.getNome());
            }
        });

        return listaDeFuncionarios;
    }

    public BigDecimal salarioTotalFuncionarios() {
        double somaSalarioFuncionarios = listaDeFuncionarios.stream().mapToDouble(func -> func.getSalario().doubleValue()).sum();

        return BigDecimal.valueOf(somaSalarioFuncionarios);
    }

    public void setListaDeFuncionarios(List<Funcionario> listaDeFuncionarios) {
        this.listaDeFuncionarios = listaDeFuncionarios;
    }

}
