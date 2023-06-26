<%@page import="facture.Facture" %>
<%@page import="escale.Escale" %>
<%

    Escale escale =  Escale.createEscale(request.getParameter("reference"));
    Facture facture = escale.facturer();

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Facture</title>
</head>
<body>
    
    <div class="container w-50 shadow p-5 rounded-3 mb-5" style="margin-top: 5rem;">
        <h1>Facture</h1>
        <table class="table">
            <thead>
              <tr>
                <th scope="col">Prestation</th>
                <th scope="col">Quaie</th>
                <th scope="col">Montant</th>
              </tr>
            </thead>
            <tbody>
                <% for (int i = 0; i < facture.getFactures().size(); i++) {
                        out.println("<tr>");
                        out.println("<td>"+facture.getFactures().get(i).getDesignation()+"</td>");
                        out.println("<td>"+facture.getFactures().get(i).getQuai().getNom()+"</td>");
                        out.println("<td>"+facture.getFactures().get(i).getPrix()+"</td>");
                        out.println("</tr>");
                } %>
            </tbody>
          </table>
    </div>
</body>
</html>