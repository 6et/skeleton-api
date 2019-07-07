package com.sixet.skeleton.core.service;


import com.sixet.skeleton.BaseTest;
import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.core.repository.TechnologyRepository;
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
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TechnologyServiceTest extends BaseTest {

    private static final Technology JAVA_TECHNOLOGY = new Technology(1L, "Java", true);
    private static final Technology ANGULAR_TECHNOLOGY = new Technology(2L, "Angular", true);
    private static final List<Technology> TECHNOLOGY_LIST = new ArrayList<>(asList(JAVA_TECHNOLOGY, ANGULAR_TECHNOLOGY));
    private static final PageRequest pageRequest = PageRequest.of(1,2, new Sort(  Sort.Direction.ASC, "name"));

    @Autowired
    private TechnologyService service;

    @MockBean
    private TechnologyRepository repository;

        /**
         * METHOD: findAll
         * RULE: This method must be return the technology list.
         * CASE: If find any result must be return a Page<Technology>.
         */
        @Test
        public void findAllTechnologies() {
            Page<Technology> page = new PageImpl<>(TECHNOLOGY_LIST, pageRequest, 2);
            given(repository.findAll(pageRequest)).willReturn(page);
            assertThat(service.findAll(pageRequest).getContent(), hasSize(2));
            verify(repository, times(1)).findAll(pageRequest);
        }
        /**
         * METHOD: update
         * RULE: This method must be update a technology.
         * CASE: If find the id must be return an updated technology.
         */
        @Test
        public void saveTechnology() {
            given(repository.save(any())).willReturn(JAVA_TECHNOLOGY);
            assertEquals(service.save(JAVA_TECHNOLOGY).getName(), JAVA_TECHNOLOGY.getName());
            verify(repository, times(1)).save(JAVA_TECHNOLOGY);
        }

//    @Test
//    public void deleteTechnology() {
//        doNothing().when(repository).delete(JAVA_TECHNOLOGY);
//        given(repository.delete(JAVA_TECHNOLOGY)).willReturn(JAVA_TECHNOLOGY);
//        assertEquals(service.save(JAVA_TECHNOLOGY).getName(), JAVA_TECHNOLOGY.getName());
//        verify(repository, times(1)).delete(JAVA_TECHNOLOGY);
//    }


    @Test
    public void findById() throws Exception {
//        when(repository.findById(anyLong())).thenReturn(JAVA_TECHNOLOGY);

//        given(repository.findById(1L)).willReturn(JAVA_TECHNOLOGY);
//        service.findById(JAVA_TECHNOLOGY.getId());
    }

        @Test(expected = NotFoundException.class)
        public void findByIDShouldReturnNotFoundException() throws Exception {
            given(repository.findById(anyLong())).willThrow(NotFoundException.class);
            service.findById(JAVA_TECHNOLOGY.getId());
        }
    }
