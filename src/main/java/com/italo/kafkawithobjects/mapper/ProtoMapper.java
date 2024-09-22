package com.italo.kafkawithobjects.mapper;

import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProtoMapper {

    ProtoMapper INSTANCE = Mappers.getMapper(ProtoMapper.class);
    @Mapping(source = "bordero",target = "numbordero")
    @Mapping(source = "dataOperacao",target = "dataop")
    BorderoProto.Bordero toBorderoProto(Bordero bordero);
}
