package ru.itis.javalab.util;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}
