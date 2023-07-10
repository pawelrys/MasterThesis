package core;

import static org.junit.Assert.assertEquals;

import core.Math.ShapeType;
import core.Math.Shapes.PointXY;
import core.Math.Shapes.Rectangle;
import org.junit.Assert;
import org.junit.Test;


public class RectangleTest {

    @Test
    public void testOnePixelRectangle()
    {
        Rectangle rect = new Rectangle( new PointXY(0,0),new PointXY(0,0),new PointXY(0,0),new PointXY(0,0));


        Assert.assertEquals(1,rect.DrawMe().get(0,0));
        Assert.assertEquals(1,rect.Area());
    }
    @Test
    public void test1()
    {
        Rectangle rect = new Rectangle( new PointXY(0,0),new PointXY(0,0),new PointXY(0,3),new PointXY(0,3));
        int array[][] = {{1,1,1,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        Assert.assertEquals(array[0][0],rect.DrawMe().get(0,0));
        Assert.assertEquals(array[0][1],rect.DrawMe().get(0,1));
        Assert.assertEquals(array[0][2],rect.DrawMe().get(0,2));
        Assert.assertEquals(array[0][3],rect.DrawMe().get(0,3));
        Assert.assertEquals(array[1][0],rect.DrawMe().get(1,0));
        Assert.assertEquals(array[3][0],rect.DrawMe().get(3,0));
        Assert.assertEquals(array[3][3],rect.DrawMe().get(3,3));
        Assert.assertEquals(0,rect.DrawMe().get(0,4));
        Assert.assertEquals(0,rect.DrawMe().get(4,0));
        Assert.assertEquals(4,rect.Area());
        Assert.assertEquals(true,rect.ShapeType == ShapeType.RECTANGLE);
    }

    @Test
    public void test2()
    {
        Rectangle rect = new Rectangle( new PointXY(0,0),new PointXY(0,0),new PointXY(3,0),new PointXY(3,0));
        int array[][] = {{1,0,0,0},{1,0,0,0},{1,0,0,0},{1,0,0,0}};
        Assert.assertEquals(array[0][0],rect.DrawMe().get(0,0));
        Assert.assertEquals(array[1][0],rect.DrawMe().get(1,0));
        Assert.assertEquals(array[2][0],rect.DrawMe().get(2,0));
        Assert.assertEquals(array[3][0],rect.DrawMe().get(3,0));
        Assert.assertEquals(array[0][1],rect.DrawMe().get(0,1));
        Assert.assertEquals(array[0][3],rect.DrawMe().get(0,3));
        Assert.assertEquals(array[3][3],rect.DrawMe().get(3,3));
        Assert.assertEquals(0,rect.DrawMe().get(0,4));
        Assert.assertEquals(0,rect.DrawMe().get(4,0));
        Assert.assertEquals(4,rect.Area());
        Assert.assertEquals(true,rect.ShapeType == ShapeType.RECTANGLE);
        Assert.assertEquals(0,rect.origin().X);
        Assert.assertEquals(0,rect.origin().Y);
    }

    @Test
    public void DifferentPointsOrder()
    {
        Rectangle rect = new Rectangle( new PointXY(11,10),new PointXY(5,5),new PointXY(5,10),new PointXY(11,5));
        Rectangle rect2 = new Rectangle( new PointXY(5,10),new PointXY(11,10),new PointXY(11,5),new PointXY(5,5));

        Assert.assertEquals(rect2.DrawMe().get(0,0),rect.DrawMe().get(0,0));
        Assert.assertEquals(rect2.DrawMe().get(5,5),rect.DrawMe().get(5,5));
        Assert.assertEquals(rect2.DrawMe().get(3,2),rect.DrawMe().get(3,2));
        Assert.assertEquals(rect2.DrawMe().get(1,5),rect.DrawMe().get(1,5));
        Assert.assertEquals(rect2.Area(),rect.Area());
        Assert.assertEquals(5,rect.origin().X);
        Assert.assertEquals(5,rect2.origin().X);
    }

    @Test
    public void test3()
    {
        Rectangle rect = new Rectangle( new PointXY(2,1),new PointXY(2,4),new PointXY(7,1),new PointXY(7,4));
        Assert.assertEquals(1,rect.DrawMe().get(0+2,0+1));
        Assert.assertEquals(1,rect.DrawMe().get(5+2,0+1));
        Assert.assertEquals(0,rect.DrawMe().get(0+2,5+1));
        Assert.assertEquals(1,rect.DrawMe().get(2+2,3+1));
        Assert.assertEquals(1,rect.DrawMe().get(3+2,3+1));
        Assert.assertEquals(1,rect.DrawMe().get(3+2,0+1));
        Assert.assertEquals(1,rect.DrawMe().get(1+2,1+1));
        Assert.assertEquals(0,rect.DrawMe().get(0+2,5+1));
        Assert.assertEquals(0,rect.DrawMe().get(5+2,4+1));
        Assert.assertEquals(0,rect.DrawMe().get(5+2,5+1));
        Assert.assertEquals(0,rect.DrawMe().get(1+2,4+1));
        Assert.assertEquals(24,rect.Area());
        Assert.assertEquals(2,rect.origin().X);
        Assert.assertEquals(1,rect.origin().Y);
    }

    @Test
    public void test4()
    {
        Rectangle rect = new Rectangle( new PointXY(1,1),new PointXY(2,1),new PointXY(1,2),new PointXY(2,2));
        Assert.assertEquals(0,rect.DrawMe().get(1,4));
        Assert.assertEquals(0,rect.DrawMe().get(4,1));
        Assert.assertEquals(4,rect.Area());
    }


    @Test
    public void testSomething2() {
        Assert.assertTrue(true);
    }
}

