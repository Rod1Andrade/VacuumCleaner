package com.github.rod1andrade.ui.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rodrigo Andrade
 */
public class SingleMenuData {

    private String label;
    private int selectedOption;
    private List<MenuOption> menuOptions = new ArrayList<>();

    public SingleMenuData(String label, int selectedOptionCode, MenuOption ... menuOptions) {
        this.label = label;
        this.menuOptions.addAll(Arrays.asList(menuOptions));

        for (int i = 0; i < menuOptions.length; i++) {
            if(menuOptions[i].getOptionCode() == selectedOptionCode) {
                this.selectedOption = i;
                break;
            }
        }
    }

    public MenuOption getSelectedMenuOption() {
        return menuOptions.get(selectedOption);
    }

    public void nextOption() {
        if(selectedOption < menuOptions.size() -1)
            selectedOption++;
    }

    public void prevOption() {
        if(selectedOption > 0)
            selectedOption--;
    }

    public String getLabel() {
        return label;
    }
}
