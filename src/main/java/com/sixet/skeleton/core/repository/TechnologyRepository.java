package com.sixet.skeleton.core.repository;

import com.sixet.skeleton.core.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Technology findByName(String name);
}
