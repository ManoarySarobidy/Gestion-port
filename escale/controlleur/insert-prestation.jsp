<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%
    try{
        Prestation prestation = new Prestation(request.getParameter("prestation"));
        Escale escale = Escale.createEscale(request.getParameter("quai"), request.getParameter("reference"));
        escale.setArrive(escale.toDate(request.getParameter("arrive")));
        escale.setDepart(escale.toDate(request.getParameter("depart")));
        escale.ajouterPrestation(prestation);
        response.sendRedirect("../ajout-prestation.jsp?reference=" + request.getParameter("reference"));
    }catch(Exception e){
        out.println("Error "+e.getMessage());
    }

%>
