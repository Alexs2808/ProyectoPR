<%-- 
GET -- OBTENER INFORMACION
POST -- ENVIAR/MODIFICAR DE ALTAS EN BD
PUT -- MODIFICACIONES
DELETE -- ELIMINACIONES
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>

<h1 align="center">Registro de Equipos</h1>
<p></p>

<div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">

                                <form id="contactForm" data-sb-form-api-token="API_TOKEN" action="SvUsuarios" method="POST">
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="NombreEquipo" type="text" placeholder="Ingresa el nombre del equipo" data-sb-validations="required" name="NombreEquipo" />
                                        <label for="NombreEquipo">Nombre del Equipo</label>
                                        <div class="invalid-feedback" data-sb-feedback="NombreEquipo:required">Es requerido el nombre del equipo.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="NombreUsuario" type="text" placeholder="Ingresa tu nombre de usuario" data-sb-validations="required" name="NombreUsuario" />
                                        <label for="NombreUsuario">Nombre del Usuario</label>
                                        <div class="invalid-feedback" data-sb-feedback="NombreUsuario:required">Es requerido un nombre de usuario.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="Contrasenia" type="password" placeholder="Ingresa contrasenia" data-sb-validations="required" name="Contrasenia" />
                                        <label for="Contrasenia">Contrasenia</label>
                                        <div class="invalid-feedback" data-sb-feedback="Contrasenia:required">Es requerida una contrasenia.</div>
                                    </div>
                                    
                                    <div class="d-none" id="submitSuccessMessage">
                                        <div class="text-center mb-3">
                                            <div class="fw-bolder">Form submission successful!</div>
                                            To activate this form, sign up at
                                            <br />
                                            <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                                        </div>
                                    </div>
                                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button></div>
                                </form>
                            </div>
                        </div>
<%@include file="components/bodyFinal.jsp"%>
