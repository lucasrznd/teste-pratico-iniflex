package com.lucasrznd.utils;

import com.lucasrznd.entities.Funcionario;

import java.util.List;

public abstract class FuncionarioPrinter {

    public static void imprimirStart() {
        System.out.println("-----------------------------------");
        System.out.println("------TESTE PRÁTICO - INIFLEX------");
        System.out.println("-----------------------------------");
    }

    public static void imprimirLinhasSeparacao() {
        System.out.println("--------------------------------");
    }

    public static void imprimirFuncionarioComCamposFormatados(Funcionario func) {
        System.out.println("\nFuncionário: " + func.getNome());

        /* • informação de data deve ser exibido no formato dd/mm/aaaa */
        System.out.println("Data de Nascimento: " + FormataCampos.formatarData(func.getDataNascimento()));

        /* • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula. */
        System.out.println("Salário: " + FormataCampos.formatarSalario(func.getSalario()));

        System.out.println("Função: " + func.getFuncao());
        imprimirLinhasSeparacao();
    }

    public static void imprimirListaDeFuncionarios(List<Funcionario> listaDeFuncionarios) {
        for (Funcionario funcionario : listaDeFuncionarios) {
            imprimirFuncionarioComCamposFormatados(funcionario);
        }
    }

    public static void imprimirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public static void imprimirTask3Ponto3() {
        System.out.println("3.3 - Imprimir todos os funcionários com todas suas informações");
    }

    public static void imprimirTask3Ponto6() {
        System.out.println("3.6 – Imprimir os funcionários, agrupados por função");
    }

    public static void imprimirTask3Ponto8() {
        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12");
    }

    public static void imprimirTask3Ponto9() {
        System.out.println("3.9 – Imprimir o funcionário com a maior idade");
    }

    public static void imprimirTask3Ponto10() {
        System.out.println("3.10 – Imprimir a lista de funcionários por ordem alfabética");
    }

    public static void imprimirTask3Ponto11() {
        System.out.println("3.11 – Imprimir o total dos salários dos funcionários:");
    }

}
