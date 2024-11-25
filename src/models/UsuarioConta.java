package models;

import java.util.ArrayList;
import java.util.List;

public class UsuarioConta {
    private String nome;
    private String email;
    private String cpf;
    private String dataNascimento;
    private String telefone;

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String tipoUsuario;
    private List<Conta> contas;

    public UsuarioConta(String nome, String email, String cpf, String dataNascimento, String telefone, String tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
        this.contas = new ArrayList<>();
    }

    // Método para adicionar uma conta ao objeto
    public void adicionarConta(int idConta, String numeroConta, String agencia, double saldo, String tipoConta) {
        Conta conta = new Conta(idConta, numeroConta, agencia, saldo, tipoConta);
        this.contas.add(conta);
    }

    // Getters e Setters (se necessário)

    public List<Conta> getContas() {
        return contas;
    }

    // Outros getters e setters para nome, email, cpf, etc.

    public static class Conta {
        private String numeroConta;
        private String agencia;
        private double saldo;
        private String tipoConta;

        private int idConta;

        public Conta(int idConta, String numeroConta, String agencia, double saldo, String tipoConta) {
            this.idConta = idConta;
            this.numeroConta = numeroConta;
            this.agencia = agencia;
            this.saldo = saldo;
            this.tipoConta = tipoConta;
        }

        public int getIdConta() {
            return idConta;
        }

        public void setIdConta(int idConta) {
            this.idConta = idConta;
        }

        // Getters e setters
        public String getNumeroConta() {
            return numeroConta;
        }

        public String getAgencia() {
            return agencia;
        }

        public double getSaldo() {
            return saldo;
        }

        public String getTipoConta() {
            return tipoConta;
        }
    }
}

