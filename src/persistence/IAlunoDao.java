package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;

public interface IAlunoDao {

	public String insertAluno(Aluno al) throws SQLException;
	public String updateAluno(Aluno al) throws SQLException;
	public String deleteAluno(Aluno al) throws SQLException;
	public Aluno selectAluno(Aluno al) throws SQLException;
	public List<Aluno> selectAlunos() throws SQLException;
	
}
