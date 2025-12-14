<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyCabecera.jsp"%>


<br>
<h1 class="text-center mb-4">Informacion de Pilotos</h1>
<br>

<div class="row gx-5 justify-content-center">
    <div class="col-lg-12 col-xl-10">
        
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">Gestión de información</h5>
            </div>
            <div class="card-body p-0">
                
                <div class="table-responsive">
                    <table class="table table-hover table-striped mb-0" action="SvPilotos" method="GET">
                        <thead class="bg-light">
                            <tr>
                                <th scope="col">ID Piloto</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Apellido Paterno</th>
                                <th scope="col">Apellido Materno</th>
                                <th scope="col">Nacionalidad</th>
                                <th scope="col">Edad</th> 
                            </tr>
                        </thead>
                        <% 
                            List<Pilotos>listaPilotos = (List)request.getSession().getAttribute("listaPilotos");
                            
                            if (listaPilotos == null) {
                                listaPilotos = new java.util.ArrayList<>();
                            }
                        %>
                        <tbody>
                            <%for (Pilotos pil : listaPilotos){%>
                            <tr>
                                <td><%= pil.getIdPiloto()%></td>
                                <td><%= pil.getNombrePiloto()%></td>
                                <td><%= pil.getApellidoPaterno()%></td>
                                <td><%= pil.getApellidoMaterno()%></td>
                                <td><%= pil.getNacionalidad()%></td>
                                <td><%= pil.getEdad()%></td> 
                               <td>
                                   <div>
                                   <form name="Editar" action="SvPilotosEdit" method="POST">
                                        <button class="btn btn-sm btn-info me-2" type="submit" id="Editar">Editar</button>
                                        <input type="hidden" name="id" value="<%=pil.getIdPiloto()%>">
                                   </form>
                                   <form name="Eliminar" action="SvPilotosEdit" method="POST">
                                        <button class="btn btn-sm btn-info me-2" type="submit" id="Eliminar">Borrar</button>
                                        <input type="hidden" name="id" value="<%=pil.getIdPiloto()%>">                                        
                                   </form>     
                                   </div>
                               </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
                
            </div>
        </div>      
    </div>
</div>
<br><br>

<%@include file="components/bodyFinal.jsp"%>
