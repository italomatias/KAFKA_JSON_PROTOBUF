package com.italo.kafkawithobjects.mapper;

import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ParcelaMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
// ADICIONADO POIS MAPSTRUCT POSSUI BUG AO CONVERTER LISTAR PRA PROTO
public interface BorderoMapper {

    BorderoMapper INSTANCE = Mappers.getMapper(BorderoMapper.class);

    @Mapping(source = "bordero", target = "numbordero")
    @Mapping(source = "valor", target = "valortotal")
    @Mapping(source = "dataOperacao", target = "dataop", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "itens", target = "parcela")
    BorderoProto.Bordero toBorderoProto(Bordero bordero);

    @Mapping(source = "numbordero", target = "bordero")
    @Mapping(source = "valortotal", target = "valor")
    @Mapping(source = "dataop", target = "dataOperacao", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "parcela", target = "itens")
    Bordero toBordero(BorderoProto.Bordero bordero);
}
