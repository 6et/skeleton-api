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
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
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

    private Pageable pageable;

    @Test(expected = NoContentException.class)
    public void findAll_withEmptyResult_mustThrowNoContentException() throws NoContentException {
        Page<Technology> page = new PageImpl<>(new ArrayList<>(), Pageable.unpaged(), 1);
        given(service.findAll(pageable)).willReturn(page);
        business.findAll(pageable);
    }

    @Test
    public void findByName_withResults_mustReturnTechnology() throws NoContentException {
        Technology technology = TechnologyUtilsTest.createTechnology();
        given(service.findByName(technology.getName())).willReturn(technology);
        assertEquals(technology, business.findByName(technology.getName()));
    }
}
