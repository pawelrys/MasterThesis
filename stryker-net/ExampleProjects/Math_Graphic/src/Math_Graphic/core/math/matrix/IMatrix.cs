using Math_Graphic.core.math.modules;

namespace Math_Graphic.core.math.matrix;

public interface IMatrix
{
    IMatrix CreateIMatrix(RealMatrix realMatrix);
    void CreateIdentityMatrix(int size);

    double Determinant();

    double GetMatrixValue(int row, int column);

    void SetMatrixValue(int row, int column, double value);

    void SetMatrixValues(double[][] values);

    int GetWidth();
    int GetHeight();
    RealMatrix GetRealMatrix();
    string ToString();
}