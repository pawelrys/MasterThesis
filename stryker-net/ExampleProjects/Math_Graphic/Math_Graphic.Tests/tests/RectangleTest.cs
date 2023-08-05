using Math_Graphic.core.math;
using Math_Graphic.core.math.shapes;

namespace Math_Graphic.Tests.tests;

public class RectangleTest
{
    [Test]
    public void testOnePixelRectangle()
    {
        var rect = new Rectangle(new PointXy(0, 0), new PointXy(0, 0), new PointXy(0, 0), new PointXy(0, 0));

        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(0, 0)));
        Assert.That(1, Is.EqualTo(rect.Area()));
    }

    [Test]
    public void test1()
    {
        var rect = new Rectangle(new PointXy(0, 0), new PointXy(0, 0), new PointXy(0, 3), new PointXy(0, 3));
        int[][] array =
            { new int[] { 1, 1, 1, 1 }, new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 0 } };
        
        Assert.That(array[0][0], Is.EqualTo(rect.DrawMe().Get(0, 0)));
        Assert.That(array[0][1], Is.EqualTo(rect.DrawMe().Get(0, 1)));
        Assert.That(array[0][2], Is.EqualTo(rect.DrawMe().Get(0, 2)));
        Assert.That(array[0][3], Is.EqualTo(rect.DrawMe().Get(0, 3)));
        Assert.That(array[1][0], Is.EqualTo(rect.DrawMe().Get(1, 0)));
        Assert.That(array[3][0], Is.EqualTo(rect.DrawMe().Get(3, 0)));
        Assert.That(array[3][3], Is.EqualTo(rect.DrawMe().Get(3, 3)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0, 4)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(4, 0)));
        Assert.That(4, Is.EqualTo(rect.Area()));
        Assert.That(true, Is.EqualTo(rect.ShapeType == ShapeType.Rectangle));
    }

    [Test]
    public void test2()
    {
        var rect = new Rectangle(new PointXy(0, 0), new PointXy(0, 0), new PointXy(3, 0), new PointXy(3, 0));
        int[][] array =
            { new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 0, 0 }, new int[] { 1, 0, 0, 0 } };
        
        Assert.That(array[0][0], Is.EqualTo(rect.DrawMe().Get(0, 0)));
        Assert.That(array[1][0], Is.EqualTo(rect.DrawMe().Get(1, 0)));
        Assert.That(array[2][0], Is.EqualTo(rect.DrawMe().Get(2, 0)));
        Assert.That(array[3][0], Is.EqualTo(rect.DrawMe().Get(3, 0)));
        Assert.That(array[0][1], Is.EqualTo(rect.DrawMe().Get(0, 1)));
        Assert.That(array[0][3], Is.EqualTo(rect.DrawMe().Get(0, 3)));
        Assert.That(array[3][3], Is.EqualTo(rect.DrawMe().Get(3, 3)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0, 4)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(4, 0)));
        Assert.That(4, Is.EqualTo(rect.Area()));
        Assert.That(true, Is.EqualTo(rect.ShapeType == ShapeType.Rectangle));
        Assert.That(0, Is.EqualTo(rect.Origin().X));
        Assert.That(0, Is.EqualTo(rect.Origin().Y));
    }

    [Test]
    public void DifferentPointsOrder()
    {
        var rect = new Rectangle(new PointXy(11, 10), new PointXy(5, 5), new PointXy(5, 10), new PointXy(11, 5));
        var rect2 = new Rectangle(new PointXy(5, 10), new PointXy(11, 10), new PointXy(11, 5), new PointXy(5, 5));

        Assert.That(rect2.DrawMe().Get(0, 0), Is.EqualTo(rect.DrawMe().Get(0, 0)));
        Assert.That(rect2.DrawMe().Get(5, 5), Is.EqualTo(rect.DrawMe().Get(5, 5)));
        Assert.That(rect2.DrawMe().Get(3, 2), Is.EqualTo(rect.DrawMe().Get(3, 2)));
        Assert.That(rect2.DrawMe().Get(1, 5), Is.EqualTo(rect.DrawMe().Get(1, 5)));
        Assert.That(rect2.Area(), Is.EqualTo(rect.Area()));
        Assert.That(5, Is.EqualTo(rect.Origin().X));
        Assert.That(5, Is.EqualTo(rect2.Origin().X));
    }

    [Test]
    public void test3()
    {
        var rect = new Rectangle(new PointXy(2, 1), new PointXy(2, 4), new PointXy(7, 1), new PointXy(7, 4));
        
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(0 + 2, 0 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(5 + 2, 0 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0 + 2, 5 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(2 + 2, 3 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(3 + 2, 3 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(3 + 2, 0 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(1 + 2, 1 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0 + 2, 5 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(5 + 2, 4 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(5 + 2, 5 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(1 + 2, 4 + 1)));
        Assert.That(24, Is.EqualTo(rect.Area()));
        Assert.That(2, Is.EqualTo(rect.Origin().X));
        Assert.That(1, Is.EqualTo(rect.Origin().Y));
    }

    [Test]
    public void test4()
    {
        var rect = new Rectangle(new PointXy(1, 1), new PointXy(2, 1), new PointXy(1, 2), new PointXy(2, 2));
        
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(1, 4)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(4, 1)));
        Assert.That(4, Is.EqualTo(rect.Area()));
    }


    [Test]
    public void testSomething2()
    {
        Assert.That(true, Is.True);
    }
}