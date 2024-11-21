package utils;

import dao.ConnectionFactory;
import models.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.UUID;

public class DBUtils {

    private static String Sql;
    private static String SqlEndereco;

    public static String GerarCodigoFunc() {
        return UUID.randomUUID().toString();
    }

    public void insertNewUser(int idGerado, String tipoUser, String cargo, Endereco enderecoUsuario) {
        System.out.println("ID gerado: " + idGerado);

        if (tipoUser.equals("FUNCIONARIO")) {
            String codFunc = GerarCodigoFunc();

            setSql("INSERT INTO funcionario (codigo_funcionario, cargo, id_usuario) VALUES (?, ?, ?)");
            setSqlEndereco("INSERT INTO endereco (cep, local, numero_casa, bairro, cidade, estado, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)");

            try (Connection conn2 = ConnectionFactory.getConnection();
                 PreparedStatement ps2 = conn2.prepareStatement(Sql);
                 PreparedStatement ps3 = conn2.prepareStatement(SqlEndereco)) {

                ps2.setString(1, codFunc);
                ps2.setString(2, cargo);
                ps2.setInt(3, idGerado);
                ps2.executeUpdate();

                ps3.setString(1, enderecoUsuario.getCep());
                ps3.setString(2, enderecoUsuario.getLocal());
                ps3.setInt(3, enderecoUsuario.getNumeroCasa());
                ps3.setString(4, enderecoUsuario.getBairro());
                ps3.setString(5, enderecoUsuario.getCidade());
                ps3.setString(6, enderecoUsuario.getEstado());
                ps3.setInt(7, idGerado);
                ps3.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao inserir dados de funcionário ou endereço: " + e.getMessage());
            }

        } else {
            setSql("INSERT INTO cliente (id_usuario) VALUES (?)");
            setSqlEndereco("INSERT INTO endereco (cep, local, numero_casa, bairro, cidade, estado, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)");

            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement ps = conn.prepareStatement(Sql);
                 PreparedStatement ps3 = conn.prepareStatement(SqlEndereco)) {

                // Insere dados na tabela 'cliente'
                ps.setInt(1, idGerado);
                ps.executeUpdate();

                // Insere dados na tabela 'endereco'
                ps3.setString(1, enderecoUsuario.getCep());
                ps3.setString(2, enderecoUsuario.getLocal());
                ps3.setInt(3, enderecoUsuario.getNumeroCasa());
                ps3.setString(4, enderecoUsuario.getBairro());
                ps3.setString(5, enderecoUsuario.getCidade());
                ps3.setString(6, enderecoUsuario.getEstado());
                ps3.setInt(7, idGerado);
                ps3.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao inserir dados de cliente ou endereço: " + e.getMessage());
            }
        }
    }

    public static String getSqlEndereco() {
        return SqlEndereco;
    }

    public static void setSqlEndereco(String sqlEndereco) {
        SqlEndereco = sqlEndereco;
    }

    public static String getSql() {
        return Sql;
    }

    public static void setSql(String sql) {
        Sql = sql;
    }
}
