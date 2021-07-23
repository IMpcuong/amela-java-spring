package TutorialSpringMVC.Service;

import java.util.HashMap;
import java.util.Map;

public class TranslatorService {

    private static Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "Xin chào");
        dictionary.put("how", "Thế nào");
        dictionary.put("book", "Quyển vở");
        dictionary.put("computer", "Máy tính");
    }

    public String translate(String key) {
        return dictionary.get(key);
    }
}
