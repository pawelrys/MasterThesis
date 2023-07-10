package core;

import static org.junit.Assert.assertEquals;

import core.Math.Shapes.PointXY;
import core.Math.Shapes.Triangle;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Michal on 2018-04-07.
 */
public class  TriangleTest {

    @Test
    public void test1() {

        Triangle triangle = new Triangle( new PointXY(0,0),new PointXY(0,1),new PointXY(1,0));




        Assert.assertEquals(1,triangle.DrawMe().get(0,0));
        Assert.assertEquals(1,triangle.DrawMe().get(0,1));
        Assert.assertEquals(1,triangle.DrawMe().get(1,0));
        Assert.assertEquals(3,triangle.Area());
    }

    @Test
    public void test2() {

        Triangle triangle = new Triangle( new PointXY(0,0),new PointXY(0,6),new PointXY(3,3));

        Assert.assertEquals(1,triangle.DrawMe().get(0,0));
        Assert.assertEquals(1,triangle.DrawMe().get(0,1));
        Assert.assertEquals(1,triangle.DrawMe().get(0,2));
        Assert.assertEquals(1,triangle.DrawMe().get(0,3));
        Assert.assertEquals(1,triangle.DrawMe().get(0,4));
        Assert.assertEquals(1,triangle.DrawMe().get(0,5));
        Assert.assertEquals(1,triangle.DrawMe().get(0,6));
        Assert.assertEquals(1,triangle.DrawMe().get(1,1));
        Assert.assertEquals(1,triangle.DrawMe().get(2,2));
        Assert.assertEquals(1,triangle.DrawMe().get(3,3));
        Assert.assertEquals(1,triangle.DrawMe().get(2,4));
        Assert.assertEquals(0,triangle.DrawMe().get(1,0));
        Assert.assertEquals(0,triangle.DrawMe().get(1,6));
        Assert.assertEquals(0,triangle.DrawMe().get(3,4));
        Assert.assertEquals(16,triangle.Area());
    }

    @Test
    public void test3() {

        Triangle triangle = new Triangle( new PointXY(1,4),new PointXY(5,6),new PointXY(3,3));
        Triangle triangle2 = new Triangle( new PointXY(5,6),new PointXY(3,3),new PointXY(1,4));
        Assert.assertEquals(triangle2.DrawMe().get(0,0),triangle.DrawMe().get(0,0));
        Assert.assertEquals(triangle2.DrawMe().get(0,1),triangle.DrawMe().get(0,1));
        Assert.assertEquals(triangle2.DrawMe().get(0,2),triangle.DrawMe().get(0,2));
        Assert.assertEquals(triangle2.DrawMe().get(0,3),triangle.DrawMe().get(0,3));
        Assert.assertEquals(triangle2.DrawMe().get(0,4),triangle.DrawMe().get(0,4));
        Assert.assertEquals(triangle2.DrawMe().get(1,1),triangle.DrawMe().get(1,1));
        Assert.assertEquals(triangle2.DrawMe().get(2,2),triangle.DrawMe().get(2,2));
        Assert.assertEquals(triangle2.DrawMe().get(3,3),triangle.DrawMe().get(3,3));
        Assert.assertEquals(triangle2.DrawMe().get(2,4),triangle.DrawMe().get(2,4));
        Assert.assertEquals(triangle2.DrawMe().get(1,2),triangle.DrawMe().get(1,2));
        Assert.assertEquals(triangle2.DrawMe().get(4,3),triangle.DrawMe().get(4,3));
        Assert.assertEquals(1,triangle.origin().X);
        Assert.assertEquals(1,triangle2.origin().X);
        Assert.assertEquals(3,triangle.origin().Y);
        Assert.assertEquals(3,triangle2.origin().Y);
    }

    @Test
    public void test4() {
        Triangle triangle = new Triangle( new PointXY(1,1),new PointXY(1,5),new PointXY(5,1));
        Assert.assertEquals(0,triangle.DrawMe().get(0,0));
        Assert.assertEquals(0,triangle.DrawMe().get(0,1));
        Assert.assertEquals(0,triangle.DrawMe().get(0,2));
        Assert.assertEquals(0,triangle.DrawMe().get(0,3));
        Assert.assertEquals(0,triangle.DrawMe().get(1,6));
        Assert.assertEquals(0,triangle.DrawMe().get(1,8));
        Assert.assertEquals(0,triangle.DrawMe().get(0,11));
        Assert.assertEquals(0,triangle.DrawMe().get(8,1));
        Assert.assertEquals(0,triangle.DrawMe().get(6,1));
        Assert.assertEquals(0,triangle.DrawMe().get(5,5));
        Assert.assertEquals(0,triangle.DrawMe().get(7,6));
    }

    @Test
    public void test5() {
        Triangle triangle = new Triangle( new PointXY(3,0),new PointXY(0,2),new PointXY(6,2));
        Assert.assertEquals(0,triangle.DrawMe().get(0,0));
        Assert.assertEquals(0,triangle.DrawMe().get(0,1));
        Assert.assertEquals(0,triangle.DrawMe().get(6,1));
        Assert.assertEquals(0,triangle.DrawMe().get(7,2));
        Assert.assertEquals(1,triangle.DrawMe().get(5,2));
        Assert.assertEquals(0,triangle.DrawMe().get(0,1));

    }

    @Test
    public void test6() {
        Triangle triangle = new Triangle( new PointXY(0,0),new PointXY(6,2),new PointXY(2,5));
        Assert.assertEquals(0,triangle.DrawMe().get(0,3));
        Assert.assertEquals(0,triangle.DrawMe().get(5,1));
        Assert.assertEquals(1,triangle.DrawMe().get(3,1));
        Assert.assertEquals(1,triangle.DrawMe().get(1,1));
        Assert.assertEquals(1,triangle.DrawMe().get(4,3));
        Assert.assertEquals(0,triangle.DrawMe().get(5,5));

    }

    @Test
    public void test7() {
        Triangle triangle = new Triangle( new PointXY(3,0),new PointXY(2,2),new PointXY(0,0));
        Assert.assertEquals(0,triangle.DrawMe().get(0,1));
        Assert.assertEquals(0,triangle.DrawMe().get(3,3));
        Assert.assertEquals(1,triangle.DrawMe().get(1,0));;

    }


    @Test
    public void testSomething2() {
        Assert.assertTrue(true);
    }
}

