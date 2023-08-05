using Math_Graphic.core.math.shapes;

namespace Math_Graphic.Tests.tests;

public class TriangleTest
{
    [Test]
    public void test1()
    {
        var triangle = new Triangle(new PointXy(0, 0), new PointXy(0, 1), new PointXy(1, 0));


        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 0)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 1)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(1, 0)));
        Assert.That(3, Is.EqualTo(triangle.Area()));
    }

    [Test]
    public void test2()
    {
        var triangle = new Triangle(new PointXy(0, 0), new PointXy(0, 6), new PointXy(3, 3));

        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 0)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 1)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 2)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 3)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 4)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 5)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(0, 6)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(1, 1)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(2, 2)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(3, 3)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(2, 4)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(1, 0)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(1, 6)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(3, 4)));
        Assert.That(16, Is.EqualTo(triangle.Area()));
    }

    [Test]
    public void test3()
    {
        var triangle = new Triangle(new PointXy(1, 4), new PointXy(5, 6), new PointXy(3, 3));
        var triangle2 = new Triangle(new PointXy(5, 6), new PointXy(3, 3), new PointXy(1, 4));
        
        Assert.That(triangle2.DrawMe().Get(0, 0), Is.EqualTo(triangle.DrawMe().Get(0, 0)));
        Assert.That(triangle2.DrawMe().Get(0, 1), Is.EqualTo(triangle.DrawMe().Get(0, 1)));
        Assert.That(triangle2.DrawMe().Get(0, 2), Is.EqualTo(triangle.DrawMe().Get(0, 2)));
        Assert.That(triangle2.DrawMe().Get(0, 3), Is.EqualTo(triangle.DrawMe().Get(0, 3)));
        Assert.That(triangle2.DrawMe().Get(0, 4), Is.EqualTo(triangle.DrawMe().Get(0, 4)));
        Assert.That(triangle2.DrawMe().Get(1, 1), Is.EqualTo(triangle.DrawMe().Get(1, 1)));
        Assert.That(triangle2.DrawMe().Get(2, 2), Is.EqualTo(triangle.DrawMe().Get(2, 2)));
        Assert.That(triangle2.DrawMe().Get(3, 3), Is.EqualTo(triangle.DrawMe().Get(3, 3)));
        Assert.That(triangle2.DrawMe().Get(2, 4), Is.EqualTo(triangle.DrawMe().Get(2, 4)));
        Assert.That(triangle2.DrawMe().Get(1, 2), Is.EqualTo(triangle.DrawMe().Get(1, 2)));
        Assert.That(triangle2.DrawMe().Get(4, 3), Is.EqualTo(triangle.DrawMe().Get(4, 3)));
        Assert.That(1, Is.EqualTo(triangle.Origin().X));
        Assert.That(1, Is.EqualTo(triangle2.Origin().X));
        Assert.That(3, Is.EqualTo(triangle.Origin().Y));
        Assert.That(3, Is.EqualTo(triangle2.Origin().Y));
    }

    [Test]
    public void test4()
    {
        var triangle = new Triangle(new PointXy(1, 1), new PointXy(1, 5), new PointXy(5, 1));
        
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 0)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 1)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 2)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 3)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(1, 6)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(1, 8)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 11)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(8, 1)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(6, 1)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(5, 5)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(7, 6)));
    }

    [Test]
    public void test5()
    {
        var triangle = new Triangle(new PointXy(3, 0), new PointXy(0, 2), new PointXy(6, 2));
        
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 0)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 1)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(6, 1)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(7, 2)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(5, 2)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 1)));
    }

    [Test]
    public void test6()
    {
        var triangle = new Triangle(new PointXy(0, 0), new PointXy(6, 2), new PointXy(2, 5));
        
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 3)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(5, 1)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(3, 1)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(1, 1)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(4, 3)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(5, 5)));
    }

    [Test]
    public void test7()
    {
        var triangle = new Triangle(new PointXy(3, 0), new PointXy(2, 2), new PointXy(0, 0));
        
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(0, 1)));
        Assert.That(0, Is.EqualTo(triangle.DrawMe().Get(3, 3)));
        Assert.That(1, Is.EqualTo(triangle.DrawMe().Get(1, 0)));
    }


    [Test]
    public void testSomething2()
    {
        Assert.That(true, Is.True);
    }
}