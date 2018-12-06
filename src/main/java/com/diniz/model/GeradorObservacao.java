package com.diniz.model;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.diniz.util.Utils;

public class GeradorObservacao {

    private static final String PREFIXO_NOTA_UNICA = "Fatura da nota fiscal de simples remessa: ";
    private static final String PREFIXO_MULTIPLAS_NOTAS = "Fatura das notas fiscais de simples remessa: ";
    private static final String DESCRICAO_NOTA_COMPLETA = "%s cujo valor é R$ %s";
    private static final String TOTAL = " Total = %s.";

    /**
     * Gera a observação completa, com valor(es).
     * 
     * @param notasFiscais
     * @return
     */
    public String geraObservacaoCompleta(final List<NotaFiscal> notasFiscais) {
        return notasFiscais == null || notasFiscais.isEmpty() ? "" : geraObservacao(notasFiscais, true);
    }

    /**
     * Gera a observação simplificada, sem valor(es)
     * 
     * @param notasFiscais
     * @return
     */
    public String geraObservacaoSimplificada(final List<NotaFiscal> notasFiscais) {
        return notasFiscais == null || notasFiscais.isEmpty() ? "" : geraObservacao(notasFiscais, false);
    }

    private String getPrefixo(final List<NotaFiscal> notasFiscais) {
        return notasFiscais.size() > 1 ? PREFIXO_MULTIPLAS_NOTAS : PREFIXO_NOTA_UNICA;
    }

    private String geraObservacao(final List<NotaFiscal> notasFiscais, final boolean isDescricaoCompleta) {
        final StringBuilder stringBuilder = new StringBuilder();
        BigDecimal total = BigDecimal.ZERO;

        for (Iterator<NotaFiscal> iterator = notasFiscais.iterator(); iterator.hasNext();) {
            final NotaFiscal notaFiscal = iterator.next();
            final String separador = stringBuilder.length() == 0 ? "" : iterator.hasNext() ? ", " : " e ";

            stringBuilder
                    .append(separador)
                    .append(isDescricaoCompleta ? String.format(DESCRICAO_NOTA_COMPLETA, notaFiscal.getNumero(), Utils.format(notaFiscal.getValor()))
                            : notaFiscal.getNumero());

            total = total.add(notaFiscal.getValor());
        }
        stringBuilder.insert(0, getPrefixo(notasFiscais)).append(".");

        if (isDescricaoCompleta) {
            stringBuilder.append(String.format(TOTAL, Utils.format(total)));
        }

        return stringBuilder.toString();
    }
}