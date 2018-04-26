package com.mono.snake.game.graphics.listener;


import com.mono.snake.game.entityEnum.KeyEnum;
import com.mono.snake.game.entityEnum.MenuAction;
import com.mono.snake.game.graphics.translation.MenuQuestions;

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
        if (action.equals(MenuQuestions.BT_START_MULTI)) {
            menuActions.addLast(MenuAction.START_GAME_MULTI);
        } else if (action.equals(MenuQuestions.BT_GO_MENU)) {
            menuActions.addLast(MenuAction.GO_MENU);
        }else if (action.equals(MenuQuestions.BT_START_SINGLE)) {
            menuActions.addLast(MenuAction.START_GAME_SINGLE);
        } else if (action.equals(MenuQuestions.BT_EXIT)) {
            menuActions.addLast(MenuAction.EXIT);
        }else if (action.equals(MenuQuestions.BT_RESUME_GAME)) {
            menuActions.addLast(MenuAction.RESUME);
        }else if (action.equals(MenuQuestions.BT_HELP)) {
            menuActions.addLast(MenuAction.HELP);
        }


    }

}


