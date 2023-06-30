package lp;

import generator.FXFontChooser;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import lombok.Data;
import lp.business.dto.Player;
import lp.enums.LangEnum;
import lp.enums.TextFXEnum;
import lp.fx.MainApp;
import lp.fx.tabs.Valueable;
import lp.jpa.dao.PlayerDao;
import lp.jpa.daoimpl.PlayerDaoImpl;
import org.apache.log4j.Logger;
import generator.service.LoggerService;
import generator.serviceimpl.LoggerServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Manager {

    private static Manager manager;

    private final Map<StringProperty, TextFXEnum> componentsForLanguage = new HashMap<>();
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private final Logger log = loggerService.getLog();
    private final List<Player> players = new ArrayList<>();
    private final PlayerDao playerDao = new PlayerDaoImpl();
    private final List<Valueable> valueableClasses = new ArrayList<>();

    private LangEnum language = LangEnum.EN;
    private Player selectedPlayer;

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

    public void setSelectedPlayer(String playerName) {
        selectedPlayer = playerDao.getPlayer(playerName);
        refreshSelectedPlayerValues();
    }

    public void reloadLanguages(LangEnum language) {
        this.language = language;
        getComponentsForLanguage().forEach((stringProperty, textFXEnum) -> stringProperty.set(textFXEnum.reloadText()));
    }

    public ObservableList<String> getPlayerNames() {
        ObservableList<String> playerNames = FXCollections.observableArrayList();
        if (players.isEmpty()) {
            players.addAll(playerDao.getAllPlayer());
        }
        players.forEach(player -> playerNames.add(player.getName()));
        Collections.sort(playerNames);
        return playerNames;
    }

    public void registerValueable(Valueable valueable) {
        valueableClasses.add(valueable);
    }

    private void refreshSelectedPlayerValues() {
        valueableClasses.forEach(Valueable::reloadData);
        reloadLanguages(language);
    }
}
