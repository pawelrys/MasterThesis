using Math_Graphic.core.math.modules;

namespace Math_Graphic.core.math.matrix;

public class MatrixMath : IMatrixMath
{
    public IMatrix InverseMatrix(IMatrix m1)
    {
        return m1.CreateIMatrix(MatrixUtils.Inverse(m1.GetRealMatrix()));
    }


    public IMatrix MatrixAddition(IMatrix m1, IMatrix m2)
    {
        if (m1.GetHeight() != m2.GetHeight() || m1.GetWidth() != m2.GetWidth()) throw new InvalidDimensionException();
        return m1.CreateIMatrix(m1.GetRealMatrix().Add(m2.GetRealMatrix()));
    }

    public IMatrix MatrixMultiplication(IMatrix m1, IMatrix m2)
    {
        if (m1.GetWidth() != m2.GetHeight()) throw new InvalidDimensionException();
        return m1.CreateIMatrix(m1.GetRealMatrix().Multiply(m2.GetRealMatrix()));
    }

    public IMatrix MatrixSubtracting(IMatrix m1, IMatrix m2)
    {
        if (m1.GetHeight() != m2.GetHeight() || m1.GetWidth() != m2.GetWidth()) throw new InvalidDimensionException();
        return m1.CreateIMatrix(m1.GetRealMatrix().Subtract(m2.GetRealMatrix()));
    }

    public IMatrix MatrixTransposition(IMatrix m1)
    {
        return m1.CreateIMatrix(m1.GetRealMatrix().Transpose());
    }

    public IMatrix ScalarMultiplication(IMatrix m1, double scalar)
    {
        return m1.CreateIMatrix(m1.GetRealMatrix().ScalarMultiply(scalar));
    }
}