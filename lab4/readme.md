Ex1

a)
EmployeeRestControllerIT Line 51:
    assertThat(found).extracting(Employee::getName).containsOnly("bob");

EmployeeRepositorTest Line 65:
    assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());


b) 
    In test class EmployeeService_UnitTeste e EmployeeController_WithMockServiceIT we mock the behavior of the repository to avoid the use of the database

c)
  - The Mockito.mock() method allows us to create a mock object of a class or an interface.  
    Then, we can use the mock to stub return values for its methods and verify if they were called.
  - We can use the @MockBean to add mock objects to the Spring application context. The mock will replace any existing bean of the same type in the application context.
    If no bean of the same type is defined, a new one will be added. This annotation is useful in integration tests where a particular bean – for example, an external service – needs to be mocked.

  Reference: 
  https://www.baeldung.com/java-spring-mockito-mock-mockbean

d) 
    The file "application-integrationtest.properties" is going to do the same as the "application.properties" file, but as the name says for integration tests.
    We can use this file to write some properties to access a real database to do the tests per example with the help of the annotation (@TestPropertySource(locations = "application-integrationtest.properties")