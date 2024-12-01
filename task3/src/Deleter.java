import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Deleter {

    // Метод для удаления полигонов по их индексам
    public static Model polygonDelete(Model model, List<Integer> polygonIndices) {
        // Клонируем модель, чтобы не изменять оригинал
        Model newModel = model.clone();

        // Удаляем полигоны
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
