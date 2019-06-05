package com.sixet.skeleton.web.assembler;

import com.sixet.skeleton.core.exception.IllegalAssemblerConvertionException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Generic class responsible for copying the matching resource from domain (D) to
 * resource (R) classes. The only require is to the both classes have the same
 * param names
 *
 * @author mvidolin
 *
 * @param <D>
 * @param <R>
 */
public class GenericAssembler<D, R> implements Assembler<D, R> {

    private Class<D> domainClass;

    private Class<R> resourceClass;

    /**
     * Assembler Constructor. You must pass an instance of the target class
     * (this could be an empty instance).
     */
    public GenericAssembler(Class<D> domainClass, Class<R> resourceClass) {
        this.domainClass = domainClass;
        this.resourceClass = resourceClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D fromResource(final R resource) {
        if (resource == null) {
            return null;
        }
        try {
            D domain = domainClass.newInstance();
            BeanUtils.copyProperties(resource, domain);
            return domain;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalAssemblerConvertionException("Could not create new instance of the target class.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<D> fromResource(Iterable<R> resources) {
        if (resources == null) {
            return null;
        }
        Collection<D> domains = new ArrayList<>();
        for (R resource : resources) {
            domains.add(this.fromResource(resource));
        }
        return domains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<D> fromResource(Collection<R> resources) {
        if (resources == null) {
            return new ArrayList<>();
        }
        Collection<D> domains = new ArrayList<>();
        for (R resource : resources) {
            domains.add(this.fromResource(resource));
        }
        return domains;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R fromDomain(D domain) {
        if (domain == null) {
            return null;
        }
        try {
            R resource = resourceClass.newInstance();
            BeanUtils.copyProperties(domain, resource);
            return resource;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalAssemblerConvertionException("Could not create new instance of the target class.", e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<R> fromDomain(Iterable<D> domains) {
        if (domains == null) {
            return null;
        }
        Collection<R> resources = new ArrayList<>();
        for (D domain : domains) {
            resources.add(this.fromDomain(domain));
        }
        return resources;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<R> fromDomain(Page<D> domain) {
        if (domain == null) {
            return null;
        }
        return new PageImpl<>(this.fromDomain(domain.getContent()),
                domain.getPageable(), domain.getTotalElements());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<R> fromDomain(Collection<D> domains) {
        if (domains == null) {
            return new ArrayList<>();
        }
        Collection<R> resources = new ArrayList<>();
        for (D domain : domains) {
            resources.add(this.fromDomain(domain));
        }
        return resources;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<R> fromDomain(List<D> domains) {
        if (domains == null) {
            return new ArrayList<>();
        }
        List<R> resources = new ArrayList<>();
        for (D domain : domains) {
            resources.add(this.fromDomain(domain));
        }
        return resources;
    }

}

