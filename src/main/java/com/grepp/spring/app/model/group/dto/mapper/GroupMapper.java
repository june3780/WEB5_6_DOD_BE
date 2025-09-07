package com.grepp.spring.app.model.group.dto.mapper;

import com.grepp.spring.app.model.group.dto.GroupDto;
import com.grepp.spring.app.model.group.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    Group toEntity(GroupDto groupDto);
    GroupDto toDto(Group group);
}
