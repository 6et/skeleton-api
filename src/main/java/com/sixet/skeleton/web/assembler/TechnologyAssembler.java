package com.sixet.skeleton.web.assembler;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.springframework.stereotype.Component;

@Component
public class TechnologyAssembler extends GenericAssembler<Technology, TechnologyResource> {

    public TechnologyAssembler() {
        super(Technology.class, TechnologyResource.class);
    }
}
