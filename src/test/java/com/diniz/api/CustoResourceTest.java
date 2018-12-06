package com.diniz.api;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.diniz.model.TipoVeiculo;

@RunWith(CdiRunner.class)
public class CustoResourceTest {

    @Inject
    private CustoResource custoResource;

    @Test
    public void testCusto() {
        final String percursoPavimentado = "125.2";
        final String percursoNaoPavimentado = "0";
        final String tipoVeiculo = TipoVeiculo.CARRETA.getId();
        final Integer peso = 7;
        final BigDecimal resultado = BigDecimal.valueOf(80.73d).setScale(2, BigDecimal.ROUND_HALF_UP);

        final Response response = custoResource.getCusto(percursoPavimentado, percursoNaoPavimentado, tipoVeiculo, peso);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity() instanceof BigDecimal);
        assertEquals(resultado, response.getEntity());
    }
}