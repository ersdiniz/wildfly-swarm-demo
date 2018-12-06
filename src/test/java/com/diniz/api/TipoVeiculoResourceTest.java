package com.diniz.api;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.diniz.model.TipoVeiculo;

@RunWith(CdiRunner.class)
public class TipoVeiculoResourceTest {

    @Inject
    private TipoVeiculoResource tipoVeiculoResource;

    @Test
    public void testVeiculos() {
        final Response response = tipoVeiculoResource.findAll();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity() instanceof List);
        assertEquals(TipoVeiculo.values().length, ((List) response.getEntity()).size());
    }
}