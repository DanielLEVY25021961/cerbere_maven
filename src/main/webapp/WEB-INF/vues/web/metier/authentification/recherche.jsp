<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<!-- DOCTYPE (DTD pour HTML5) -->
<!DOCTYPE html>


<%-- JSP AFFICHANT LA PAGE DE RECHERCHE D'UN UTILISATEUR.  --%>

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
    							info="Page de recherche d'un utilisateur" %>


<%--================================================================--%>
<%-- 						IMPORTS JAVA 							--%>
<%--================================================================--%>  
<%@ page import="i2.application.cerbere.commun.Entite"%>
<%@ page import="java.util.List" %>


<%--================================================================--%>
<%-- 					IMPORT DES TAGLIBS DE JSTL					--%>
<%-- Cette inclusion STATIQUE d'une jsp a lieu AVANT la traduction  --%>
<%-- de la présente jsp en code Java.							    --%>
<%--================================================================--%>           
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
			(recherche.jsp):: Test Cerbère :: Fonctions de recherche
		</title>

		
	</head>
	
	
									<%-- ================= --%>
									<%-- 	    BODY       --%>
									<%-- ================= --%>
	
	
	<body>
	
		<script type="javascript">
			// ouverture d'une nouvelle fenêtre en mode pop-up
			function popup(src,name,w,h) {
	    		var size;	
	    		size = ", width=" + w + ", height=" + h;
	    		window.open(src, name, "resizable=yes, scrollbars=no, toolbar=no, status=no, menubar=no, location=no, directories=no" + size);
			}
		</script>
	
		<p style="text-align:center;">
			[ <a href="demo.action?action=0">Identité</a> 
			| <a href="demo.action?action=10">Recherche</a> 
			| <a href="en-tetes.jsp">En-têtes HTTP</a> 
			| <a href="demo.action?action=2">R&eacute;-authentification</a>
			| <a href="demo.action?action=3">D&eacute;connexion</a> 
			| <a href="accueil.do">vers l'accueil</a> ]
		</p>
	
		<h1>Fonctions de recherche Cerbère</h1>
	
		<%-- ================ --%>
		<%-- message d'erreur --%>
		<%-- ================ --%>
		<div class="avertissement">${alerte}</div>
	
		<%-- =================================== --%>
		<%-- recherche des personnes par service --%>
		<%-- =================================== --%>
		<div><a name="services"></a></div>
		
		
		<h2>Recherche de personnes par service.</h2>
		
		<div>
		
			<%-- ================================================= --%>
			<%-- ================================================= --%>
			<%-- Formulaire de saisie de la recherche par service. --%>
			<%-- ================================================= --%>
			<form title="frmService" method="get" action="recherche.action">
			
				<div>
				
					<input type="hidden" name="action" value="10" />
					
					<table border="1">
						<tr>
							<td>Service</td>
							<td><input type="text" name="service" value="${param.service}" /></td>
						</tr>
						<tr>
							<td>Récursif</td>
							<td><input type="checkbox" name="recursif" value="1" /></td>
						</tr>
					</table>
					
					<div style="text-align:center;">
						<input type="submit" value="Rechercher" />
					</div>
				
				</div>
				
			</form>
			
		</div>
	
		<%-- ======================================= --%>
		<%-- Réponse concernant les services trouvés.--%>
		<%-- ======================================= --%>
		<p>
			Sous-entités :
			<c:choose>
			
				<c:when test="${empty services}">
					<i>Aucune sous-entité n'a été trouvée.</i>
				</c:when>
				
				<c:otherwise>
				
					<ul>
					
						<%-- PARCOURS DE TOUS lES SOUS-SERVICES 
						DU SERVICE PASSE DANS LA RECHERCHE. --%>
						<%-- List<Entite> services passée dans la requête par la Servlet  --%>
						<c:forEach var="serviceObjet" items="${requestScope.services}">
												
							<c:url value="recherche.action" var="url">
								<c:param name="action" value="10" />
								<c:param name="service" value="${service.nom}" />
								<c:param name="recursif" value="0" />
							</c:url>
							
														
							<%-- Injection du sous-service (OBJECT) en cours 
							d'itération dans une variable 
							'sousservice' de portée page.--%>
							<%-- value="${serviceObjet}" et PAS value="serviceObjet"--%>
							<%--qui renverrait une String 'serviceObjet'. --%>
							<c:set var="sousservice" value="${serviceObjet}" scope="page" />
							
							<%--Déclaration d'une variable JAVA. --%>
							<%! Entite sousService = null;  %>							
							
							<%--  Lecture de la variable 'sousservice' 
							dans la portée page dans un SCRIPLET JAVA.  --%>
	
							<%							
							/**
							* Récupération de la variable de portée page.<br/>
							*/							
							this.sousService 
								= (Entite) pageContext.getAttribute(
										"sousservice", PageContext.PAGE_SCOPE);
							
							/**
							* Récupération de la liste des sous-services
							* du sous-service.<br/>
							*/
							final List<Entite> sousSousServices 
								= this.sousService.findSousEntites();
							
							/**
							* Injection de la liste des sous-sous-services 
							* dans le contexte page.<br/>
							*/
							pageContext.setAttribute("sousSousServices", sousSousServices);
							
							%>
							
						
							
							<li>
							
								<%-- Affichage du nom du sous-service 
									sans lien lorsque le sous-service 
									ne contient pas d'entités. --%>
									<%-- Lecture DANS LA MAP 'infos' 
									passée à la requête 
									qui précise si le sous-service 
									contient lui-même des entités. --%>
								<c:choose>
									
									<c:when test="${infos[serviceObjet.nom] == 'falseString'}">									
										<c:out value="${serviceObjet.nom}" />
										
										<%--Sous-services --%>
										<c:if test="${!empty sousSousServices}">
											<ul>
												<c:forEach var="sousSousService" items="${sousSousServices}">
													<li><c:out value="${sousSousService.nom}" /></li>
												</c:forEach>
											</ul>
										</c:if>
									</c:when>
									
									<c:otherwise>
										<%-- Affichage du nom du sous-service
										avec un lien qui relancera une 
										recherche si et seulement si 
										le sous-service contient 
										aussi un service.  --%>
										<a href="<c:out value="${url}" />">
											<c:out value="${serviceObjet.nom}" />
											<c:forEach var="sousSousService" items="${sousSousServices}">
												<c:out value="${sousSousService.nom}" />
											</c:forEach>
										</a>
									</c:otherwise>
								</c:choose>
								
							</li>
			               
			            </c:forEach>
		            
		            </ul>
		            
				</c:otherwise>
				
			</c:choose>
		</p>
	
		<%-- ========================================== --%>
		<%-- Réponse concernant les Personnes trouvées. --%>
		<%-- ========================================== --%>
		<p>
			Personnes :
			<c:choose>
			
				<c:when test="${empty personnes}">
					<i>Aucune personne n'a été trouvée.</i>
				</c:when>
				
				<c:otherwise>
					<ul>
						<c:forEach var="personne" items="${personnes}">
						
							<c:url value="recherche.action" var="urlDetail">
								<c:param name="action" value="12" />
								<c:param name="mel" value="${personne.mel}" />
							</c:url>
							
							<li>
								<c:out value="${personne.nom}" /> 
								<c:out value="${personne.prenom}" /> 
								[<a href="Javascript:popup('<c:out value="${urlDetail}"/>','detail',450,500);">Détail</a>]
							</li>
							
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</p>

	
			<%-- ================================================== --%>
			<%-- ================================================== --%>
			<%-- Formulaire de saisie de la recherche par personne. --%>
			<%-- ================================================== --%>
		<div><a name="profil"></a></div>
		
		<h2>Recherche d'utilisateur par profil.</h2>
		
		<fieldset>
			<legend>
				<b>Note</b>
			</legend>
			Cette recherche est fournie à titre d'illustration des API Cerbère. Il
			n'est pas souhaite de proposer dans une application une telle
			recherche libre sur les profils.
		</fieldset>
		
		<div style="text-align:center">
		
			<form title="frmProfil" method="get" action="recherche.action">
			
				<div><input type="hidden" name="action" value="11" /></div>
				
				<table border="0">
					<tr>
						<td>Entite</td>
						<td><input type="text" name="entite"
							value="<c:out value="${param.entite}" />" /></td>
					</tr>
					<tr>
						<td>Récursif</td>
						<td><input type="checkbox" name="recursif" value="1" /></td>
					</tr>
					<tr>
						<td>Profil</td>
						<td><input type="text" name="profil"
							value="<c:out value="${param.profil}" />" /></td>
					</tr>
					<tr>
						<td>Restriction</td>
						<td><input type="text" name="restriction"
							value="<c:out value="${param.restriction}" />" /></td>
					</tr>
				</table>
				
				<div><input type="submit" value="Rechercher" /></div>
				
			</form>
			
		</div>
	
		<%-- ===================================================== --%>
		<%-- Réponse concernant les Personnes et profils trouvées. --%>
		<%-- ===================================================== --%>
		<c:choose>
		
			<c:when test="${empty utilisateurs}">
				<i>Aucun utilisateur avec profil n'a été trouvé.</i>
			</c:when>
			
			<c:otherwise>
				<ul>
					<c:forEach var="utilisateur" items="${utilisateurs}">
					
						<c:url value="recherche.action" var="urlDetail">
							<c:param name="action" value="12" />
							<c:param name="mel" value="${utilisateur.mel}" />
						</c:url>
						
						<li>
							<c:out value="${utilisateur.nom}" /> 
							<c:out value="${utilisateur.prenom}" /> 
							(profils : 
							<c:forEach
							var="profil" items="${utilisateur.habilitation.nomsProfils}">
								<c:out value="${profil}" />
							</c:forEach>) 
							[<a href="Javascript:popup('<c:out value="${urlDetail}"/>','detail',450,500);">détail</a>]
						</li>
						
					</c:forEach>
				</ul>
			</c:otherwise>
			
		</c:choose>
	
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
