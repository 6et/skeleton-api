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
     * CASE: If didn't find any result must be return a 404 - NoContentException
     */
    @Test(expected = NoContentException.class)
    public void findAllWithEmptyResultMustThrowNoContentException() throws NoContentException {
        Page<Technology> page = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), 1);
        given(service.findAll(isA(Pageable.class))).willReturn(page);
        business.findAll(PageRequest.of(1,10));
    }

    /**
     * METHOD: findAll
     * RULE: This method must be return the technology list.
     * CASE: If find any result must be return a Page<Technology>.
     */
    @Test
    public void findAllWithResultMustReturn() throws NoContentException {
        Technology tech = TechnologyUtilsTest.createTechnology();
        Technology tech1 = TechnologyUtilsTest.createTechnology();
        Page<Technology> page = new PageImpl<>(Arrays.asList(tech,tech1), Pageable.unpaged(), 1);
        given(service.findAll(isA(Pageable.class))).willReturn(page);
        business.findAll(PageRequest.of(1,2));
    }

    /**
     * METHOD: create
     * RULE: This method create a technology.
     * CASE: With valid content must be create a technology and return 200 - OK.
     */
    @Test
    public void createWithValidContentMustReturn() throws BusinessException {
        Technology tech = TechnologyUtilsTest.createTechnology();
        given(service.save(tech)).willReturn(tech);
        business.create(tech);
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
        Technology tech = TechnologyUtilsTest.createTechnology();
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
        Technology tech = TechnologyUtilsTest.createTechnology();
        given(service.findById(1L)).willThrow(NotFoundException.class);
        business.update(tech.getId(), tech);
    }
}


