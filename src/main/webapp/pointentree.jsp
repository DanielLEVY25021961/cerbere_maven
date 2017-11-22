<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<!-- DOCTYPE (DTD pour HTML5) -->
<!DOCTYPE html>


<%--===============================================================--%>
<%-- IMPORT DES TAGLIBS DE STRUTS + JSTL 						   --%>
<%-- Cette inclusion STATIQUE d'une jsp a lieu AVANT la traduction --%>
<%-- de la présente jsp en code Java.							   --%>
<%--===============================================================--%>       
<%--Import de toutes les taglibs de Struts, Struts-EL et JSTL + CONSTANTES
 regroupées dans le fichier /WEB-INF/jsp/commun/taglibs.jsp --%>
<%@ include file="/WEB-INF/vues/web/commun/taglibs.jsp"%> 

 
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
    							info="Test" %>
    							
    							
    							 
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
		<meta http-equiv="Content-Language" content="fr" />
		<meta name="keywords" lang="fr" content="test" />
		

		<%--=============================================================--%>
		<%--                   DECLARATION DE LA css                     --%>
		<%--=============================================================--%>
		<%-- Dans une jsp, / en amont de l'URL DEVANT LA CSS signifie 	 --%>
		<%--sous le contexte. Permet donc de définir une adresse ABSOLUE --%> 
		<%--(PAR RAPPORT AU CONTEXTE). ATTENTION : vrai pour la Css 	 --%>		
		<link href="<c:url value='static/css/nav_fixe.css'/>" rel="stylesheet" 
			media="screen" type="text/css" title="global" />
					

		<!--==============================================================-->
		<!--                   Icone à côté du titre                      -->
		<!--==============================================================-->
		<%-- Dans une jsp, "/" en amont de l'URL signifie RELATIVEMENT	 --%>
		<%--A la position courante (hormis pour les css).				 --%>
		<%-- "/" NE PERMET DONC PAS de définir une adresse ABSOLUE		 --%> 
		<%--(PAR RAPPORT AU CONTEXTE) dans une jsp (différent de HTML).  --%>
		<%-- BONNE PRATIQUE : RECUPERER LE CONTEXTE GRACE A une EL.		 --%>
		<%-- ${pageContext.request.contextPath} 						 --%>						
		<link rel="icon" type="image/png" 
			href="static/icones/drapeau_france.png" />
		

		<title>pointentree.jsp</title>
		
		
	</head>
		
									<%-- ================= --%>
									<%-- 	    BODY       --%>
									<%-- ================= --%>
			
	<body>
		
		<h1>pointentree.jsp</h1>
		
		<div class="contenu">
		
			<p>
				Cette page nommée <b>pointentree.jsp</b> est écrite en 
				HTML5 avec un encodage UTF-8. 
			</p>
			
			<p>
				Elle est située  
				directement sous la racine (<i>contexte</i>) du serveur 
				<b><i>('WebContent' ou 'webapp' sous ECLIPSE)</i></b>, donc 
				à la position 'http://localhost:8080/cerbere_maven/pointentree.jsp' 
				si le <b><i>serveur</i></b> est 'localhost:8080' 
				et le <b><i>contexte</i></b> 'cerbere_maven'.
			</p>
			
			<p>
				Cette page est servie même en tapant dans 
				l'URL du navigateur 
				'http://localhost:8080/cerbere_maven/pointentree.jsp' 
				puisqu'elle est directement sous le contexte 
				(WebContent ou webapp de Eclipse).
			</p>
			
			<p>
				<b>ATTENTION : </b>
			</p>
			
			<p>
				<b>Cette page ne peut être directement 
				servie en tapant 
				'http://localhost:8080/cerbere/pointentree.jsp' 
				que si l'utilisateur s'est déjà loggé sous Cerbere.</b>
			</p>
			
			<p>
				En effet, le filtre Cerbere dans le web.xml 
				intercepte TOUTES les requêtes, sauf les URL "ouvertes" :
			</p>
			
			<div>
				<pre>
				&lt;filter-mapping&gt;
    					&lt;filter-name&gt;
    						&lt;url-pattern&gt;/*&lt;/url-pattern&gt;
    					&lt;/filter-name&gt;
    				&lt;/filter-mapping&gt;
    			</pre>
   			</div>  
   			
   			<p>Et "contexte/pointentree.jsp" ne 
   			fait PAS partie des URL ouvertes.</p>
		
		</div>
		
		
		
		<!-- =================== -->
		<!-- LIENS DE NAVIGATION -->
		<!-- =================== -->
		<div class="navigation">
		
			<!-- Barre horizontale. -->
			<hr />
			
			<div>
				
				<ul>
					
					<li>
					
						<!-- ============================= -->
						<!-- LIEN VERS contexte/pointentree.html -->
		<%-- Dans une jsp, "/" en amont de l'URL signifie RELATIVEMENT	 --%>
		<%--A la position courante (hormis pour les css).				 --%>
		<%-- "/" NE PERMET DONC PAS de définir une adresse ABSOLUE		 --%> 
		<%--(PAR RAPPORT AU CONTEXTE) dans une jsp (différent de HTML).  --%>
		<%-- BONNE PRATIQUE : RECUPERER LE CONTEXTE GRACE A une EL.		 --%>
		<%-- ${pageContext.request.contextPath} --%>				
						<a href="${pageContext.request.contextPath}/pointentree.html">
							vers /pointentree.html sous le contexte
							(http://localhost:8080/cerbere/pointentree.html)
						</a>
					
					</li>
					
					<li>
					
						<!-- ============================= -->
						<!-- LIEN VERS contexte/accueil.do -->
						<!-- ============================= -->				
						<a href="${pageContext.request.contextPath}/accueil.do">
							vers l'accueil = 
							/WEB-INF/jsp/application/accueil.jsp 
							via l'action accueil.do 
							(http://localhost:8080/cerbere/accueil.do)
						</a>
						
					</li>
				</ul>
							
			</div>
			
		</div>
			
	</body>

</html>