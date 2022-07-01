package com.learning.core.schedulers;

public interface EmailService {

	void sendEmail(
            String toEmail,
            String ccEmail,
            String fromEmail,
            String subject,
            String content
    );
}
