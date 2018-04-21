package com.mono.snake.game.graphics.listener;


import com.mono.snake.game.entityEnum.KeyEnum;
import com.mono.snake.game.entityEnum.MenuAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * KeyAdapter that gets current pressed key
 */
public class MenuListener implements ActionListener {

    /**
     * Queue of key events
     */
    private final ConcurrentLinkedDeque<MenuAction> menuActions;

    /**
     * Default constructor
     */
    public MenuListener() {
        this.menuActions = new ConcurrentLinkedDeque<> ();
    }

    /**
     * Get direction queue
     * @return Queue
     */
    public ConcurrentLinkedDeque<MenuAction> getMenuActions() {
        return menuActions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Zacznij od nowa")) {
            menuActions.addLast(MenuAction.START_GAME);
        }
        else if (action.equals("Powrót do menu")) {
            menuActions.addLast(MenuAction.GO_MENU);

        }else if (action.equals("Zacznij grę!")) {
            menuActions.addLast(MenuAction.START_GAME);
        }
        else if (action.equals("Wyjscie")) {
            menuActions.addLast(MenuAction.EXIT);
        }else if (action.equals("Wznów grę")) {
            menuActions.addLast(MenuAction.RESUME);
        }else if (action.equals("Wyjscie do menu")) {
            menuActions.addLast(MenuAction.GO_MENU);
        }else if (action.equals("Pomoc")) {
            menuActions.addLast(MenuAction.HELP);
        }


    }

}


