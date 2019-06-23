package com.sixet.skeleton.web.assembler;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
public class TechnologyAssemblerTest {

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
