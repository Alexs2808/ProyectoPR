<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %>
<%@include file="components/bodyCabecera.jsp" %>
<section class="py-5">
                <div class="container px-5 my-5">
                    <div class="text-center mb-5">
                        <h1 class="fw-bolder">Comencemos a trabajar</h1>
                        <p class="lead fw-normal text-muted mb-0">"Never Give Up"</p>
                        
                        <%
                            String tokenTCP = (String) session.getAttribute("tokenTCP");
                            if (tokenTCP == null) {
                                response.sendRedirect("index.jsp");
                                return;
                            }
                        %>

                        <section class="py-5">
                            <div class="container px-5 my-5 text-center">
                                <h1 class="fw-bolder">Comencemos a trabajar</h1>

                                <p><!--<strong>Token TCP:</strong> < tokenTCP %>--></p>

                                <form action="SvLanzarTCP" method="post">
                                    <input type="hidden" name="tokenTCP" value="<%= tokenTCP %>">
                                    <button class="btn btn-success mt-3">
                                        Conectar Cliente TCP
                                    </button>
                                </form>
                            </div>
                        </section>

                        
                    </div>
                    <div class="row gx-5">
                        <div class="col-lg-6">
                            <div class="position-relative mb-5">
                                <img class="img-fluid rounded-3 mb-3" src="https://live.staticflickr.com/1/1231440_aade9c556b.jpg" alt="..." width="550"/>
                                <a class="h3 fw-bolder text-decoration-none link-dark stretched-link" href="registroPilotos.jsp">Registrar piloto(s)</a>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="position-relative mb-5">
                                <img class="img-fluid rounded-3 mb-3" src="https://live.staticflickr.com/3439/3197107428_14ca2e4211.jpg" alt="..." width="550"/>
                                <a class="h3 fw-bolder text-decoration-none link-dark stretched-link" href="SvPracticas">Registrar datos</a>
                            </div>
                        </div>
                        
                        <div class="col-lg-6">
                            <div class="position-relative mb-5">
                                <img class="img-fluid rounded-3 mb-3" src="https://live.staticflickr.com/2362/2192546562_eae95b68ec.jpg" alt="..." width="550"/>
                                <a class="h3 fw-bolder text-decoration-none link-dark stretched-link" href="SvPilotos">Gestionar informacion de pilotos</a>
                            </div>
                        </div>
                        
                        <div class="col-lg-6">
                            <div class="position-relative mb-5">
                                <img class="img-fluid rounded-3 mb-3" src="https://live.staticflickr.com/3451/3304736964_6d369df4db.jpg" alt="..." width="550"/>
                                <a class="h3 fw-bolder text-decoration-none link-dark stretched-link" href="SvEstadisticas">Mostrar Estadísticas</a>
                            </div>
                        </div>

                    </div>
                </div>
            </section>
                                    
<!-- MENSAJE UDP TEMPORAL -->
<div id="udpMensaje" style="
    display:none;
    position:fixed;
    top:20px;
    right:20px;
    background:#0d6efd;
    color:white;
    padding:12px 20px;
    border-radius:10px;
    z-index:9999;
    box-shadow: 0 4px 10px rgba(0,0,0,0.2);
">
</div>

<script>
function revisarMensajesUDP() {
    fetch("<%= request.getContextPath() %>/SvLanzarUDP")
        .then(response => response.text())
        .then(texto => {
            if (texto && texto.trim() !== "") {
                mostrarMensajeUDP(texto);
            }
        });
}

function mostrarMensajeUDP(mensaje) {
    const div = document.getElementById("udpMensaje");
    div.innerText = mensaje;
    div.style.display = "block";

    setTimeout(() => {
        div.style.display = "none";
    }, 3000);
}

setInterval(revisarMensajesUDP, 2000);
</script>


                                    
<%@include file="components/bodyFinal.jsp" %>
