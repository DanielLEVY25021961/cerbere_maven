<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<!-- DOCTYPE (DTD pour HTML5) -->
<!DOCTYPE html>


<%-- JSP AFFICHANT LES EN-TETES HTTP D'UNE REQUETE (en-tetes.jsp).  --%>

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
    							info="En-têtes HTTP de la requête" %>


<%--================================================================--%>
<%-- 						IMPORTS JAVA 							--%>
<%--================================================================--%>  
<%@ page import="java.util.Enumeration" %>


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

		
		<%--=============================================================--%>
		<%--                   DECLARATION DE LA css                     --%>
		<%--=============================================================--%>
		<link href="<c:url value='stylecss/nav_fixe.css'/>" 
			rel="stylesheet" media="screen" type="text/css" title="global" />


		<%--============================ --%>
		<%--		TITRE DE LA JSP		 --%>
		<%--============================ --%>			
		<title>(en-tetes.jsp):: Test Cerbère :: En-têtes HTTP.</title>
			
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
		
		<%-- INCLUSION DE LA JSP RELATIVE AUX INFOS SUR LA REQUETE. --%>
		<%@ include file="/WEB-INF/vues/web/metier/authentification/infosrequete.jspf" %>
		
		<%-- INCLUSION DE LA JSP RELATIVE AUX ATTRIBUTS LA REQUETE. --%>
		<%@ include file="/WEB-INF/vues/web/metier/authentification/attributsrequete.jspf" %>
		
		<h1>En-têtes de la requête HTTP</h1>
		
		<table border="1">
		
			<%-- SCRIPLET JAVA --%>
			<%
				/**
				* Enumeration listant les headers.
				*/
				final Enumeration<?> hnames = request.getHeaderNames();
				while (hnames.hasMoreElements()) {
					
					/* Nom du Header. */
					final String name = (String) hnames.nextElement();
					out.print("<tr><td>" + name + "</td><td>");
					
					/* Valeurs du Header. */
					final Enumeration<?> hvalues = request.getHeaders(name);
					while (hvalues.hasMoreElements()) {
						out.print((String) hvalues.nextElement());
					}
					out.println("</td></tr>");
				}
			%>
		</table>
		
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
