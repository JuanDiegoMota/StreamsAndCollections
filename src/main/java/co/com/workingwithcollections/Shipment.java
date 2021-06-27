package co.com.workingwithcollections;

import java.util.*;
import java.util.function.Consumer;

public class Shipment implements Iterable<Product>{
    private static final int LIGHT_VAN_MAX_WEIGHT = 20;
    private static final int MISSING_PRODUCT = -1;
    private List<Product> lightVanProducts;
    private List<Product> heavyVanProducts;
    private final List<Product> products = new ArrayList<>();
    public void add(Product product) {
        products.add(product);
    }
    public boolean replace(Product oldProduct, Product newProduct) {
        int position = products.indexOf(oldProduct);
        if(position == MISSING_PRODUCT) {
            return false;
        } else {
            products.set(position, newProduct);
            return true;
        }
    }

    public void prepare() {
        products.sort(Product.BY_WEIGHT);
        int splitPoint = findSplitPoint();
        lightVanProducts = products.subList(0, splitPoint);
        heavyVanProducts = products.subList(splitPoint, products.size());
    }
    private int findSplitPoint() {
        int size = products.size();
        for(int i = 0; i < size; i++) {
            Product product = products.get(i);
            if (product.getWeight() > LIGHT_VAN_MAX_WEIGHT) {
                return i;
            }
        }
        return 0;
    }
    public List<Product> getHeavyVanProducts() {
        return heavyVanProducts;
    }
    public List<Product> getLightVanProducts() {
        return lightVanProducts;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
    @Override
    public void forEach(Consumer<? super Product> action) {
        Iterable.super.forEach(action);
    }
    @Override
    public Spliterator<Product> spliterator() {
        return Iterable.super.spliterator();
    }
}