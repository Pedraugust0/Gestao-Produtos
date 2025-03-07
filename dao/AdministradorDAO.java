package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Administrador;
import model.entity.Usuario;
import util.ConexaoBanco;

public class AdministradorDAO {
	
	public static void salvar(Administrador adm) {
		String query = "INSERT INTO Administrador(id, nome, cpf, senha) VALUES(?, ?, ?, ?)";
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(adm.getId()));
			statement.setString(2, adm.getNome());
			statement.setString(3, adm.getCpf());
			statement.setString(4, adm.getSenha());;
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o administrador: " + e.getMessage());
		}
		
	}
	
	public static void pegar(long id) {
		String query = "SELECT * FROM Administrador WHERE id=?";
		Administrador administrador;
		String nome, cpf, senha;
		
		// Executar a query
		try(Connection conn = ConexaoBanco.getConnection();
			PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, Long.toString(id));
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				nome = result.getString("nome");
				cpf = result.getString("cpf");
				senha = result.getString("senha");
				
				administrador = new Administrador(id, nome, cpf, senha);
				
				System.out.println(administrador);
			}
			
			System.out.println("Não foi encontrado administrador com este id!!");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar o administrador: " + e.getMessage());
		}
		
	}
	
}
