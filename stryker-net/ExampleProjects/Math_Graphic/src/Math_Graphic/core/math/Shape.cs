using Math_Graphic.core.common;

namespace Math_Graphic.core.math;

public abstract class Shape : IGeometricObj
{
    public ShapeType ShapeType;
    public abstract int Area();
    public abstract Bitmap DrawMe();
}