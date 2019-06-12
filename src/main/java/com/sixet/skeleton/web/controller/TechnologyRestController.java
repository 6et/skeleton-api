package com.sixet.skeleton.web.controller;

import com.sixet.skeleton.core.business.TechnologyBusiness;
import com.sixet.skeleton.core.domain.Technology;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.web.assembler.TechnologyAssembler;
import com.sixet.skeleton.web.resource.TechnologyResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/technologies", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechnologyRestController {

    private final TechnologyBusiness business;
    private final TechnologyAssembler assembler;

    @GetMapping
    @ApiOperation(value = "Return a page with all technologies.")
    public ResponseEntity<Page<TechnologyResource>> get(Pageable pageable) throws NoContentException {
        return ResponseEntity.ok(assembler.fromDomain(business.findAll(pageable)));
    }

    @PostMapping
    @ApiOperation(value = "Create a technology.")
    public ResponseEntity<Technology> create(@RequestBody TechnologyResource resource) {
        return ResponseEntity.ok(business.create(assembler.fromResource(resource)));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update a technology.", response = Technology.class)
    public ResponseEntity<Technology> update(@PathVariable Long id, @RequestBody TechnologyResource resource) {
        return ResponseEntity.ok(business.update(id, assembler.fromResource(resource)));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a technology.", response = Technology.class)
    public ResponseEntity<Technology> delete(@PathVariable Long id) throws NotFoundException {
        business.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @ApiOperation(value = "Get technology by name")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully retrieved list"),
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//    }
//    )
//    @ApiParam(value = "name", example = "java", required = true)
//    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<Technology> getTechnologyByName(@PathVariable String name) throws NoContentException {
//        log.info("Searching for " + name + " technology.");
//        Technology technology = business.findByName(name);
//        log.info("Result " + technology.toString());
//        return ResponseEntity.ok(technology);
//    }
}
