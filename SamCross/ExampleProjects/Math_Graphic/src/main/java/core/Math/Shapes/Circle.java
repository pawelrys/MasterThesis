package  core.Math.Shapes;

/**
 * Created by Michal on 2018-04-07.
 */
import core.Comon.Bitmap;
import  core.Math.Shape;
import core.Math.ShapeType;

public class Circle extends Shape {
    PointXY center;
    int area = -1;
    int r;
    public Circle(PointXY center, int r){
        this.center = center;
        this.r = r;
        ShapeType = core.Math.ShapeType.CIRCLE;
    }
    @Override
    public Bitmap DrawMe() {
        area = 0;
        int array[][] = new int[1000][1000];
        for(int i=0; i<=2*r; i++){
            for(int j=0; j<=2*r; j++) {
                if((i - r) * (i - r) + (j - r) * (j - r) <= r*r){
                    array[origin().X+i][origin().Y+j] = 1;
                    area++;
                }
            }
        }
        return new Bitmap(array);
    }

    public PointXY origin(){
        return new PointXY(center.X-r, center.Y-r);
    }

    @Override
    public int Area(){
        if(area == -1) DrawMe();
        return area;
    }
}
