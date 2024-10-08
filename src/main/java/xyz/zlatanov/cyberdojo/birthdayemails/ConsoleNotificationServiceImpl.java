package xyz.zlatanov.cyberdojo.birthdayemails;

public class ConsoleNotificationServiceImpl implements NotificationService {

	@Override
	public void notify(Employee employee, String subject, String message) {
		System.out.println(String.format("""
				Notifying %s
				Subject: %s
				%s
				""", employee, subject, message));
	}
}
