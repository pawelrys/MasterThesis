namespace Math_Graphic.core.math.matrix;

public class InvalidDimensionException : Exception
{
    public InvalidDimensionException() : base(){}

    public InvalidDimensionException(string msg) : base(msg) {}
}