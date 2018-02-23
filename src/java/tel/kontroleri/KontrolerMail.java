package tel.kontroleri;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tel.dao.KorisnikDAO;
import tel.domain.Korisnik;

@Controller
@RequestMapping("/sendEmail.do")
public class KontrolerMail {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private KorisnikDAO korisnikDAO;

    @RequestMapping(method = RequestMethod.POST)
    public String doSendEmail(HttpServletRequest request, Model model) throws MessagingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Korisnik k = korisnikDAO.getKorisnik(userDetail.getUsername());
        int korisnikID = (int) request.getSession().getAttribute("id");

        Korisnik korisnik = korisnikDAO.getUserById(korisnikID);
        model.addAttribute("korisnik", korisnik);

        String recipientAddress = "shomi.telefonskiimenik@gmail.com";
        String name = request.getParameter("name");
        String emailForm = request.getParameter("mail");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper emailMessage = new MimeMessageHelper(mimeMessage, "UTF-8");

        emailMessage.setTo(recipientAddress);
        emailMessage.setSubject(subject);

        final String htmlContent = "<html><body style='border-width:1px;'>"
                + "<div style='padding: 35px;font-size: 22px;font-family: arial;font-style: italic;color: #8C9CEC;'>"
                + "<p style='color: #8C9CEC;'>"
                + name
                + "<p style='color: #8C9CEC;'>" + "sa e-mail adresom" + " " + "\"" + emailForm + "\"" + "</p>"
                + "<p style='color: #8C9CEC;'>" + "je uputio tekst i pitanje" + "</p>"
                + "<p style='color: red;'>" + "-----------------------------------------------------------------------" + "</p>"
                + "<p style='color: red;'>" + message + "</p>"
                + "<p style='color: red;'>" + "-----------------------------------------------------------------------" + "</p>"
                + "<br/>"
                + "<p style='color: #8C9CEC;'>" + "Poruka je poslata sa naloga" + "</p>"
                + "<p style='color: #8C9CEC;'>" + "Korisnik " + " "
                + korisnik.getIme()
                + " "
                + korisnik.getPrezime() + "," + "</p>"
                + "<p style='color: #8C9CEC;'>" + "i registrovanom e-mail adresom" + " " + "</p>"
                + "<p style='color: #8C9CEC;'>" + "\"" + korisnik.getEmail()
                + "\"." + "</p>";

        MimeMultipart multipart = new MimeMultipart("related");

        BodyPart messageBodyPart = new MimeBodyPart();

        multipart.addBodyPart(messageBodyPart);

        multipart.addBodyPart(messageBodyPart);
        messageBodyPart.setContent(htmlContent, "text/html; charset=UTF-8");
        multipart.addBodyPart(messageBodyPart);

        mimeMessage.setContent(multipart);

        this.mailSender.send(mimeMessage);

        return "Result";
    }

    @RequestMapping(value = {"/EmailForm"})
    public String emailForm(Model model) {

        return "EmailForm";
    }

}
