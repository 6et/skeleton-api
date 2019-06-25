package com.sixet.skeleton.core.business;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.core.service.TechnologyService;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sixet.skeleton.utils.TechnologyUtilsTest.createTechnology;
import static java.util.Arrays.asList;
import static junit.framework.TestCase.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

/**
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TechnologyBusinessTest {

    @MockBean
    private TechnologyService service;

    @Autowired
    private TechnologyBusiness business;

    /**
     * METHOD: findAll
     * RULE: This method must be return Page<Technology>.
     * CASE: If didn't find any result must be return a empty list.
     */
    @Test
    public void findAllWithEmptyResultMustThrowNoContentException() {
        Page<Technology> page = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), 1);
        given(service.findAll(isA(Pageable.class))).willReturn(page);
        assertTrue(business.findAll(PageRequest.of(1,10)).isEmpty());
    }

    /**
     * METHOD: findAll
     * RULE: This method must be return the technology list.
     * CASE: If find any result must be return a Page<Technology>.
     */
    @Test
    public void findAllWithResultMustReturn() throws NoContentException {
        List<Technology> technologies = new ArrayList<>(asList(createTechnology(), createTechnology()));
        Page<Technology> page = new PageImpl<>(technologies, Pageable.unpaged(), 1);
        given(service.findAll(isA(Pageable.class))).willReturn(page);
        assertFalse(business.findAll(PageRequest.of(1,2)).isEmpty());
        assertThat(technologies, hasSize(2));
    }

    /**
     * METHOD: create
     * RULE: This method create a technology.
     * CASE: With valid content must be create a technology and return 200 - OK.
     */
    @Test
    public void createWithValidContentMustReturn() throws BusinessException {
        Technology tech = createTechnology();
        given(service.save(tech)).willReturn(tech);
        assertNotNull(business.create(tech));
    }

    /**
     * METHOD: create
     * RULE: This method create a technology.
     * CASE: With invalid content must be return a 409 - Business Exception.
     */
    @Test(expected = BusinessException.class)
    public void createWithInvalidContentMustReturnBusinessException() throws BusinessException {
        Technology tech = new Technology(1L, null, true);
        given(service.save(tech)).willReturn(null);
        business.create(tech);
    }

    /**
     * METHOD: update
     * RULE: This method must be update a technology.
     * CASE: If find the id must be return an updated technology.
     */
    @Test
    public void updateWithValidIdMustReturn() throws Exception {
        Technology tech = createTechnology();
        given(service.findById(1L)).willReturn(tech);
        given(service.save(tech)).willReturn(tech);
        business.update(tech.getId(), tech);
    }

    /**
     * METHOD: update
     * RULE: This method must be update a technology.
     * CASE: If didn't find the id must be return 404 - NotFoundException
     */
    @Test(expected = NotFoundException.class)
    public void updateWithInvalidIdMustReturnNotFoundException() throws Exception {
        Technology tech = createTechnology();
        given(service.findById(1L)).willThrow(NotFoundException.class);
        business.update(tech.getId(), tech);
    }

    /**
     * METHOD: delete
     * RULE: This method must be delete a technology.
     * CASE: If find the id must be return a deleted technology.
     */
    @Test
    public void deleteWithValidIdMustReturn() throws Exception {
        Technology tech = createTechnology();
        given(service.findById(1L)).willReturn(tech);
        business.delete(tech.getId());
    }

    /**
     * METHOD: delete
     * RULE: This method must be delete a technology.
     * CASE: If didn't find the id must be return 404 - NotFoundException
     */
    @Test(expected = NotFoundException.class)
    public void deleteWithInvalidIdMustReturnNotFoundException() throws Exception {
        Technology tech = createTechnology();
        given(service.findById(1L)).willThrow(NotFoundException.class);
        business.delete(tech.getId());
    }
}


