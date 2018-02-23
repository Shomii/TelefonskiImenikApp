package tel.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tel.dao.KontaktDAO;
import tel.dao.KorisnikDAO;
import tel.domain.Kontakt;
import tel.model.KontaktModel;
import tel.service.ServisKontakata;
import tel.domain.Korisnik;

@Service
public class ServisKontakataImpl implements ServisKontakata {

    @Autowired
    private KontaktDAO kontaktDAO;

    @Autowired
    private KorisnikDAO korisnikDAO;

    @Autowired
    private ServletContext context;

    @Override
    public void dodavanjeNovogKontakta(KontaktModel kontaktModel, MultipartFile[] projectImages, Korisnik korisnik) {

        Kontakt kontakt = new Kontakt();

        kontakt.setIme(kontaktModel.getIme());
        kontakt.setPrezime(kontaktModel.getPrezime());
        kontakt.setEmail(kontaktModel.getEmail());
        kontakt.setPozivniBroj(kontaktModel.getPozivniBroj());
        kontakt.setBrojTelefona(kontaktModel.getBrojTelefona());
        kontakt.setAdresa(kontaktModel.getAdresa());
        kontakt.setGrad(kontaktModel.getGrad());
        kontakt.setOpis(kontaktModel.getOpis());
        kontakt.setKontaktSlika(kontaktModel.getKontaktSlika());

        kontakt.setKorisnikZaImenik(korisnik);

        MultipartFile kontaktSlika = projectImages[0];

        if (!kontaktSlika.isEmpty()) {
            try {

                String filepath = context.getRealPath("resources\\slike");
                System.out.println(filepath);
                FileOutputStream fos;
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                String[] multipartParts = kontaktSlika.getOriginalFilename().split("\\.");
                String extension = multipartParts[multipartParts.length - 1];
                if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("JPG") || extension.equals("bmp")) {

                    String imageName = generateUniqueFileName() + "." + extension;
                    String filename = filepath + "\\" + imageName;
                    fos = new FileOutputStream(filename);
                    fos.write(kontaktSlika.getBytes());
                    fos.close();

                    System.out.println(imageName);
                    System.out.println(filename);

                    kontakt.setKontaktSlika(imageName);

                } else {
                    System.out.println("nije dobra extenzija");
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ServisKontakataImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServisKontakataImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        getKontaktDAO().dodavanjeNovogKontakta(kontakt);
    }

    @Override
    public List<KontaktModel> sviKontakti() {

        List<KontaktModel> kontaktModeli = new ArrayList<>();
        List<Kontakt> kontakti = getKontaktDAO().sviKontakti();
        KontaktModel kontaktModel = null;

        for (Kontakt kontakt : kontakti) {
            kontaktModeli.add(modelDoDomena(kontakt));
        }

        return kontaktModeli;

    }

    @Override
    public KontaktModel podaciOKontaktu(KontaktModel kontaktModel) {

        Kontakt kontakt = null;
        kontakt = getKontaktDAO().podaciOKontaktu(kontakt);

        kontaktModel.setKontaktID(kontakt.getKontaktID());
        kontaktModel.setIme(kontakt.getIme());
        kontaktModel.setPrezime(kontakt.getPrezime());
        kontaktModel.setEmail(kontakt.getEmail());
        kontaktModel.setPozivniBroj(kontakt.getPozivniBroj());
        kontaktModel.setBrojTelefona(kontakt.getBrojTelefona());
        kontaktModel.setAdresa(kontakt.getAdresa());
        kontaktModel.setGrad(kontakt.getGrad());
        kontaktModel.setOpis(kontakt.getOpis());
        kontaktModel.setKontaktSlika(kontakt.getKontaktSlika());

        return kontaktModel;
    }

    @Override
    public KontaktModel kontaktIme(String ime) {

        Kontakt kontakt = new Kontakt();
        KontaktModel kontaktModel = null;

        kontakt = getKontaktDAO().kontaktIme(ime);

        kontaktModel.setKontaktID(kontakt.getKontaktID());
        kontaktModel.setIme(kontakt.getIme());
        kontaktModel.setPrezime(kontakt.getPrezime());
        kontaktModel.setEmail(kontakt.getEmail());
        kontaktModel.setPozivniBroj(kontakt.getPozivniBroj());
        kontaktModel.setBrojTelefona(kontakt.getBrojTelefona());
        kontaktModel.setAdresa(kontakt.getAdresa());
        kontaktModel.setGrad(kontakt.getGrad());
        kontaktModel.setOpis(kontakt.getOpis());
        kontaktModel.setKontaktSlika(kontakt.getKontaktSlika());

        return kontaktModel;
    }

    @Override
    public KontaktModel kontaktiId(Integer kontaktID) {

        Kontakt kontakt = getKontaktDAO().kontaktiId(kontaktID);
        if (kontakt == null) {
            return new KontaktModel();
        }
        return modelDoDomena(kontakt);
    }

    public KontaktModel modelDoDomena(Kontakt kontakt) {

        KontaktModel kontaktModel = new KontaktModel();

        kontaktModel.setKontaktID(kontakt.getKontaktID());
        kontaktModel.setIme(kontakt.getIme());
        kontaktModel.setPrezime(kontakt.getPrezime());
        kontaktModel.setEmail(kontakt.getEmail());
        kontaktModel.setPozivniBroj(kontakt.getPozivniBroj());
        kontaktModel.setBrojTelefona(kontakt.getBrojTelefona());
        kontaktModel.setAdresa(kontakt.getAdresa());
        kontaktModel.setGrad(kontakt.getGrad());
        kontaktModel.setOpis(kontakt.getOpis());
        kontaktModel.setKontaktSlika(kontakt.getKontaktSlika());

        return kontaktModel;
    }

    @Override
    public void azuriranje(KontaktModel kontaktModel, MultipartFile[] projectImages, Korisnik korisnik) {

        Kontakt kontakt = new Kontakt();

        kontakt.setKontaktID(kontaktModel.getKontaktID());
        kontakt.setIme(kontaktModel.getIme());
        kontakt.setPrezime(kontaktModel.getPrezime());
        kontakt.setEmail(kontaktModel.getEmail());
        kontakt.setPozivniBroj(kontaktModel.getPozivniBroj());
        kontakt.setBrojTelefona(kontaktModel.getBrojTelefona());
        kontakt.setAdresa(kontaktModel.getAdresa());
        kontakt.setGrad(kontaktModel.getGrad());
        kontakt.setOpis(kontaktModel.getOpis());
        kontakt.setKontaktSlika(kontaktModel.getKontaktSlika());

        MultipartFile kontaktSlika = projectImages[0];

        kontakt.setKorisnikZaImenik(korisnik);

        if (!kontaktSlika.isEmpty()) {
            try {
                String UPLOAD_DIRECTORY = "";
                String filepath = context.getRealPath("resources\\slike") + File.separator + UPLOAD_DIRECTORY;
                System.out.println(filepath);
                FileOutputStream fos;
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                String[] multipartParts = kontaktSlika.getOriginalFilename().split("\\.");
                String extension = multipartParts[multipartParts.length - 1];
                if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("JPG") || extension.equals("bmp")) {

                    String imageName = generateUniqueFileName() + "." + extension;
                    String filename = filepath + File.separator + imageName;
                    fos = new FileOutputStream(filename);
                    fos.write(kontaktSlika.getBytes());
                    fos.close();

                    System.out.println(imageName);
                    System.out.println(filename);

                    kontakt.setKontaktSlika(imageName);

                } else {
                    System.out.println("nije dobra extenzija");
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ServisKontakataImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServisKontakataImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        getKontaktDAO().azuriranje(kontakt);
    }

    @Override
    public void brisanje(KontaktModel kontaktModel) {
        Kontakt kontakt = new Kontakt();

        kontakt.setKontaktID(kontaktModel.getKontaktID());
        getKontaktDAO().brisanje(kontakt);
    }

    public KontaktDAO getKontaktDAO() {
        return kontaktDAO;
    }

    public void setKontaktDAO(KontaktDAO kontaktDAO) {
        this.kontaktDAO = kontaktDAO;
    }

    String generateUniqueFileName() {
        String filename = "";
        long millis = System.currentTimeMillis();

        String rndchars = RandomStringUtils.randomAlphanumeric(8);
        filename = rndchars + millis;
        return filename;
    }

}
