<%@page import="utilisateur.Utilisateur" %>
<%@page import="utilisateur.Profile" %>
<%

    request.getSession().setAttribute("utilisateur", new Utilisateur("UTI001", "Tendry", new Profile("PRO002", "Chef-Capitainerie")));
    response.sendRedirect("./prevision/formulaire.jsp");

%>