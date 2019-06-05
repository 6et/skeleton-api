package com.sixet.skeleton.web.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TechnologyResource {

    private Long id;
    private String name;
    private boolean active;
}
