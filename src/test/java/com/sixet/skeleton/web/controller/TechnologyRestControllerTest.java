package com.sixet.skeleton.web.controller;

import com.sixet.core.business.TechnologyBusiness;
import com.sixet.core.domain.Technology;
import com.sixet.exception.NoContentException;
import com.sixet.utils.TechnologyUtilsTest;
import com.sixet.web.resource.TechnologyResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class TechnologyRestControllerTest extends BaseRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TechnologyBusiness technologyBusiness;

    /**
     * ENDPOINT: /technologies
     * RULE: This endpoint must be return a technology list.
     * CASE: With not found list must be return 204 status (NoContent)
     */
    @Test
    @WithMockUser
    public void getTechnologies_withNoContent_mustReturn204() throws Exception {
        given(technologyBusiness.findAll(isA(Pageable.class))).willThrow(NoContentException.class);
        this.mvc.perform(get("/technologies")).andExpect(status().isNoContent());
    }

    /**
     * ENDPOINT: /technologies
     * RULE: This endpoint must be return the technology list.
     * CASE: With found list must be return 200 status (Ok)
     */
    @Test
    @WithMockUser
    public void getTechnologies_withContent_mustReturn200() throws Exception {

        Arrays.asList(TechnologyUtilsTest.createTechnology(), TechnologyUtilsTest.createTechnology());
        List l = new ArrayList<Technology>();

        given(technologyBusiness.findAll(isA(Pageable.class))).willReturn(PageRequest.of(0,10));
        this.mvc.perform(get("/technologies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * ENDPOINT: /technologies/{name}
     * RULE: This endpoint must be return a technology.
     * CASE: With found technology must be return 200 status (Ok)
     */
    @Test
    @WithMockUser
    public void getTechnologyByName_withContent_mustReturn200() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        given(technologyBusiness.findByName(technology.getName())).willReturn(technology);
        this.mvc.perform(get("/technologies/{name}", technology.getName()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * ENDPOINT: /technologies/{name}
     * RULE: This endpoint must be return a technology.
     * CASE: With not found technology must be return 204 status (NoContent)
     */
    @Test
    @WithMockUser
    public void getTechnologyByName_withNoContent_mustReturn204() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        given(technologyBusiness.findByName(technology.getName())).willThrow(NoContentException.class);
        this.mvc.perform(get("/technologies/{name}", technology.getName()))
                .andExpect(status().isNoContent());
    }
}
