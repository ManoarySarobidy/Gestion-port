<%@page import="utilisateur.Utilisateur" %>
<%@page import="utilisateur.Profile" %>
<%

    request.getSession().setAttribute("utilisateur", new Utilisateur("UTI002", "Aina", new Profile("PRO001", "Capitainerie")));
    response.sendRedirect("./prevision/formulaire.jsp");

%>