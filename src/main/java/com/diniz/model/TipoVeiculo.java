package com.diniz.model;

import static com.diniz.model.ParametroCusto.*;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

@XmlType
public enum TipoVeiculo {

    CAMINHAO_BAU("1", "Caminhão baú", VEICULO_CAMINHAO_BAU),
    CAMINHAO_CACAMBA("2", "Caminhão caçamba", VEICULO_CAMINHAO_CACAMBA),
    CARRETA("3", "Carreta", VEICULO_CARRETA);

    private final String id;
    private final String descricao;
    private final BigDecimal fator;

    private TipoVeiculo(final String id, final String descricao, final BigDecimal fator) {
        this.id = id;
        this.descricao = descricao;
        this.fator = fator;
    }

    public static TipoVeiculo of(final String id) {
        for (TipoVeiculo tipoVeiculo : TipoVeiculo.values()) {
            if (tipoVeiculo.getId().equalsIgnoreCase(id)) {
                return tipoVeiculo;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getFator() {
        return fator;
    }
}