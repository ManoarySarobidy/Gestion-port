<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%
    try{
        Prestation prestation = new Prestation();
        Escale escale = new Escale();
        prestation.setPrix(Double.parseDouble(request.getParameter("prix")));
        prestation.setDebut(escale.toDate(request.getParameter("arrive")));
        prestation.setFin(escale.toDate(request.getParameter("depart")));
        prestation.update(null , request.getParameter("idescaleprestation"));
        response.sendRedirect("../ajout-prestation.jsp?reference=" + request.getParameter("reference"));
    }catch(Exception e){
        e.printStackTrace( new java.io.PrintWriter(out) );
        // out.println("Error "+e.getMessage());
    }

%>
