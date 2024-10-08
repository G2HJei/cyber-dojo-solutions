package xyz.zlatanov.cyberdojo.birthdayemails;

import java.time.LocalDate;

public record Employee(String firstName, String lastName, LocalDate birthDate, String email) {
}
