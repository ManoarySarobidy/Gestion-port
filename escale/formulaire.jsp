<%@page import="escale.Prestation" %>
<%@page import="escale.Tarif" %>
<%@page import="escale.Escale" %>
<%@page import="connection.BddObject" %>
<%

    Prestation prestation = new Prestation();
    prestation.setIdPrestation("PRES001");
    Escale escale = Escale.createEscale("QUA001", "ESC06252023N054");
    // out.print(escale);
    prestation.setEscale(escale);
    // out.print(prestation.getEscale());
    out.println(prestation.getPrix(BddObject.getPostgreSQL()));
    Tarif[] tarifs = prestation.getTarifs();
    

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Escale</title>
</head>
<body>
    <div class="container">
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Prestation</th>
                <th>Type</th>
                <th>Heure debut</th>
                <th>Heure fin</th>
                <th>Majoration</th>
                <th>Debut</th>
                <th>Fin</th>
                <th>Pavillon</th>
                <th>Prix</th>
            </tr>
            <% for (Tarif tarif : tarifs) { %>
            <tr>
                <td><%=tarif.getIdTarif() %></td>
                <td><%=tarif.getPrestation().getIdPrestation() %></td>
                <td><%=tarif.getType().getIdType() %></td>
                <td><%=tarif.getHeureDebut() %></td>
                <td><%=tarif.getHeureFin() %></td>
                <td><%=tarif.getMajoration() %></td>
                <td><%=tarif.getDebut() %></td>
                <td><%=tarif.getFin() %></td>
                <td><%=tarif.getPavillon().getIdPavillon() %></td>
                <td><%=tarif.getPrix() %></td>
                <td></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>