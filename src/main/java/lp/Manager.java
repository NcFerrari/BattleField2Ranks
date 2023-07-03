package lp;

import generator.service.LoggerService;
import generator.serviceimpl.LoggerServiceImpl;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;
import lp.business.dto.Player;
import lp.enums.LangEnum;
import lp.enums.TextFXEnum;
import lp.fx.MainApp;
import lp.fx.tabs.Valuable;
import lp.jpa.dao.PlayerDao;
import lp.jpa.daoimpl.PlayerDaoImpl;
import org.apache.log4j.Logger;

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
    private final Map<String, Player> players = new HashMap<>();
    private final PlayerDao playerDao = new PlayerDaoImpl();
    private final List<Valuable> valuableClasses = new ArrayList<>();

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
        loadPlayersFromDB();
    }

    public static void main(String[] args) {
        Manager.getInstance();
    }

    public void setSelectedPlayer(String playerName) {
        selectedPlayer = players.get(playerName);
        refreshSelectedPlayerValues();
    }

    public void reloadLanguages(LangEnum language) {
        this.language = language;
        getComponentsForLanguage().forEach((stringProperty, textFXEnum) -> stringProperty.set(textFXEnum.reloadText()));
    }

    public ObservableList<String> getPlayerNames() {
        ObservableList<String> playerNames = FXCollections.observableArrayList();
        playerNames.addAll(players.keySet());
        Collections.sort(playerNames);
        return playerNames;
    }

    public void loadPlayersFromDB() {
        players.clear();
        playerDao.getAllPlayer().forEach(player -> players.put(player.getName(), player));
        if (getSelectedPlayer() != null) {
            setSelectedPlayer(getSelectedPlayer().getName());
        }
    }

    public void registerValuable(Valuable valuable) {
        valuableClasses.add(valuable);
    }

    private void refreshSelectedPlayerValues() {
        valuableClasses.forEach(Valuable::reloadData);
        reloadLanguages(language);
    }
}
