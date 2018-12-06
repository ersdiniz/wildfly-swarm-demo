package com.diniz.model;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class GeradorObservacaoTest {

    @Inject
    private GeradorObservacao geradorObservacao;

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

    @Test
    public void testGeracaoObservacaoCompleta() {
        final String resultado1 = "Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total = 1.550,30.";
        final String resultado2 = "Fatura da nota fiscal de simples remessa: 3 cujo valor é R$ 35,00. Total = 35,00.";

        assertEquals(resultado1, geradorObservacao.geraObservacaoCompleta(mockNotasFiscais()));
        assertEquals(resultado2, geradorObservacao.geraObservacaoCompleta(mockNotaFiscal()));
    }

    @Test
    public void testGeracaoObservacaoSimplificada() {
        final String resultado1 = "Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5.";
        final String resultado2 = "Fatura da nota fiscal de simples remessa: 3.";

        assertEquals(resultado1, geradorObservacao.geraObservacaoSimplificada(mockNotasFiscais()));
        assertEquals(resultado2, geradorObservacao.geraObservacaoSimplificada(mockNotaFiscal()));
    }
}