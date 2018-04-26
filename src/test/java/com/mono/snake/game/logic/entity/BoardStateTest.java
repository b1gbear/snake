package com.mono.snake.game.logic.entity;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.entityEnum.GameType;
import com.mono.snake.game.snake_consciousness.SnakeConsciousness;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessAI;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;


public class BoardStateTest {

    public BoardState getState() {
        return new BoardState(new Point(10,10),GameState.HELP);
    }


    @Test
    public void testConstructor() {
        BoardState boardState = new BoardState(new Point(10,10),GameState.HELP);
        Assert.assertEquals(new Point(10,10),boardState.getBoardSize());
        Assert.assertEquals(GameState.HELP,boardState.getGameState());
        Assert.assertNotEquals(null,boardState.getPlayers());
        Assert.assertNotEquals(null,boardState.getBots());
        Assert.assertNotEquals(null,boardState.getFruits());
    }


    @Test
    public void resetTest() {
        BoardState boardState = getState();
        Assert.assertEquals(GameState.HELP,boardState.getGameState());
        Assert.assertEquals(new Point(10,10),boardState.getBoardSize());

        boardState.getPlayers().add(new SnakeConsciousnessPlayer(new Snake(new Point(1,1)),2));

        boardState.reset(GameType.MULTI);
        Assert.assertEquals(GameType.MULTI,boardState.getGameType());
        Assert.assertEquals(2,boardState.getPlayers().size());
        Assert.assertTrue(boardState.getBots().isEmpty());
        Assert.assertEquals(2,boardState.getPlayers().size());
        Assert.assertTrue(boardState.getFruits().isEmpty());

        boardState.getPlayers().add(new SnakeConsciousnessPlayer(new Snake(new Point(1,1)),2));
        boardState.getPlayers().add(new SnakeConsciousnessPlayer(new Snake(new Point(1,1)),2));


        boardState.reset(GameType.SINGLE);
        Assert.assertEquals(GameType.SINGLE,boardState.getGameType());
        Assert.assertEquals(1,boardState.getPlayers().size());
        Assert.assertTrue(boardState.getBots().isEmpty());
        Assert.assertEquals(1,boardState.getPlayers().size());
        Assert.assertTrue(boardState.getFruits().isEmpty());
    }


    @Test
    public void getterSetterTest() {
        BoardState boardState = getState();
        Assert.assertEquals(new Point(10,10),boardState.getBoardSize());
        Assert.assertEquals(GameState.HELP,boardState.getGameState());

        boardState.setLostState(new LostState(true,33));

        Assert.assertEquals(true,boardState.getLostState().isMultiplayer());
        Assert.assertEquals(33,boardState.getLostState().getWinnerIs());


        boardState.setBoardSize(new Point(1,22));
        Assert.assertEquals(new Point(1,22),boardState.getBoardSize());

        boardState.setGameType(GameType.MULTI);
        Assert.assertEquals(GameType.MULTI,boardState.getGameType());


        LinkedList<SnakeConsciousness> bots = new LinkedList<>();
        bots.add(new SnakeConsciousnessAI(new Snake(new Point(1,1))));
        bots.add(new SnakeConsciousnessAI(new Snake(new Point(1,1))));
        bots.add(new SnakeConsciousnessAI(new Snake(new Point(1,1))));
        boardState.setBots(bots);
        Assert.assertEquals(boardState.getBots().size(),3);

        LinkedList<Fruit> fruits = new LinkedList<>();
        fruits.add(new Fruit(new Point(1,2),3));
        fruits.add(new Fruit(new Point(1,2),3));
        fruits.add(new Fruit(new Point(1,2),3));
        fruits.add(new Fruit(new Point(1,2),3));
        fruits.add(new Fruit(new Point(1,2),3));
        boardState.setFruits(fruits);
        Assert.assertEquals(5,boardState.getFruits().size());

        LinkedList<SnakeConsciousnessPlayer> players = new LinkedList<>();
        players.add(new SnakeConsciousnessPlayer(new Snake(new Point(11,22)),2));
        players.add(new SnakeConsciousnessPlayer(new Snake(new Point(11,22)),2));
        players.add(new SnakeConsciousnessPlayer(new Snake(new Point(11,22)),2));
        players.add(new SnakeConsciousnessPlayer(new Snake(new Point(11,22)),2));
        boardState.setPlayers(players);
        Assert.assertEquals(4,boardState.getPlayers().size());
    }

}

