package xyz.zlatanov.cyberdojo.birthdayemails;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FileEmployeeRepositoryImplTest {

	EmployeeRepository employeeRepository = new FileEmployeeRepositoryImpl("birthdayemails/employees.txt");

	@Test
	void shouldProvideEmployees() {
		final var employees = this.employeeRepository.getAllEmployees();
		assertTrue(employees.contains(new Employee("John", "Doe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com")));
		assertTrue(employees.contains(new Employee("Mary", "Ann", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com")));
	}
}