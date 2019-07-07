package com.sixet.skeleton.web.controller;

import com.google.gson.Gson;
import com.sixet.skeleton.BaseTest;
import com.sixet.skeleton.core.business.TechnologyBusiness;
import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class TechnologyRestControllerTest extends BaseTest {

    private static final Technology JAVA_TECHNOLOGY = new Technology(1L, "Java", true);
    private static final Technology ANGULAR_TECHNOLOGY = new Technology(2L, "Angular", true);
    private static final List<Technology> TECHNOLOGY_LIST = new ArrayList<>(asList(JAVA_TECHNOLOGY, ANGULAR_TECHNOLOGY));

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
    public void getTechnologiesList() throws Exception {
        Page<Technology> page = new PageImpl<>(TECHNOLOGY_LIST,
                PageRequest.of(1,2, new Sort(  Sort.Direction.ASC, "name")), 2);
        given(business.findAll(isA(Pageable.class))).willReturn(page);
        this.mvc.perform(get("/technologies?page=1&size=2&sort=name,asc")
                .content(new Gson().toJson(page)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.totalPages",is(2)))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(1)))
                .andExpect(jsonPath("$.pageable.pageSize", is(2)))
                .andExpect(jsonPath("$.pageable.sort.sorted", is(true)))
                .andExpect(status().isOk());
    }

    /**
     * ENDPOINT: /technologies
     * METHOD: GET
     * RULE: This endpoint must be return the technology list.
     * CASE: If didn't find any result must be return a empty technology list.
     */
    @Test
    @WithMockUser
    public void getTechnologiesListShouldReturnEmptyList() throws Exception {
        Page<Technology> page = new PageImpl<>(new ArrayList<>());
        given(business.findAll(isA(Pageable.class))).willReturn(page);
        this.mvc.perform(get("/technologies")
                .content(new Gson().toJson(page)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(status().isOk());
    }

    /**
     * ENDPOINT: /technologies/create
     * METHOD: POST
     * RULE: This endpoint create a technology.
     * CASE: With valid content must be create a technology and return 200 - OK.
     */
    @Test
    @WithMockUser
    public void createTechnology() throws Exception {
        given(business.create(any())).willReturn(JAVA_TECHNOLOGY);
        this.mvc.perform(post("/technologies/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(JAVA_TECHNOLOGY)))
                .andExpect(jsonPath("$.name", is(JAVA_TECHNOLOGY.getName())))
                .andExpect(status().isCreated());
    }

    /**
     * ENDPOINT: /technologies/create
     * METHOD: POST
     * RULE: This endpoint must be create a technology.
     * CASE: With invalid content must be return a 409 - Business Exception.
     */
    @Test
    @WithMockUser
    public void createTechnologyShouldReturnBusinessException() throws Exception {
        given(business.create(any())).willThrow(BusinessException.class);
        this.mvc.perform(post("/technologies/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(new TechnologyResource(1L, "java", true))))
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
    public void updateTechnology() throws Exception {
        given(business.update(anyLong(), any())).willReturn(JAVA_TECHNOLOGY);
        this.mvc.perform(put("/technologies/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(JAVA_TECHNOLOGY)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is(JAVA_TECHNOLOGY.getName())))
                .andExpect(status().isOk());

    }
    /**
     * ENDPOINT: /technologies/update/{id}
     * METHOD: PUT
     * RULE: This endpoint must be update a technology.
     * CASE: If didn't find the id must be return 404 - NotFoundException
     */
    @Test
    @WithMockUser
    public void updateTechnologyShouldReturnNotFoundException() throws Exception {
        given(business.update(anyLong(), any())).willThrow(NotFoundException.class);
        this.mvc.perform(put("/technologies/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(JAVA_TECHNOLOGY)))
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
    public void deleteTechnology() throws Exception {
        doNothing().when(business).delete(anyLong());
        this.mvc.perform(delete("/technologies/delete/{id}", anyLong())).andExpect(status().isNoContent());
    }

    /**
     * ENDPOINT: /technologies/delete/{id}
     * METHOD: DELETE
     * RULE: This endpoint must be delete a technology.
     * CASE: If didn't find the id must be return a NotFoundException.class
     */
    @Test
    @WithMockUser
    public void deleteTechnologyShouldReturnNotFoundException() throws Exception {
        doThrow(NotFoundException.class).when(business).delete(anyLong());
        this.mvc.perform(delete("/technologies/delete/{id}", anyLong()))
                .andExpect(status().is4xxClientError());
    }
}
