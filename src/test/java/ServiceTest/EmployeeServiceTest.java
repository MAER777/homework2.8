package ServiceTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.homework.exception.*;
import pro.sky.homework.person.Employee;
import pro.sky.homework.service.EmployeeServiceImpl;
import pro.sky.homework.service.ValidatorService;

import java.util.stream.Stream;

public class EmployeeServiceTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new ValidatorService());

    public static Stream<Arguments> addIncorrectEmployeeTest() {
        return Stream.of(
                Arguments.of("Ivan44"),
                Arguments.of("Lima33"),
                Arguments.of("Lima!3")
        );
    }

    public static Stream<Arguments> addIncorrectSurNameEmployeeTest() {
        return Stream.of(
                Arguments.of("Ero!"),
                Arguments.of("Limes1"),
                Arguments.of("Limase!3")
        );
    }

    @BeforeEach
    public void setEmployeeService() {
        employeeService.addEmployee("Mark", "Ero", 1, 1_000);
        employeeService.addEmployee("Alik", "Markov", 2, 5_000);
        employeeService.addEmployee("Bazik", "Trushin", 3, 10_000);
    }

    @AfterEach
    public void afterEach() {
        employeeService.getEmployees()
                .forEach(employee -> employeeService.delEmployee(employee.getName(), employee.getSurName(), employee.getDepartment(), employee.getSalary()));
    }

    @Test
    public void addEmployeeTest() {
        int beforeCount = employeeService.getEmployees().size();
        Employee expected = new Employee("Markus", "Ero", 1, 1_000);

        Assertions.assertThat(employeeService.addEmployee("Markus", "Ero", 1, 1_000))
                .isEqualTo(expected)
                .isIn(employeeService.getEmployees());
        Assertions.assertThat(employeeService.getEmployees()).hasSize(beforeCount + 1);
        Assertions.assertThat(employeeService.infoEmployee("Markus", "Ero", 1, 1_000)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("addIncorrectEmployeeTest")
    public void addIncorrectEmployeeTest(String incorrectName) {
        Assertions.assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.addEmployee(incorrectName, "Ero", 1, 1_000));
    }

    @ParameterizedTest
    @MethodSource("addIncorrectSurNameEmployeeTest")
    public void addIncorrectSurnameEmployeeTest(String incorrectSurName) {
        Assertions.assertThatExceptionOfType(IncorrectSurNameException.class)
                .isThrownBy(() -> employeeService.addEmployee("Mark", incorrectSurName, 1, 1_000));
    }

    @Test
    public void addAlreadyExistTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee("Mark", "Ero", 1, 1_000));
    }

    @Test
    public void addFullListExistTest() {
        Stream.iterate(1, i -> i + 1)
                .limit(17)
                .map(number -> new Employee(
                        "Mark" + ((char) ('a' + number)),
                        "Ero" + ((char) ('a' + number)),
                        1,
                        1_000)
                )
                .forEach(employee ->
                        employeeService.addEmployee(
                                employee.getName(),
                                employee.getSurName(),
                                employee.getDepartment(),
                                employee.getSalary()
                        )
                );

        Assertions.assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.addEmployee("Marks", "Erok", 1, 1_000));
    }

    @Test
    public void dellEmployeeTest() {
        int beforeCount = employeeService.getEmployees().size();
        Employee expected = new Employee("Mark", "Ero", 1, 1_000);

        Assertions.assertThat(employeeService.delEmployee("Mark", "Ero", 1, 1_000))
                .isEqualTo(expected)
                .isNotIn(employeeService.getEmployees());
        Assertions.assertThat(employeeService.getEmployees()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.infoEmployee("Mark", "Ero", 1, 1_000));
    }

    @Test
    public void dellNotEmployeeTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.infoEmployee("Markus", "Ivanos", 1, 1_000));
    }

    @Test
    public void infoEmployeeTest() {
        int beforeCount = employeeService.getEmployees().size();
        Employee expected = new Employee("Mark", "Ero", 1, 1_000);

        Assertions.assertThat(employeeService.infoEmployee("Mark", "Ero", 1, 1_000))
                .isEqualTo(expected)
                .isIn(employeeService.getEmployees());
        Assertions.assertThat(employeeService.getEmployees()).hasSize(beforeCount);
    }

    @Test
    public void getEmployeeTest() {
        Assertions.assertThat(employeeService.getEmployees())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Employee("Mark", "Ero", 1, 1_000),
                        new Employee("Alik", "Markov", 2, 5_000),
                        new Employee("Bazik", "Trushin", 3, 10_000)
                );
    }
}
