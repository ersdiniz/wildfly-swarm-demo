package com.diniz.model;

import static com.diniz.model.ParametroCusto.CUSTO_TONELADA_ADICIONAL;
import static com.diniz.model.ParametroCusto.LIMITE_TONELADA_SEM_ADICIONAL;
import static com.diniz.model.ParametroCusto.VIA_NAO_PAVIMENTADA;
import static com.diniz.model.ParametroCusto.VIA_PAVIMENTADA;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculoCusto {

    private static final int SCALE_DUAS_CASAS = 2;

    /**
     * Calcula o custo total do transporte com base nos parâmetros informados em
     * {@link ParametroCusto}
     * 
     * @param percursoPavimentado
     * @param percursoNaoPavimentado
     * @param tipoVeiculoId
     * @param peso
     * @return BigDecimal
     */
    public BigDecimal of(final String percursoPavimentado, final String percursoNaoPavimentado, final String tipoVeiculoId, final Integer peso) {
        final TipoVeiculo tipoVeiculo = TipoVeiculo.of(tipoVeiculoId);

        Objects.requireNonNull(convertPercurso(percursoPavimentado));
        Objects.requireNonNull(convertPercurso(percursoNaoPavimentado));
        Objects.requireNonNull(tipoVeiculo);
        Objects.requireNonNull(peso);

        return getCusto(convertPercurso(percursoPavimentado), convertPercurso(percursoNaoPavimentado), tipoVeiculo, BigDecimal.valueOf(peso));
    }

    private BigDecimal getCusto(final BigDecimal percursoPavimentado, final BigDecimal percursoNaoPavimentado, final TipoVeiculo tipoVeiculo,
            final BigDecimal peso) {
        final BigDecimal custoPercursoPavimentado = getValor(percursoPavimentado, VIA_PAVIMENTADA);
        final BigDecimal custoPercursoNaoPavimentado = getValor(percursoNaoPavimentado, VIA_NAO_PAVIMENTADA);

        final BigDecimal custoVeiculoPercursoPavimentado = getValor(custoPercursoPavimentado, tipoVeiculo.getFator());
        final BigDecimal custoVeiculoPercursoNaoPavimentado = getValor(custoPercursoNaoPavimentado, tipoVeiculo.getFator());

        final BigDecimal custoVeiculoPercurso = custoVeiculoPercursoPavimentado.add(custoVeiculoPercursoNaoPavimentado);

        return custoVeiculoPercurso.add(getCustoAdicional(percursoPavimentado, percursoNaoPavimentado, peso))
                .setScale(SCALE_DUAS_CASAS, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getValor(final BigDecimal valor, final BigDecimal multiplicador) {
        return BigDecimal.ZERO.compareTo(valor) < 0 ? valor.multiply(multiplicador) : BigDecimal.ZERO;
    }

    private BigDecimal getCustoAdicional(final BigDecimal percursoPavimentado, final BigDecimal percursoNaoPavimentado, final BigDecimal peso) {
        if (peso.compareTo(LIMITE_TONELADA_SEM_ADICIONAL) < 1) {
            return BigDecimal.ZERO;
        }
        final BigDecimal pesoExcedente = peso.subtract(LIMITE_TONELADA_SEM_ADICIONAL);
        final BigDecimal custoPeloExcedente = pesoExcedente.multiply(CUSTO_TONELADA_ADICIONAL);

        final BigDecimal custoAdicionalPercursoPavimentado = getValor(percursoPavimentado, custoPeloExcedente);
        final BigDecimal custoAdicionalPercursoNaoPavimentado = getValor(percursoNaoPavimentado, custoPeloExcedente);

        return custoAdicionalPercursoPavimentado.add(custoAdicionalPercursoNaoPavimentado);
    }

    private BigDecimal convertPercurso(final String percurso) {
        return BigDecimal.valueOf(Double.valueOf(percurso.trim().replace(",", ".")));
    }
}