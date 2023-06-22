<%@page import="utilisateur.Utilisateur" %>
<%

    request.getSession().setAttribute("utilisateur", new Utilisateur("UTI001", "Tendry"));
    response.sendRedirect("./prevision/formulaire.jsp");

%>