package com.projetospring.apijava.service;

import com.projetospring.apijava.domain.Cliente;
import com.projetospring.apijava.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);
    void sendEmail(SimpleMailMessage msg);
    void sendNewPasswordEmail(Cliente cliente, String newPassword);
}
