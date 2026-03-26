import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Locale;
import java.util.Map;

public class CalculatorController {

    public HBox langButtonBox;
    public Label lblResult;
    private Locale currentLocale = new Locale("en","UK");
    private Map<String, String> localizedStrings;

    @FXML
    public void initialize() {
        setLanguage(currentLocale);

        Button engButton = createLanguageButton("English", new Locale("en", "UK"));
        Button friButton = createLanguageButton("French", new Locale("fr", "FR"));
        Button jpaButton = createLanguageButton("日本語", new Locale("ja", "JP"));
        Button farButton = createLanguageButton("فارسی", new Locale("fa", "IR"));

        langButtonBox.getChildren().addAll(engButton, friButton, jpaButton, farButton);
    }

    private Button createLanguageButton(String text, Locale locale) {
        Button button = new Button(text);
        button.setOnAction(e -> setLanguage(locale));
        HBox.setMargin(button, new javafx.geometry.Insets(5, 10, 5, 10));
        return button;
    }


    private void setLanguage(Locale locale) {
        currentLocale = locale;
        lblResult.setText(""); // Clear previous result

        // Load localized strings
        localizedStrings = LocalizationService.getLocalizedStrings(locale);

        // Update all UI text
        lblTitle.setText(localizedStrings.getOrDefault("title", "Average Calculator"));
        lblDistant.setText(localizedStrings.getOrDefault("distance", "Distant (km):"));
        lblTime.setText(localizedStrings.getOrDefault("time", "Time (h):"));
        btnCalculate.setText(localizedStrings.getOrDefault("calculate", "Calculate Average Speed"));

        // Update time display with new locale
        displayLocalTime(locale);

        // Apply text direction based on language
        applyTextDirection(locale);
    }
}
