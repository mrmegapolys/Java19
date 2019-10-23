import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Main06 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        checkSerializer();
    }

    private static void checkSerializer() throws UnsupportedEncodingException {
        List<String> skills = getSkills();
        Person person = new Doctor(123, "Alex", skills);
        String serialized = Serializer.serialize(person, "json");
        System.out.println(serialized);
    }

    private static List<String> getSkills() {
        List<String> skills = new ArrayList<>();
        skills.add("soft skills");
        skills.add("hard skills");
        return skills;
    }
}
