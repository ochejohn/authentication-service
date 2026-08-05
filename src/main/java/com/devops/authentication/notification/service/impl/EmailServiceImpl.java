package com.devops.authentication.notification.service.impl;

import com.devops.authentication.notification.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;


    @Value("${spring.mail.username:no-reply@authentication.com}")
    private String senderEmail;



    @Override
    public void sendVerificationEmail(
            String recipientEmail,
            String firstName,
            String verificationLink
    ) {

        Context context = new Context();

        context.setVariable(
                "firstName",
                firstName
        );

        context.setVariable(
                "verificationLink",
                verificationLink
        );


        sendEmail(
                recipientEmail,
                "Verify Your Email Address",
                "email/verification-email",
                context
        );
    }




    @Override
    public void sendPasswordResetEmail(
            String recipientEmail,
            String firstName,
            String resetLink
    ) {

        Context context = new Context();

        context.setVariable(
                "firstName",
                firstName
        );

        context.setVariable(
                "resetPasswordLink",
                resetLink
        );


        sendEmail(
                recipientEmail,
                "Reset Your Password",
                "email/forgot-password",
                context
        );
    }




    private void sendEmail(
            String recipientEmail,
            String subject,
            String template,
            Context context
    ) {

        try {

            String htmlContent =
                    templateEngine.process(
                            template,
                            context
                    );


            MimeMessage message =
                    mailSender.createMimeMessage();


            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true,
                            "UTF-8"
                    );


            helper.setFrom(senderEmail);

            helper.setTo(recipientEmail);

            helper.setSubject(subject);


            helper.setText(
                    htmlContent,
                    true
            );


            mailSender.send(message);


            log.info(
                    "Email sent successfully to {}",
                    recipientEmail
            );


        } catch (MessagingException | MailException exception) {


            log.error(
                    "Failed to send email to {}",
                    recipientEmail,
                    exception
            );


            throw new RuntimeException(
                    "Unable to send email.",
                    exception
            );
        }
    }

}