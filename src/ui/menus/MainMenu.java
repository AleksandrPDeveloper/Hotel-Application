package ui.menus;

import ui.input.InputScanner;
import ui.menus.buttons.Button;
import ui.menus.buttons.main.*;

import java.util.HashMap;
import java.util.Map;

import static ui.input.Patterns.DIGITS15;

/**
 * This main menu class, that has main functions for both menus:
 * Admin and Main.
 * I prefer use one class for menus, but with requirements of project
 * need to create to classes for each menu.
 * */
public class MainMenu implements Menu{
    private final Map<String, Button> menuMap = new HashMap<>();

    @Override
    public String getErrorText() {
        return "Wrong option. Chose 1-5.";
    }

    @Override
    public void printMenu() {
        String startMsg = "Input number from 1 to " + menuMap.size() + "...";
        System.out.println(startMsg);
        menuMap.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public MainMenu(){
        putClause("1", new FindAndReserveARoom(this));
        putClause("2", new SeeMyReservations(this));
        putClause("3", new CreateAnAccount(this));
        putClause("4", new Admin(this));
        putClause("5", new Exit(this));
    }

    @Override
    public void backMenu(){
        printMenu();
        enterInput();
    }
    @Override
    public void putClause(String clause, Button button) {
        menuMap.put(clause, button);
    }

    @Override
    public Button getClause(String clause) {
        return menuMap.get(clause);
    }

    @Override
    public void enterInput() {
        String input = InputScanner.processInput(DIGITS15.getPattern(), getErrorText());

        // I use this only for project requirements, of course I want to use if, else statement.
        int switchCaseExample = input.isEmpty() ? 1 : 0;

        switch (switchCaseExample) {
            case 1 -> backMenu();
            default -> getClause(input).press();
        }
    }
}
