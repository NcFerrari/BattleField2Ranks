package lp;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Tab;
import lombok.Data;
import lp.enums.LangEnum;
import lp.enums.TextFXEnum;
import lp.fx.KitInfoPane;
import lp.fx.MainApp;
import org.apache.log4j.Logger;
import service.LoggerService;
import serviceimpl.LoggerServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public List<Tab> getTabs() {
        List<Tab> resultList = new ArrayList<>();
        KitInfoPane kitInfoPane = new KitInfoPane();
        resultList.add(kitInfoPane);

        return resultList;
    }

    public void reloadLanguages(LangEnum language) {
        this.language = language;
        getComponentsForLanguage().forEach((stringProperty, textFXEnum) -> stringProperty.set(textFXEnum.reloadText()));
    }

    public Map<StringProperty, TextFXEnum> getComponentsForLanguage() {
        return componentsForLanguage;
    }
}
