package xyz.zlatanov.cyberdojo.birthdayemails;

public interface NotificationService {

    void notify(Employee employee, String subject, String message);
}
