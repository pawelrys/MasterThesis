using Math_Graphic.core.common;

namespace Math_Graphic.core.math.shapes;

public class Rectangle : Shape
{
    private readonly PointXy _p1;
    private readonly PointXy _p2;
    private readonly PointXy _p3;
    private readonly PointXy _p4;

    public Rectangle(PointXy p1, PointXy p2, PointXy p3, PointXy p4)
    {
        ShapeType = ShapeType.Rectangle;
        _p1 = new PointXy();
        _p2 = new PointXy();
        _p3 = new PointXy();
        _p4 = new PointXy();

        _p1.X = Math.Min(Math.Min(p1.X, p2.X), Math.Min(p3.X, p4.X));
        _p1.Y = Math.Min(Math.Min(p1.Y, p2.Y), Math.Min(p3.Y, p4.Y));

        _p2.X = Math.Max(Math.Max(p1.X, p2.X), Math.Max(p3.X, p4.X));
        _p2.Y = Math.Min(Math.Min(p1.Y, p2.Y), Math.Min(p3.Y, p4.Y));

        _p3.X = Math.Max(Math.Max(p1.X, p2.X), Math.Max(p3.X, p4.X));
        _p3.Y = Math.Max(Math.Max(p1.Y, p2.Y), Math.Max(p3.Y, p4.Y));

        _p4.X = Math.Min(Math.Min(p1.X, p2.X), Math.Min(p3.X, p4.X));
        _p4.Y = Math.Max(Math.Max(p1.Y, p2.Y), Math.Max(p3.Y, p4.Y));
    }


    public override Bitmap DrawMe()
    {
        var a = _p3.X - _p1.X;
        var b = _p3.Y - _p1.Y;
        var array = new int[1000, 1000];
        for (var i = 0; i <= a; i++)
        {
            for (var j = 0; j <= b; j++)
            {
                array[Origin().X + i, Origin().Y + j] = 1;
            }
        }

        return new Bitmap(array);
    }

    public PointXy Origin()
    {
        return new PointXy(_p1.X, _p1.Y);
    }

    public override int Area()
    {
        var a = _p3.X - _p1.X;
        var b = _p3.Y - _p1.Y;
        return (a + 1) * (b + 1);
    }
}