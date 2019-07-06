package com.sixet.skeleton.core.service;


import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.repository.TechnologyRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TechnologyServiceTest {

    @Autowired
    private TechnologyService service;

    @MockBean
    private TechnologyRepository repository;

    private static final Technology JAVA = new Technology(1L, "Java", true);
    private static final Technology ANGULAR = new Technology(2L, "Angular", true);
    private static final List<Technology> TECHNOLOGY_LIST = new ArrayList<>(asList(JAVA, ANGULAR));


    /**
     * METHOD: findAll
     * RULE: This method must be return Page<Technology>.
     * CASE: If didn't find any result must be return a empty list.
     */
   /* @Test
    public void findAllMustReturnEmptyList() {
        given(repository.findAll(isA(Pageable.class))).willReturn(TECHNOLOGY_LIST);
        assertFalse(service.findAll(PageRequest.of(1,10)).isEmpty());
        assertThat(TECHNOLOGY_LIST, hasSize(2));
    }*/
}
