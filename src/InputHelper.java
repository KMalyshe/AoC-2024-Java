import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputHelper {

    public List<String> parseInput(String file) {
        try {
            return Files.readAllLines(Paths.get("inputs/" + file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
}
