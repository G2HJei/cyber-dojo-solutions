package xyz.zlatanov.cyberdojo.birthdayemails;

import java.time.LocalDate;

import static java.time.Month.FEBRUARY;

public class BirthdayServiceImpl implements BirthdayService {

	private final EmployeeRepository	employeeRepository;
	private final NotificationService	notificationService;

	public BirthdayServiceImpl(EmployeeRepository employeeRepository, NotificationService notificationService) {
		this.employeeRepository = employeeRepository;
		this.notificationService = notificationService;
	}

	@Override
	public void sendGreetings(LocalDate birthday) {
		employeeRepository.getAllEmployees().stream()
				.filter(e -> eligibleForGreetings(birthday, e))
				.forEach(e -> notificationService.notify(e, "Happy birthday!",
						String.format("Happy birthday, dear %s!", e.firstName())));
	}

	private static boolean eligibleForGreetings(LocalDate today, Employee e) {
		final var employeeBirthdate = e.birthDate();
		return isBornOn(today, employeeBirthdate) || isEligibleDueToLeapYear(today, employeeBirthdate);
	}

	private static boolean isBornOn(LocalDate today, LocalDate employeeBirthdate) {
		return today.getDayOfMonth() == employeeBirthdate.getDayOfMonth() &&
				today.getMonth() == employeeBirthdate.getMonth();
	}

	private static boolean isEligibleDueToLeapYear(LocalDate today, LocalDate employeeBirthdate) {
		return today.getMonth() == FEBRUARY
				&& today.getDayOfMonth() == 28
				&& employeeBirthdate.getDayOfMonth() == 29
				&& employeeBirthdate.getMonth() == FEBRUARY;
	}
}
