<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des produits</title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-icons.css"/>">
</head>
<body>

	<header>
		<ul class="nav nav-pills m-5">
			<li class="nav-item"><a class="nav-link" href="<c:url value="/"/>">Accueil</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/commentaire"/>">Commentaire</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/fournisseur"/>">Fournisseur</a></li>
			<li class="nav-item"><a class="nav-link active" href="<c:url value="/produit"/>">Produit</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/client"/>">Client</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/adresse"/>">Adresse</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/commande"/>">Commande</a></li>
		</ul>
	</header>
	<div class="container-fluid">
		<div class="card mt-3">
			<div class="card-header bg-primary text-white display-6">Liste des produits</div>
			<div class="card-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Identifiant</th>
							<th>Date</th>
							<th>Note</th>
							<th>Commentaires</th>
							<th>Produit</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${mesProduits}" var="prod">
						<c:url value="/produit/${prod.id}/edit" var="editUrl"/>
						<c:url value="/produit/${prod.id}/delete" var="deleteUrl"/>
						<tr>
							<td>${prod.id}</td>
							<td>${prod.libelle}</td>
							<td>${prod.prixAchat}</td>
							<td>${prod.prixVente}</td>
							<td>${prod.reference}</td>
							<td>${prod.modele}</td>
							<td>${prod.stock}</td>
							<td><div class="btn-group btn-group-sm">
									<a href="${editUrl}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
									<a href="${deleteUrl}"class="btn btn-danger"><i class="bi bi-trash"></i></a>
								</div></td>
						</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<div class="card-footer">
				<a href="<c:url value="/produit/add"/>" class="btn btn-success btn-lg"><i
					class="bi bi-plus-square"></i></a>
			</div>
		</div>
	</div>


	<script src="<c:url value="/js/bootstrap.bundle.js"/>"></script>
</body>
</html>