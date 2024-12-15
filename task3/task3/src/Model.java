import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class Model implements Cloneable {
    public ArrayList<Vector3f> vertices = new ArrayList<>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<>();
    public ArrayList<Vector3f> normals = new ArrayList<>();
    public ArrayList<Polygon> polygons = new ArrayList<>();

    public Model() {}
    public void addPolygon(Polygon polygon) {
        this.polygons.add(polygon);
    }
    public void deletePolygons(List<Integer> indicesToDelete) {
        indicesToDelete.sort((a, b) -> Integer.compare(b, a));
        for (int index : indicesToDelete) {
            if (index >= 0 && index < polygons.size()) {
                polygons.remove(index);
            }
        }
    }
    public ArrayList<Vector3f> cloneVertices() {
        ArrayList<Vector3f> clonedVertices = new ArrayList<>();
        for (Vector3f vertex : this.vertices) {
            clonedVertices.add(vertex.clone());
        }
        return clonedVertices;
    }
    public ArrayList<Vector2f> cloneTextureVertices() {
        ArrayList<Vector2f> clonedTextureVertices = new ArrayList<>();
        for (Vector2f textureVertex : this.textureVertices) {
            clonedTextureVertices.add(textureVertex.clone());
        }
        return clonedTextureVertices;
    }
    public ArrayList<Vector3f> cloneNormals() {
        ArrayList<Vector3f> clonedNormals = new ArrayList<>();
        for (Vector3f normal : this.normals) {
            clonedNormals.add(normal.clone());
        }
        return clonedNormals;
    }
    public ArrayList<Polygon> clonePolygons() {
        ArrayList<Polygon> clonedPolygons = new ArrayList<>();
        for (Polygon polygon : this.polygons) {
            clonedPolygons.add(polygon.clone());
        }
        return clonedPolygons;
    }
    @Override
    public Model clone() {
        try {
            Model clone = (Model) super.clone();
            clone.vertices = new ArrayList<>(this.vertices);
            clone.polygons = new ArrayList<>(this.polygons);
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void exportToOBJ() {
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
                int vertexIndex = polygon.getVertexIndices().get(i) + 1;
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
