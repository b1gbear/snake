package com.mono.snake.game.snake_consciousness;

import com.mono.snake.game.entityEnum.MovementType;
import com.mono.snake.game.logic.entity.GameSettings;
import org.junit.Assert;
import org.junit.Test;

public class TurboDataTest {

    @Test
    public void turboConstructorTest(){
        TurboData turboData = new TurboData();
        Assert.assertEquals(GameSettings.TURBO_TIMEOUT * (-10), turboData.getTurboRequest());
        Assert.assertEquals(GameSettings.TURBO_TIMEOUT * (-10), turboData.getLastTurbo());
        Assert.assertEquals(MovementType.NORMAL, turboData.getMovementType());
        Assert.assertEquals(0, turboData.getTurboPenalty(),0.01);
    }


    @Test
    public void turboGetterSetterTest(){
        TurboData turboData = new TurboData();

        turboData.setMovementType(MovementType.TURBO);
        Assert.assertEquals(MovementType.TURBO,turboData.getMovementType());

        turboData.setTurboPenalty(9.0);
        Assert.assertEquals(9.0,turboData.getTurboPenalty(),0.01);

        turboData.setTurboRequest(555);
        Assert.assertEquals(555,turboData.getTurboRequest());

        turboData.setLastTurbo(112);
        Assert.assertEquals(112,turboData.getLastTurbo());
    }


}
