package desu.nya.desktop.screen;

import desu.nya.desktop.entities.Player;
import desu.nya.desktop.entities.Raid;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageToTextTranslator {
    private final Tesseract tesseract;
    private final LocalTime nightBeginsAt;
    private final LocalTime nightEndsAt;

    public ImageToTextTranslator() {
        this.tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng+rus");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);

        LocalTime.of(0, 0);
        nightBeginsAt = LocalTime.of(0, 0);
        nightEndsAt = LocalTime.of(0, 0);
    }

    public List<Player> getPlayerList(BufferedImage image) {
        try {
            String result = tesseract.doOCR(image);
            return Arrays.stream(result.split(" ")).map(Player::new).collect(Collectors.toList());
        } catch (TesseractException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Raid getRaidInfo(BufferedImage image) {
        try {
            String result = tesseract.doOCR(image);
            String results[] = result.split(" ");
            String raidName = results[0];
            int count = Integer.parseInt(results[1].substring(12).replaceAll("\\)\\(", ""));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");
            LocalDateTime dateTime = LocalDateTime.parse(results[2], formatter);
            return new Raid("", false, dateTime, count);
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}
