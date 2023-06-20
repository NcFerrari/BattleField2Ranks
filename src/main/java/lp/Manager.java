package lp;

import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import lombok.Data;
import lp.enums.LangEnum;
import lp.enums.TextFXEnum;
import lp.fx.MainApp;
import org.apache.log4j.Logger;
import service.LoggerService;
import serviceimpl.LoggerServiceImpl;

import java.util.HashMap;
import java.util.Map;

@Data
public class Manager {

    private static Manager manager;
    private LangEnum language = LangEnum.EN;
    private final Map<StringProperty, TextFXEnum> componentsForLanguage = new HashMap<>();
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private final Logger log = loggerService.getLog();

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
            javafx.application.Application.launch(MainApp.class);
        }
        return manager;
    }

    private Manager() {
    }

    public static void main(String[] args) {
        Manager.getInstance();
    }

    public void reloadLanguages(LangEnum language) {
        this.language = language;
        getComponentsForLanguage().forEach((stringProperty, textFXEnum) -> stringProperty.set(textFXEnum.reloadText()));
    }

    public ComboBox<LangEnum> setLanguageChoiceBox(ComboBox<LangEnum> languageChoiceBox) {
        languageChoiceBox.getItems().addAll(LangEnum.EN, LangEnum.CZ);
        languageChoiceBox.getSelectionModel().selectFirst();
        languageChoiceBox.setOnAction(event ->
                reloadLanguages(languageChoiceBox.getSelectionModel().getSelectedItem()));
        return languageChoiceBox;
    }
}
