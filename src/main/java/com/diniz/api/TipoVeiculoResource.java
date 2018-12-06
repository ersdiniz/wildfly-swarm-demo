package com.diniz.api;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.diniz.dto.DynamicDto;
import com.diniz.model.TipoVeiculo;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/tipos-veiculos")
@Stateless
public class TipoVeiculoResource {

    @GET
    public Response findAll() {
        final List<DynamicDto> listDtos = new ArrayList<DynamicDto>(TipoVeiculo.values().length);
        for (TipoVeiculo tipo : TipoVeiculo.values()) {
            listDtos.add(DynamicDto.of().with("id", tipo.getId()).with("descricao", tipo.getDescricao()));
        }
        return Response.ok(listDtos).build();
    }
}