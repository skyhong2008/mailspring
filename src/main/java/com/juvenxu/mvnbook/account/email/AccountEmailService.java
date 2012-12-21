package com.juvenxu.mvnbook.account.email;

public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText)
            throws AccountEmailException;

    void sendMail(String to, String cc, String subject, String htmlText)
            throws AccountEmailException;
}
