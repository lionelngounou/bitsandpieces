package lionel.demos.bitsandpieces.collection.sorting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Comparator;

/**Helper comparator to compare a bean <b>T</b> by many of its properties, somewhat similar to sql statement
 * <code>select * from T order by prop2, prop1 desc, prop3 nulls first, prop5, ... </code>
 * @param <T>
 */
public class BeanMultiPropertyComparator<T> implements Comparator<T> {

    private Collection<ComparableBeanProperty> comparableBeanProperties;

    public BeanMultiPropertyComparator(Collection<ComparableBeanProperty> comparableBeanProperties) {
        this.comparableBeanProperties = comparableBeanProperties;
    }

    @Override
    public int compare(T t1, T t2) {
        if (t1 == t2 || comparableBeanProperties == null || comparableBeanProperties.isEmpty()) {
            return 0;
        }
        if (t1 == null) {
            return -1;
        }
        if (t2 == null) {
            return 1;
        }
        int r = 0;
        Class<?> clazz = t1.getClass();
        for (ComparableBeanProperty cp : comparableBeanProperties) {
            try {
                Method method = clazz.getMethod(getGetterMethodName(cp.getName()));
                Object retVal1 = method.invoke(t1),//returned value of method 1
                        retVal2 = method.invoke(t2);//returned value of method 2
                if (retVal1 == retVal2) {
                    r = 0;
                    continue;
                }
                if (retVal1 == null || retVal2 == null) {
                    return cp.getNullOrder();
                }
                //Primitive Wrappers, String, Date , Calendar instances are comparable
                if (retVal1 instanceof Comparable) {
                    r = ((Comparable<Object>) retVal1).compareTo(retVal2) * cp.getOrder();
                }
                if (r != 0) {
                    return r;
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                //e.printStackTrace();
            }
        }
        return r;
    }

    public static class ComparableBeanProperty {

        public static final class Order {

            private Order() {
            }
            public static final short ASCENDING = 1, DESCENDING = -1, NULL_FIRST = 1, NULL_LAST = -1;
        }
        private String name;
        private short order, nullOrder;

        public ComparableBeanProperty(String name) {
            this(name, Order.ASCENDING);
        }

        public ComparableBeanProperty(String name, short order) {
            this(name, order, Order.NULL_LAST);
        }

        public ComparableBeanProperty(String name, short order, short nullOrder) {
            this.name = name;
            this.order = order >= 0 ? Order.ASCENDING : Order.DESCENDING;
            this.nullOrder = nullOrder >= 0 ? Order.NULL_FIRST : Order.NULL_LAST;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOrder(short order) {
            this.order = order;
        }

        public short getOrder() {
            return order;
        }

        public void setNullOrder(short nullOrder) {
            this.nullOrder = nullOrder;
        }

        public short getNullOrder() {
            return nullOrder;
        }
    }

    public static String getGetterMethodName(String propertyName) {
        if (propertyName == null || propertyName.length() == 0) {
            return propertyName;
        }
        return "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }
}
