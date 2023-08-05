namespace Math_Graphic.core.math.modules;

public static class MatrixUtils
{
    public static RealMatrix CreateRealMatrix(int row, int col)
    {
        var realMatrix = new RealMatrix(new double[row][]);
        for (var idx = 0; idx < realMatrix.GetData().Length; idx++)
        {
            realMatrix.GetData()[idx] = new double[col];
        }

        return realMatrix;
    }

    public static RealMatrix CreateRealMatrix(double[][] values)
    {
        return new RealMatrix(values);
    }

    public static RealMatrix CreateRealIdentityMatrix(int size)
    {
        var result = CreateRealMatrix(size, size).GetData();
        for (var i = 0; i < size; ++i)
            result[i][i] = 1.0;

        return new RealMatrix(result);
    }

    public static RealMatrix Inverse(RealMatrix getRealMatrix)
    {
        var matrix = getRealMatrix.GetData();
        var n = matrix.Length;
        var result = MatrixDuplicate(matrix);

        var lum = MatrixDecompose(matrix, out var perm,
            out _);

        var b = new double[n];
        for (var i = 0; i < n; ++i)
        {
            for (var j = 0; j < n; ++j)
            {
                if (i == perm[j])
                    b[j] = 1.0;
                else
                    b[j] = 0.0;
            }

            var x = HelperSolve(lum, b);

            for (var j = 0; j < n; ++j)
                result[j][i] = x[j];
        }

        return new RealMatrix(result);
    }

    private static double[][] MatrixDuplicate(double[][] matrix)
    {
        var result = CreateRealMatrix(matrix.Length, matrix[0].Length).GetData();
        for (var i = 0; i < matrix.Length; ++i)
        for (var j = 0; j < matrix[i].Length; ++j)
            result[i][j] = matrix[i][j];
        return result;
    }

    private static double[] HelperSolve(double[][] luMatrix, double[] b)
    {
        var n = luMatrix.Length;
        var x = new double[n];
        b.CopyTo(x, 0);

        for (var i = 1; i < n; ++i)
        {
            var sum = x[i];
            for (var j = 0; j < i; ++j)
                sum -= luMatrix[i][j] * x[j];
            x[i] = sum;
        }

        x[n - 1] /= luMatrix[n - 1][n - 1];
        for (var i = n - 2; i >= 0; --i)
        {
            var sum = x[i];
            for (var j = i + 1; j < n; ++j)
                sum -= luMatrix[i][j] * x[j];
            x[i] = sum / luMatrix[i][i];
        }

        return x;
    }

    private static double[][] MatrixDecompose(double[][] matrix, out int[] perm,
        out int toggle)
    {
        var rows = matrix.Length;

        var result = MatrixDuplicate(matrix);

        perm = new int[rows];
        for (var i = 0; i < rows; ++i)
        {
            perm[i] = i;
        }

        toggle = 1;
        for (var j = 0; j < rows - 1; ++j)
        {
            var colMax = Math.Abs(result[j][j]);
            var pRow = j;

            for (var i = j + 1; i < rows; ++i)
            {
                if (!(Math.Abs(result[i][j]) > colMax)) continue;
                colMax = Math.Abs(result[i][j]);
                pRow = i;
            }

            if (pRow != j)
            {
                (result[pRow], result[j]) = (result[j], result[pRow]);

                (perm[pRow], perm[j]) = (perm[j], perm[pRow]);

                toggle = -toggle;
            }

            if (result[j][j] == 0.0)
            {
                var goodRow = -1;
                for (var row = j + 1; row < rows; ++row)
                {
                    if (result[row][j] != 0.0)
                        goodRow = row;
                }

                (result[goodRow], result[j]) = (result[j], result[goodRow]);

                (perm[goodRow], perm[j]) = (perm[j], perm[goodRow]);

                toggle = -toggle;
            }

            for (var i = j + 1; i < rows; ++i)
            {
                result[i][j] /= result[j][j];
                for (var k = j + 1; k < rows; ++k)
                {
                    result[i][k] -= result[i][j] * result[j][k];
                }
            }
        }

        return result;
    }

    public static double MatrixDeterminant(double[][] matrix)
    {
        var lum = MatrixDecompose(matrix, out _, out var toggle);
        double result = toggle;
        for (var i = 0; i < lum.GetLength(0); ++i)
            result *= lum[i][i];
        return result;
    }
}