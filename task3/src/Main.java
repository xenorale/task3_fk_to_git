import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        try {
            Path fileName = Path.of("../../Torus.obj");
            String fileContent = Files.readString(fileName);
            System.out.println("Loading model ...");

            Model model = ObjReader.read(fileContent);
            Model modelWithDeletedPolygons = Deleter.polygonDelete(model, List.of(1, 5, 82, 23, 256));
            System.out.println("Vertices: " + modelWithDeletedPolygons.vertices.size());
            System.out.println("Texture vertices: " + modelWithDeletedPolygons.textureVertices.size());
            System.out.println("Polygons: " + modelWithDeletedPolygons.polygons.size());

            ObjWriter objWriter = new ObjWriter();
            objWriter.write(modelWithDeletedPolygons, "../../Torus_modified.obj");
            System.out.println("Model saved as Torus_modified.obj");

        } catch (IOException e) {
            System.err.println("An error occurred while processing the model: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
