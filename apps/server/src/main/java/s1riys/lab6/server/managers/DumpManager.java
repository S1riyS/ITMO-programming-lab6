package s1riys.lab6.server.managers;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import s1riys.lab6.common.exceptions.InvalidCSVContentException;
import s1riys.lab6.common.models.Product;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedList;

/**
 * The DumpManager class manages the serialization and deserialization of a collection of products to/from a CSV file.
 */
public class DumpManager {
    private final String fileName;

    /**
     * Constructs a DumpManager object with the specified file name.
     *
     * @param fileName The name of the file to read from or write to.
     */
    public DumpManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Converts a collection of products to a CSV string.
     *
     * @param collection The collection of products to convert.
     * @return The CSV string representation of the collection.
     */
    private String collection2CSV(Collection<Product> collection) {
        try {
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter, ';');

            for (Product product : collection) {
                csvWriter.writeNext(Product.toArray(product));
            }
            return stringWriter.toString();
        } catch (Exception e) {
//            console.printError(e.getMessage());
//            console.printError("Ошибка сериализации");
            return null;
        }
    }

    /**
     * Writes the collection of products to the specified file.
     *
     * @param collection The collection of products to write.
     */
    public void writeCollection(Collection<Product> collection) {
        OutputStreamWriter writer = null;
        try {
            var csv = collection2CSV(collection);
            if (csv == null) return;

            writer = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8);
            try {
                writer.write(csv);
                writer.flush();
//                console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException e) {
//                console.printError("Неожиданная ошибка сохранения");
            }
        } catch (FileNotFoundException | NullPointerException e) {
//            console.printError("Файл не найден");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
//                console.printError("Ошибка закрытия файла");
            }
        }
    }

    /**
     * Converts a CSV string to a collection of products.
     *
     * @param s The CSV string to convert.
     * @return The collection of products.
     */
    private LinkedList<Product> CSV2collection(String s) {
        try {
            StringReader stringReader = new StringReader(s);
            CSVReader csvReader = new CSVReader(stringReader, ';');
            LinkedList<Product> products = new LinkedList<>();
            String[] record = null;

            while ((record = csvReader.readNext()) != null) {
                Product product = Product.fromArray(record);
                if (product != null && product.validate()) {
                    products.add(product);
                } else {
                    throw new InvalidCSVContentException();
                }
            }
            csvReader.close();
            return products;
        } catch (InvalidCSVContentException e) {
//            console.printError("Файл с коллекцией содержит не действительные данные");
        } catch (Exception e) {
//            console.printError("Ошибка десериализации");
        }
        return null;
    }

    /**
     * Reads the collection of products from the specified file and populates the provided collection.
     *
     * @param collection The collection to populate with the products.
     */
    public void readCollection(Collection<Product> collection) {
        if (fileName != null && !fileName.isEmpty()) {
            try (
                    FileInputStream fis = new FileInputStream(fileName);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))
            ) {
                var stringBuilder = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                    line = reader.readLine();
                }

                collection.clear();
                var newCollection = CSV2collection(stringBuilder.toString());
                collection.addAll(newCollection);
                if (!collection.isEmpty()) {
//                    console.println("Коллекция успешна загружена!");
                    return;
                } // else console.printError("В загрузочном файле не обнаружена коллекция!");
            } catch (FileNotFoundException exception) {
//                console.printError("Загрузочный файл не найден!");
            } catch (IllegalStateException | IOException exception) {
//                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
//            console.printError("Передано некорректное имя файла");
        }
    }
}
