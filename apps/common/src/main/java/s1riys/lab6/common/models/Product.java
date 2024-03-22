package s1riys.lab6.common.models;

import s1riys.lab6.common.models.utils.ModelWithId;
import s1riys.lab6.common.models.utils.Validatable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents a product.
 */
public class Product extends ModelWithId implements Comparable<Product>, Validatable, Serializable {
    private long id; //«начение пол€ должно быть больше 0, «начение этого пол€ должно быть уникальным, «начение этого пол€ должно генерироватьс€ автоматически
    private String name; // Field cannot be null, String cannot be empty
    private Coordinates coordinates; // Field cannot be null
    private Date creationDate; // Field cannot be null, Value of this field should be generated automatically
    private long price; // Value of the field should be greater than 0
    private String partNumber; // Length of the string should be at least 21, Field can be null
    private UnitOfMeasure unitOfMeasure; // Field can be null
    private Organization manufacturer; // Field can be null

    /**
     * Constructs a Product object with the specified parameters.
     *
     * @param name          the name of the product (cannot be null or empty)
     * @param coordinates   the coordinates of the product (cannot be null)
     * @param creationDate  the creation date of the product (cannot be null, should be generated automatically)
     * @param price         the price of the product (should be greater than 0)
     * @param partNumber    the part number of the product (length of the string should be at least 21, can be null)
     * @param unitOfMeasure the unit of measure of the product (can be null)
     * @param manufacturer  the manufacturer of the product (can be null)
     */
    public Product(
            Long id,
            String name,
            Coordinates coordinates,
            Date creationDate,
            long price,
            String partNumber,
            UnitOfMeasure unitOfMeasure,
            Organization manufacturer
    ) {
        super();
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the coordinates of the product.
     *
     * @return the coordinates of the product
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the creation date of the product.
     *
     * @return the creation date of the product
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public long getPrice() {
        return price;
    }

    /**
     * Returns the part number of the product.
     *
     * @return the part number of the product
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Returns the unit of measure of the product.
     *
     * @return the unit of measure of the product
     */
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Returns the manufacturer of the product.
     *
     * @return the manufacturer of the product
     */
    public Organization getManufacturer() {
        return manufacturer;
    }

    /**
     * Validates the product object.
     *
     * @return true if the product is valid, false otherwise
     */
    @Override
    public boolean validate() {
        if (this.id <= 0) return false;
        if (this.name == null || name.isEmpty()) return false;
        if (this.coordinates == null || !this.coordinates.validate()) return false;
        if (this.creationDate == null) return false;
        if (this.price <= 0) return false;
        if (this.partNumber != null) {
            if (this.partNumber.length() < 21) return false;
        }
        if (this.manufacturer != null) {
            if (!this.manufacturer.validate()) return false;
        }
        return true;
    }

    /**
     * Converts a Product object to an array of strings.
     *
     * @param product the product to convert
     * @return an array of strings representing the product
     */
    public static String[] toArray(Product product) {
        List<String> list = new ArrayList<>();

        list.add(Long.toString(product.id));
        list.add(product.name);
        list.add(Integer.toString(product.coordinates.getX()));
        list.add(Long.toString(product.coordinates.getY()));
        list.add(product.creationDate.toString());
        list.add(Long.toString(product.price));

        String partNumberToAdd = Objects.requireNonNullElse(product.partNumber, "null");
        list.add(partNumberToAdd);

        String unitOfMeasureToAdd;
        if (product.unitOfMeasure == null) unitOfMeasureToAdd = "null";
        else unitOfMeasureToAdd = product.unitOfMeasure.name();
        list.add(unitOfMeasureToAdd);

        if (product.manufacturer == null) {
            for (int i = 0; i < 5; i++) {
                list.add("null");
            }
        } else {
            list.add(Long.toString(product.manufacturer.getId()));
            list.add(product.manufacturer.getName());
            list.add(product.manufacturer.getFullName());
            list.add(Double.toString(product.manufacturer.getAnnualTurnover()));
            list.add(Integer.toString(product.manufacturer.getEmployeesCount()));
        }

        return list.toArray(new String[0]);
    }

    /**
     * Converts an array of strings to a Product object.
     *
     * @param listOfValues the array of strings to convert
     * @return a Product object created from the array of strings
     */
    public static Product fromArray(String[] listOfValues) {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        try {
            var id = Long.parseLong(listOfValues[0]);
            var name = listOfValues[1];
            var coordinateX = Integer.parseInt(listOfValues[2]);
            var coordinateY = Long.parseLong(listOfValues[3]);
            var creationDate = format.parse(listOfValues[4]);
            var price = Long.parseLong(listOfValues[5]);
            var partNumber = (listOfValues[6].equals("null")) ? null : listOfValues[6];
            var unitOfMeasure = (listOfValues[7].equals("null")) ? null : UnitOfMeasure.valueOf(listOfValues[7]);

            Organization manufacturer;
            boolean manufacturerIsNull = true;
            for (int i = 8; i < 13; i++) {
                if (!listOfValues[i].equals("null")) {
                    manufacturerIsNull = false;
                    break;
                }
            }
            if (manufacturerIsNull) {
                manufacturer = null;
            } else {
                manufacturer = new Organization(
                        Long.parseLong(listOfValues[8]),
                        listOfValues[9],
                        listOfValues[10],
                        Double.parseDouble(listOfValues[11]),
                        Integer.parseInt(listOfValues[12])
                );
            }

            Product product = new Product(id, name, new Coordinates(coordinateX, coordinateY), creationDate, price, partNumber, unitOfMeasure, manufacturer);
            return product;

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | ParseException ignored) {
        }
        return null;
    }

    /**
     * Compares this product with another product based on price and ID.
     *
     * @param o the product to compare to
     * @return a negative integer, zero, or a positive integer as this product is less than, equal to, or greater than the specified product
     */
    @Override
    public int compareTo(Product o) {
        return creationDate.compareTo(o.creationDate);

//        int priceComparison = Long.compare(this.price, o.price);
//        if (priceComparison != 0) return priceComparison;
//        else return -Long.compare(this.id, o.id);
    }


    /**
     * Returns a string representation of the product.
     *
     * @return a string representation of the product
     */
    @Override
    public String toString() {
        return "Product{" +
                "id = " + this.id + ", " +
                "name = " + this.name + ", " +
                "coordinates = " + this.coordinates + ", " +
                "creationDate = " + this.creationDate + ", " +
                "price = " + this.price + ", " +
                "partNumber = " + this.partNumber + ", " +
                "unitOfMeasure = " + this.unitOfMeasure + ", " +
                "manufacturer = " + this.manufacturer +
                "}";
    }

    public Product copy(long id) {
        return new Product(id, this.name, this.coordinates, this.creationDate,
                this.price, this.partNumber, this.unitOfMeasure, this.manufacturer
        );
    }

    /**
     * Checks if this product is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the products are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(coordinates, product.coordinates) &&
                Objects.equals(creationDate, product.creationDate) &&
                Objects.equals(price, product.price) &&
                Objects.equals(partNumber, product.partNumber) &&
                unitOfMeasure == product.unitOfMeasure &&
                Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public long getId() {
        return this.id;
    }
}
