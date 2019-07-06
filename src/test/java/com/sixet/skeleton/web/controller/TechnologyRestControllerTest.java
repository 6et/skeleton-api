package com.sixet.skeleton.web.controller;

import com.google.gson.Gson;
import com.sixet.skeleton.core.business.TechnologyBusiness;
import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.utils.TechnologyUtilsTest;
import com.sixet.skeleton.web.resource.TechnologyResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.sixet.skeleton.utils.TechnologyUtilsTest.createTechnology;
import static com.sixet.skeleton.utils.TechnologyUtilsTest.createTechnologyResource;
import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
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

    private static final Technology JAVA = new Technology(1L, "Java", true);
    private static final Technology ANGULAR = new Technology(2L, "Angular", true);
    private static final List<Technology> TECHNOLOGY_LIST = new ArrayList<>(asList(JAVA, ANGULAR));

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
    public void getTechnologiesShouldReturnTechnologyList() throws Exception {
        Page<Technology> page = new PageImpl<>(TECHNOLOGY_LIST, PageRequest.of(1,2, new Sort(  Sort.Direction.ASC, "name")), 2);
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
    public void getTechnologiesShouldReturnTechnologyEmptyList() throws Exception {
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
    public void shouldCreateTechnology() throws Exception {
        Technology technology = createTechnology();
        TechnologyResource resource = createTechnologyResource();
        given(business.create(any())).willReturn(technology);
        this.mvc.perform(post("/technologies/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(resource)))
                .andExpect(jsonPath("$.name", is(resource.getName())))
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
    public void shouldCreateTechnologyMustReturnBusinessException() throws Exception {
        Technology technology = createTechnology();
        TechnologyResource resource = createTechnologyResource();
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
    public void shouldUpdateTechnology() throws Exception {
        Technology technology = createTechnology();
        given(business.update(anyLong(), any())).willReturn(technology);
        this.mvc.perform(put("/technologies/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(technology)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is(technology.getName())))
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
    public void updateWithInvalidIdMustReturnNotFoundException() throws Exception {
        Technology technology = TechnologyUtilsTest.createTechnology();
        given(business.update(anyLong(), any())).willThrow(NotFoundException.class);
        this.mvc.perform(put("/technologies/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new Gson().toJson(technology)))
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
        doNothing().when(business).delete(anyLong());
        this.mvc.perform(delete("/technologies/delete/{id}", anyLong())).andExpect(status().isNoContent());
    }

    /**
     * ENDPOINT: /technologies/delete/{id}
     * METHOD: DELETE
     * RULE: This endpoint must be delete a technology.
     * CASE: If didn't find the id must be return a deleted technology.
     */
    @Test
    @WithMockUser
    public void deleteWithValidIdMustReturnBusinessException() throws Exception {
        doThrow(NotFoundException.class).when(business).delete(anyLong());
        this.mvc.perform(delete("/technologies/delete/{id}", anyLong()))
                .andExpect(status().is4xxClientError());
    }
}
