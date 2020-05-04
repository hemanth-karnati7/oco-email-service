package com.hkcoding.admin.service;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  @Autowired private JavaMailSender javaMailSender;

  @Value("${api.address}")
  private String apiAddress;

  public void processPlainMessage(String[] toAddresses, String subject, String textMessage) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(apiAddress);
    message.setTo(toAddresses);
    message.setSubject(subject);
    message.setText(textMessage);
    try {
      javaMailSender.send(message);
    } catch (Exception e) {
      LOGGER.error("Exeception occured while sending PLAIN message: {}", e.getMessage());
    }
  }

  public void processMimeMessage(
      String[] toAddresses, String subject, String textMessage, MultipartFile file) {
    MimeMessage message = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(apiAddress);
      helper.setTo(toAddresses);
      helper.setSubject(subject);
      helper.setText(textMessage, true);
      helper.addAttachment(file.getOriginalFilename(), file);
      javaMailSender.send(message);
    } catch (Exception e) {
      LOGGER.info("Exeception occured while sending MIME message: {}", e.getMessage());
    }
  }
}
