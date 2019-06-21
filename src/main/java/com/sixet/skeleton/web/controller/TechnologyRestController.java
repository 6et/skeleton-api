package com.sixet.skeleton.web.controller;

import com.sixet.skeleton.core.business.TechnologyBusiness;
import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NoContentException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = TechnologyResource.class),
            @ApiResponse(code = 204, message = "No content was found in the resource you were trying to reach",
                         response = StandardErrorHandler.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach was not found")})
    @ApiOperation(value = "Return an object Page with all technologies.", response = Page.class)
    public ResponseEntity<Page<TechnologyResource>> get(Pageable pageable) throws NoContentException {
        return ResponseEntity.ok(assembler.fromDomain(business.findAll(pageable)));
    }

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = TechnologyResource.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach was not found")})
    @ApiOperation(value = "Create a technology.")
    public ResponseEntity<TechnologyResource> create(@RequestBody TechnologyResource resource) throws BusinessException {
        return ResponseEntity.ok(assembler.fromDomain(business.create(assembler.fromResource(resource))));
    }

    @PutMapping(value = "/update/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = TechnologyResource.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach was not found")})
    @ApiOperation(value = "Update a technology.", response = TechnologyResource.class)
    public ResponseEntity<TechnologyResource> update(@PathVariable Long id, @RequestBody TechnologyResource resource) {
        return ResponseEntity.ok(assembler.fromDomain(business.update(id, assembler.fromResource(resource))));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = TechnologyResource.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach was not found")})
    @ApiOperation(value = "Delete a technology.", response = TechnologyResource.class)
    public ResponseEntity<TechnologyResource> delete(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.fromDomain(business.delete(id)));
    }
}
