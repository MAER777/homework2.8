package ServiceTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework.exception.DepartmentNotFoundException;
import pro.sky.homework.person.Employee;
import pro.sky.homework.service.DepartmentServiceImpl;
import pro.sky.homework.service.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    public static Stream<Arguments> getMaxSalaryDepTest() {
        return Stream.of(
                Arguments.of(1, 3_000),
                Arguments.of(2, 7_000)
        );
    }

    public static Stream<Arguments> getMinSalaryDepTest() {
        return Stream.of(
                Arguments.of(1,1_000),
                Arguments.of(2,5_000)
        );
    }
    public static Stream<Arguments> getMaxSumSalaryDepTest() {
        return Stream.of(
                Arguments.of(1,4_000),
                Arguments.of(2,12_000),
                Arguments.of(4,0)
        );
    }

    public static Stream<Arguments> showDepartmentTest() {
        return Stream.of(
                Arguments.of(
                        1,
                        List.of(
                                new Employee("Mark", "Ero", 1, 1_000),
                                new Employee("Masha", "Erdo", 1, 3_000)
                        )
                ),
                Arguments.of(
                        2,
                        List.of(
                                new Employee("Irog", "Era", 2, 7_000),
                                new Employee("Liza", "Iza", 2, 5_000)
                        )
                ),
                Arguments.of(4,
                        Collections.emptyList())
        );
    }
    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.getEmployees()).thenReturn(
                List.of(
                        new Employee("Mark", "Ero", 1, 1_000),
                        new Employee("Irog", "Era", 2, 7_000),
                        new Employee("Masha", "Erdo", 1, 3_000),
                        new Employee("Liza", "Iza", 2, 5_000))
        );
    }

    @ParameterizedTest
    @MethodSource("getMaxSalaryDepTest")
    public void getMaxSalaryDepTest(int departmentId, double expected) {
        Assertions.assertThat(departmentService.getMaxSalaryDep(departmentId))
                .isEqualTo(expected);
    }

    @Test
    public void maxNotFoundDepartmentSalaryTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(()-> departmentService.getMaxSalaryDep(4));
    }

    @ParameterizedTest
    @MethodSource("getMinSalaryDepTest")
    public void getMinSalaryDepTest(int departmentId, double expected) {
        Assertions.assertThat(departmentService.getMinSalaryDep(departmentId))
                .isEqualTo(expected);
    }

    @Test
    public void minNotFoundDepartmentSalaryTest() {
        Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                .isThrownBy(()-> departmentService.getMinSalaryDep(4));
    }

    @ParameterizedTest
    @MethodSource("getMaxSumSalaryDepTest")
    public void getMaxSumSalaryDepTest(int departmentId, double expected) {
        Assertions.assertThat(departmentService.getMaxSumSalaryDep(departmentId))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("showDepartmentTest")
    public void showDepartmentTest(int departmentId, List<Employee> expected) {
        Assertions.assertThat(departmentService.showDepart(departmentId))
                .containsExactlyInAnyOrderElementsOf(expected);
    }


    @Test
    public void showDepartmentAllTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Mark", "Ero", 1, 1_000),
                        new Employee("Masha", "Erdo", 1, 3_000)
                ),
                2,
                List.of(
                        new Employee("Irog", "Era", 2, 7_000),
                        new Employee("Liza", "Iza", 2, 5_000)
                )
        );
        Assertions.assertThat(departmentService.showAll())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }
}
