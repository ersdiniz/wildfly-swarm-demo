package com.diniz.model;

import java.math.BigDecimal;

public class ParametroCusto {

    public static final BigDecimal VEICULO_CAMINHAO_BAU = BigDecimal.valueOf(1.00d);
    public static final BigDecimal VEICULO_CAMINHAO_CACAMBA = BigDecimal.valueOf(1.05d);
    public static final BigDecimal VEICULO_CARRETA = BigDecimal.valueOf(1.12d);

    public static final BigDecimal VIA_PAVIMENTADA = BigDecimal.valueOf(0.54d);
    public static final BigDecimal VIA_NAO_PAVIMENTADA = BigDecimal.valueOf(0.62d);

    public static final BigDecimal CUSTO_TONELADA_ADICIONAL = BigDecimal.valueOf(0.02d);
    public static final BigDecimal LIMITE_TONELADA_SEM_ADICIONAL = BigDecimal.valueOf(5);
}