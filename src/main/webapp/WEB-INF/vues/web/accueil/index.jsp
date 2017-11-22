<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<!-- DOCTYPE (DTD pour HTML5) -->
<!DOCTYPE html>

	
<%-- JSP D'ACCUEIL DE CERBERE-MAVEN.  --%>


<%--================================================================--%>
<%-- 						DIRECTIVE PAGE						   	--%>
<%-- 				Conforme au XML	(jsp:directive.page)			--%>
<%-- DECLARATION DE Java. 											--%>
<%-- 'language="java"' signifie que cette jsp peut contenir du java.--%>
<%-- 'contentType="text/html; charset=UTF-8"' signifie que la jsp 	--%>
<%-- contiendra des balises HTML en UTF-8.  						--%>
<%-- 'pageEncoding="UTF-8"' signifie que l'encodage 				--%>
<%-- de la présente page est en UTF-8. 								--%>
<%-- isErrorPage="false" signifie que la présente jsp n'est pas une --%>
<%-- page d'erreur.												    --%>
<%-- 'errorPage="/WEB-INF/vues/web/commun/erreur.jsp"'	 			--%>
<%-- localise la page d'erreurs ET GENERE UN OBJET "exception".    	--%>
<%-- session="true" signifie que la présente jsp aura accès aux    	--%>
<%-- informations de Session.								    	--%>
<%-- isThreadSafe="true" indique que la présente jsp peut être		--%>
<%-- employée pour des accès simultanés.							--%>
<%-- info="..." contient le descriptif de la présente page.			--%>						    	
<%--================================================================--%>
<%@ page
	language="java" 
		contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    			isErrorPage="false" 
    				errorPage="/WEB-INF/vues/web/commun/erreur.jsp" 
    					session="true" 
    						isThreadSafe="true"
    							info="Page d'accueil" %>


<%--================================================================--%>
<%-- 						IMPORTS JAVA 							--%>
<%--================================================================--%>   
<%@ page import="i2.application.cerbere.commun.Cerbere" %> 
<%@ page import="i2.application.cerbere.commun.Personne" %> 


<%--================================================================--%>
<%-- 					IMPORT DES TAGLIBS DE JSTL					--%>
<%-- Cette inclusion STATIQUE d'une jsp a lieu AVANT la traduction  --%>
<%-- de la présente jsp en code Java.							    --%>
<%--================================================================--%>           
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
<%-- PASSAGE D'ATTRIBUTS A LA REQUETE --%>
<%
request.setAttribute("niveauFormulaire", Cerbere.FORMULAIRE);
request.setAttribute("niveauCertificatLogiciel", Cerbere.CERTIFICAT_1);
request.setAttribute("niveauCertificatCarte", Cerbere.CERTIFICAT_2);
request.setAttribute("monsieur", Personne.CIVILITE_M);
request.setAttribute("madame", Personne.CIVILITE_F);
%>


