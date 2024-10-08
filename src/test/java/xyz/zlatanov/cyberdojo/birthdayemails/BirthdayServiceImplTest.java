package xyz.zlatanov.cyberdojo.birthdayemails;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

class BirthdayServiceImplTest {

	EmployeeRepository		employeeRepository	= mock(EmployeeRepository.class);
	NotificationService		notificationService	= mock(NotificationService.class);
	BirthdayService			birthdayService		= new BirthdayServiceImpl(employeeRepository, notificationService);

	static final Employee	DWAYNE				= new Employee("Dwayne", "Hicks", LocalDate.of(2151, 12, 12), "john@sky.net");
	static final Employee	ELLEN				= new Employee("Ellen", "Ripley", LocalDate.of(2092, 1, 7), "ellen@weyland-yutani.com");
	static final Employee	LEAP_JACK			= new Employee("Jack", "Ripper", LocalDate.of(1888, 2, 29), "ellen@weyland-yutani.com");

	@BeforeEach
	void setup() {
		when(employeeRepository.getAllEmployees()).thenReturn(List.of(DWAYNE, ELLEN, LEAP_JACK));
	}

	@Test
	void shouldNotifyOnlyEmployeesWithBirthday() {
		birthdayService.sendGreetings(LocalDate.of(2599, 12, 12));
		verify(notificationService, times(1)).notify(any(), any(), any());
		verify(notificationService, times(1))
				.notify(DWAYNE, "Happy birthday!", "Happy birthday, dear Dwayne!");
	}

	@Test
	void shouldNotifyOn29FebBornOnLeapYear() {
		birthdayService.sendGreetings(LocalDate.of(1908, 2, 29));
		verify(notificationService, times(1)).notify(any(), any(), any());
		verify(notificationService, times(1))
				.notify(LEAP_JACK, "Happy birthday!", "Happy birthday, dear Jack!");
	}

	@Test
	void shouldNotifyOn28FebBornOnNormalYear() {
		birthdayService.sendGreetings(LocalDate.of(2011, 2, 28));
		verify(notificationService, times(1)).notify(any(), any(), any());
		verify(notificationService, times(1))
				.notify(LEAP_JACK, "Happy birthday!", "Happy birthday, dear Jack!");
	}

}