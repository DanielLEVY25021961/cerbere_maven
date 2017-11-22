<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<%-- JSP AFFICHANT LES INFOS UTILISATEUR.  --%>

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
<%-- 'errorPage="/WEB-INF/jsp/commun/erreur.jsp"'	 				--%>
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
    				errorPage="/WEB-INF/jsp/commun/erreur.jsp" 
    					session="true" 
    						isThreadSafe="true"
    							info="Page d'affichage des infos utilisateur" %>


<%--================================================================--%>
<%-- 						IMPORTS JAVA 							--%>
<%--================================================================--%>  



<%--================================================================--%>
<%-- 					IMPORT DES TAGLIBS DE JSTL					--%>
<%-- Cette inclusion STATIQUE d'une jsp a lieu AVANT la traduction  --%>
<%-- de la présente jsp en code Java.							    --%>
<%--================================================================--%>           
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--===============================================================--%>
<%-- 					DECLARATION DE LA DTD 					   --%>
<%--===============================================================--%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 


<%--===============================================================--%>
<%-- DEBUT DU FICHIER XHTML --%>
<%--===============================================================--%>  
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">


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
		<meta name="keywords" lang="fr" content="cerbere 4.0" />

		
		<%--==============================================================--%>
		<%--                   DECLARATION DE LA css                      --%>
		<%--==============================================================--%>
		<link href="<c:url value='stylecss/nav_fixe.css'/>" 
			rel="stylesheet" media="screen" type="text/css" title="global" />

		
		<%--============================ --%>
		<%--		TITRE DE LA JSP		 --%>
		<%--============================ --%>
		<title>
			(infos-utilisateur.jsp):: Cerbère :: Information utilisateur
		</title>
		
						
		<script type="Javascript">
			// mise au premier plan de la fenêtre
			function init() {
	    	window.focus();
			}
		</script>
		
	</head>

	
									<%-- ================= --%>
									<%-- 	    BODY       --%>
									<%-- ================= --%>
	
	<body onload="init();">
	
		<div>
			<h1>Information sur l'utilisateur.</h1>
		</div>
	
	
		<div style="text-align:center">
			[ <a href="Javascript:window.close();">FERMER</a> ]
		</div>
	
		<c:if test="${empty alerte}">
		
			<table border="1">
				<caption>Identité</caption>
				<tr>
					<td>Identifiant interne</td>
					<td><c:out value="${userinfos.identifiant}" /></td>
				</tr>
				<tr>
					<td>Civilite</td>
					<td>${userinfos.civilite}</td>
				</tr>
				<tr>
					<td>Nom</td>
					<td><c:out value="${userinfos.nom}" /></td>
				</tr>
				<tr>
					<td>Prénom</td>
					<td><c:out value="${userinfos.prenom}" /></td>
				</tr>
				<tr>
					<td>Courriel</td>
					<td><c:out value="${userinfos.mel}" /></td>
				</tr>
				<tr>
					<td>Matricule</td>
					<td><c:out value="${userinfos.valeurs.MATRICULE}" /></td>
				</tr>
				<tr>
					<td>Unite</td>
					<td><c:out value="${userinfos.valeurs.UNITE}" /></td>
				</tr>
				<tr>
					<td>Adresse (rue / code postal / ville / pays (code pays))</td>
					<td><c:out value="${userinfos.valeurs.ADR_RUE}" /> - <c:out
							value="${userinfos.valeurs.ADR_CODEPOSTAL}" /> <c:out
							value="${userinfos.valeurs.ADR_VILLE}" /> - <c:out
							value="${userinfos.valeurs.LIB_PAYS}" /> (<c:out
							value="${userinfos.valeurs.CODE_PAYS}" />)</td>
				</tr>
				<tr>
					<td>Téléphones (fixe/mobile/fax)</td>
					<td><c:out value="${userinfos.valeurs.TEL}" /> / <c:out
							value="${userinfos.valeurs.TEL_MOBILE}" /> / <c:out
							value="${userinfos.valeurs.FAX}" /></td>
				</tr>
				<tr>
					<td>Compte vérifié</td>
					<td><c:choose>
							<c:when test="${userinfos.verifie}">OUI</c:when>
							<c:otherwise>NON</c:otherwise>
						</c:choose></td>
				</tr>
	
			</table>
			<br />
	
			<table border="1">
				<caption>Profils</caption>
				<c:forEach items="${userinfos.habilitation.tousProfils}" var="profil">
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
			
		</c:if>
		
		<div class="avertissement">
			<c:out value="${alerte}" />
		</div>
		
		<div style="text-align:center">
			[ <a href="Javascript:window.close();">FERMER</a> ]
		</div>
		
	</body>
	
</html>