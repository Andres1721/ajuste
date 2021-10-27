package edu.sena.utilidad.Ajuste3;

import edu.sena.entity.ajuste3.Ventas;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class MailMasivo {

    public static void detalleOrd(Ventas ordenesVenta, Ventas enviarVentas) {
                final String usuario = "hibissoft@gmail.com";
        final String clave = "adsi2141449";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });

        try {
            MimeMessage mensage = new MimeMessage(session);
            mensage.setFrom(new InternetAddress(usuario));
            mensage.addRecipient(Message.RecipientType.TO, new InternetAddress(enviarVentas.getCorreo()));
            mensage.setSubject("Sus pedido es:");
            mensage.setContent("<center> "
                    + "<img src='https://i.imgur.com/fPeKUxG.jpg' width='200px' height='200px' >"
                    + "</center>"
                    + "<br/>"
                    + "<h1> Hola:" + ordenesVenta.getNombres() + " </h1>"
                    + "<br/>"
                    + "<h4>Los detalles de su pedido son los siguientes:</h4> "
                    + "<table style = 'width:100%'>"
                    + "<tr>"
                    + "<td>Número de orden</td>"
                    + "<td>Fecha</td>"
                    + "<td>Valor unitario</td>"
                    + "<td>Detalle</td>"
                    + "<td>Cantidad</td>"
                    + "<td>Valor Total</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>" + ordenesVenta.getDireccion() + "</td>"
                    + "<td>" + ordenesVenta.getPrecioTotal() + "</td>"
                    + "</tr>"
                    + "</table >",
                    "text/html");
            Transport.send(mensage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
