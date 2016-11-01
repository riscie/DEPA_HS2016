package patterns.composite;

import java.security.acl.Group;
import java.util.LinkedList;
import java.util.List;

public class GroupFigure extends Figure {
    private String name;
    private List<Figure> figures = new LinkedList<>();


    public GroupFigure(String name, Figure... figures) {
        this.name = name;
        for (Figure f : figures) {
            addFigure(f);
        }
    }

    public void addFigure(Figure f) {
        if (f instanceof GroupFigure) {
            GroupFigure gf = (GroupFigure) f;
            if (!gf.contains(this)) {
                figures.add(f);
            } else {
                System.out.println("ZYKLUS! ");
            }
        } else {
            figures.add(f);
        }
    }

    @Override
    public void draw(String prefix) {
        System.out.println(prefix + name);
        for (Figure f : figures) {
            f.draw(prefix + ">>");
        }
    }


    private boolean contains(Figure figure) {
        for (Figure f : figures) {
            if (f instanceof GroupFigure) {
                GroupFigure gf = (GroupFigure) f;
                if (gf.contains(figure)) {
                    return true;
                }
            } else {
                if (figure == f) {
                    return true;
                }
            }
        }
        return false;
    }
}
