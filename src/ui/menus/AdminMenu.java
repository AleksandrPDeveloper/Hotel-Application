package ui.menus;


import ui.input.InputScanner;
import ui.menus.buttons.admin.*;

import static ui.input.Patterns.DIGITS16;

public class AdminMenu extends MainMenu{
    public AdminMenu() {
        putClause("1", new SeeAllCustomers(this));
        putClause("2", new SeeAllRooms(this));
        putClause("3", new SeeAllReservations(this));
        putClause("4", new AddARoom(this));
        putClause("5", new BackToMainMenu(this));
        putClause("6", new CreateTestData(this));
    }

    @Override
    public void enterInput() {
        String input = InputScanner.processInput(DIGITS16.getPattern(), getErrorText());

        // I use this only for project requirements, of course I want to use if, else statement.
        int switchCaseExample = input.isEmpty() ? 1 : 0;

        switch (switchCaseExample) {
            case 1 -> backMenu();
            default -> getClause(input).press();
        }
    }
    @Override
    public String getErrorText() {
        return "Wrong option. Chose 1-6.";
    }
}
