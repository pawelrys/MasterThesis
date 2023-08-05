using Math_Graphic.core.math.modules;

namespace Math_Graphic.core.math.matrix;

public class Matrix : IMatrix
{
    private RealMatrix _realMatrix;

    public Matrix(RealMatrix realMatrix)
    {
        _realMatrix = realMatrix;
    }

    public Matrix()
    {
        _realMatrix = MatrixUtils.CreateRealMatrix(3, 3);
    }

    public IMatrix CreateIMatrix(RealMatrix realMatrix)
    {
        return new Matrix(realMatrix);
    }

    public void CreateIdentityMatrix(int size)
    {
        _realMatrix = MatrixUtils.CreateRealIdentityMatrix(size);
    }

    public double Determinant()
    {
        return MatrixUtils.MatrixDeterminant(_realMatrix.GetData());
    }

    public double GetMatrixValue(int row, int column)
    {
        return _realMatrix.GetData()[row][column];
    }


    public void SetMatrixValue(int row, int column, double value)
    {
        _realMatrix.SetEntry(row, column, value);
    }

    public void SetMatrixValues(double[][] values)
    {
        _realMatrix = MatrixUtils.CreateRealMatrix(values);
    }

    public int GetWidth()
    {
        return _realMatrix.GetColumnDimension();
    }

    public int GetHeight()
    {
        return _realMatrix.GetRowDimension();
    }

    public override string ToString()
    {
        var data = _realMatrix.GetData();
        var row = data.Length;
        var col = data[0].Length;
        var result = "<table>";
        for (var i = 0; i < row; i++)
        {
            result += "\n<tr>\n";
            for (var j = 0; j < col; j++)
            {
                result += "<td>" + $"{data[i][j]:F1}" + "</td> ";
            }

            result += "\n</tr>";
        }

        result += "\n</table>";
        return result;
    }

    public RealMatrix GetRealMatrix()
    {
        return _realMatrix;
    }
}