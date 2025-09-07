package com.grepp.spring.app.model.group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GroupDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isGrouped;

}
