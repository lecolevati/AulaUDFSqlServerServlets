<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aluno</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
		<br />
	</div>
	<div>
		<form action="aluno" method="post">
			<table>
				<tr>
					<td colspan="3"><input type="number" id="codigo" name="codigo" placeholder="Codigo"
						value="${aluno.codigo }"></td>
					<td><input type="submit" value="Buscar" id="button" name="button"></td>
				</tr>
				<tr>
					<td colspan="4"><input type="text" id="nome" name="nome" placeholder="Nome"
						value="${aluno.nome }"></td>
				</tr>
				<tr>
					<td colspan="4"><input type="number" step="0.01" id="altura" name="altura" placeholder="Altura"
						value="${aluno.altura }"></td>					
				</tr>
				<tr>
					<td colspan="4"><input type="number" step="0.01" id="peso" name="peso" placeholder="Peso"
						value="${aluno.peso }"></td>					
				</tr>
				<tr>
					<td colspan="4"><input type="number" id="imc" name="imc" placeholder="IMC"
						readonly="readonly" value="${aluno.imc }"></td>					
				</tr>
				<tr>
					<td><input type="submit" value="Cadastrar" id="button" name="button"></td>
					<td><input type="submit" value="Atualizar" id="button" name="button"></td>
					<td><input type="submit" value="Excluir" id="button" name="button"></td>
					<td><input type="submit" value="Listar" id="button" name="button"></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<c:if test="${not empty saida }">
			<p><c:out value="${saida }" /></p>
		</c:if>
		<c:if test="${not empty erro }">
			<H2 style="color: red"><c:out value="${erro }" /></H2>
		</c:if>
	</div>
	<div>
		<br />
		<c:if test="${not empty listaAlunos }">
			<table border = 1>
				<thead>
					<tr>
						<th>Código</th>
						<th>Nome</th>
						<th>Altura</th>
						<th>Peso</th>
						<th>IMC</th>
						<th>Situação</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${listaAlunos }">
					<tr>
						<td>${a.codigo }</td>
						<td>${a.nome }</td>
						<td>${a.altura }</td>
						<td>${a.peso }</td>
						<td>${a.imc }</td>
						<td>${a.situacao }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>