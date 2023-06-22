<%@page import="prevision.Proposition" %>
<%@page import="port.Port" %>
<%@page import="port.Quai" %>
<%@page import="escale.Escale"%>
<%
    Port port = Port.createPort();
    Proposition[] propositions = port.getPropositions();
    Escale[] escales = port.getEscales();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/css/bootstrap.css">
    <title>Liste de Propositions</title>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            
            <h2>Proposition</h2>
            <table class="table mt-3">
                <tr>
                    <th>ID Prevision</th>
                    <th>Bateau</th>
                    <th>Date arrive</th>
                    <th>Date depart</th>
                    <th>Attente (en minute)</th>
                    <th>Duree (en minute)</th>
                    <th>Reference</th>
                    <th>Quai</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (int i = 0; i < propositions.length; i++) { %>
                    <tr>
                        <td><%=propositions[i].getIdPrevision() %></td>
                        <td><%=propositions[i].getBateau().getNom() %></td>
                        <td><%=propositions[i].getArrive() %></td>
                        <td><%=propositions[i].getDepart() %></td>
                        <td><%=propositions[i].getAttente() %></td>
                        <td><%=propositions[i].getDuree() %></td>
                        <td><%=propositions[i].getReference() %></td>
                        <td><%=propositions[i].getQuai().getNom() %></td>
                        <td><a href="../escale/ajout-prestation.jsp?proposition=<%=i %>"><svg xmlns="http://www.w3.org/2000/svg" height="25" viewBox="0 -960 960 960" width="48"><path d="M378-246 154-470l43-43 181 181 384-384 43 43-427 427Z"/></svg></a></td>
                        <td><a href="../prevision/update.jsp?idPrevision=<%=propositions[i].getIdPrevision() %>"><svg xmlns="http://www.w3.org/2000/svg" height="25" viewBox="0 -960 960 960" width="48"><path d="M483-120q-75 0-141-28.5T226.5-226q-49.5-49-78-115T120-482q0-75 28.5-140t78-113.5Q276-784 342-812t141-28q80 0 151.5 35T758-709v-106h60v208H609v-60h105q-44-51-103.5-82T483-780q-125 0-214 85.5T180-485q0 127 88 216t215 89q125 0 211-88t86-213h60q0 150-104 255.5T483-120Zm122-197L451-469v-214h60v189l137 134-43 43Z"/></svg></a></td>
                    </tr>
                <% } %>

            </table>
        </div>
        <div class="row">
            <h2> Liste des Escales </h2>
            <table class="table mt-3">
                <tr>
                    <th>Reference</th>
                    <th>Debut</th>
                    <th></th>
                </tr>
                <% for (int i = 0; i < escales.length; i++) { %>
                    <tr>
                        <td><%=escales[i].getReference() %></td>
                        <td><%=escales[i].getArrive() %></td>
                        <td>
                            <a href="" class="btn btn-primary"> Previsions </a>
                        </td>               
                    </tr>
                <% } %>

            </table>
        </div>
        <a href="../prevision/formulaire.jsp" class="btn btn-primary">Ajouter des previsions</a>
    </div>
</body>
</html>