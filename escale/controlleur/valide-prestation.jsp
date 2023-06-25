<%@page import="port.Quai" %>
<%@page import="escale.Prestation"%>
<%@page import="utilisateur.Utilisateur"%>
<%
    
        Prestation prestation = new Prestation(request.getParameter("prestation"));
        prestation.validate((Utilisateur) request.getSession().getAttribute("utilisateur"));

%>
