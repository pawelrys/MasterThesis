namespace Math_Graphic.core.common;

public class Bitmap
{
    private readonly int[,] _yourArray;
    public Bitmap(int size)
    {
        _yourArray = new int[size, size];
    }
    public Bitmap(int [,] array)
    {
        _yourArray = array;
    }

    public int GetSize(){
        return _yourArray.Length;
    }

    public int Get(int i, int j){
        return _yourArray[i, j];
    }
    public int[,] GetArray(){
        return _yourArray;
    }
}