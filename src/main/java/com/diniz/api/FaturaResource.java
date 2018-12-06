package com.diniz.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.diniz.dto.DynamicDto;
import com.diniz.model.GeradorObservacao;
import com.diniz.model.NotaFiscal;

/**
 * Resource criado para demonstração das observações criadas
 */
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Path("/faturas")
@Stateless
public class FaturaResource {

    @Inject
    private GeradorObservacao geradorObservacao;

    @GET
    @Path("observacoes")
    public Response getObservacao() {
        return Response.ok(mockObservacoes()).build();
    }

    private DynamicDto mockObservacoes() {
        return DynamicDto.of()
                .with("notasMultiplasComValor", geradorObservacao.geraObservacaoCompleta(mockNotasFiscais()))
                .with("notasMultiplasSemValor", geradorObservacao.geraObservacaoSimplificada(mockNotasFiscais()))
                .with("notaUnicaComValor", geradorObservacao.geraObservacaoCompleta(mockNotaFiscal()))
                .with("notaUnicaSemValor", geradorObservacao.geraObservacaoSimplificada(mockNotaFiscal()));
    }

    private List<NotaFiscal> mockNotasFiscais() {
        final List<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>(5);
        notasFiscais.add(new NotaFiscal(1L, BigDecimal.valueOf(10d)));
        notasFiscais.add(new NotaFiscal(2L, BigDecimal.valueOf(35d)));
        notasFiscais.add(new NotaFiscal(3L, BigDecimal.valueOf(5d)));
        notasFiscais.add(new NotaFiscal(4L, BigDecimal.valueOf(1500d)));
        notasFiscais.add(new NotaFiscal(5L, BigDecimal.valueOf(0.3d)));
        return notasFiscais;
    }

    private List<NotaFiscal> mockNotaFiscal() {
        return Collections.singletonList(new NotaFiscal(3L, BigDecimal.valueOf(35d)));
    }
}