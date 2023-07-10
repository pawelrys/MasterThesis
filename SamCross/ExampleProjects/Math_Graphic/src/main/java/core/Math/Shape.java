package  core.Math;

/**
 * Created by Michal on 2018-04-07.
 */
public abstract class Shape implements IGemoetricObj {
       /**
        * typ
        * @return
        */
       public ShapeType ShapeType;
       /**
        * Zakoloruj liczy pole powierchni
        */
       public abstract int Area();
}
