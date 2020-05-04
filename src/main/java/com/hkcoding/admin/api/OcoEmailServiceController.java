package com.hkcoding.admin.api;

import com.hkcoding.admin.service.EmailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/email-service")
@Api(tags = {"oco-email-service"})
public class OcoEmailServiceController {

  @Autowired private EmailService service;

  @PostMapping(path = "/plain")
  public String postPlainMessage(
      @RequestHeader(value = "toAddresses", required = true) String[] toAddresses,
      @RequestHeader(value = "subject", required = false) String subject,
      @RequestBody(required = true) String textMessage) {
    service.processPlainMessage(toAddresses, subject, textMessage);
    return "PLAIN Mail Posted";
  }

  @PostMapping(path = "/mime")
  public String postMimeMessage(
      @RequestHeader(value = "toAddresses", required = true) String[] toAddresses,
      @RequestHeader(value = "subject", required = false) String subject,
      @RequestParam(value = "file", required = true) MultipartFile file,
      @RequestParam(value = "text", required = false) String textMessage) {
    service.processMimeMessage(toAddresses, subject, textMessage, file);
    return "MIME Mail Posted";
  }
}
