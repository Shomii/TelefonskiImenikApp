package tel.kontroleri;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class KontrolerSlika {

    private static final Logger logger = LoggerFactory
            .getLogger(KontrolerSlika.class);

    private static final String SLIKE = "Slike_imenika";
    private static final String PROPERTY = "catalina.home";
    private static final String PUTANJA_DO_SLIKA = "C:\\Users\\Korisnik\\Documents" + File.separator + "Slike_imenika";
    private static final File DIREKTORIJUM = new File(PUTANJA_DO_SLIKA);
    private static final String APSOLUTNA_PUTANJA = DIREKTORIJUM.getAbsolutePath() + File.separator;
    private static final String NIJE_USPELO = "Niste uspeli da dodate [%s] zato što %s";
    private static final String USPEH = "Uspešno ste dodali sliku = [%s]";

    @RequestMapping("/dodavanjeSlika")
    public String getUpload() {

        return "dodavanjeSlika";
    }

    @RequestMapping(value = "/dodajSliku", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("name") String name,
            @RequestParam("image_file") MultipartFile file) {

        if (!file.isEmpty() && name != null) {
            try {
//                            if(name==null){
//                                    return "Slika mora imati ime....";
//                                }
                byte[] bytes = file.getBytes();

                String rootPath = System.getProperty("catalina.home");
                File dir = new File(PUTANJA_DO_SLIKA);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

//                                String fileName="";
//                                MultipartFile multipartFile=null;
//
//		if(multipartFile!=null){
//			fileName = multipartFile.getOriginalFilename();
//			//do whatever you want
//		}
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name + ".jpg");

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                return "Uspeli ste u dodavanju slike : " + name /*+ " za vaš kontakt"*/;
            } catch (Exception e) {
                return "Nešto nije u redu sa dodavanjem slike " + name + " => " + e.getMessage();
            }
        } else {
            return "Slika nije dodata "
                    + " ... polje za unos slike je prazno.";
        }
    }

    private void createImagesDirIfNeeded() {
        if (!DIREKTORIJUM.exists()) {
            DIREKTORIJUM.mkdirs();
        }
    }

    @RequestMapping(value = "/image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        createImagesDirIfNeeded();
//        createPizzaImagesDirIfNeeded();

        File serverFile = new File(PUTANJA_DO_SLIKA);

        return Files.readAllBytes(serverFile.toPath());
    }

}
