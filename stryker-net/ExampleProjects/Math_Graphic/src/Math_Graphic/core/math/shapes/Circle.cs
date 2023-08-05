using Math_Graphic.core.common;

namespace Math_Graphic.core.math.shapes;

public class Circle : Shape
{
    private readonly PointXy _center;
    private int _area = -1;
    private readonly int _r;

    public Circle(PointXy center, int r)
    {
        _center = center;
        _r = r;
        ShapeType = ShapeType.Circle;
    }

    public override Bitmap DrawMe()
    {
        _area = 0;
        var array = new int[1000, 1000];
        for (var i = 0; i <= 2 * _r; i++)
        {
            for (var j = 0; j <= 2 * _r; j++)
            {
                if ((i - _r) * (i - _r) + (j - _r) * (j - _r) > _r * _r) continue;
                array[Origin().X + i, Origin().Y + j] = 1;
                _area++;
            }
        }

        return new Bitmap(array);
    }

    public PointXy Origin()
    {
        return new PointXy(_center.X - _r, _center.Y - _r);
    }

    public override int Area()
    {
        if (_area == -1) DrawMe();
        return _area;
    }
}