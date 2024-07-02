package com.lucasrznd;

import com.lucasrznd.entities.Funcionario;
import com.lucasrznd.services.FuncionarioService;
import com.lucasrznd.utils.FormataCampos;
import com.lucasrznd.utils.FuncionarioPrinter;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestePraticoApplication {

    private static final int PORCENTAGEM_AUMENTO = 10;

    private static final double SALARIO_MINIMO = 1212.00;

    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();

        FuncionarioPrinter.imprimirStart();

        /* OK - 3.1 – Inserir todos os funcionários */
        List<Funcionario> listaDeFuncionarios = funcionarioService.findAll();

        /* OK - 3.2 - Remover o funcionário “João” da lista */
        funcionarioService.deleteByNome("João");

        /* OK - 3.3 - Imprimir todos os funcionários com todas suas informações */
        FuncionarioPrinter.imprimirTask3Ponto3();
        FuncionarioPrinter.imprimirListaDeFuncionarios(listaDeFuncionarios);

        /* OK - 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor */
        for (Funcionario funcionario : listaDeFuncionarios) {
            funcionarioService.aumentarSalario(funcionario, PORCENTAGEM_AUMENTO);
        }

        /* PRECISO MELHORAR - 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”. */
        Map<String, List<Funcionario>> funcionariosPorFuncao = listaDeFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        /* PRECISO MELHORAR - 3.6 – Imprimir os funcionários, agrupados por função */
        FuncionarioPrinter.imprimirTask3Ponto6();
        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            FuncionarioPrinter.imprimirMensagem("* Função *: " + funcao);
            FuncionarioPrinter.imprimirListaDeFuncionarios(funcionarios);
            FuncionarioPrinter.imprimirMensagem("");
        });

        /* OK - 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12 */
        FuncionarioPrinter.imprimirTask3Ponto8();
        List<Funcionario> funcionariosAniversariantesMesOutubro = funcionarioService.findByMesAniversario(Month.OCTOBER);
        List<Funcionario> funcionariosAniversariantesMesDezembro = funcionarioService.findByMesAniversario(Month.DECEMBER);
        FuncionarioPrinter.imprimirMensagem("Total Mês 10: " + funcionariosAniversariantesMesOutubro.size());
        FuncionarioPrinter.imprimirMensagem("Total Mês 12: " + funcionariosAniversariantesMesDezembro.size());
        FuncionarioPrinter.imprimirListaDeFuncionarios(funcionariosAniversariantesMesOutubro);
        FuncionarioPrinter.imprimirListaDeFuncionarios(funcionariosAniversariantesMesDezembro);

        /* OK - 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade */
        FuncionarioPrinter.imprimirTask3Ponto9();
        FuncionarioPrinter.imprimirMensagem("Nome: " + funcionarioService.funcionarioMaisVelho().getNome() + ", Idade: " + funcionarioService.idadeFuncionarioMaisVelho());
        FuncionarioPrinter.imprimirLinhasSeparacao();

        /* OK - 3.10 – Imprimir a lista de funcionários por ordem alfabética */
        FuncionarioPrinter.imprimirTask3Ponto10();
        FuncionarioPrinter.imprimirListaDeFuncionarios(funcionarioService.findAllByOrdemAlfabetica());

        /* OK - 3.11 – Imprimir o total dos salários dos funcionários */
        FuncionarioPrinter.imprimirTask3Ponto11();
        FuncionarioPrinter.imprimirMensagem("Salário Total dos Funcionários: R$ " + FormataCampos.formatarSalario(funcionarioService.salarioTotalFuncionarios()));
        FuncionarioPrinter.imprimirLinhasSeparacao();

        /* OK - 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00 */
        FuncionarioPrinter.imprimirTask3Ponto12();
        for (Funcionario funcionario : listaDeFuncionarios) {
            FuncionarioPrinter.imprimirMensagem("Quantidade de Salários Mínimos Equivalentes: " + funcionarioService.contarEquivalenciaSalarialFuncionario(funcionario, SALARIO_MINIMO));
            FuncionarioPrinter.imprimirFuncionarioComCamposFormatados(funcionario);
        }
    }
}