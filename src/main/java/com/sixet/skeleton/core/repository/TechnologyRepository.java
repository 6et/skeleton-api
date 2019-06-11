package com.sixet.skeleton.core.repository;

import com.sixet.skeleton.core.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TechnologyRepository interface provides the access functionalities to the database to Technology entity.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Technology findByName(String name);
}
