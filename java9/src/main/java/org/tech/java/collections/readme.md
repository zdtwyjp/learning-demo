# Java 9 集合工厂方法

Java 9 List，Set 和 Map 接口中，新的静态工厂方法可以创建这些集合的不可变实例。

这些工厂方法可以以更简洁的方式来创建集合。


# 新方法创建集合

Java 9 中，以下方法被添加到 List，Set 和 Map 接口以及它们的重载对象。


```
static <E> List<E> of(E e1, E e2, E e3);
static <E> Set<E>  of(E e1, E e2, E e3);
static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3);
static <K,V> Map<K,V> ofEntries(Map.Entry<? extends K,? extends V>... entries)
```

List 和 Set 接口, of(...) 方法重载了 0 ~ 10 个参数的不同方法 。

Map 接口, of(...) 方法重载了 0 ~ 10 个参数的不同方法 。

Map 接口如果超过 10 个参数, 可以使用 ofEntries(...) 方法。






