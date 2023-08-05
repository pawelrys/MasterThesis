namespace Math_Graphic.core.math.modules;

public class RealMatrix
{
    private readonly double[][] _data;

    public RealMatrix(double[][] data)
    {
        _data = data;
    }

    public double[][] GetData()
    {
        return _data;
    }

    public void SetEntry(int row, int column, double value)
    {
        _data[row][column] = value;
    }

    public int GetColumnDimension()
    {
        return _data[0].Length;
    }

    public int GetRowDimension()
    {
        return _data.Length;
    }

    public RealMatrix Add(RealMatrix getRealMatrix)
    {
        for (var i = 0; i < _data.Length; i++)
        {
            for (var j = 0; j < _data[i].Length; j++)
            {
                _data[i][j] += getRealMatrix.GetData()[i][j];
            }
        }

        return this;
    }

    public RealMatrix Multiply(RealMatrix getRealMatrix)
    {
        var c = new double[_data.Length][];
        for (var i = 0; i < _data.Length; i++)
        {
            c[i] = new double[_data[i].Length];
            for (var j = 0; j < _data[i].Length; j++)
            {
                c[i][j] = 0;
                for (var k = 0; k < _data.Length; k++)
                {
                    c[i][j] += _data[i][k] * getRealMatrix._data[k][j];
                }
            }
        }

        return new RealMatrix(c);
    }

    public RealMatrix Subtract(RealMatrix getRealMatrix)
    {
        for (var i = 0; i < _data.Length; i++)
        {
            for (var j = 0; j < _data[i].Length; j++)
            {
                _data[i][j] -= getRealMatrix.GetData()[i][j];
            }
        }

        return this;
    }

    public RealMatrix Transpose()
    {
        var w = _data.Length;

        var result = new double[w][];

        for (var i = 0; i < w; i++)
        {
            var h = _data[i].Length;
            result[i] = new double[h];
            for (var j = 0; j < h; j++)
            {
                result[i][j] = _data[j][i];
            }
        }

        return new RealMatrix(result);
    }

    public RealMatrix ScalarMultiply(double scalar)
    {
        foreach (var value in _data)
        {
            for (var j = 0; j < value.Length; j++)
            {
                value[j] *= scalar;
            }
        }

        return this;
    }
}