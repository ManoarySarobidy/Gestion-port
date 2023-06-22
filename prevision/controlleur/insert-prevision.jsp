<%@page import="prevision.Prevision" %>
<%@page import="utilisateur.Utilisateur" %>
<%

    try {
        Prevision prevision = new Prevision(request.getParameter("bateau"), request.getParameter("arrive"), request.getParameter("depart"));
        prevision.insert((Utilisateur) request.getSession().getAttribute("utilisateur"));
        response.sendRedirect("../../proposition/formulaire.jsp");
    } catch (Exception e) {
        response.sendRedirect("../formulaire.jsp?error=" + e.getMessage());
    }

%>