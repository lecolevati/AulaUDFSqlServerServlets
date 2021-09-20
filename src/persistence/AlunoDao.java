package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class AlunoDao implements IAlunoDao {

	private Connection c;
	
	public AlunoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public String insertAluno(Aluno al) throws SQLException {
		String sql = "INSERT INTO aluno VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, al.getCodigo());
		ps.setString(2, al.getNome());
		ps.setFloat(3, al.getAltura());
		ps.setFloat(4, al.getPeso());
		ps.execute();
		ps.close();
		
		String saida = "Aluno inserido com sucesso";
		
		return saida;
	}

	@Override
	public String updateAluno(Aluno al) throws SQLException {
		String sql = "UPDATE aluno SET nome = ?, altura = ?, peso = ? WHERE cod = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, al.getNome());
		ps.setFloat(2, al.getAltura());
		ps.setFloat(3, al.getPeso());
		ps.setInt(4, al.getCodigo());

		ps.execute();
		ps.close();
		
		String saida = "Aluno atualizado com sucesso";
		
		return saida;
	}

	@Override
	public String deleteAluno(Aluno al) throws SQLException {
		String sql = "DELETE aluno WHERE cod = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, al.getCodigo());
		ps.execute();
		ps.close();
		
		String saida = "Aluno excluido com sucesso";
		
		return saida;
	}

	@Override
	public Aluno selectAluno(Aluno al) throws SQLException {
		String sql = "SELECT nome, altura, peso FROM aluno WHERE cod = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, al.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			al.setNome(rs.getString("nome"));
			al.setAltura(rs.getFloat("altura"));
			al.setPeso(rs.getFloat("peso"));
		}
		rs.close();
		ps.close();
		
		float imc = consultaImc(al);
		al.setImc(imc);
		return al;
	}

	private float consultaImc(Aluno al) throws SQLException {
		float imc = 0.0F;
		String sql = "SELECT dbo.fn_calcimc(?) AS imc";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, al.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			imc = rs.getFloat("imc");
		}
		rs.close();
		ps.close();
		
		return imc;
		
	}

	@Override
	public List<Aluno> selectAlunos() throws SQLException {
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		String sql = "SELECT cod, nome, altura, peso, imc, situacao FROM fn_alunoimc()";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Aluno al = new Aluno();
			al.setCodigo(rs.getInt("cod"));
			al.setNome(rs.getString("nome"));
			al.setAltura(rs.getFloat("altura"));
			al.setPeso(rs.getFloat("peso"));
			al.setImc(rs.getFloat("imc"));
			al.setSituacao(rs.getString("situacao"));
			
			listaAlunos.add(al);
		}
		rs.close();
		ps.close();
		
		return listaAlunos;
	}
	
}
