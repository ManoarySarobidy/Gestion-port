<%@page import="facture.Facture"%>
<%@page import="utilisateur.Utilisateur"%>
<%
    
    try {
        Facture facture = new Facture();
        facture.setId(request.getParameter("facture"));
        facture.validate((Utilisateur) request.getSession().getAttribute("utilisateur"));
        response.sendRedirect("../../escale/ajout-prestation.jsp?reference=" + request.getParameter("reference"));
    } catch (Exception e) {
        response.sendRedirect("../../escale/ajout-prestation.jsp?reference=" + request.getParameter("reference") + "&&error=" + e.getMessage());
    }

%>
