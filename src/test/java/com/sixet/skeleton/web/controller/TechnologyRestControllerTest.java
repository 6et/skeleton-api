package com.sixet.skeleton.web.controller;

import com.google.gson.Gson;
import com.sixet.skeleton.core.business.TechnologyBusiness;
import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

/**
 *
 */
@RunWith(SpringRunner.class)
public class TechnologyRestControllerTest extends BaseRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TechnologyBusiness business;

    /**
     * ENDPOINT: /technologies
     * METHOD: GET
     * RULE: This endpoint must be return a technology list.
     * CASE: With not found list must be return 204 status (NoContent)
     */
    @Test
    @WithMockUser
    public void getTechnologies_withNoContent_mustReturn204() throws Exception {
        given(business.findAll(isA(Pageable.class))).willThrow(NoContentException.class);
        this.mvc.perform(get("/technologies")).andExpect(status().isNoContent());
    }

    /**
     * ENDPOINT: /technologies
     * METHOD: GET
     * RULE: This endpoint must be return the technology list.
     * CASE: With found list must be return 200 status (Ok)
     */
    @Test
    @WithMockUser
    public void getTechnologies_withContent_mustReturn200() throws Exception {

        Page<Technology> page = new PageImpl<>(Collections.emptyList(), Pageable.unpaged(), 1);

        given(business.findAll(isA(Pageable.class))).willReturn(page);
        this.mvc.perform(get("/technologies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * ENDPOINT: /technologies
     * METHOD: POST
     * RULE: This endpoint must be create a technology.
     * CASE: With Technology name must be return 200 status (Ok)
     */
    @Test
    @WithMockUser
    public void postTechnology_withName_mustReturn200() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        given(business.create(technology)).willReturn(technology);
        this.mvc.perform(post("/technologies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(resource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(resource.getName())));
    }

    /**
     * ENDPOINT: /technologies
     * METHOD: POST
     * RULE: This endpoint must be create a technology.
     * CASE: Without the Technology name must be return a 409 - Business Exception.
     */
    @Test
    @WithMockUser
    public void postTechnology_withoutName_mustReturn4xx() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        given(business.create(technology)).willThrow(BusinessException.class);
        this.mvc.perform(post("/technologies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(resource)))
                .andExpect(status().is4xxClientError());
    }
}
