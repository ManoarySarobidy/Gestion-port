<%@page import="escale.DebutEscale" %>
<%@page import="escale.Escale" %>
<%@page import="prevision.Proposition" %>
<%
    int index = Integer.parseInt(request.getParameter("index"));
    out.println(index);
    try {
        Proposition[] propositions = Proposition.getPropositions();
        out.println(index);

        Proposition proposition = propositions[index];
        Escale escale = proposition.createEscale();
        escale.debuter();
        response.sendRedirect("../liste-proposition.jsp");
    } catch (Exception e) {
        response.sendRedirect("../liste-proposition.jsp?error=" + e.getMessage());
    }

%>
