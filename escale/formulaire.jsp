<%@page import="escale.Prestation" %>
<%@page import="escale.Tarif" %>
<%@page import="escale.Escale" %>
<%

    Prestation prestation = new Prestation();
    prestation.setIdPrestation("PRES001");
    prestation.setEscale(Escale.createEscale("QUA001", "ESC06242023I052"));
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
                <th>Quai</th>
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
                <td></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>