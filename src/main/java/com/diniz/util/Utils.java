package com.diniz.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Utils {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###,##0.00");

    /**
     * Formata o valor BigDecimal para o padrão brasileiro
     * 
     * @param valor
     * @return Ex.: 1.250,12
     */
    public static String format(final BigDecimal valor) {
        return DECIMAL_FORMAT.format(valor);
    }
}