package lp.enums;

public enum TextEnum {
    PANE_STYLES("file:///C://temp/pane-styles.css"),
    ONE_THIRD_STYLE("one-third"),
    TITLE_STYLE("title"),
    RANK_IMAGE_STYLE("rank-image"),
    SUB_TITLE_STYLE("sub-title"),
    DB_VALUE_STYLE("db-value"),
    VALUE_STYLE("value"),
    EMPTY_STRING(""),
    NAME("name"),
    APP_STARTED("Application starting");

    private final String text;

    TextEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
