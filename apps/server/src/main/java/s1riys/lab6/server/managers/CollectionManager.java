package s1riys.lab6.server.managers;

import org.apache.commons.lang3.SerializationUtils;
import s1riys.lab6.common.models.Product;
import s1riys.lab6.common.models.Organization;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The CollectionManager class manages the collection of products, including serialization and deserialization.
 */
public class CollectionManager {
    private final DumpManager dumpManager;
    private final HashMap<Long, Product> hashMapCollection = new HashMap<>();
    private final TreeSet<Product> sortedCollection = new TreeSet<>();
    private final LocalDateTime initTime;
    private LocalDateTime saveTime;

    /**
     * Constructs a CollectionManager object with the specified DumpManager and initializes the collection.
     *
     * @param dumpManager The DumpManager object used for serialization and deserialization.
     */
    public CollectionManager(DumpManager dumpManager) {
        this.dumpManager = dumpManager;
        this.initTime = LocalDateTime.now();
        readFromCSV();
    }

    /**
     * Retrieves the default collection as a HashMap.
     *
     * @return The default collection as a HashMap.
     */
    public HashMap<Long, Product> getDefaultCollection() {
        return hashMapCollection;
    }

    /**
     * Retrieves the sorted collection as a TreeSet.
     *
     * @return The sorted collection as a TreeSet.
     */
    public TreeSet<Product> getSortedCollection() {
        return sortedCollection;
    }

    /**
     * Retrieves the type of the collection.
     *
     * @return The type of the collection.
     */
    public String getCollectionType() {
        return "Product";
    }

    /**
     * Retrieves the size of the collection.
     *
     * @return The size of the collection.
     */
    public int getCollectionSize() {
        return hashMapCollection.size();
    }

    /**
     * Retrieves the initialization date and time of the collection.
     *
     * @return The initialization date and time of the collection.
     */
    public LocalDateTime getInitDate() {
        return initTime;
    }

    /**
     * Updates the save time to the current date and time.
     */
    private void updateSaveTime() {
        this.saveTime = LocalDateTime.now();
    }

    /**
     * Retrieves the save time of the collection.
     *
     * @return The save time of the collection.
     */
    public LocalDateTime getSaveTime() {
        return saveTime;
    }

    /**
     * Reads the collection from the CSV file using the DumpManager.
     */
    private void readFromCSV() {
        Collection<Product> tmpCollection = new ArrayList<>();
        dumpManager.readCollection(tmpCollection);
        for (Product product : tmpCollection) {
            add(product);
        }
    }

    /**
     * Writes the collection to the CSV file using the DumpManager.
     */
    public void writeToCSV() {
        dumpManager.writeCollection(hashMapCollection.values());
        updateSaveTime();
    }

    public Product getById(Long id) {
        return getDefaultCollection().get(id);
    }

    /**
     * Adds a product to the collection.
     *
     * @param product The product to add.
     */
    public long add(Product product) {
        long nextProductId = (long) getDefaultCollection().values().stream()
                .filter(Objects::nonNull)
                .map(Product::getId)
                .mapToInt(Long::intValue)
                .max().orElse(0) + 1;


        long nextOrgId = (long) getDefaultCollection().values().stream()
                .map(Product::getManufacturer)
                .filter(Objects::nonNull)
                .map(Organization::getId)
                .mapToInt(Long::intValue)
                .max().orElse(0) + 1;

        if (product.getManufacturer() != null) {
            product.getManufacturer().setId(nextOrgId);
        }

        Product processedProduct = product.copy(nextProductId);
        sortedCollection.add(processedProduct);
        hashMapCollection.put(processedProduct.getId(), processedProduct);

        return nextProductId;
    }

    /**
     * Removes a product from the collection based on the specified ID.
     *
     * @param id The ID of the product to remove.
     */
    public void remove(Long id) {
        sortedCollection.remove(hashMapCollection.get(id));
        hashMapCollection.remove(id);
    }

    /**
     * Updates a product in the collection based on the specified ID.
     *
     * @param id      The ID of the product to update.
     * @param product The updated product.
     */
    public void update(Long id, Product product) {
        sortedCollection.remove(hashMapCollection.get(id));
        sortedCollection.add(product);
        hashMapCollection.put(id, product);
    }

    /**
     * Clears the collection, removing all products.
     */
    public void clear() {
        sortedCollection.clear();
        hashMapCollection.clear();
    }
}
