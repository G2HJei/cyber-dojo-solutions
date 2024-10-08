package xyz.zlatanov.cyberdojo.birthdayemails;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FileEmployeeRepositoryImpl implements EmployeeRepository {

	private final List<Employee>	employees;
	private final DateTimeFormatter	formatter	= DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public FileEmployeeRepositoryImpl(String storageLocation) {
		this.employees = loadEmployees(storageLocation);
	}

	public List<Employee> loadEmployees(String filePath) {
		final var data = Optional.ofNullable(FileEmployeeRepositoryImpl.class.getClassLoader().getResourceAsStream(filePath))
				.map(s -> {
					try {
						return new String(s.readAllBytes(), UTF_8);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				})
				.orElseThrow();

		List<Employee> result = new ArrayList<>();
		final var scanner = new Scanner(data);
		scanner.nextLine(); // skip header
		while (scanner.hasNextLine()) {
			final var line = scanner.nextLine();
			final var split = line.split(",");
			result.add(new Employee(split[1].trim(), split[0].trim(), LocalDate.parse(split[2].trim(), this.formatter), split[3].trim()));
		}
		scanner.close();
		return result;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.employees;
	}
}
