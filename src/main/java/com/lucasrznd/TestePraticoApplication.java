package com.lucasrznd;

import com.lucasrznd.entities.Funcionario;
import com.lucasrznd.repositories.FuncionarioRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TestePraticoApplication {
    public static void main(String[] args) {
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        List<Funcionario> listaDeFuncionarios;

        /* Valor de Aumento utilizado na task 3.4 */
        final int PORCENTAGEM_AUMENTO = 10;

        System.out.println("-----------------------------------");
        System.out.println("------TESTE PRÁTICO - INIFLEX------");
        System.out.println("-----------------------------------");

        /* OK - 3.1 – Inserir todos os funcionários */
        listaDeFuncionarios = funcionarioRepository.getListaDeFuncionarios();

        /* OK - 3.2 - Remover o funcionário “João” da lista */
        Funcionario funcionarioEncontrado = findByNome(listaDeFuncionarios, "João");
        listaDeFuncionarios.remove(funcionarioEncontrado);

        /* OK - 3.3 - Imprimir todos os funcionários com todas suas informações */
        System.out.println("3.3 - Imprimir todos os funcionários com todas suas informações");
        for (Funcionario func : listaDeFuncionarios) {
            imprimirFuncionarioComCamposFormatados(func);
        }

        /* OK - 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor */
        listaDeFuncionarios = listaDeFuncionarios.stream()
                .peek(funcionario -> funcionario.setSalario(aumentarSalario(funcionario, PORCENTAGEM_AUMENTO))).collect(Collectors.toList());

        /* PRECISO MELHORAR - 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”. */
        Map<String, List<Funcionario>> funcionariosPorFuncao = listaDeFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        /* PRECISO MELHORAR - 3.6 – Imprimir os funcionários, agrupados por função */
        System.out.println("3.6 – Imprimir os funcionários, agrupados por função");
        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println("Função: " + funcao);
            funcionarios.forEach(System.out::println);
            System.out.println();
        });
        System.out.println("--------------------------------");

        /* OK - 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12 */
        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12");
        for (Funcionario func : listaDeFuncionarios) {
            if (func.getDataNascimento().getMonth().equals(Month.OCTOBER) || func.getDataNascimento().getMonth().equals(Month.DECEMBER)) {
                imprimirFuncionarioComCamposFormatados(func);
            }
        }

        /* OK - 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade */
        System.out.println("3.9 – Imprimir o funcionário com a maior idade");
        Funcionario funcMaiorIdade = listaDeFuncionarios.get(0);
        for (int i = 0; i < listaDeFuncionarios.size(); i++) {
            if (listaDeFuncionarios.get(i).getDataNascimento().isBefore(funcMaiorIdade.getDataNascimento())) {
                funcMaiorIdade = listaDeFuncionarios.get(i);
            }
        }
        int idade = Period.between(funcMaiorIdade.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("Nome: " + funcMaiorIdade.getNome() + ", Idade: " +  idade);
        System.out.println("--------------------------------");

        /* OK - 3.10 – Imprimir a lista de funcionários por ordem alfabética */
        System.out.println("3.10 – Imprimir a lista de funcionários por ordem alfabética");
        listaDeFuncionarios.sort(new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario f1, Funcionario f2) {
                return f1.getNome().compareTo(f2.getNome());
            }
        });
        for (Funcionario func : listaDeFuncionarios) {
            imprimirFuncionarioComCamposFormatados(func);
        }

        /* OK - 3.11 – Imprimir o total dos salários dos funcionários */
        System.out.println("3.11 – Imprimir o total dos salários dos funcionários:");
        double salarioTotalFuncionarios = listaDeFuncionarios.stream().mapToDouble(func -> func.getSalario().doubleValue()).sum();
        BigDecimal salarioBigDecimal = BigDecimal.valueOf(salarioTotalFuncionarios);
        System.out.println("Salário Total: " + formatarSalario(salarioBigDecimal));
        System.out.println("--------------------------------");

        /* NAO CONSEGUI FAZER - 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00 */
//        System.out.println("3.12 – Imprimir quantos salários mínimos ganha cada funcionário");
//        double salarioMinimo = 1212.00;
//        int salarioEquivalenteAoMinimo = 0;
//
//        Funcionario func1 = listaDeFuncionarios.get(3);
//        double salarioFunc1 = func1.getSalario().doubleValue();
//        System.out.println(v);
////        if (salarioFunc1 < salarioMinimo) {
////             salarioEquivalenteAoMinimo = 0;
////        } else if (salarioFunc1 > salarioMinimo) {
////            double var = salarioFunc1 - salarioMinimo;
////            salarioEquivalenteAoMinimo++;
////        }
////
////        if (salarioFunc1 < salarioMinimo) {
////            salarioEquivalenteAoMinimo = 1;
////        } else {
////
////        }
//        System.out.println("--------------------------------");

    }

    public static void imprimirFuncionarioComCamposFormatados(Funcionario func) {
        System.out.println("\nFuncionário: " + func.getNome());

        /* • informação de data deve ser exibido no formato dd/mm/aaaa */
        System.out.println("Data de Nascimento: " + formatarData(func.getDataNascimento()));

        /* • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula. */
        System.out.println("Salário: " + formatarSalario(func.getSalario()));

        System.out.println("Função: " + func.getFuncao());
        System.out.println("--------------------------------");
    }

    public static Funcionario findByNome(List<Funcionario> funcionarioList, String nome) {
        for (Funcionario func : funcionarioList) {
            if (func.getNome().equals(nome)) {
                return func;
            }
        }
        return null;
    }

    public static String formatarData(LocalDate data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return data.format(dtf);
    }

    public static String formatarSalario(BigDecimal salario) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#,##0.00");

        return decimalFormat.format(salario);
    }

    public static BigDecimal aumentarSalario(Funcionario func, int porcentagem) {
        double salarioAumentado = func.getSalario().doubleValue() + (func.getSalario().doubleValue() * porcentagem / 100);

        return BigDecimal.valueOf(salarioAumentado);
    }

}