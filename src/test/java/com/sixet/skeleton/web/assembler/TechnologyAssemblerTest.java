package com.sixet.skeleton.web.assembler;

import com.sixet.skeleton.BaseTest;
import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class TechnologyAssemblerTest extends BaseTest {

    @Autowired
    private TechnologyAssembler assembler;

    /**
     * METHOD: fromDomain
     * RULE: This method convert the domain to resource.
     * CASE: If converted must be return
     */
    @Test
    public void convertDomainToResourceMustReturnResource() {
        Technology domain = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        assertEquals(assembler.fromDomain(domain), resource);
    }

    /**
     * METHOD: fromDomain
     * RULE: This method convert the resource to domain.
     * CASE: If converted must be return
     */
    @Test
    public void convertResourceToDomainMustReturnDomain() {
        Technology domain = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        assertEquals(assembler.fromResource(resource), domain);
    }
}
