package com.github.rod1andrade.ui.menu;

/**
 * @author Rodrigo Andrade
 */
public class MenuOption {
    private int id;
    private int optionCode;
    private String label;

    public MenuOption(String label, int id, int optionCode) {
        this.label = label;
        this.id = id;
        this.optionCode = optionCode;
    }

    public int getId() {
        return id;
    }

    public int getOptionCode() {
        return optionCode;
    }

    public String getLabel() {
        return label;
    }
}
