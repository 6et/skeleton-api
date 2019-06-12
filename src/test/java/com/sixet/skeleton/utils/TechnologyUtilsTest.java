package com.sixet.skeleton.utils;

import com.sixet.skeleton.core.domain.Technology;

/**
 *
 */
public class TechnologyUtilsTest {

    /** Crate a technology to use in the case tests.
     * @return
     */
    public static Technology createTechnology() {
        return new Technology(1L, "java", true);
    }
}
