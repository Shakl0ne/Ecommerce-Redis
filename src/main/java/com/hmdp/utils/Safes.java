package com.hmdp.utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class Safes {
    private static final Stream<Object> EMPTY_STREAM = Stream.empty();
    private static final Supplier<Object> EMPTY_SUPPLIER = () -> null;
    private static final Map.Entry<Object, Object> EMPTY_ENTRY = new AbstractMap.SimpleImmutableEntry<>(null, null);
    private static final InputStream EMPTY_INPUT_STREAM = new InputStream() {
        @Override
        public int read() {
            return -1;
        }
    };


    public static <T> List<T> of(List<T> list) {
        return Optional.ofNullable(list).orElse(Collections.emptyList());
    }


    public static <T> Set<T> of(Set<T> set) {
        return Optional.ofNullable(set).orElse(Collections.emptySet());
    }


    public static <T> SortedSet<T> of(SortedSet<T> set) {
        return Optional.ofNullable(set).orElse(Collections.emptySortedSet());
    }


    public static <T> NavigableSet<T> of(NavigableSet<T> set) {
        return Optional.ofNullable(set).orElse(Collections.emptyNavigableSet());
    }


    public static <T> Collection<T> of(Collection<T> coll) {
        return Optional.ofNullable(coll).orElse(Collections.emptySet());
    }


    public static <T> Iterator<T> of(Iterator<T> iter) {
        return Optional.ofNullable(iter).orElse(Collections.emptyIterator());
    }


    public static <T> Iterable<T> of(Iterable<T> iter) {
        return Optional.ofNullable(iter).orElse(Collections::emptyIterator);
    }


    public static <K, V> Map<K, V> of(Map<K, V> map) {
        return Optional.ofNullable(map).orElse(Collections.emptyMap());
    }


    public static <K, V> SortedMap<K, V> of(SortedMap<K, V> map) {
        return Optional.ofNullable(map).orElse(Collections.emptySortedMap());
    }


    public static <K, V> NavigableMap<K, V> of(NavigableMap<K, V> map) {
        return Optional.ofNullable(map).orElse(Collections.emptyNavigableMap());
    }


    @SuppressWarnings("unchecked")
    public static <K, V> Map.Entry<K, V> of(Map.Entry<K, V> entry) {
        return Optional.ofNullable(entry).orElse((Map.Entry<K, V>) EMPTY_ENTRY);
    }


    @SuppressWarnings("unchecked")
    public static <T> Stream<T> of(Stream<T> stream) {
        return Optional.ofNullable(stream).orElse((Stream<T>) EMPTY_STREAM);
    }


    @SuppressWarnings("unchecked")
    public static <T> Supplier<T> of(Supplier<T> supplier) {
        return Optional.ofNullable(supplier).orElse((Supplier<T>) EMPTY_SUPPLIER);
    }


    public static BigDecimal of(BigDecimal bigDecimal) {
        return of(bigDecimal, BigDecimal.ZERO);
    }


    public static BigDecimal of(BigDecimal bigDecimal, BigDecimal defaultValue) {
        return Optional.ofNullable(bigDecimal).orElse(defaultValue);
    }


    public static Integer of(Integer val) {
        return of(val, 0);
    }



    public static Integer of(Integer val, Integer defaultValue) {
        return Optional.ofNullable(val).orElse(defaultValue);
    }

    public static Long of(Long val) {return of(val, 0L); }

    public static Long of(Long val, Long defaultValue) {
        return Optional.ofNullable(val).orElse(defaultValue);
    }

    public static String of(String string) {
        return of(string, "");
    }


    public static String of(String source, String defaultValue) {
        return Optional.ofNullable(source).orElse(defaultValue);
    }


    public static InputStream of(InputStream inputStream) {
        return Optional.ofNullable(inputStream).orElse(EMPTY_INPUT_STREAM);
    }

    public static <T> List<T> filterNull(List<T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        if (list.isEmpty()) {
            return list;
        }
        if (list.stream().allMatch(Objects::nonNull)) {
            return list;
        }
        return list.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }


    public static <T> T first(Collection<T> coll) {
        if (coll == null || coll.isEmpty()) {
            return null;
        }
        if (coll instanceof List) {
            return ((List<T>) coll).get(0);
        }
        if (coll instanceof Queue) {
            return ((Queue<T>) coll).peek();
        }
        if (coll instanceof SortedSet) {
            return ((SortedSet<T>) coll).first();
        }
        return first(coll.iterator());
    }


    public static <K, V> Map.Entry<K, V> first(Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        if (map instanceof NavigableMap) {
            return ((NavigableMap<K, V>) map).firstEntry();
        }
        return first(map.entrySet().iterator());
    }


    public static <T> T first(Iterable<T> iter) {
        if (iter == null) {
            return null;
        }
        if (iter instanceof Collection) {
            return first((Collection<T>) iter);
        }
        return first(iter.iterator());
    }


    public static <T> T first(Iterator<T> iter) {
        if (iter == null) {
            return null;
        }
        if (iter.hasNext()) {
            return iter.next();
        }
        return null;
    }


    public static <T> T first(Enumeration<T> enumeration) {
        if (enumeration == null) {
            return null;
        }
        if (enumeration.hasMoreElements()) {
            return enumeration.nextElement();
        }
        return null;
    }


    public static <E> E first(E[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }


    public static <T> T last(Collection<T> coll) {
        if (coll == null || coll.isEmpty()) {
            return null;
        }
        if (coll instanceof List) {
            return ((List<T>) coll).get(coll.size() - 1);
        }
        if (coll instanceof Deque) {
            return ((Deque<T>) coll).getLast();
        }
        if (coll instanceof SortedSet) {
            return ((SortedSet<T>) coll).last();
        }
        return last(coll.iterator());
    }


    public static <K, V> Map.Entry<K, V> last(Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        if (map instanceof NavigableMap) {
            return ((NavigableMap<K, V>) map).lastEntry();
        }
        return last(map.entrySet().iterator());
    }


    public static <T> T last(Iterable<T> iter) {
        if (iter == null) {
            return null;
        }
        if (iter instanceof Collection) {
            return last((Collection<T>) iter);
        }
        return last(iter.iterator());
    }


    public static <T> T last(Iterator<T> iter) {
        if (iter == null) {
            return null;
        }
        T t = null;
        while (iter.hasNext()) {
            t = iter.next();
        }
        return t;
    }


    public static <T> T last(Enumeration<T> enumeration) {
        if (enumeration == null) {
            return null;
        }
        T t = null;
        while (enumeration.hasMoreElements()) {
            t = enumeration.nextElement();
        }
        return t;
    }


    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }


    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }


    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }


    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }


    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }


    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }


    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }


    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isNotEmpty(byte[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isNotEmpty(short[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isNotEmpty(int[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isNotEmpty(long[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isNotEmpty(float[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isNotEmpty(double[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }


    public static boolean isNotEmpty(Collection<?> coll) {
        return coll != null && !coll.isEmpty();
    }


    public static boolean isEmpty(Iterator<?> iterator) {
        return iterator == null || !iterator.hasNext();
    }


    public static boolean isNotEmpty(Iterator<?> iterator) {
        return iterator != null && iterator.hasNext();
    }


    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return isEmpty((Collection) iterable);
        }
        return iterable == null || isEmpty(iterable.iterator());
    }


    public static boolean isNotEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return isNotEmpty((Collection) iterable);
        }
        return iterable == null || isNotEmpty(iterable.iterator());
    }


    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }


    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }


    public static boolean isEmpty(CharSequence val) {
        return val == null || val.length() == 0;
    }


    public static boolean isNotEmpty(CharSequence val) {
        return val != null && val.length() > 0;
    }

    private Safes() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation not allowed");
    }
}
