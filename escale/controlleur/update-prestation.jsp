<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%
    
    try {
        Escale escale = Escale.createEscale(request.getParameter("reference"));
        Prestation prestation = escale.getById(null, request.getParameter("prestation"));
        prestation.setPrix(Double.parseDouble(request.getParameter("prix")));
        prestation.setDebut(escale.toDate(request.getParameter("arrive")));
        prestation.setFin(escale.toDate(request.getParameter("depart")));
        prestation.setId(request.getParameter("prestation"));
        prestation.update(null);
        response.sendRedirect("../ajout-prestation.jsp?reference=" + request.getParameter("reference") + "&&quai=" + request.getParameter("quai"));
    } catch(Exception e) {
        response.sendRedirect("../update-prestation.jsp?error=" + e.getMessage() + "&&reference=" + request.getParameter("reference") + "&&prestation=" + request.getParameter("prestation") + "&&quai=" + request.getParameter("quai"));
    }

%>
