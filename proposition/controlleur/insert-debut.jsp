<%@page import="escale.DebutEscale" %>
<%@page import="escale.Escale" %>
<%@page import="prevision.Proposition" %>
<%
    try {
        int index = Integer.parseInt(request.getParameter("index"));
        Proposition[] propositions = Proposition.getPropositions();
        Proposition proposition = propositions[index];
        Escale escale = proposition.createEscale();
        escale.debuter();
        response.sendRedirect("../liste-proposition.jsp");
    } catch (Exception e) {
        response.sendRedirect("../liste-proposition.jsp?error=" + e.getMessage());
    }

%>
