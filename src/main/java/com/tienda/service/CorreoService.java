package com.tienda.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    //Esta clase es generica y se puede usar en cualquier app springboot
   
    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoHtml(
            String para,
            String asunto,
            String contenidoHtml)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage(); //crear objeto de mensaje 
        MimeMessageHelper helper
                = new MimeMessageHelper(message,
                        true);
        helper.setTo(para);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true);
        mailSender.send(message);
    }
}
