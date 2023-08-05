using Math_Graphic.core.math.shapes;

namespace Math_Graphic.Tests.tests;

public class CircleTest
{
    [Test]
    public void testOnePixelCircle()
    {
        var rect = new Circle(new PointXy(0, 0), 0);

        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(0, 0)));
        Assert.That(1, Is.EqualTo(rect.Area()));
    }

    [Test]
    public void testOneLengthRadius()
    {
        var rect = new Circle(new PointXy(2, 2), 1);
        
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0, 0)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0 + 1, 0 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(0 + 1, 1 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0 + 1, 2 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(1 + 1, 0 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(1 + 1, 1 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(1 + 1, 2 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(2 + 1, 0 + 1)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(2 + 1, 1 + 1)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(2 + 1, 2 + 1)));
        Assert.That(5, Is.EqualTo(rect.Area()));
        Assert.That(1, Is.EqualTo(rect.Origin().X));
        Assert.That(1, Is.EqualTo(rect.Origin().Y));
    }

    [Test]
    public void Test1()
    {
        var rect = new Circle(new PointXy(5, 6), 4);

        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(4 + 1, 4 + 2)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(0 + 1, 1 + 2)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(4 + 1, 0 + 2)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(0 + 1, 4 + 2)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(4 + 1, 8 + 2)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(8 + 1, 4 + 2)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(7 + 1, 6 + 2)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(7 + 1, 7 + 2)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(5 + 1, 5 + 2)));
        Assert.That(1, Is.EqualTo(rect.DrawMe().Get(1 + 1, 6 + 2)));
        Assert.That(0, Is.EqualTo(rect.DrawMe().Get(1 + 1, 1 + 2)));
        Assert.That(49, Is.EqualTo(rect.Area()));
        Assert.That(1, Is.EqualTo(rect.Origin().X));
        Assert.That(2, Is.EqualTo(rect.Origin().Y));
    }
}