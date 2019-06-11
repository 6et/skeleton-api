package com.sixet.skeleton.web.assembler;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.springframework.stereotype.Component;

/**
 * TechnologyAssembler class provides the access functionalities to the convert entities do domain an vice versa.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
@Component
public class TechnologyAssembler extends GenericAssembler<Technology, TechnologyResource> {

    public TechnologyAssembler() {
        super(Technology.class, TechnologyResource.class);
    }
}
