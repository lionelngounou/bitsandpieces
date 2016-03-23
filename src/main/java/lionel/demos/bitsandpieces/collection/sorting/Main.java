package lionel.demos.bitsandpieces.collection.sorting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lionel.ngounou
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        List<Car> cars = getCars();        
        System.out.println("finished adding");
        
        //set property comparators
        BeanMultiPropertyComparator.ComparableBeanProperty cpMake = new BeanMultiPropertyComparator.ComparableBeanProperty("make", BeanMultiPropertyComparator.ComparableBeanProperty.Order.DESCENDING);
        BeanMultiPropertyComparator.ComparableBeanProperty cpColor = new BeanMultiPropertyComparator.ComparableBeanProperty("color", (short) 1, BeanMultiPropertyComparator.ComparableBeanProperty.Order.NULL_FIRST);
        BeanMultiPropertyComparator.ComparableBeanProperty cpMileage = new BeanMultiPropertyComparator.ComparableBeanProperty("mileage", BeanMultiPropertyComparator.ComparableBeanProperty.Order.DESCENDING);
        BeanMultiPropertyComparator.ComparableBeanProperty cpSize = new BeanMultiPropertyComparator.ComparableBeanProperty("size");
        BeanMultiPropertyComparator.ComparableBeanProperty cpDoors = new BeanMultiPropertyComparator.ComparableBeanProperty("doors");
        Collection<BeanMultiPropertyComparator.ComparableBeanProperty> comparableBeanProperties = new LinkedList<>();
        comparableBeanProperties.add(cpMake);
        comparableBeanProperties.add(cpColor);
        comparableBeanProperties.add(cpMileage);
        comparableBeanProperties.add(cpSize);
        comparableBeanProperties.add(cpDoors);
        BeanMultiPropertyComparator<Car> ms = new BeanMultiPropertyComparator<>(comparableBeanProperties);
        
        System.out.println("start sorting ");
        Collections.sort(cars, ms);
        System.out.println("finish sorting \n");
        
        print(cars);
    }
    
    public static List<Car> getCars(){
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            cars.add(new Car());
        }
        cars.add(null);
        Car c = new Car();
        c.setColor(null);
        cars.add(c);
        c = new Car();
        c.setColor(null);
        cars.add(c);
        c = new Car();
        c.setColor(null);
        cars.add(c);
        c = new Car();
        c.setColor(null);
        cars.add(c);
        c = new Car();
        c.setColor(null);
        cars.add(c);
        return cars;
    }

    static void print(Collection<Car> cars) {
        String s = Car.boxedVal("MAKE") + Car.boxedVal("COLOR") + Car.boxedVal("MILEAGE")
                + Car.boxedVal("SIZE") + Car.boxedVal("DOORS") + "|";
        System.out.println(s);
        System.out.println("--------------------------------------------------------");
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}
