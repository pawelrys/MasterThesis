package  core.Comon;

/**
 * Created by Michal on 2018-04-07.
 */
public class Bitmap {

    public int[][] yourArray; // tablica kontener danych na obiekty graficzne.
    public Bitmap(int size)
    {
        yourArray = new int[size][size];
    }
    public Bitmap(int array[][])
    {
        yourArray = array;
    }

    public int GetSize(){
        return yourArray.length;
    }

    public int get(int i, int j){
        return yourArray[i][j];
    }
    public int[][] getArray(){
        return yourArray;
    }

}
