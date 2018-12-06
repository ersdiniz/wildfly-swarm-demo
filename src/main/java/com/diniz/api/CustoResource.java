package com.diniz.api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.diniz.model.CalculoCusto;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/custos")
@Stateless
public class CustoResource {

    @Inject
    private CalculoCusto calculoCusto;

    @GET
    public Response getCusto(
            @QueryParam("percursoPavimentado") final String percursoPavimentado,
            @QueryParam("percursoNaoPavimentado") final String percursoNaoPavimentado,
            @QueryParam("tipoVeiculo") final String tipoVeiculo,
            @QueryParam("peso") final Integer peso) {
        return Response.ok(calculoCusto.of(percursoPavimentado, percursoNaoPavimentado, tipoVeiculo, peso)).build();
    }
}