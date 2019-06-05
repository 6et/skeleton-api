package com.sixet.skeleton.core.business;

import com.sixet.core.domain.Technology;
import com.sixet.core.service.TechnologyService;
import com.sixet.exception.NoContentException;
import com.sixet.utils.TechnologyUtilsTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class TechnologyBusinessTest extends BaseBusinessTest {

    @MockBean
    private TechnologyService service;

    @Autowired
    private TechnologyBusiness business;

    @Test(expected = NoContentException.class)
    public void findAll_withEmptyResult_mustThrowNoContentException() throws NoContentException {
        given(service.findAll()).willReturn(new ArrayList<>());
        business.findAll();
    }

    @Test
    public void findByName_withResults_mustReturnTechnology() throws NoContentException {
        Technology technology = TechnologyUtilsTest.createTechnology();
        given(service.findByName(technology.getName())).willReturn(technology);
        assertEquals(technology, business.findByName(technology.getName()));
    }
}
