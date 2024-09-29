package com.italo.kafkawithobjects.mapper;

import com.italo.kafkawithobjects.model.Itens;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ParcelaMapper {

    ParcelaMapper INSTANCE = Mappers.getMapper(ParcelaMapper.class);

    @Mapping(target = "datavencimento", source = "vencimento", dateFormat = "dd/MM/yyyy")
    BorderoProto.Parcela toItensParcela(Itens itens);

    @Mapping(target = "vencimento", source = "datavencimento", dateFormat = "dd/MM/yyyy")
    Itens toItens(BorderoProto.Parcela parcela);
}
