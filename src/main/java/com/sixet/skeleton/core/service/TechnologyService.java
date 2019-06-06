package com.sixet.skeleton.core.service;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TechnologyService {

    private final TechnologyRepository repository;

    @Transactional(readOnly = true)
    public Page<Technology> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Technology findById(Long id) {
        return repository.getOne(id);
    }

    @Transactional
    public Technology save(Technology technology) {
        return repository.save(technology);
    }

    @Transactional
    public void delete(Technology technology) {
        repository.delete(technology);
    }

    @Transactional(readOnly = true)
    public Technology findByName(String name) {
        return repository.findByName(name);
    }
}
