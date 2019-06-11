package com.sixet.skeleton.core.business;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.core.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * TechnologyBusiness class provides the business rules.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisan@ciandt.com">Get Trevisan</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TechnologyBusiness {

    private final TechnologyService service;

    public Page<Technology> findAll(Pageable pageable) throws NoContentException {
        Page<Technology> technologies = service.findAll(pageable);
        if (technologies.getContent().isEmpty()) {
            throw new NoContentException();
        }
        return technologies;
    }

    public Technology create(Technology technology) {
        return service.save(technology);
    }

    public Technology update(Long id, Technology technology)  {
        Technology tech = this.verifyIfTechnologyExists(id);
        technology.setId(tech.getId());
        tech.setName(technology.getName());
        tech.setActive(technology.isActive());
        return service.save(tech);
    }

    public void delete(Long id) {
        service.delete(this.verifyIfTechnologyExists(id));
    }

    public Technology findByName(String name) throws NoContentException {
        return service.findByName(name);
    }

    /**
     * Verify if the technology exists.
     * @param id
     * @return
     */
    private Technology verifyIfTechnologyExists(Long id) {
        Technology tech = service.findById(id);
        if (tech == null) {
            throw new NotFoundException(String.format("Technology %d not found.", id));
        }
        return tech;
    }
}
