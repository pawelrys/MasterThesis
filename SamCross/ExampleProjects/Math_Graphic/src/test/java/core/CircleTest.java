package core;

import static org.junit.Assert.assertEquals;

import core.Math.Shapes.Circle;
import core.Math.Shapes.PointXY;
import org.junit.Assert;
import org.junit.Test;

public class CircleTest {


    @Test
    public void testOnePixelCircle()
    {
        Circle rect = new Circle( new PointXY(0,0),0);

        Assert.assertEquals(1,rect.DrawMe().get(0,0));
        Assert.assertEquals(1,rect.Area());
    }

    @Test
    public void testOneLengthRadius()
    {
        Circle rect = new Circle( new PointXY(2,2),1);
        Assert.assertEquals(0,rect.DrawMe().get(0,0));
        Assert.assertEquals(0,rect.DrawMe().get(0+1,0+1));
        Assert.assertEquals(1,rect.DrawMe().get(0+1,1+1));
        Assert.assertEquals(0,rect.DrawMe().get(0+1,2+1));
        Assert.assertEquals(1,rect.DrawMe().get(1+1,0+1));
        Assert.assertEquals(1,rect.DrawMe().get(1+1,1+1));
        Assert.assertEquals(1,rect.DrawMe().get(1+1,2+1));
        Assert.assertEquals(0,rect.DrawMe().get(2+1,0+1));
        Assert.assertEquals(1,rect.DrawMe().get(2+1,1+1));
        Assert.assertEquals(0,rect.DrawMe().get(2+1,2+1));
        Assert.assertEquals(5,rect.Area());
        Assert.assertEquals(1,rect.origin().X);
        Assert.assertEquals(1,rect.origin().Y);
    }

    @Test
    public void test1()
    {
        Circle rect = new Circle( new PointXY(5,6),4);

        Assert.assertEquals(1,rect.DrawMe().get(4+1,4+2));
        Assert.assertEquals(0,rect.DrawMe().get(0+1,1+2));
        Assert.assertEquals(1,rect.DrawMe().get(4+1,0+2));
        Assert.assertEquals(1,rect.DrawMe().get(0+1,4+2));
        Assert.assertEquals(1,rect.DrawMe().get(4+1,8+2));
        Assert.assertEquals(1,rect.DrawMe().get(8+1,4+2 ));
        Assert.assertEquals(1,rect.DrawMe().get(7+1,6+2));
        Assert.assertEquals(0,rect.DrawMe().get(7+1,7+2));
        Assert.assertEquals(1,rect.DrawMe().get(5+1,5+2));
        Assert.assertEquals(1,rect.DrawMe().get(1+1,6+2));
        Assert.assertEquals(0,rect.DrawMe().get(1+1,1+2));
        Assert.assertEquals(49,rect.Area());
        Assert.assertEquals(1,rect.origin().X);
        Assert.assertEquals(2,rect.origin().Y);
    }


    public void testSomething() {

        Assert.assertEquals(1,1);
    }



}

