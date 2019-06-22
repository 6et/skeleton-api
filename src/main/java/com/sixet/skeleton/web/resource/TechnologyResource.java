package com.sixet.skeleton.web.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link com.sixet.skeleton.core.domain.Technology} resource representation.
 * @author gtrevisan
 * @since Jun 11, 2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TechnologyResource {

    private Long id;
    private String name;
    private boolean active;
}
