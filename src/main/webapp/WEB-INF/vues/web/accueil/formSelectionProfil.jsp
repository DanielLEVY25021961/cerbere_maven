<%--NE PAS METTRE DE DECLARATION XML ici (BUG AVEC IE6) --%>
<%-- ?xml version="1.0" encoding="UTF-8" ? --%>

<!-- DOCTYPE (DTD pour HTML5) -->
<!DOCTYPE html>

<%--		FORMULAIRE DE SELECTION DU PROFIL CERBERE 				--%>


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
    							info="Sélection du profil Cerbere" %>
    							
    							
    							 
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
		<meta name="keywords" lang="fr" content="Cerbere" />
		

		<%--=============================================================--%>
		<%--                   DECLARATION DE LA css                     --%>
		<%--=============================================================--%>
		<%-- Dans une jsp, / en amont de l'URL DEVANT LA CSS signifie 	 --%>
		<%--sous le contexte. Permet donc de définir une adresse ABSOLUE --%> 
		<%--(PAR RAPPORT AU CONTEXTE). ATTENTION : vrai pour la Css 	 --%>		
		<link href="<c:url 
		value='/stylecss/nav_fixe.css'/>" 
			rel="stylesheet" 
				media="screen" type="text/css" title="global" />
		<link rel="stylesheet" type="text/css" 
		href="${pageContext.request.contextPath}/stylecss/screen.css" />			

		<%--==============================================================--%>
		<%--                   Icone à côté du titre                      --%>
		<%--==============================================================--%>
		<%-- Dans une jsp, "/" en amont de l'URL signifie RELATIVEMENT	 --%>
		<%--A la position courante (hormis pour les css).				 --%>
		<%-- "/" NE PERMET DONC PAS de définir une adresse ABSOLUE		 --%> 
		<%--(PAR RAPPORT AU CONTEXTE) dans une jsp (différent de HTML).  --%>
		<%-- BONNE PRATIQUE : RECUPERER LE CONTEXTE GRACE A une EL.		 --%>
		<%-- ${pageContext.request.contextPath} 						 --%>						
		<link rel="icon" type="image/png" 
href="${pageContext.request.contextPath}/image/image_png/drapeau_france.png" />
		

		<title>formSelectionProfil.jsp</title>
		
		
	</head>
		
									<%-- ================= --%>
									<%-- 	    BODY       --%>
									<%-- ================= --%>
			
	<body>
		
		<%-- =================== --%>
		<%-- CONTENU			 --%>
		<%-- =================== --%>
		<div class="contenu">
		
			<h1>FORMULAIRE DE SELECTION DES PROFILS</h1>
			
						
			<%--==================== --%>
			<%--	FORMULAIRE		 --%>
			<%--==================== --%>
			<div class="formProfils">
			
				<hr />
				
				<%--=================================================== --%>
				<%--ACTION = context/action --%>
				<%--=================================================== --%>
				<form action="${pageContext.request.contextPath}/accueil.do" 
							method="get">
							
					<display:table name="requestScope.listeProfils" 
						pagesize="20" 
							defaultsort="2" 
								defaultorder="ascending" 
									export="true" 
										requestURI="/accueil.do"
											id="profil">
					
						<display:column>
							<input class="choixProfil" 
								type="radio" 
									name="choixProfil" 
										value="${profil_rowNum - 1}" 
										id="choix" />
						</display:column>
						
						<display:column property="nom" 
									title="Nom" sortable="true"/>
								
						<display:column property="portee" 
									title="Portée" sortable="true"/>
						
						<display:column property="restriction" 
								title="Restriction" sortable="true"/>
							
					</display:table>
									
					<hr />
											
					<input type="submit" value="valider" />

					<input type="reset" value="reset" />
										
				</form>
						
			</div>
							
		</div>
		
		
		<%-- =================== --%>
		<%-- LIENS DE NAVIGATION --%>
		<%-- =================== --%>
		<div class="navigation">
		
			<%-- Barre horizontale. --%>
			<hr />
			
			<div>
				
				<ul>
					
					<li>
					
						<%-- ============================= --%>
						<%-- LIEN VERS contexte/index.html --%>
		<%-- Dans une jsp, "/" en amont de l'URL signifie RELATIVEMENT	 --%>
		<%--A la position courante (hormis pour les css).				 --%>
		<%-- "/" NE PERMET DONC PAS de définir une adresse ABSOLUE		 --%> 
		<%--(PAR RAPPORT AU CONTEXTE) dans une jsp (différent de HTML).  --%>
		<%-- BONNE PRATIQUE : RECUPERER LE CONTEXTE GRACE A une EL.		 --%>
		<%-- ${pageContext.request.contextPath} --%>				
						
					
					</li>
					
				
				</ul>
							
			</div>
			
		</div>
			
	</body>

</html>