import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class DeleterTest {
    private Model model;
    @BeforeEach
    public void setUp() {
        model = new Model();
        model.vertices.add(new Vector3f(1.0f, 1.0f, 1.0f));
        model.vertices.add(new Vector3f(-1.0f, -1.0f, -1.0f));
        model.vertices.add(new Vector3f(2.0f, 2.0f, 2.0f));
        // Добавление полигонов
        Polygon polygon = new Polygon(model.vertices);
        polygon.setVertexIndices(new ArrayList<>(List.of(0, 1, 2)));
        model.polygons.add(polygon);
    }

    @Test
    public void testDeletePolygon() {
        model = Deleter.polygonDelete(model, new ArrayList<>(List.of(0)));
        assertEquals(0, model.polygons.size(), "Количество полигонов должно быть 0 после удаления");
    }
    @Test
    public void testDeleteInvalidVertex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Deleter.deleteVertex(model, 999);
        }, "Удаление вершины с неверным индексом должно привести к исключению");
    }
}
