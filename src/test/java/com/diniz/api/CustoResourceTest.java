package com.diniz.api;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.diniz.model.CalculoCustoException;
import com.diniz.model.TipoVeiculo;

@RunWith(CdiRunner.class)
public class CustoResourceTest {

    @Inject
    private CustoResource custoResource;

    @Test
    public void testCusto() {
        getDataProvider().forEach(
                mock -> {
                    final Response response = custoResource.getCusto(mock.getPercursoPavimentado(), mock.getPercursoNaoPavimentado(),
                            mock.getTipoVeiculo(), mock.getPeso());

                    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
                    assertTrue(response.getEntity() instanceof BigDecimal);
                    assertEquals(mock.getResultadoEsperado().setScale(2, BigDecimal.ROUND_HALF_UP), response.getEntity());
                });
    }

    @Test(expected = CalculoCustoException.class)
    public void testPercursoPavimentadoNull() {
        custoResource.getCusto(null, "0", TipoVeiculo.CARRETA.getId(), 1);
    }

    @Test(expected = CalculoCustoException.class)
    public void testPercursoNaoPavimentadoNull() {
        custoResource.getCusto("0", null, TipoVeiculo.CARRETA.getId(), 1);
    }

    @Test(expected = CalculoCustoException.class)
    public void testTipoVeiculoNull() {
        custoResource.getCusto("0", "0", null, 1);
    }

    @Test(expected = CalculoCustoException.class)
    public void testPesoNull() {
        custoResource.getCusto("0", "0", TipoVeiculo.CARRETA.getId(), null);
    }

    private List<MockCusto> getDataProvider() {
        final List<MockCusto> mock = new ArrayList<MockCusto>();
        mock.add(new MockCusto("100", "0", TipoVeiculo.CAMINHAO_CACAMBA.getId(), 8, BigDecimal.valueOf(62.70d)));
        mock.add(new MockCusto("0", "60", TipoVeiculo.CAMINHAO_BAU.getId(), 4, BigDecimal.valueOf(37.20d)));
        mock.add(new MockCusto("0", "180", TipoVeiculo.CARRETA.getId(), 12, BigDecimal.valueOf(150.19d)));
        mock.add(new MockCusto("80", "20", TipoVeiculo.CAMINHAO_BAU.getId(), 6, BigDecimal.valueOf(57.60d)));
        mock.add(new MockCusto("50", "30", TipoVeiculo.CAMINHAO_CACAMBA.getId(), 5, BigDecimal.valueOf(47.88d)));
        return mock;
    }

    private static class MockCusto {
        private String percursoPavimentado;
        private String percursoNaoPavimentado;
        private String tipoVeiculo;
        private Integer peso;
        private BigDecimal resultadoEsperado;

        public MockCusto(final String percursoPavimentado, final String percursoNaoPavimentado, final String tipoVeiculo, final Integer peso,
                final BigDecimal resultadoEsperado) {
            this.percursoPavimentado = percursoPavimentado;
            this.percursoNaoPavimentado = percursoNaoPavimentado;
            this.tipoVeiculo = tipoVeiculo;
            this.peso = peso;
            this.resultadoEsperado = resultadoEsperado;
        }

        public String getPercursoPavimentado() {
            return percursoPavimentado;
        }

        public String getPercursoNaoPavimentado() {
            return percursoNaoPavimentado;
        }

        public String getTipoVeiculo() {
            return tipoVeiculo;
        }

        public Integer getPeso() {
            return peso;
        }

        public BigDecimal getResultadoEsperado() {
            return resultadoEsperado;
        }
    }
}