package com.guvvalas.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chapter {

    String name;

    Integer id;

    String url;

    String content;

    String description;

    Integer courseId;

    Integer sequence;

    Boolean isActive=Boolean.FALSE;

    Boolean isPublish=Boolean.FALSE;
}
