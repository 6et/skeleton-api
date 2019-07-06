package com.sixet.skeleton.core.business;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.core.service.TechnologyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TechnologyBusinessTest {

    private static final Technology JAVA_TECHNOLOGY = new Technology(1L, "Java", true);
    private static final Technology ANGULAR_TECHNOLOGY = new Technology(2L, "Angular", true);
    private static final List<Technology> TECHNOLOGY_LIST = new ArrayList<>(asList(JAVA_TECHNOLOGY, ANGULAR_TECHNOLOGY));

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
    public void findAllTechnologiesShouldReturnEmptyList() {
        Page<Technology> page = new PageImpl<>(new ArrayList<>());
        given(service.findAll(isA(Pageable.class))).willReturn(page);
        assertTrue(business.findAll(PageRequest.of(1,10)).isEmpty());
    }

    /**
     * METHOD: findAll
     * RULE: This method must be return the technology list.
     * CASE: If find any result must be return a Page<Technology>.
     */
    @Test
    public void findAllTechnologies() {
        Page<Technology> page = new PageImpl<>(TECHNOLOGY_LIST,
                PageRequest.of(1,2, new Sort(  Sort.Direction.ASC, "name")), 2);
        given(service.findAll(isA(Pageable.class))).willReturn(page);
        assertThat(business.findAll(PageRequest.of(1,2)).getContent(), hasSize(2));
    }

    /**
     * METHOD: create
     * RULE: This method create a technology.
     * CASE: With valid content must be create a technology and return 200 - OK.
     */
    @Test
    public void createTechnology() throws BusinessException {
        given(service.save(any())).willReturn(JAVA_TECHNOLOGY);
        Technology technologyCreated = business.create(JAVA_TECHNOLOGY);
        assertNotNull(technologyCreated);
        assertEquals(technologyCreated.getName(), JAVA_TECHNOLOGY.getName());
    }

    /**
     * METHOD: create
     * RULE: This method create a technology.
     * CASE: With invalid content must be return a 409 - Business Exception.
     */
    @Test(expected = BusinessException.class)
    public void createTechnologyShouldReturnBusinessException() throws BusinessException {
        given(service.save(any())).willReturn(null);
        business.create(new Technology());
    }

    /**
     * METHOD: update
     * RULE: This method must be update a technology.
     * CASE: If find the id must be return an updated technology.
     */
    @Test
    public void updateTechnology() {
        given(service.findById(anyLong())).willReturn(JAVA_TECHNOLOGY);
        given(service.save(any())).willReturn(JAVA_TECHNOLOGY);
        Technology technologyUpdated = business.update(JAVA_TECHNOLOGY.getId(), JAVA_TECHNOLOGY);
        assertEquals(technologyUpdated.getName(), JAVA_TECHNOLOGY.getName());
        assertEquals(technologyUpdated.getId(), JAVA_TECHNOLOGY.getId());

    }

    /**
     * METHOD: update
     * RULE: This method must be update a technology.
     * CASE: If didn't find the id must be return 404 - NotFoundException
     */
    @Test(expected = NotFoundException.class)
    public void updateTechnologyShouldReturnNotFoundException() {
        given(service.findById(anyLong())).willThrow(NotFoundException.class);
        business.update(JAVA_TECHNOLOGY.getId(), JAVA_TECHNOLOGY);
    }

    /**
     * METHOD: delete
     * RULE: This method must be delete a technology.
     * CASE: If find the id must be return a deleted technology.
     */
//    @Test
//    public void deleteWithValidIdMustReturn() {
//        given(service.findById(anyLong())).willReturn(JAVA_TECHNOLOGY);
//        assertbusiness.delete(JAVA_TECHNOLOGY.getId());
//    }

    /**
     * METHOD: delete
     * RULE: This method must be delete a technology.
     * CASE: If didn't find the id must be return 404 - NotFoundException
     */
    @Test(expected = NotFoundException.class)
    public void deleteWithInvalidIdMustReturnNotFoundException() throws Exception {
        given(service.findById(anyLong())).willThrow(NotFoundException.class);
        business.delete(JAVA_TECHNOLOGY.getId());
    }
}


