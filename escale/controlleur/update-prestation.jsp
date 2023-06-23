<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%@page import="escale.Prestation"%>
<%
    try{
        Prestation prestation = new Prestation();
        Escale escale = new Escale();
        prestation.setPrix(Double.parseDouble(request.getParameter("prix")));
        prestation.setArrive(escale.toDate(request.getParameter("arrive")));
        prestation.setDepart(escale.toDate(request.getParameter("depart")));
        prestation.update(null , request.getParameter("idescaleprestation"));
        response.sendRedirect("../ajout-prestation.jsp?reference=" + request.getParameter("reference"));
    }catch(Exception e){
        out.println("Error "+e.getMessage());
    }

%>
