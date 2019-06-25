package com.sixet.skeleton.web.controller;

import com.sixet.skeleton.core.business.TechnologyBusiness;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.handler.StandardErrorHandler;
import com.sixet.skeleton.web.assembler.TechnologyAssembler;
import com.sixet.skeleton.web.resource.TechnologyResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;

/**
 * TechnologyRestController class provides the access to the endpoints of the application.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
@Slf4j
@RestController
@Api(value = "Technology", tags = "Technology")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/technologies")
public class TechnologyRestController {

    private final TechnologyBusiness business;
    private final TechnologyAssembler assembler;

    @ApiOperation(value = "Return an object Page with all technologies." )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list", response = TechnologyResource.class)
    })
    @GetMapping
    public ResponseEntity<Page<TechnologyResource>> findAll(Pageable pageable) {
        return ResponseEntity.ok(assembler.fromDomain(business.findAll(pageable)));
    }

    @ApiOperation(value = "Create a technology.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully technology created.", response = TechnologyResource.class),
        @ApiResponse(
                code = 409,
                message = "The resource you were trying to create was indicates generic business exceptions.",
                response = StandardErrorHandler.class)
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TechnologyResource> create(@RequestBody TechnologyResource res) throws BusinessException {
        TechnologyResource resource = assembler.fromDomain(business.create(assembler.fromResource(res)));
        return ResponseEntity.created(URI.create("/technologies/" + resource.getId())).body(resource);
    }

    @ApiOperation(value = "Update a technology.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully technology updated.", response = TechnologyResource.class),
        @ApiResponse(
                code = 404,
                message = "The resource you were trying to reach was not found",
                response = StandardErrorHandler.class)
    })
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<TechnologyResource> update(@PathVariable Long id, @RequestBody TechnologyResource resource) {
        return ResponseEntity.ok(assembler.fromDomain(business.update(id, assembler.fromResource(resource))));
    }

    @ApiOperation(value = "Delete a technology.")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Successfully technology deleted."),
        @ApiResponse(
                code = 404,
                message = "The resource you were trying to reach was not found",
                response = StandardErrorHandler.class)
    })
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        business.delete(id);
    }
}
