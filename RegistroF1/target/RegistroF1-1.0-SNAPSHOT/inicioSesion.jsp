<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>

<h1>Registro de Equipos</h1>
<p></p>

<div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">

                                <form id="contactForm" data-sb-form-api-token="API_TOKEN" action="SvLogin" method="POST">

                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="NombreUsuario" name="NombreUsuario" type="text" placeholder="Ingresa tu nombre de usuario" data-sb-validations="required" name="NombreUsuario" />
                                        <label for="NombreUsuario">Nombre del Usuario</label>
                                        <div class="invalid-feedback" data-sb-feedback="NombreUsuario:required">Es requerido un nombre de usuario.</div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="Contrasenia" name="Contrasenia" type="password" placeholder="Ingresa contrasenia" data-sb-validations="required" name="Contrasenia" />
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
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Iniciar Sesion</button></div>
                                </form>
                            </div>
                        </div>
<br>
<br>
<%@include file="components/bodyFinal.jsp"%>
