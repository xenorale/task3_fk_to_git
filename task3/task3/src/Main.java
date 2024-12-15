import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество полигонов для удаления: ");
        int numberOfPolygons = scanner.nextInt();

        List<Integer> polygonIndices = new ArrayList<>();

        System.out.println("Введите индексы полигонов для удаления:");
        for (int i = 0; i < numberOfPolygons; i++) {
            System.out.print("Полигон " + (i + 1) + " индекс: ");
            int index = scanner.nextInt();
            polygonIndices.add(index);
        }

        try {
            Path fileName = Path.of("D:\\ВГУ\\KG\\Torus.obj");
            String fileContent = Files.readString(fileName);
            System.out.println("Загрузка модели ...");

            Model model = ObjReader.read(fileContent);
            Model modelWithDeletedPolygons = Deleter.polygonDelete(model, polygonIndices);

            System.out.println("Вершины: " + modelWithDeletedPolygons.vertices.size());
            System.out.println("Текстурные вершины: " + modelWithDeletedPolygons.textureVertices.size());
            System.out.println("Полигоны: " + modelWithDeletedPolygons.polygons.size());

            ObjWriter objWriter = new ObjWriter();
            objWriter.write(modelWithDeletedPolygons, "D:\\ВГУ\\KG\\Torus_modified.obj");
            System.out.println("Модель сохранена как Torus_modified.obj");

        } catch (IOException e) {
            System.err.println("Произошла ошибка при обработке модели: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
    }
}
