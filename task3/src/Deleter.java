import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Deleter {
    public static Model polygonDelete(Model model, List<Integer> polygonIndices) {
        Model newModel = model.clone();
        HashSet<Integer> polygonIndexSet = new HashSet<>(polygonIndices);
        Iterator<Polygon> iterator = newModel.polygons.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Polygon polygon = iterator.next();
            if (polygonIndexSet.contains(index)) {
                iterator.remove();
            }
            index++;
        }
        return newModel;
    }
}