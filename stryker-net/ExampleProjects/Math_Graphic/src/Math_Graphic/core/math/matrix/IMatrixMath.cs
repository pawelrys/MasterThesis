namespace Math_Graphic.core.math.matrix;

public interface IMatrixMath
{
    IMatrix InverseMatrix(IMatrix m1);

    IMatrix MatrixAddition(IMatrix m1, IMatrix m2);

    IMatrix MatrixMultiplication(IMatrix m1, IMatrix m2);

    IMatrix MatrixSubtracting(IMatrix m1, IMatrix m2);

    IMatrix MatrixTransposition(IMatrix m1);

    IMatrix ScalarMultiplication(IMatrix m1, double scalar);
}