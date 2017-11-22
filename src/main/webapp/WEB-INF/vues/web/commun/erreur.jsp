<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<!-- DOCTYPE (DTD pour HTML5) -->
<!DOCTYPE html>

	


<%--================================================================--%>
<%-- 						DIRECTIVE PAGE						   	--%>
<%-- 				Conforme au XML	(jsp:directive.page)			--%>
<%-- DECLARATION DE Java. 											--%>
<%-- 'language="java"' signifie que cette jsp peut contenir du java.--%>
<%-- 'contentType="text/html; charset=UTF-8"' signifie que la jsp 	--%>
<%-- contiendra des balises HTML en UTF-8.  						--%>
<%-- 'pageEncoding="UTF-8"' signifie que l'encodage 				--%>
<%-- de la présente page est en UTF-8. 								--%>
<%-- isErrorPage="true" signifie que la présente jsp est une 		--%>
<%-- page d'erreur.												    --%>
<%-- 'errorPage="/WEB-INF/jsp/commun/erreur.jsp"'	 				--%>
<%-- localise la page d'erreurs ET GENERE UN OBJET "exception".    	--%>
<%-- session="true" signifie que la présente jsp aura accès aux    	--%>
<%-- informations de Session.								    	--%>
<%-- isThreadSafe="true" indique que la présente jsp peut être		--%>
<%-- employée pour des accès simultanés.							--%>
<%-- info="..." contient le descriptif de la présente page.			--%>						    	
<%--================================================================--%>
<jsp:directive.page 
	language="java" 
		contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    			isErrorPage="true" 
    				errorPage="/WEB-INF/vues/web/commun/erreur.jsp" 
    					session="true" 
    						isThreadSafe="true"
    							info="Page d'erreur"/>



<%--================================================================--%>
<%-- 						IMPORTS JAVA 							--%>
<%--================================================================--%>  
<%@page import="java.lang.Throwable"%>


<%--===============================================================--%>
<%-- IMPORT DES TAGLIBS DE STRUTS + JSTL 						   --%>
<%-- Cette inclusion STATIQUE d'une jsp a lieu AVANT la traduction --%>
<%-- de la présente jsp en code Java.							   --%>
<%--===============================================================--%>       
<%--Import de toutes les taglibs de Struts, Struts-EL et JSTL + CONSTANTES
 regroupées dans le fichier /WEB-INF/vues/web/commun/taglibs.jsp--%>
<%@ include file="/WEB-INF/vues/web/commun/taglibs.jsp"%>


  
<%--===============================================================--%>
<%-- DEBUT DU FICHIER XHTML --%>
<%--===============================================================--%>  
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr">



									<%-- ================= --%>
									<%-- 	    HEAD       --%>
									<%-- ================= --%>

	<head>
		
		<%--============================================================--%>
		<%--				DEFINITION DES RESOURCEBUNDLE STRUTS		--%>
		<%-- Définit la variable "${messages}" précisant le nom         --%>
		<%-- de base 'messages' des messages.properties.                --%>
		<%-- la balise  message-resources parameter="resource.messages" --%>
		<%-- est précisée dans struts-config.xml, mais pas dans web.xml --%>
		<%--============================================================--%>
		<fmt:setBundle basename="resource.messages" var="messages"/>

				
		<%--============================================================ --%>
		<%--				Balises Méta								 --%>
		<%--============================================================ --%>		
		<meta name="author" content="Daniel Lévy" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Language" content="fr" />
		<meta name="keywords" lang="fr" content="tutoriel, struts 1.2.9" />
		

		<%--==============================================================--%>
		<%--                   DECLARATION DE LA css                      --%>
		<%--==============================================================--%>		
		<link href="<c:url value='styles/nav_fixe.css'/>" 
			rel="stylesheet" media="screen" type="text/css" title="global" />

		
		<%--============================ --%>
		<%--		TITRE DE LA JSP		 --%>
		<%--============================ --%>
		<%--==============================================================--%>
		<%--                TITRE DE L'ONGLET AVEC STRUTS                 --%>
		<%-- Va chercher dans messages_fr_FR.properties le titre de la jsp--%>
		<%-- Utilise bundle="${messages}"                                 --%>
		<%-- car messages.properties n'est pas déclaré dans web.xml.      --%>
		<%--==============================================================--%>			
		<title>
			<fmt:message key="erreur.jsp.titre" bundle="${messages}" />
		</title>
		
		
	</head>
	
	
	
									<%-- ================= --%>
									<%-- 	    BODY       --%>
									<%-- ================= --%>
		
	
     <body>
	
		<%--=========================================== --%>
		<%-- RECUPERATION DU CONTEXTE ET 				--%>
		<%-- STOCKAGE DANS UNE VARIABLE monContexte   	--%>
		<%--=========================================== --%>
		<% final String monContexte = request.getContextPath(); %> 

		
		<%--=================================================================--%>
		<%--           AFFICHE LE TITRE DE L'APPLICATION                     --%>
		<%-- Va chercher dans application.properties le titre de l'appli.    --%>
		<%-- Pas de bundle nécessaire car application.properties est déclaré --%>
		<%-- dans web.xml.				 									 --%>
		<%--=================================================================--%>		
		<%-- TITRE DE L'APPLICATION : --%>		
		<div class="titreapplication">TITRE DE L'APPLICATION : 
			<fmt:message key="application.titre"/></div>
		
		<h1>Erreurs (erreur.jsp)</h1>
		
				
		<%-- INCLUSION DE LA JSP RELATIVE AUX INFOS SUR LA REQUETE. --%>
		<%@ include file="/WEB-INF/vues/web/metier/authentification/infosrequete.jspf" %>
		
		<%-- INCLUSION DE LA JSP RELATIVE AUX ATTRIBUTS LA REQUETE. --%>
		<%@ include file="/WEB-INF/vues/web/metier/authentification/attributsrequete.jspf" %>
		
		
		
		<div id="screen">
			
		    <div id="content">
		    
		    <%-- ***** EXCEPTIONS ********* --%>
		    <h2>Exceptions</h2>
		    	
		    <div class="Exception">
			    <p>
			    	Exception (pageContext.exception.message) : 
			    	<%-- /**Message de l'exception.*/ --%>
			    	<b>${pageContext.exception.message}</b>
			    </p>
	
				<c:forEach var="st" items="${pageContext.exception.stackTrace}">
				  <p>${st}</p>
				</c:forEach>
		    </div>
		    	
		    <h1>Contenu de l'attribut alerte : </h1>
		    <div class="avertissement">${alerte}</div>
		    
		  	</div> 
		  	 
		</div>
		
     </body>
     
</html> 