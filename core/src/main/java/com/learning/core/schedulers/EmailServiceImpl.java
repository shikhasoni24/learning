package com.learning.core.schedulers;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EmailService.class, immediate=true)
public class EmailServiceImpl implements EmailService {

	protected static final String SERVICE_NAME = "Email Service";
	private static final String TAG = EmailServiceImpl.class.getSimpleName();
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Reference
	MessageGatewayService messageGatewayService;

	@Override
    public void sendEmail(String toEmail, String ccEmail, String fromEmail, String subject, String content) {
        try {
            // Setting up the email message
            Email email = new SimpleEmail();
            // Get the details to send email
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(toEmail);
            email.addCc(ccEmail);
            email.setFrom(fromEmail);
            // Inject the message gateway service and send email
            MessageGateway<Email> messageGateway = messageGatewayService.getGateway(Email.class);
            // Send the email
            messageGateway.send(email);
        } catch (EmailException e) {
            LOGGER.error("{}: exception occurred: {}", TAG, e.getMessage());
        }
    }
}
