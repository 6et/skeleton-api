package com.sixet.skeleton.core.business;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.core.service.TechnologyService;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

/**
 *
 */
@RunWith(SpringRunner.class)
public class TechnologyBusinessTest extends BaseBusinessTest {

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
    public void findAll_withEmptyResult_mustThrowNoContentException() throws NoContentException {
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
    public void findAll_withResult_mustReturn() throws NoContentException {
        Technology tech = TechnologyUtilsTest.createTechnology();
        Technology tech1 = TechnologyUtilsTest.createTechnology();
        Page<Technology> page = new PageImpl<>(Arrays.asList(tech,tech1), Pageable.unpaged(), 1);
        given(service.findAll(isA(Pageable.class))).willReturn(page);
        business.findAll(PageRequest.of(1,2));
    }


}
