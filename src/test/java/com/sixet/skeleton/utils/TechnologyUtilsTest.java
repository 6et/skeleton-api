package com.sixet.skeleton.utils;

import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.web.resource.TechnologyResource;

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

    /** Crate a technology to use in the case tests.
     * @return
     */
    public static TechnologyResource createTechnologyResource() {
        return new TechnologyResource(1L, "java", true);
    }
}
