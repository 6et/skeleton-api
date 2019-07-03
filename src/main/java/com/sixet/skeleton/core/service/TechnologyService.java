package com.sixet.skeleton.core.service;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.core.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TechnologyService interface provides the functionality to access the database.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TechnologyService {

    private final TechnologyRepository repository;

    @Transactional(readOnly = true)
    public Page<Technology> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Searches a {@link Technology} by its login
     * @param id of the {@link Technology}
     * @return {@link Technology}
     */
    @Transactional(readOnly = true)
    public Technology findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Technology %d not found.", id)));
    }

    @Transactional
    public Technology save(Technology technology) {
        return repository.save(technology);
    }

    @Transactional
    public void delete(Technology technology) {
        repository.delete(technology);
    }
}
