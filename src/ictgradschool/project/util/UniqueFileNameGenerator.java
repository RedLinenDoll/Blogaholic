package ictgradschool.project.util;
import java.util.UUID;
public class UniqueFileNameGenerator {

    public static String getUniqueFileName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
