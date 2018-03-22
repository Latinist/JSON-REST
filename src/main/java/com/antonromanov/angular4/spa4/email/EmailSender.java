package com.antonromanov.angular4.spa4.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@PropertySource("classpath:application.properties") //read default 'SENDER' (my) email from application.properties
@Component
public class EmailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);

    @Value("${email.address}") // setting email from application.properties
    private String fromAddress;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailStatus sendPlainText(String to, String subject, String text) {
        return sendM(to, subject, text, false);
    }

    private EmailStatus sendM(String to, String subject, String text, Boolean isHtml) {

        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setFrom(fromAddress);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
            LOGGER.info("Send email '{}' to: {}", subject, to);
            return new EmailStatus(to, subject, text).success();

        } catch (MessagingException mailException) {
            mailException.printStackTrace();
            LOGGER.error(String.format("Problem with sending email to: {}, error message: {}", to, mailException.getMessage()));
            return new EmailStatus(to, subject, text).error(mailException.getMessage());
        }
    }

}
