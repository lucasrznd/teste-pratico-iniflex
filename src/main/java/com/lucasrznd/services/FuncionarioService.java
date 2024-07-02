package com.lucasrznd.services;

import com.lucasrznd.entities.Funcionario;
import com.lucasrznd.repositories.FuncionarioRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public List<Funcionario> findByMesAniversario(Month mes) {
        return funcionarioRepository.findByMesAniversario(mes);
    }

    public List<Funcionario> findAllByOrdemAlfabetica() {
        return funcionarioRepository.findAllByOrdemAlfabetica();
    }

    public Funcionario aumentarSalario(Funcionario func, int porcentagem) {
        Funcionario funcionarioEncontrado = funcionarioRepository.findByNome(func.getNome());

        if (funcionarioEncontrado != null) {
            int index = funcionarioRepository.findAll().indexOf(funcionarioEncontrado);
            funcionarioEncontrado.setSalario(BigDecimal.valueOf(func.getSalario().doubleValue() + (func.getSalario().doubleValue() * porcentagem / 100)));

            funcionarioRepository.findAll().set(index, funcionarioEncontrado);
        } else {
            throw new RuntimeException("Funcionário não encontrado.");
        }

        return funcionarioEncontrado;
    }

    public Funcionario funcionarioMaisVelho() {
        Funcionario funcionarioMaisVelho = funcionarioRepository.findByDataNascimentoDesc();

        return funcionarioMaisVelho;
    }

    public int idadeFuncionarioMaisVelho() {
        Funcionario funcionarioMaisVelho = funcionarioRepository.findByDataNascimentoDesc();
        int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();

        return idade;
    }

    public BigDecimal salarioTotalFuncionarios() {
        return funcionarioRepository.salarioTotalFuncionarios();
    }

    public int contarEquivalenciaSalarialFuncionario(Funcionario funcionario, Double salarioMinimo) {
        double salarioFuncionario = funcionario.getSalario().doubleValue();
        int numeroDeSalariosMinimosEquivalentes = 0;

        do {
            numeroDeSalariosMinimosEquivalentes++;
            salarioFuncionario = salarioFuncionario - salarioMinimo;
        } while (salarioFuncionario > salarioMinimo);

        return numeroDeSalariosMinimosEquivalentes;
    }

    public List<Funcionario> deleteByNome(String nome) {
        Funcionario funcionarioEncontrado = funcionarioRepository.findByNome(nome);
        List<Funcionario> listaDeFuncionarios = findAll();

        if (funcionarioEncontrado != null) {
            listaDeFuncionarios.remove(funcionarioEncontrado);
        } else {
            throw new RuntimeException("Funcionário não encontrado.");
        }

        return listaDeFuncionarios;
    }

}
