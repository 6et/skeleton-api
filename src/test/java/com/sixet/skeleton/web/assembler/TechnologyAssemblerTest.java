package com.sixet.skeleton.web.assembler;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TechnologyAssemblerTest extends BaseAssemblerTest {

    @Autowired
    private TechnologyAssembler assembler;

    /**
     * METHOD: fromDomain
     * RULE: This method convert the domain to resource.
     * CASE: If converted must be return
     */
    @Test
    public void convertDomainToResource_mustReturnResource() {
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
    public void convertResourceToDomain_mustReturnDomain() {
        Technology domain = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        assertEquals(assembler.fromResource(resource), domain);
    }
}
