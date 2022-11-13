package ui.menus;

import ui.menus.buttons.Button;

/**
 * This interface helps invoke menu classes as one
 * */
public interface Menu {

    void backMenu();
    String getErrorText();
    void enterInput();
    void putClause(String clause, Button button);
    Button getClause(String clause);
    void printMenu();
}
