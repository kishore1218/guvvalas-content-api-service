package com.guvvalas.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 *
 */
@Data
@Builder
public class Course {

    Integer courseId;

    String name;

    String icon;

    String url;

    String description;

    List<Chapter> chapters;

    Boolean isPublish=Boolean.FALSE;

    Boolean isActive=Boolean.FALSE;
}