<%--===============================================================--%>
<%-- DEBUT DU FICHIER XHTML --%>
<%--===============================================================--%>  
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr">


									<%-- ================= --%>
									<%-- 	    HEAD       --%>
									<%-- ================= --%>

	<head>

		<%--============================================================ --%>
		<%--				Balises Méta								 --%>
		<%--============================================================ --%>			
		<meta name="author" content="Daniel Lévy" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Language" content="fr" />
		<meta name="keywords" lang="fr" content="cerbere 4.5" />

		
		<%--==============================================================--%>
		<%--                   DECLARATION DE LA css                      --%>
		<%--==============================================================--%>		
		<link href="<c:url value='static/css/nav_fixe.css'/>" 
			rel="stylesheet" media="screen" type="text/css" title="global" />
			

		<%--============================ --%>
		<%--		TITRE DE LA JSP		 --%>
		<%--============================ --%>
		<title>(index.jsp):: Test Cerbère :: Identité Cerbère</title>
					
	</head>
	

	
									<%-- ================= --%>
									<%-- 	    BODY       --%>
									<%-- ================= --%>
		
	<body>
	
		<p style="text-align:center;">
			[ <a href="demo.action?action=0">Identité</a> 
			| <a href="demo.action?action=10">Recherche</a> 
			| <a href="en-tetes.jsp">En-têtes HTTP</a> 
			| <a href="demo.action?action=2">R&eacute;-authentification</a>
			| <a href="demo.action?action=3">D&eacute;connexion</a> 
			| <a href="accueil.do">vers l'accueil</a> ]
		</p>
	
		
		
		<h1>Informations sur l'utilisateur.</h1>
		
		<table border="1">
			<tr>
				<td>Identifiant Cerbère interne</td>
				<td>&nbsp;${user.cerbere.utilisateur.identifiant}</td>
			</tr>
			<tr>
				<td>Civilite</td>
				<td>&nbsp;${user.cerbere.utilisateur.civilite}</td>
			</tr>
			<tr>
				<td>Nom</td>
				<td>&nbsp;${user.cerbere.utilisateur.nom}</td>
			</tr>
			<tr>
				<td>Prenom</td>
				<td>&nbsp;${user.cerbere.utilisateur.prenom}</td>
			</tr>
			<tr>
				<td>Mel</td>
				<td>&nbsp;${user.cerbere.utilisateur.mel}</td>
			</tr>
			<tr>
				<td>Matricule</td>
				<td>&nbsp;${user.cerbere.utilisateur.matricule}</td>
			</tr>
			<tr>
				<td>Téléphone fixe</td>
				<td>&nbsp;${user.cerbere.utilisateur.numTelFixe}</td>
			</tr>
			<tr>
				<td>Téléphone mobile</td>
				<td>&nbsp;${user.cerbere.utilisateur.numTelMobile}</td>
			</tr>
			<tr>
				<td>Fax</td>
				<td>&nbsp;${user.cerbere.utilisateur.numFax}</td>
			</tr>
			<tr>
				<td>Rue</td>
				<td>&nbsp;${user.cerbere.utilisateur.adresseRue}</td>
			</tr>
			<tr>
				<td>Code Postal</td>
				<td>&nbsp;${user.cerbere.utilisateur.adresseCodePostal}</td>
			</tr>
			<tr>
				<td>Ville</td>
				<td>&nbsp;${user.cerbere.utilisateur.adresseVille}</td>
			</tr>
			<tr>
				<td>Code du pays</td>
				<td>&nbsp;${user.cerbere.utilisateur.adressePaysCode}</td>
			</tr>
			<tr>
				<td>Libellé du pays</td>
				<td>&nbsp;${user.cerbere.utilisateur.adressePaysNom}</td>
			</tr>
			<tr>
				<td>DN certificat (si authentification par certificat)</td>
				<td>&nbsp;${user.cerbere.utilisateur.certificat.subjectDN}</td>
			</tr>
			<tr>
				<td>Niveau d'authentification</td>
				<td>&nbsp; <c:choose>
						<c:when
							test="${user.cerbere.utilisateur.authNiveau == niveauAuth_FORM}">Formulaire</c:when>
						<c:when
							test="${user.cerbere.utilisateur.authNiveau == niveauAuth_CRT1}">Certificat *</c:when>
						<c:when
							test="${user.cerbere.utilisateur.authNiveau == niveauAuth_CRT2}">Certificat **</c:when>
						<c:when
							test="${user.cerbere.utilisateur.authNiveau == niveauAuth_CRT3}">Certificat ***</c:when>
						<c:otherwise>INCONNU</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Compte vérifié (de confiance)</td>
				<td>&nbsp; <c:choose>
						<c:when test="${user.cerbere.utilisateur.verifie}">OUI</c:when>
						<c:otherwise>NON</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	
		<h1>Informations sur l'entité.</h1>
		<table border="1">
			<tr>
				<td>Unité</td>
				<td>&nbsp;${user.cerbere.utilisateur.unite}</td>
			</tr>
		</table>
	
		<h1>Informations sur l'entreprise.</h1>
		<c:choose>
			<c:when test="${empty user.cerbere.entreprise.SIREN}">
	    <p>Vous n'êtes pas rattaché à une entreprise.</p>
	    
			</c:when>
			<c:otherwise>
				<table border="1">
					<tr>
						<td>Numéro SIREN</td>
						<td>&nbsp;${user.cerbere.entreprise.SIREN}</td>
					</tr>
					<tr>
						<td>Raison sociale</td>
						<td>&nbsp;${user.cerbere.entreprise.raisonSociale}</td>
					</tr>
					<tr>
						<td>Rue</td>
						<td>&nbsp;${user.cerbere.entreprise.adresseRue}</td>
					</tr>
					<tr>
						<td>Code Postal</td>
						<td>&nbsp;${user.cerbere.entreprise.adresseCodePostal}</td>
					</tr>
					<tr>
						<td>Ville</td>
						<td>&nbsp;${user.cerbere.entreprise.adresseVille}</td>
					</tr>
					<tr>
						<td>Code du Pays</td>
						<td>&nbsp;${user.cerbere.entreprise.adressePaysCode}</td>
					</tr>
					<tr>
						<td>Libellé du pays</td>
						<td>&nbsp;${user.cerbere.entreprise.adressePaysNom}</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
	
		<h1>Informations sur l'application.</h1>
		<table border="1">
			<tr>
				<td>Nom</td>
				<td>&nbsp;${user.cerbere.application.nom}</td>
			</tr>
			<tr>
				<td>Niveau requis</td>
				<td>&nbsp; <c:choose>
						<c:when
							test="${user.cerbere.application.authNiveau == niveauAuth_FORM}">Formulaire</c:when>
						<c:when
							test="${user.cerbere.application.authNiveau == niveauAuth_CRT1}">Certificat *</c:when>
						<c:when
							test="${user.cerbere.application.authNiveau == niveauAuth_CRT2}">Certificat **</c:when>
						<c:when
							test="${user.cerbere.application.authNiveau == niveauAuth_CRT3}">Certificat ***</c:when>
						<c:otherwise>INCONNU</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>SSO valide</td>
				<td>&nbsp; <c:choose>
						<c:when test="${user.cerbere.application.SSO}">OUI</c:when>
						<c:otherwise>NON</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	
		<h1>Liste des profils.</h1>
		<table border="1">
			<tr>
				<td>Nom</td>
				<td>Portee</td>
				<td>Restriction</td>
			</tr>
			<c:forEach items="${user.cerbere.habilitation.tousProfils}"
				var="profil">
				<tr>
					<td><c:out value="${profil.nom}" /></td>
					<td><c:choose>
							<c:when test="${empty profil.portee}">&nbsp;</c:when>
							<c:otherwise>
								<c:out value="${profil.portee}" />
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${empty profil.restriction}">&nbsp;</c:when>
							<c:otherwise>
								<c:out value="${profil.restriction}" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
		
		<%-- INCLUSION DE LA JSP RELATIVE AUX INFOS SUR LA REQUETE. --%>
		<%@ include file="/WEB-INF/vues/web/metier/authentification/infosrequete.jspf" %>
		
		<%-- INCLUSION DE LA JSP RELATIVE AUX ATTRIBUTS LA REQUETE. --%>
		<%@ include file="/WEB-INF/vues/web/metier/authentification/attributsrequete.jspf" %>
	
		<p style="text-align:center;">
			[ <a href="demo.action?action=0">Identité</a> 
			| <a href="demo.action?action=10">Recherche</a> 
			| <a href="en-tetes.jsp">En-têtes HTTP</a> 
			| <a href="demo.action?action=2">R&eacute;-authentification</a>
			| <a href="demo.action?action=3">D&eacute;connexion</a> 
			| <a href="accueil.do">vers l'accueil</a> ]
		</p>
		
	</body>
	
</html>