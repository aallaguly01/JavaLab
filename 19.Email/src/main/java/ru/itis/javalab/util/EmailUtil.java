package ru.itis.javalab.util;


public interface EmailUtil {
    void sendMail(String to, String subject, String from, String text);
}
