import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Deleter {
    public static Model polygonDelete(Model model, List<Integer> polygonIndices) {
        // Клонируем модель, чтобы не изменять оригинал
        Model newModel = model.clone();

        Iterator<Polygon> iterator = newModel.polygons.iterator();
        while (iterator.hasNext()) {
            Polygon polygon = iterator.next();
            int index = newModel.polygons.indexOf(polygon);
            if (polygonIndices.contains(index)) {
                iterator.remove(); // Удаляем полигон по индексу
            }
        }

        return newModel; // Возвращаем измененную модель
    }
}
