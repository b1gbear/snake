package com.mono.snake.game.logic;

import com.mono.snake.game.entity.Fruit;
import com.mono.snake.game.entity.Point;
import com.mono.snake.game.entity.Snake;
import com.mono.snake.game.entityEnum.DirectionEnum;
import com.mono.snake.game.entityEnum.GameState;
import com.mono.snake.game.entityEnum.GameType;
import com.mono.snake.game.logic.entity.BoardState;
import com.mono.snake.game.snake_consciousness.SnakeConsciousnessAI;
import com.mono.snake.game.utility.DirectionUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    @Mock
    BoardState boardState;


    @Test
    public void constructorTest() {
        Board board = new Board(new BoardState(new Point(300,300), GameState.MENU));
        Assert.assertTrue(null != board.getState());
        Assert.assertTrue(null != board.getKeysOne());
        Assert.assertTrue(null != board.getKeysTwo());
    }

    @Test
    public void testPointCrossesBoardStatic() {
        Point size = new Point(10, 10);

        Assert.assertTrue(Board.pointCrossesBoard(new Point(9, 10), size));
        Assert.assertTrue(Board.pointCrossesBoard(new Point(-1, 2), size));
        Assert.assertTrue(Board.pointCrossesBoard(new Point(10, 2), size));

        Assert.assertFalse(Board.pointCrossesBoard(new Point(9, 9), size));
        Assert.assertFalse(Board.pointCrossesBoard(new Point(0, 0), size));
        Assert.assertFalse(Board.pointCrossesBoard(new Point(0, 9), size));
        Assert.assertFalse(Board.pointCrossesBoard(new Point(9, 0), size));

    }

    @Test
    public void newGameMockedTest() {
        {
            // Multi
            Board board = new Board(boardState);
            board.startNewGame(GameType.MULTI);
            Mockito.verify(boardState).reset(GameType.MULTI);
            Mockito.verify(boardState).setGameState(GameState.GAME);
        }

    }
    @Test
    public void newGameSingleMockedTest() {
            // Single
            Board boardTwo = new Board(boardState);
            boardTwo.startNewGame(GameType.SINGLE);
            Mockito.verify(boardState).reset(GameType.SINGLE);
            Mockito.verify(boardState).setGameState(GameState.GAME);
    }

    @Test
    public void newGameNormalTest() {
        Board board = new Board(new BoardState(new Point(100,100),GameState.GAME));
        board.startNewGame(GameType.MULTI);
        Assert.assertEquals(DirectionEnum.RIGHT,board.getKeysOne().getDirection());
        Assert.assertEquals(DirectionEnum.LEFT,board.getKeysTwo().getDirection());
        Assert.assertNotEquals(null,board.getState());
    }



    @Test
    public void addAITest() {
        Board board = new Board(new BoardState(new Point(100, 100), GameState.GAME));
        Assert.assertTrue(board.getState().getBots().isEmpty());
        board.addAI();
        Assert.assertEquals(board.getState().getBots().size(),5);
    }


    @Test
    public void createFruitsBasedOnSnakeTest(){
        Snake snake = new Snake(new Point(10,10));
        snake.setSize(10);
        for (int i = 0; i < 10; i++) {
            snake.moveTowardsVector(DirectionUtility.directionToVector(DirectionEnum.DOWN));
        }
        List<Fruit> fruitList = Board.createFruitsBasedOnSnake(snake,2);
        Assert.assertTrue(4 < fruitList.size());
    }



    @Test
    public void AICrossesBoardTest(){
        Board board = new Board(new BoardState(new Point(100, 100), GameState.GAME));
        for (int i = 0; i < 20; i++) {
            board.getState().getBots().add(new SnakeConsciousnessAI(new Snake(new Point(100+i,100+i))));
        }
        Assert.assertEquals(20,board.getState().getBots().size());
        board.AIcrossedBoard();
        Assert.assertTrue(board.getState().getBots().isEmpty());

    }

    @Test
    public void tickTest(){
        Board board = new Board(new BoardState(new Point(100, 100), GameState.GAME));

    }

    @Test
    public void anyOfSnakesEatsTest(){
        Board board = new Board(new BoardState(new Point(100, 100), GameState.GAME));
        board.getState().getFruits().add(new Fruit(new Point(10,10),100));
        board.getState().getBots().add(new SnakeConsciousnessAI(new Snake(new Point(10,10))));
        board.anyOfSnakesEats();
        Assert.assertEquals(101,board.getState().getBots().get(0).getSnake().getSize());
    }

    @Test
    public void iKeyboardMoveTest(){

    }

    @Test
    public void iSnakeMoveTest(){


    }

    @Test
    public void iCrossedBoardTest(){


    }

    @Test
    public void iCrossedOtherSnakeTest(){


    }

    @Test
    public  void aIsnakesCrossEachOtherOrMeTest() {

    }

    @Test
    public  void aIsnakesDecideTest() {

    }
}

