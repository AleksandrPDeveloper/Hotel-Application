package ui.menus.buttons;

import ui.input.InputScanner;
import ui.input.Patterns;
import ui.menus.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * This is major class for all buttons for each, menu.
 * They have all that necessary for clause in menu.
 * This class has link to menu, and abstract method "press".
 * Also, this class has all types of input from user.
 * */
public abstract class Button {
    protected String name;
    protected Menu menu;

    abstract public void press();

    public Button(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    private String getName() {
        return name;
    }

    /**
     *
     * @param text Text is printing for user before input.
     * @return User input without patterns.
     */
    protected String inputName(String text) {
        System.out.println(text);

        String input = InputScanner.processInput();
        if (input.isEmpty()) {
            System.out.println(text);
            input = inputName(text);
        }
        return input;
    }

    /**
     *
     * @param text Text is printing for user before input.
     * @return User input with e-mail pattern email@domain.com.
     */
    protected String inputEmail(String text) {
        String wrongInput = "Requires input in the format \"email@domain.com\". Try again...";
        System.out.println(text);
        String input = InputScanner.processInput(Patterns.EMAIL.getPattern(), wrongInput);
        if (input.isEmpty()) {
            input = inputEmail(text);
        }
        return input;
    }

    /**
     *
     * @param text Text is printing for user before input.
     * @return User input with y/n pattern.
     */
    protected String inputYN(String text) {
        String wrongInput = "Input Yes(y) or No(n). Try again...";
        System.out.println(text);
        String input = InputScanner.processInput(Patterns.YN.getPattern(), wrongInput);
        if (input.isEmpty()) {
            input = inputYN(text);
        }
        return input;
    }

    /**
     *
     * @param text Text is printing for user before input.
     * @return User input with 0-9 pattern.
     */
    protected String inputDigits(String text) {
        String wrongInput = "You need use only digits 0-9 in room number. Try again...";
        System.out.println(text);
        String input = InputScanner.processInput(Patterns.DIGITS.getPattern(), wrongInput);
        if (input.isEmpty()) {
            input = inputDigits(text);
        }
        return input;
    }

    /**
     *
     * @param text Text is printing for user before input.
     * @return User input with 1-2 pattern.
     */
    protected String inputEnumeration(String text){
        String wrongInput = "Please input 1 or 2 for choose single or double room. Try again...";
        System.out.println(text);
        String input = InputScanner.processInput(Patterns.DIGITS12.getPattern(), wrongInput);
        if (input.isEmpty()) {
            input = inputEnumeration(text);
        }
        return input;
    }

    /**
     *
     * @param text Text is printing for user before input.
     * @return User input with date pattern YYYY-MM-DD.
     */
    protected Date inputDate(String text){
        String pattern = "yyyy-MM-dd";
        String wrongInput = "Please input date in format YYYY-MM-DD. Try again...";
        System.out.println(text);
        String input = InputScanner.processInput(Patterns.DATE.getPattern(), wrongInput);
        Date date = null;
        if (input.isEmpty()) {
            date = inputDate(text);
        }
        if (date == null){
            try{
                date = new SimpleDateFormat(pattern).parse(input);
            } catch (ParseException e) {
                System.out.println(wrongInput);
                throw new RuntimeException(e);
            }
            finally {
                if (date == null){
                    date = inputDate(text);
                }
            }
        }
        return date;
    }

    /**
     * This method add hours to date. And return start of date.
     * @param calendar use with setting date.
     * @param h adding hours.
     * @return Date start of day with added hours.
     */
    protected Date updateTime(Calendar calendar, Integer h){
        calendar.add(Calendar.HOUR, h);
        var date = calendar.getTime();
        return atStartOfDay(date);
    }

    /**
     * find this on <a href="https://stackoverflow.com/questions/10308356/how-to-obtain-the-start-time-and-end-time-of-a-day">stackoverflow</a>.
     * @param date date isn't start of day.
     * @return Date start of day with added hours.
     */
    protected Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    /**
     * find this on <a href="https://stackoverflow.com/questions/10308356/how-to-obtain-the-start-time-and-end-time-of-a-day">stackoverflow</a>.
     * @param date Date to conversion to LocalDateTime.
     * @return LocalDateTime from Date.
     */
    protected LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * find this on <a href="https://stackoverflow.com/questions/10308356/how-to-obtain-the-start-time-and-end-time-of-a-day">stackoverflow</a>.
     * @param localDateTime LocalDateTime to conversion to Date
     * @return Date from LocalDateTime.
     */
    protected Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return getName();
    }
}
