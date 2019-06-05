package com.sixet.skeleton.web.assembler;

import com.sixet.skeleton.core.exception.BusinessException;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * Assembler interface. If a class is responsible for transforming one object
 * into another it should implement this interface.
 *
 * @author mvidolin
 *
 * @param <D>
 *            the domain object type
 * @param <R>
 *            the resource object type
 */
public interface Assembler<D, R> {

    /**
     * Transforms an object of type R (resource) into an object of type D
     * (domain)
     *
     * @param resource
     *            object
     * @return domain object
     */
    D fromResource(R resource) throws BusinessException;

    /**
     * Transforms a collection of R (resource) objects into a collection of D
     * (domain) objects
     *
     * @param resources
     *            collection of resource
     * @return collection of domain
     */
    Iterable<D> fromResource(Iterable<R> resources) throws BusinessException ;

    /**
     * Transforms a collection of R (resource) objects into a collection of D
     * (domain) objects
     *
     * @param resources
     *            collection of resource
     * @return collection of domain
     */
    Collection<D> fromResource(Collection<R> resources) throws BusinessException;

    /**
     * Transforms an object of type D (domain) into an object of type R
     * (Resource)
     *
     * @param domain
     *            object
     * @return resource object
     */
    R fromDomain(D domain);

    /**
     * Transforms a collection of D (domain) objects into a collection of R
     * (resource) objects
     *
     * @param domains
     * 			collection of domains
     * @return collection of resources
     */
    Iterable<R> fromDomain(Iterable<D> domains);

    /**
     * Transforms a collection of D (domain) objects into a collection of R
     * (resource) objects
     *
     * @param domains
     * 			collection of domains
     * @return collection of resources
     */
    Collection<R> fromDomain(Collection<D> domains);

    /**
     * Transforms a collection of D (domain) objects into a collection of R
     * (resource) objects
     *
     * @param domains
     * 			collection of domains
     * @return collection of resources
     */
    List<R> fromDomain(List<D> domains);

    /**
     * Transforms a Page of D (domain) objects into a page of R
     * (resource) objects
     *
     * @param domain
     * 			Page of domains
     * @return Page of resources
     */
    Page<R> fromDomain(Page<D> domain);

}