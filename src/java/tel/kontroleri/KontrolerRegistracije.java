package tel.kontroleri;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tel.dao.KorisnikDAO;
import tel.domain.Korisnik;
import tel.model.KorisnikModel;
import tel.service.ServisKorisnika;
import java.security.SecureRandom;
import java.math.BigInteger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.mail.javamail.MimeMessageHelper;

@Controller
public class KontrolerRegistracije {

    @Autowired
    private ServisKorisnika servisKorisnika;

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    SecureRandom random = new SecureRandom();

    @RequestMapping(value = {"/"})
    public String index() {

        return "index";
    }

    @RequestMapping(value = {"/EmailForm"})
    public String emailForm() {

        return "EmailForm";
    }

    @RequestMapping(value = {"/mapa"})
    public String mapa(Model model) {

        return "mapa";
    }

    @RequestMapping(value = {"/header"})
    public String mapa2(Model model) {

        return "header";
    }

    @RequestMapping(value = {"/header1"})
    public String head(Model model) {

        return "header1";
    }

    @RequestMapping("/registracija")
    public String getRegister(Model model) {
        model.addAttribute("noviKorisnik", new KorisnikModel());
        model.addAttribute("datum", new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

        return "registracija";
    }

    @RequestMapping(value = "/registracija", method = RequestMethod.POST)
    public String postRegister(@Valid @ModelAttribute("noviKorisnik") KorisnikModel korisnik, BindingResult rezultatRegistracije, ModelMap model, HttpServletRequest request, Locale locale) throws MessagingException, IOException {

        if (rezultatRegistracije.hasErrors()) {
            return "registracija";
        }

        String usernameError = "";
        List<KorisnikModel> sviKorisnici = servisKorisnika.sviKorisnici();

        for (KorisnikModel existingUser : sviKorisnici) {
            if (existingUser.getUsername().equals(korisnik.getUsername())) {
                usernameError = messageSource.getMessage("label.registerError_1", new Object[]{korisnik.getUsername()}, locale);
                model.addAttribute("usernameError", usernameError);
                return "/registracija";
            }
        }

        String emailError = "";

        for (KorisnikModel existingUser : sviKorisnici) {
            if (existingUser.getEmail().equals(korisnik.getEmail())) {
                emailError = messageSource.getMessage("label.registerError_2", new Object[]{korisnik.getEmail()}, locale);
                model.addAttribute("emailError", emailError);
                return "/registracija";
            }
        }

        String passwordDontMatch = "";

        if (!korisnik.getPassword().equals(korisnik.getRetypePassword())) {
            passwordDontMatch = messageSource.getMessage("label.registerError_3", new Object[]{" !"}, locale);
            model.addAttribute("passwordDontMatch", passwordDontMatch);
            return "/registracija";
        }

        String recipientAddress = korisnik.getEmail();
        String subject = messageSource.getMessage("label.activatingLink", new Object[]{" link"}, locale);
        String path = request.getContextPath();
        String url = "http://localhost:8080" + path + "/aktivacija?username=" + korisnik.getUsername();

        String dear = messageSource.getMessage("label.mail_1", new Object[]{","}, locale);
        String code = messageSource.getMessage("label.mail_2", new Object[]{":"}, locale);
        String code2 = messageSource.getMessage("label.mail_2_2", new Object[]{"!"}, locale);
        String pass = messageSource.getMessage("label.mail_3", new Object[]{":"}, locale);
        String text = messageSource.getMessage("label.mail_4", new Object[]{"."}, locale);
        String text2 = messageSource.getMessage("label.mail_4_2", new Object[]{"."}, locale);
        String text3 = messageSource.getMessage("label.mail_5", new Object[]{"."}, locale);
        String text4 = messageSource.getMessage("label.mail_6", new Object[]{"."}, locale);
        System.out.println(url);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject(subject);

        message.setTo(recipientAddress);

        final String htmlContent = "<html><body style='border-width:1px;background-image: url(cid:image); background-repeat: no-repeat;'>"
                + "<div style='padding: 35px;font-size: 22px;font-family: arial;font-style: italic;color: #8C9CEC;'>"
                + "<p style='color: #8C9CEC;'>" + dear + " "
                + korisnik.getIme()
                + " "
                + korisnik.getPrezime() + "," + "</p>"
                + "<p style='color: #8C9CEC;'>" + code + " " + "</p>"
                + "<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
                + "<tr><td>"
                + "<table border='0' cellspacing='0' cellpadding='0'>"
                + "<tr>"
                + "<td align='center' style='-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;' bgcolor='#e9703e'><a href='" + url + "' target='_blank' style='font-size: 16px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; text-decoration: none; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; padding: 12px 18px; border: 1px solid #e9703e; display: inline-block;'>" + code2 + "</a></td>"
                + "</tr></table></td></tr></table>"
                + "<br/>"
                + "<p style='color: #8C9CEC;'>" + pass + " \""
                + korisnik.getPassword()
                + "\"." + "</p><br/>"
                + "<p style='color: #8C9CEC;'>" + text + "</p>"
                + "<p style='color: #8C9CEC;'>" + text2 + "</p>"
                + "<br/>"
                + "<p style='color: #8C9CEC;'>" + text3 + "</p>"
                + "<br/>"
                + "<p style='color: #8C9CEC;'>" + text4 + "</p>"
                + "</div></body></html>";

        MimeMultipart multipart = new MimeMultipart("related");

        BodyPart messageBodyPart = new MimeBodyPart();

        multipart.addBodyPart(messageBodyPart);

        StringBuffer sb = new StringBuffer();
        sb.append("<HTML><BODY style='border-width:0px;background:#8C9CEC;'>");
        sb.append("<H2 style='font-weight:bold;font-size:16px;color:white'><span>");
        sb.append(dear);
        sb.append(" ");
        sb.append(korisnik.getIme());
        sb.append(" ");
        sb.append(korisnik.getPrezime());
        sb.append("</span>");
        sb.append(",</H2>");
        sb.append("<img src=\"cid:image\">");
        sb.append("<BR/>");
        sb.append("<span style='color:white'>");
        sb.append(code);
        sb.append(" ");
        sb.append(url);
        sb.append("</span>");
        sb.append("<BR/>");
        sb.append("<BR/>");
        sb.append("<span style='color:white'>");
        sb.append(text);
        sb.append("</span>");
        sb.append("<BR/>");
        sb.append("<BR/>");
        sb.append("<span style='color:white'>");
        sb.append(text2);
        sb.append("</span>");
        sb.append("<BR/>");
        sb.append("<BR/>");
        sb.append("<span style='color:white'>");
        sb.append(text3);
        sb.append("</span>");
        sb.append("</BODY></HTML>");
        messageBodyPart.setContent(htmlContent, "text/html; charset=UTF-8");

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource("C:\\Users\\Korisnik\\Documents\\NetBeansProjects\\TelefonskiImenikApp_5_11_24_5\\web\\resources\\images\\phoneBook.png");
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(messageBodyPart);
        mimeMessage.setContent(multipart);

        this.mailSender.send(mimeMessage);

        String mailMsg = messageSource.getMessage("label.mailMsg", new Object[]{" !"}, locale);
        model.addAttribute("mailMsg", mailMsg);
        getServisKorisnika().registracijaKorisnika(korisnik);

        return "index";
    }

    @RequestMapping(value = {"/aktivacija"})
    public String aktivacija(Model model, @RequestParam String username, Locale locale) {

        Korisnik korisnik = korisnikDAO.getKorisnik(username);

        KorisnikModel korisnikModel = new KorisnikModel();
        korisnikModel.setId(korisnik.getId());
        korisnikModel.setIme(korisnik.getIme());
        korisnikModel.setPrezime(korisnik.getPrezime());
        korisnikModel.setEmail(korisnik.getEmail());
        korisnikModel.setDatumKreiranja(korisnik.getDatumKreiranja());
        korisnikModel.setEnabled(korisnik.isEnabled());
        korisnikModel.setPassword(korisnik.getPassword());
        korisnikModel.setUsername(korisnik.getUsername());

        korisnikModel.setEnabled(true);

        getServisKorisnika().azuriranje(korisnikModel);

        String mailMsgActivatingOk = messageSource.getMessage("label.mailMsgActivatingOk", new Object[]{" !"}, locale);
        model.addAttribute("mailMsgActivatingOk", mailMsgActivatingOk);

        return "index";
    }

    @RequestMapping("/promenaLozinke")
    public String promenaLozinke(Model model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Korisnik k = korisnikDAO.getKorisnik(userDetail.getUsername());

        model.addAttribute("novaLozinka", k);

        return "promenaLozinke";
    }

    @RequestMapping(value = "/promenaLozinke", method = RequestMethod.POST)
    public String postPromenaLozinke(@Valid @ModelAttribute("novaLozinka") KorisnikModel korisnikModel,
            BindingResult rezultatAzuriranja, ModelMap model/*, HttpServletRequest request*/, Locale locale, HttpServletRequest request) {

        if (rezultatAzuriranja.hasErrors()) {
            return "greska";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Korisnik k = korisnikDAO.getKorisnik(userDetail.getUsername());
        int korisnikID = (int) request.getSession().getAttribute("id");

        Korisnik korisnikZaPromenu = korisnikDAO.getUserById(korisnikID);

        String oldPassword = "";

        String newPasswordDontMatch = "";

        if (!korisnikModel.getNewPassword().equals(korisnikModel.getRetypePassword())) {
            System.out.println("korisnikModel.getNewPassword() " + korisnikModel.getNewPassword());
            System.out.println("korisnikModel.getRetypePassword() " + korisnikModel.getRetypePassword());
            newPasswordDontMatch = messageSource.getMessage("label.registerError_3", new Object[]{" !"}, locale);

            model.addAttribute("newPasswordDontMatch", newPasswordDontMatch);
            System.out.println("-------------------- ");
            System.out.println("korisnikZaPromenu.getPassword() " + korisnikZaPromenu.getPassword());
            return "/promenaLozinke";
        }
        korisnikModel.setPassword(passwordEncoder.encode(korisnikModel.getNewPassword()));
        korisnikModel.setId(korisnikZaPromenu.getId());
        korisnikModel.setUsername(korisnikZaPromenu.getUsername());
        korisnikModel.setEmail(korisnikZaPromenu.getEmail());
        korisnikModel.setDatumKreiranja(korisnikZaPromenu.getDatumKreiranja());
        korisnikModel.setEnabled(korisnikZaPromenu.isEnabled());
        korisnikModel.setIme(korisnikZaPromenu.getIme());
        korisnikModel.setPrezime(korisnikZaPromenu.getPrezime());

        getServisKorisnika().azuriranje(korisnikModel);

        return "index";

    }

    @RequestMapping("/zaboravljenaLozinka")
    public String zaboravljenaLozinka(Model model, HttpServletRequest request) {

        KorisnikModel korisnikModel = new KorisnikModel();
        korisnikModel.getEmailReset();
        model.addAttribute("zaboravljenaLozinka", korisnikModel);

        return "zaboravljenaLozinka";
    }

    @RequestMapping(value = "/zaboravljenaLozinka", method = RequestMethod.POST)
    public String zaboravljenaLozinkaPost(@Valid @ModelAttribute("zaboravljenaLozinka") KorisnikModel korisnikModel, @RequestParam String emailReset,
            BindingResult rezultat, Model model, HttpServletRequest request, Locale locale) throws MessagingException {

        if (rezultat.hasErrors()) {
            return "greska";
        }

        Korisnik korisnik = korisnikDAO.findByEmail(emailReset);
        System.out.println("korisnik email je -->" + korisnik.getEmail());

        korisnikModel.setEmail(korisnik.getEmail());

        String emailDontMatch = "";

        if (!korisnikModel.getEmail().equals(korisnikModel.getEmailReset())) {

            emailDontMatch = messageSource.getMessage("label.registerError_3", new Object[]{" !"}, locale);

            model.addAttribute("emailDontMatch", emailDontMatch);

            return "/zaboravljenaLozinka";

        }

        String novaLozinka = novaLozinka();
        korisnikModel.setPassword(passwordEncoder.encode(novaLozinka));
        System.out.println("novaLozinka je -->" + novaLozinka);

        korisnikModel.setId(korisnik.getId());
        korisnikModel.setUsername(korisnik.getUsername());
        korisnikModel.setEmail(korisnik.getEmail());
        korisnikModel.setDatumKreiranja(korisnik.getDatumKreiranja());
        korisnikModel.setEnabled(korisnik.isEnabled());
        korisnikModel.setIme(korisnik.getIme());
        korisnikModel.setPrezime(korisnik.getPrezime());

        String recipientAddress = korisnik.getEmail();
        String subject = messageSource.getMessage("label.forgotPassTitleMail", new Object[]{" ,"}, locale);
        String dear = messageSource.getMessage("label.mail_1", new Object[]{","}, locale);
        String text1 = messageSource.getMessage("label.mail_2_Reset", new Object[]{":"}, locale);
        String pass = messageSource.getMessage("label.mail_3_Reset", new Object[]{":"}, locale);
        String text2 = messageSource.getMessage("label.mail_4", new Object[]{"."}, locale);
        String text3 = messageSource.getMessage("label.mail_4_2", new Object[]{"."}, locale);
        String text4 = messageSource.getMessage("label.mail_5", new Object[]{"."}, locale);
        String text5 = messageSource.getMessage("label.mail_6", new Object[]{"."}, locale);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper emailPassReset = new MimeMessageHelper(mimeMessage, "UTF-8");
        emailPassReset.setTo(recipientAddress);
        emailPassReset.setSubject(subject);

        final String htmlContent = "<html><body style='border-width:1px;background-image: url(cid:image); background-position: center center; background-repeat: no-repeat;'>"
                + "<div style='padding: 35px;font-size: 22px;font-family: arial;font-style: italic;color: #8C9CEC;'>"
                + "<p style='color: #8C9CEC;'>" + dear + " "
                + korisnik.getIme()
                + " "
                + korisnik.getPrezime() + "," + "</p>"
                + "<p style='color: #8C9CEC;'>" + text1 + " " + "</p>"
                + "<br/>"
                + "<p style='color: #8C9CEC;'>" + pass + " \""
                + novaLozinka
                + "\"." + "</p><br/>"
                + "<p style='color: #8C9CEC;'>" + text2 + "</p>"
                + "<p style='color: #8C9CEC;'>" + text3 + "</p>"
                + "<br/>"
                + "<p style='color: #8C9CEC;'>" + text4 + "</p>"
                + "<br/>"
                + "<p style='color: #8C9CEC;'>" + text5 + "</p>"
                + "</div></body></html>";

        MimeMultipart multipart = new MimeMultipart("related");

        BodyPart messageBodyPart = new MimeBodyPart();

        multipart.addBodyPart(messageBodyPart);

        multipart.addBodyPart(messageBodyPart);
        messageBodyPart.setContent(htmlContent, "text/html; charset=UTF-8");
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource("C:\\Users\\Korisnik\\Documents\\NetBeansProjects\\TelefonskiImenikApp_5_11_24_5\\web\\resources\\images\\dontForget2.png");
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<image>");

        multipart.addBodyPart(messageBodyPart);

        mimeMessage.setContent(multipart);

        this.mailSender.send(mimeMessage);

        String mailMsgForgotPass = messageSource.getMessage("label.mailMsgForgotPass", new Object[]{" !"}, locale);

        model.addAttribute("mailMsgForgotPass", mailMsgForgotPass);

        getServisKorisnika().azuriranje(korisnikModel);

        return "index";

    }

    @RequestMapping(value = {"/admin"})
    public String kontakti(Model model) {
        List<KorisnikModel> korisnikModeli = getServisKorisnika().sviKorisnici();
        model.addAttribute("korisnici", korisnikModeli);

        return "admin";
    }

    @RequestMapping(value = {"/brisanjeKorisnika"})
    public String brisanje(@RequestParam Integer id, Model model, HttpServletRequest request) {

        KorisnikModel korisnikModel = getServisKorisnika().korisnikId(id);
        getServisKorisnika().brisanje(korisnikModel);

        return "redirect:/admin";
    }

    private Korisnik getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        }
        Korisnik user = korisnikDAO.findByUsername(userName);
        return user;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public String novaLozinka() {
        return new BigInteger(130, random).toString(32);
    }

    public ServisKorisnika getServisKorisnika() {
        return servisKorisnika;
    }

    public void setServisKorisnika(ServisKorisnika servisKorisnika) {
        this.servisKorisnika = servisKorisnika;
    }
}
