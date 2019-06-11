package com.sixet.skeleton.web.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * {@link com.sixet.skeleton.core.domain.Technology} resource representation.
 * @author gtrevisan
 * @since Jun 11, 2019
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TechnologyResource {

    private Long id;
    private String name;
    private boolean active;
}
