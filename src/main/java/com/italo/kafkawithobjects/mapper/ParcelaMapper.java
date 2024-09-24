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
    @Mapping(source = "vencimento",target = "dataVencimento",dateFormat = "dd/MM/yyyy")
    List<BorderoProto.Parcela> toItensParcela(List<Itens> itens);
}
