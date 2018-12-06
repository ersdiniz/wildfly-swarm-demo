package com.diniz.model;

import static com.diniz.model.ParametroCusto.*;

import java.math.BigDecimal;

public class CalculoCusto {

    private static final String MENSAGEM_CAMPO_OBRIGATORIO = "O campo \"%s\" é obrigatório.";
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

        validaCamposObrigatorios(percursoPavimentado, percursoNaoPavimentado, peso, tipoVeiculo);

        return getCusto(convertPercurso(percursoPavimentado), convertPercurso(percursoNaoPavimentado), tipoVeiculo, BigDecimal.valueOf(peso));
    }

    private BigDecimal getCusto(final BigDecimal percursoPavimentado, final BigDecimal percursoNaoPavimentado, final TipoVeiculo tipoVeiculo,
            final BigDecimal peso) {
        final BigDecimal custoPercursoPavimentado = multiplicaValores(percursoPavimentado, VIA_PAVIMENTADA);
        final BigDecimal custoPercursoNaoPavimentado = multiplicaValores(percursoNaoPavimentado, VIA_NAO_PAVIMENTADA);

        final BigDecimal custoVeiculoPercursoPavimentado = multiplicaValores(custoPercursoPavimentado, tipoVeiculo.getFator());
        final BigDecimal custoVeiculoPercursoNaoPavimentado = multiplicaValores(custoPercursoNaoPavimentado, tipoVeiculo.getFator());

        final BigDecimal custoVeiculoPercurso = custoVeiculoPercursoPavimentado.add(custoVeiculoPercursoNaoPavimentado);

        return custoVeiculoPercurso.add(getCustoAdicional(percursoPavimentado, percursoNaoPavimentado, peso))
                .setScale(SCALE_DUAS_CASAS, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getCustoAdicional(final BigDecimal percursoPavimentado, final BigDecimal percursoNaoPavimentado, final BigDecimal peso) {
        if (peso.compareTo(LIMITE_TONELADA_SEM_ADICIONAL) < 1) {
            return BigDecimal.ZERO;
        }
        final BigDecimal pesoExcedente = peso.subtract(LIMITE_TONELADA_SEM_ADICIONAL);
        final BigDecimal custoPeloExcedente = pesoExcedente.multiply(CUSTO_TONELADA_ADICIONAL);

        final BigDecimal custoAdicionalPercursoPavimentado = multiplicaValores(percursoPavimentado, custoPeloExcedente);
        final BigDecimal custoAdicionalPercursoNaoPavimentado = multiplicaValores(percursoNaoPavimentado, custoPeloExcedente);

        return custoAdicionalPercursoPavimentado.add(custoAdicionalPercursoNaoPavimentado);
    }

    private BigDecimal multiplicaValores(final BigDecimal valor, final BigDecimal multiplicador) {
        return BigDecimal.ZERO.compareTo(valor) < 0 ? valor.multiply(multiplicador) : BigDecimal.ZERO;
    }

    private BigDecimal convertPercurso(final String percurso) {
        return BigDecimal.valueOf(Double.valueOf(percurso.trim().replace(",", ".")));
    }

    private void validaCamposObrigatorios(final String percursoPavimentado, final String percursoNaoPavimentado, final Integer peso,
            final TipoVeiculo tipoVeiculo) {
        if (percursoPavimentado == null) {
            throw new CalculoCustoException(String.format(MENSAGEM_CAMPO_OBRIGATORIO, "percursoPavimentado"));
        }
        if (percursoNaoPavimentado == null) {
            throw new CalculoCustoException(String.format(MENSAGEM_CAMPO_OBRIGATORIO, "percursoNaoPavimentado"));
        }
        if (tipoVeiculo == null) {
            throw new CalculoCustoException(String.format(MENSAGEM_CAMPO_OBRIGATORIO, "tipoVeiculo"));
        }
        if (peso == null) {
            throw new CalculoCustoException(String.format(MENSAGEM_CAMPO_OBRIGATORIO, "peso"));
        }
    }
}