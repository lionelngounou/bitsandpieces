package lionel.demos.bitsandpieces.collection;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**Defines various static methods for predicate, and consumer operations on collections*/
public class CollectionFilter {

    /**
     * Finds the first value matching the matcher's condition within the
     * <code>Matcher.matches()</code> method. If the result is null, null is
     * returned.
     */
    public static <E> E find(final Collection<? extends E> elements, final Condition< E> matcher) {
        for (E e : elements) {
            if (matcher.matches(e)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Finds the first value matching the matcher's condition within the
     * <code>Matcher.matches()</code> method. If the result is null, the
     * defaultResult is returned.
     */
    public static <E> E find(final Collection<? extends E> elements, final Condition<E> matcher, final E defaultResult) {
        E element = find(elements, matcher);
        return element == null ? defaultResult : element;
    }

    /**
     * Finds all values matching the matcher's condition within the
     *  <code>Matcher.matches()</code> method.
     */
    public static <E> List<E> findAll(final Collection<? extends E> elements, final Condition<E> matcher) {
        List<E> matchingItems = new ArrayList<>();
        for (E e : elements) {
            if (matcher.matches(e)) {
                matchingItems.add(e);
            }
        }
        return matchingItems;
    }

    /**
     * Finds all values matching the matcher's condition within the
     *  <code>Matcher.matches()</code> method. and puts the results in the
     * results' collection
     */
    public static <E> void findAll(final Collection<? extends E> elements, final Collection<? super E> resultsCollection, final Condition<E> matcher) {
        for (E e : elements) {
            if (matcher.matches(e)) {
                resultsCollection.add(e);
            }
        }
    }

    /**
     * Removes all values matching the matcher's condition within the
     *  <code>Matcher.matches()</code> method.
     */
    public static <E> boolean removeAll(final Collection<? extends E> elements, final Condition<E> matcher) {
        boolean removed = false;
        for (E e : elements) {
            if (matcher.matches(e)) {
                if (elements.remove(e));
            }
        }
        removed = true;
        return removed;
    }

    /**
     * Retains all values matching the matcher's condition within the
     *  <code>Matcher.matches()</code> method.
     */
    public static <E> boolean retainAll(final Collection<? extends E> elements, final Condition<E> matcher) {
        boolean retained = false;
        for (E e : elements) {
            if (!matcher.matches(e)) {
                elements.remove(e);
            } else {
                retained = true;
            }
        }
        return retained;
    }

    /**
     * Executes the given action on each element in the given collection
     */
    public static <E> void each(final Collection<? extends E> elements, final Action<E> action) {
        for (E e : elements) {
            action.execute(e);
        }
    }

    /**
     * This encapsulates the condition that should be used to test this element
     * - similar to a predicate
     */
    public interface Condition<E> {

        /**
         * Tests if the specified element matches the condition specified in
         * this method.
         */
        boolean matches(E element);
    }

    /**
     * This encapsulates the action that should be performed/executed on this
     * element - similar to a consumer
     */
    public interface Action<E> {

        /**
         * This method executes the embedded action on this element
         */
        void execute(E element);
    }
}
