package com.italo.kafkawithobjects.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Bordero implements Serializable {

    private String bordero;
    private BigDecimal valor;
    private Date dataOperacao;
    private List<Itens> itens;
}
