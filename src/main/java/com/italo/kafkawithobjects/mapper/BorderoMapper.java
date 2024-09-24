package com.italo.kafkawithobjects.mapper;

import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BorderoMapper {

    BorderoMapper INSTANCE = Mappers.getMapper(BorderoMapper.class);
    @Mapping(source = "bordero",target = "numbordero")
    @Mapping(source = "valor", target = "valortotal")
    @Mapping(source = "dataOperacao",target = "dataop",dateFormat = "dd/MM/yyyy")
    BorderoProto.Bordero toBorderoProto(Bordero bordero);
}
