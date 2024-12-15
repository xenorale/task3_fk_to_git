import java.util.ArrayList;
import java.util.Locale;

public class Model implements Cloneable {
    public ArrayList<Vector3f> vertices = new ArrayList<>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<>();
    public ArrayList<Vector3f> normals = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();

    public Model() {}

    // Метод клонирования вершин
    public ArrayList<Vector3f> cloneVertices() {
        ArrayList<Vector3f> clonedVertices = new ArrayList<>();
        for (Vector3f vertex : this.vertices) {
            clonedVertices.add(vertex.clone()); // Предполагается, что Vector3f поддерживает clone()
        }
        return clonedVertices;
    }

    // Метод клонирования текстурных вершин
    public ArrayList<Vector2f> cloneTextureVertices() {
        ArrayList<Vector2f> clonedTextureVertices = new ArrayList<>();
        for (Vector2f textureVertex : this.textureVertices) {
            clonedTextureVertices.add(textureVertex.clone()); // Предполагается, что Vector2f поддерживает clone()
        }
        return clonedTextureVertices;
    }

    // Метод клонирования нормалей
    public ArrayList<Vector3f> cloneNormals() {
        ArrayList<Vector3f> clonedNormals = new ArrayList<>();
        for (Vector3f normal : this.normals) {
            clonedNormals.add(normal.clone());
        }
        return clonedNormals;
    }

    // Метод клонирования полигонов
    public ArrayList<Polygon> clonePolygons() {
        ArrayList<Polygon> clonedPolygons = new ArrayList<>();
        for (Polygon polygon : this.polygons) {
            clonedPolygons.add(polygon.clone());
        }
        return clonedPolygons;
    }

    // Метод clone
    @Override
    public Model clone() {
        Model clonedModel = new Model();
        clonedModel.vertices = this.cloneVertices();
        clonedModel.textureVertices = this.cloneTextureVertices();
        clonedModel.normals = this.cloneNormals();
        clonedModel.polygons = this.clonePolygons();
        return clonedModel;
    }

    // Экспорт модели в формат OBJ
    public void exportToOBJ() {
        // Используем локаль US только в рамках форматирования строк
        for (Vector3f vertex : vertices) {
            System.out.printf(Locale.US, "v %.6f %.6f %.6f%n", vertex.getX(), vertex.getY(), vertex.getZ());
        }

        for (Vector3f normal : normals) {
            System.out.printf(Locale.US, "vn %.6f %.6f %.6f%n", normal.getX(), normal.getY(), normal.getZ());
        }

        for (Vector2f textureVertex : textureVertices) {
            System.out.printf(Locale.US, "vt %.6f %.6f%n", textureVertex.getX(), textureVertex.getY());
        }

        for (Polygon polygon : polygons) {
            System.out.print("f");
            for (int i = 0; i < polygon.getVertexIndices().size(); i++) {
                StringBuilder facePart = new StringBuilder();

                int vertexIndex = polygon.getVertexIndices().get(i) + 1; // Индексация начинается с 1
                facePart.append(vertexIndex);

                if (!polygon.getTextureVertexIndices().isEmpty() && i < polygon.getTextureVertexIndices().size()) {
                    int textureIndex = polygon.getTextureVertexIndices().get(i) + 1;
                    facePart.append("/").append(textureIndex);
                } else {
                    facePart.append("/");
                }

                if (!polygon.getNormalIndices().isEmpty() && i < polygon.getNormalIndices().size()) {
                    int normalIndex = polygon.getNormalIndices().get(i) + 1;
                    facePart.append("/").append(normalIndex);
                }

                System.out.print(" " + facePart);
            }
            System.out.println();
        }
    }
}
