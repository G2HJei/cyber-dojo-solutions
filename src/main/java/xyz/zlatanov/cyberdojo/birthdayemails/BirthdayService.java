package xyz.zlatanov.cyberdojo.birthdayemails;

import java.time.LocalDate;

public interface BirthdayService {

    void sendGreetings(LocalDate birthday);
}
