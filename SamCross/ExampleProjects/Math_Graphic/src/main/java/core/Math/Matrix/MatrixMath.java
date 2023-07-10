package  core.Math.Matrix;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Michal on 2018-04-08.
 */
public class MatrixMath implements IMatrixMath {
    @Override
    public IMatrix inverseMatrix(IMatrix m1) {
        return m1.createIMatrix(MatrixUtils.inverse(m1.getRealMatrix()));
    }

    @Override

    public IMatrix matrixAddition(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if(m1.getHeight() != m2.getHeight() || m1.getWidth() != m2.getWidth()) throw new InvalidDimensionException();
        return m1.createIMatrix(m1.getRealMatrix().add(m2.getRealMatrix()));
    }

    @Override
    public IMatrix matrixMultiplication(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if(m1.getWidth() != m2.getHeight()) throw new InvalidDimensionException();
        return m1.createIMatrix(m1.getRealMatrix().multiply(m2.getRealMatrix()));
    }

    @Override
    public IMatrix matrixSubtracting(IMatrix m1, IMatrix m2) throws InvalidDimensionException {
        if(m1.getHeight() != m2.getHeight() || m1.getWidth() != m2.getWidth()) throw new InvalidDimensionException();
        return m1.createIMatrix(m1.getRealMatrix().subtract(m2.getRealMatrix()));
    }

    @Override
    public IMatrix matrixTransposition(IMatrix m1) throws InvalidDimensionException {
        return m1.createIMatrix(m1.getRealMatrix().transpose());
    }

    @Override
    public IMatrix scalarMultiplication(IMatrix m1, double scalar) {
        return m1.createIMatrix(m1.getRealMatrix().scalarMultiply(scalar));
    }
}
