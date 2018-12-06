package com.diniz.model;

import java.math.BigDecimal;

public class NotaFiscal {

    private Long numero;
    private BigDecimal valor;

    public NotaFiscal(final Long numero, final BigDecimal valor) {
        this.numero = numero;
        this.valor = valor;
    }

    public Long getNumero() {
        return numero;
    }

    public BigDecimal getValor() {
        return valor;
    }
}