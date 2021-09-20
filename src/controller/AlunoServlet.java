package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import persistence.AlunoDao;

@WebServlet("/aluno")
public class AlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AlunoDao aDao;
	
    public AlunoServlet() throws ClassNotFoundException, SQLException {
    	aDao = new AlunoDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("button");
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		Aluno al = validaCampos(request, cmd);
		String saida = "";
		String erro = "";
		
		try {
			if (cmd.contains("Cadastrar")) {
				if (al != null) {
					saida = aDao.insertAluno(al);
					al = new Aluno();
				}
			}
			if (cmd.contains("Atualizar")) {
				if (al != null) {
					saida = aDao.updateAluno(al);
					al = new Aluno();
				}
			}
			if (cmd.contains("Excluir")) {
				if (al != null) {
					saida = aDao.deleteAluno(al);
					al = new Aluno();
				}
			}
			if (cmd.contains("Buscar")) {
				if (al != null) {
					al = aDao.selectAluno(al);
				}
			}
			if (cmd.contains("Listar")) {
				listaAlunos = aDao.selectAlunos();
			}
		} catch (SQLException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("aluno", al);
			request.setAttribute("listaAlunos", listaAlunos);
			RequestDispatcher rd = request.getRequestDispatcher("aluno.jsp");
			rd.forward(request, response);
		}		
	}

	private Aluno validaCampos(HttpServletRequest request, String cmd) {
		Aluno al = new Aluno();
		if (cmd.contains("Cadastrar") || cmd.contains("Atualizar")) {
			if (!request.getParameter("codigo").trim().isEmpty() &&
					!request.getParameter("nome").trim().isEmpty() &&
					!request.getParameter("altura").trim().isEmpty() &&
					!request.getParameter("peso").trim().isEmpty())
				al.setCodigo(Integer.parseInt(request.getParameter("codigo").trim()));
				al.setNome(request.getParameter("nome").trim());
				al.setAltura(Float.parseFloat(request.getParameter("altura").trim()));
				al.setPeso(Float.parseFloat(request.getParameter("peso").trim()));
			}
		if (cmd.contains("Excluir") || cmd.contains("Buscar")) {
			if (!request.getParameter("codigo").trim().isEmpty()) {
				al.setCodigo(Integer.parseInt(request.getParameter("codigo").trim()));
			}
		}
		return al;
	}
}
