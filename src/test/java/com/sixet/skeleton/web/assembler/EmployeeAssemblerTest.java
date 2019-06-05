package com.sixet.skeleton.web.assembler;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link EmployeeAssembler}'s test class.
 */
@RunWith(SpringRunner.class)
public class EmployeeAssemblerTest extends BaseAssemblerTest {

//  @Autowired
//  private EmployeeAssembler assembler;
//
//  /**
//   * METHOD: fromDomain()
//   * RULE: Must be convert with success the resource to the domain.
//   * CASE: Convert all fields from domain to resource.
//   */
//  @Test
//  public void fromDomain_withOneObject_mustConvert() {
//    Employee domain = new Employee();
//    domain.setId(1L);
//    domain.setLogin("login");
//    domain.setStatus("A");
//
//    EmployeeResource resource = this.assembler.fromDomain(domain);
//
//    assertEquals(domain.getId(), resource.getId());
//    assertEquals(domain.getLogin(), resource.getLogin());
//  }
//
//  /**
//   * METHOD: fromResource()
//   * RULE: Must be convert with success the resource to the domain.
//   * CASE: Convert all the fields of the resource to the domain.
//   */
//  @Test
//  public void fromResource_withOneObject_mustConvert() {
//    EmployeeResource resource = new EmployeeResource();
//    resource.setId(1L);
//    resource.setLogin("login");
//
//    Employee domain = this.assembler.fromResource(resource);
//
//    assertEquals(resource.getId(), domain.getId());
//    assertEquals(resource.getLogin(), domain.getLogin());
//  }
//
//  /**
//   * METHOD: fromDomain()
//   * RULE: Must be convert with success two domains to two resources.
//   * CASE: Must be return a list with the converted iten.
//   */
//  @Test
//  public void fromDomain_withTwoObjects_mustConvert() {
//    Employee domain1 = new Employee();
//    domain1.setId(1L);
//    domain1.setLogin("login");
//    domain1.setStatus("A");
//
//    Employee domain2 = new Employee();
//    domain2.setId(2L);
//    domain2.setLogin("login1");
//    domain2.setStatus("I");
//
//    Collection<EmployeeResource> resources = this.assembler.fromDomain(Arrays.asList(domain1, domain2));
//
//    assertEquals(resources.size(), 2);
//  }

}
