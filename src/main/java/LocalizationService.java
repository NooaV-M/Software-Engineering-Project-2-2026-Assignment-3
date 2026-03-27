import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LocalizationService {
    /**
     * Get localized strings for a specific locale
     */
    public static Map<String, String> getLocalizedStrings(Locale locale) {
        Map<String, String> strings = new HashMap<>();

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(
                    "MessagesBundle",
                    locale
            );

            // Extract all keys
            for (String key : bundle.keySet()) {
                strings.put(key, bundle.getString(key));
            }
        } catch (Exception e) {
            System.err.println("Failed to load resource bundle for locale: " + locale);
            // Fallback to English
            try {
                ResourceBundle fallback = ResourceBundle.getBundle(
                        "MessagesBundle",
                        new Locale("en", "UK")
                );
                for (String key : fallback.keySet()) {
                    strings.put(key, fallback.getString(key));
                }
            } catch (Exception ex) {
                // Use hardcoded defaults as last resort
                strings.put("itemNumberPrompt","Enter the number of items to purchase:");
                strings.put("itemPricePrompt","Enter the price of the item:");
                strings.put("itemQuantityPrompt","Enter the quantity of the item:");
                strings.put("totalCostMessage","Total cost:");
                strings.put("checkout","Enter the quantity of the item:");
            }
        }
        return strings;
    }
}