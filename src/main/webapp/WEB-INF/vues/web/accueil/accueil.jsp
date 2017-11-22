<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<%-- JSP D'ACCUEIL DE L'APPLICATION.  --%>

<%--===============================================================--%>
<%-- DECLARATION DE LA DTD --%>
<%--===============================================================--%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%--===============================================================--%>
<%-- IMPORT DES TAGLIBS DE STRUTS et JSTL --%>
<%--===============================================================--%>           
<!--  Import de toutes les taglibs de JSTL-->
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/x.tld" prefix="x" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn" %>


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
    							info="Page d'accueil" %>



<%--================================================================--%>
<%-- 						IMPORTS JAVA 							--%>
<%--================================================================--%>  

    
<%--===============================================================--%>
<%-- DEBUT DU FICHIER XHTML --%>
<%--===============================================================--%>  
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">


							<%-- ================= --%>
							<%-- 	    HEAD       --%>
							<%-- ================= --%>


	<head>
		
		<%--============================================================--%>
		<%--				DEFINITION DES RESOURCEBUNDLE				--%>
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
		<meta name="keywords" lang="fr" content="accueil" />
		

		<%--=============================================================--%>
		<%--                   DECLARATION DE LA css                     --%>
		<%--=============================================================--%>
		<link href="<c:url value='stylecss/nav_fixe.css'/>" rel="stylesheet" 
					media="screen" type="text/css" title="global" />
					
					
		<%--==============================================================--%>
		<%--                   TITRE DE L'ONGLET                       	  --%>
		<%-- Va chercher dans messages_fr_FR.properties le titre de la jsp--%>
		<%-- Utilise bundle="${messages}"                                 --%>
		<%-- car messages.properties n'est pas déclaré dans web.xml.      --%>
		<%--==============================================================--%>		
		<title>
			<fmt:message key="accueil.jsp.titre" bundle="${messages}" />
		</title>
		
		<html:base/>
		
	</head>
	
						<%-- ================= --%>
						<%-- 	    BODY       --%>
						<%-- ================= --%>
		
	<body>
		
		 <%-- RECUPERATION DU CONTEXTE   --%>
		<% final String monContexte = request.getContextPath(); %> 
		
		<%--==============================================================--%>
		<%--           AFFICHE LE TITRE DE L'APPLICATION                  --%>
		<%-- Va chercher dans application.properties le titre de l'appli. --%>
		<%-- Pas de bundle nécessaire car application.properties  		  --%>
		<%-- est déclaré dans web.xml.				 					  --%>
		<%--==============================================================--%>		
		<%-- TITRE DE L'APPLICATION : --%>		
		<div class="titreapplication">
			TITRE DE L'APPLICATION : <fmt:message key="application.titre"/>
		</div>
		
		<h1>Accueil (accueil.jsp)</h1>
		
		<div id = "message.accueil">
		
	<%--		Récupère et affiche le texte d'accueil défini dans la clé --%>
	<%--		'label.accueil.texte' de resource.messages.properties--%>
			<p>
				<fmt:message key="label.accueil.texte" bundle="${messages}"/>	
			</p>
			
			<p>
				Bonjour
				&nbsp;
				${sessionScope.cerbereUser.civilite}
				&nbsp;
				${sessionScope.cerbereUser.prenom}
				&nbsp;
				${sessionScope.cerbereUser.nom}
			</p>
			
			<p>
				idCerbere&nbsp;:&nbsp;${sessionScope.cerbereUser.idCerbere}
				&nbsp;
				E-mail&nbsp;:&nbsp;${sessionScope.cerbereUser.mel} 
			</p>
			
			<p>
				Unité&nbsp;:&nbsp;${sessionScope.cerbereUser.unite}
				&nbsp;
				Entreprise&nbsp;:&nbsp;${sessionScope.cerbereUser.entreprise}
			</p>
			
			<p>
				Nom du Profil choisi
				&nbsp;:&nbsp;
				${sessionScope.cerbereUser.nomProfil}
				&nbsp;
				Portée du Profil
				&nbsp;:&nbsp;
				${sessionScope.cerbereUser.porteeProfil}
				&nbsp;
				Restriction du Profil
				&nbsp;:&nbsp;
				${sessionScope.cerbereUser.restrictionProfil}
			</p>
			
		</div>
		
		<div>
		
			<%-- INCLUSION DE LA JSP RELATIVE AUX INFOS SUR LA REQUETE. --%>
			<%@ include file="/WEB-INF/vues/web/metier/authentification/infosrequete.jspf" %>
			
			<%-- INCLUSION DE LA JSP RELATIVE AUX ATTRIBUTS LA REQUETE. --%>
			<%@ include file="/WEB-INF/vues/web/metier/authentification/attributsrequete.jspf" %>
		
		</div>
		
		<%-- =================== --%>
		<%-- LIENS DE NAVIGATION --%>
		<%-- =================== --%>
		<div class="navigation">
		
			<%-- Barre horizontale. --%>
			<hr />
			
			<ul>
			
				<li>
			
					<%-- Lien vers pointentree.html --%>
					<div>
						
						<%--Définition d'une variable JSTL de type URL  --%>
						<%--Pointe vers 'contexte/pointentree.html' --%>
						<c:url var="urlPointEntree" 
							scope="page" value="/pointentree.html" />
				
						<%--Utilisation de la variable de 
						type URL dans le lien. --%>
						<a href="${urlPointEntree}">
							vers pointentree.html 
							sous le WebContent (/pointentree.html)
						</a>
					
					</div>
				
				</li>
				
				<li>
				
					<%-- Lien vers pointentree.jsp --%>
					<div>
					
						<c:url var="urlPentreejsp" 
							scope="page" value="/pointentree.jsp"/>
					
						<a href="${urlPentreejsp}">
							vers pointentree.jsp 
							sous le WebContent (/pointentree.jsp)
						</a>
						
					</div>
				
				</li>
				
							
				<li>
				
					<%-- Lien vers CERBERE DEMO --%>
					<div>
						
						<%--Définition d'une variable JSTL de type URL  --%>
						<%--Pointe vers 'contexte/WEB-INF/vues/index.jsp' --%>
						<c:url var="urlIndex" 
						scope="page" value="/cerbere-demo.action?action=0" />
				
						<%--Utilisation de la variable de type 
						URL dans le lien. --%>
						<a href="${urlIndex}">
							vers accueil de Cerbere-Demo 
							(/WEB-INF/vues/index.jsp via 
							l'action cerbere-demo.action)
						</a>
					
					</div>
				
				
				</li>
			
			</ul>
			
		</div>
		
	</body>
	
</html>
