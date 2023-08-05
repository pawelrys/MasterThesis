using Math_Graphic.core.common;

namespace Math_Graphic.core.math.shapes;

public class Triangle : Shape
{
    private readonly PointXy _p1;
    private readonly PointXy _p2;
    private readonly PointXy _p3;
    private int _area = -1;

    public Triangle(PointXy p1, PointXy p2, PointXy p3)
    {
        ShapeType = ShapeType.Triangle;

        _p1 = p1;
        _p2 = p2;
        _p3 = p3;
    }


    private bool PointInTriangle(int x, int y)
    {
        var s = _p1.Y * _p3.X - _p1.X * _p3.Y + (_p3.Y - _p1.Y) * x + (_p1.X - _p3.X) * y;
        var t = _p1.X * _p2.Y - _p1.Y * _p2.X + (_p1.Y - _p2.Y) * x + (_p2.X - _p1.X) * y;


        var a = -_p2.Y * _p3.X + _p1.Y * (_p3.X - _p2.X) + _p1.X * (_p2.Y - _p3.Y) + _p2.X * _p3.Y;
        if (a >= 1) return s >= 0 && t >= 0 && (s + t) <= a;
        s = -s;
        t = -t;
        a = -a;
        return s >= 0 && t >= 0 && (s + t) <= a;
    }


    public override Bitmap DrawMe()
    {
        _area = 0;
        var sizeX = Math.Max(Math.Max(_p1.X, _p2.X), _p3.X) - Math.Min(Math.Min(_p1.X, _p2.X), _p3.X);
        var sizeY = Math.Max(Math.Max(_p1.Y, _p2.Y), _p3.Y) - Math.Min(Math.Min(_p1.Y, _p2.Y), _p3.Y);
        var array = new int[1000, 1000];
        for (var i = 0; i <= sizeX; i++)
        {
            for (var j = 0; j <= sizeY; j++)
            {
                if (!PointInTriangle(i, j)) continue;
                array[Origin().X + i, Origin().X + j] = 1;
                _area++;
            }
        }

        return new Bitmap(array);
    }

    public PointXy Origin()
    {
        var x1 = Math.Min(Math.Min(_p1.X, _p2.X), _p3.X);
        var y1 = Math.Min(Math.Min(_p1.Y, _p2.Y), _p3.Y);
        return new PointXy(x1, y1);
    }

    public override int Area()
    {
        if (_area == -1) DrawMe();
        return _area;
    }
}