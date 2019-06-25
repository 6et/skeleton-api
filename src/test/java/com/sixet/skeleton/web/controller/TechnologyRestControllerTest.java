package com.sixet.skeleton.web.controller;

import com.google.gson.Gson;
import com.sixet.skeleton.core.business.TechnologyBusiness;
import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sixet.skeleton.utils.TechnologyUtilsTest.createTechnology;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.core.Is.is;

/**
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class TechnologyRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TechnologyBusiness business;

    /**
     * ENDPOINT: /technologies
     * METHOD: GET
     * RULE: This endpoint must be return the technology list.
     * CASE: If find any result must be return a Technology list.
     */
    @Test
    @WithMockUser
    public void findAllWithResultMustReturn() throws Exception {
        List<Technology> technologies = new ArrayList<>(asList(createTechnology(), createTechnology()));
        Page<Technology> page = new PageImpl<>(technologies, Pageable.unpaged(), 1);
        given(business.findAll(isA(Pageable.class))).willReturn(page);
        this.mvc.perform(get("/technologies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    /**
     * ENDPOINT: /technologies/create
     * METHOD: POST
     * RULE: This endpoint create a technology.
     * CASE: With valid content must be create a technology and return 200 - OK.
     */
    @Test
    @WithMockUser
    public void createWithValidContentMustReturn() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        given(business.create(technology)).willReturn(technology);
        this.mvc.perform(post("/technologies/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(resource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(resource.getName())));
    }

    /**
     * ENDPOINT: /technologies/create
     * METHOD: POST
     * RULE: This endpoint must be create a technology.
     * CASE: With invalid content must be return a 409 - Business Exception.
     */
    @Test
    @WithMockUser
    public void createWithInvalidContentMustReturnBusinessException() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        given(business.create(technology)).willThrow(BusinessException.class);
        this.mvc.perform(post("/technologies/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(resource)))
                .andExpect(status().is4xxClientError());
    }

    /**
     * ENDPOINT: /technologies/update/{id}
     * METHOD: PUT
     * RULE: This endpoint must be update a technology.
     * CASE: If find the id must be return an updated technology.
     */
    @Test
    @WithMockUser
    public void updateWithValidIdMustReturn() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        given(business.update(technology.getId(), technology)).willReturn(technology);
        this.mvc.perform(put("/technologies/update/{id}", resource.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(resource)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is(resource.getName())));
    }

    /**
     * ENDPOINT: /technologies/update/{id}
     * METHOD: PUT
     * RULE: This endpoint must be update a technology.
     * CASE: If didn't find the id must be return 404 - NotFoundException
     */
    @Test
    @WithMockUser
    public void updateWithInvalidIdMustReturnNotFoundException() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        TechnologyResource resource = TechnologyUtilsTest.createTechnologyResource();
        given(business.update(technology.getId(), technology)).willThrow(NotFoundException.class);
        this.mvc.perform(put("/technologies/update/{id}", resource.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(resource)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is4xxClientError());
    }

    /**
     * ENDPOINT: /technologies/delete/{id}
     * METHOD: DELETE
     * RULE: This endpoint must be delete a technology.
     * CASE: If find the id must be return a deleted technology.
     */
    @Test
    @WithMockUser
    public void deleteWithValidIdMustReturn() throws Exception {
        this.mvc.perform(delete("/technologies/delete/{id}", 1L)).andExpect(status().isNoContent());
    }
}
