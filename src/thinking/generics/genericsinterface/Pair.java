//: net/mindview/util/Pair.java
package thinking.generics.genericsinterface;

public class Pair<K,V> {
  public final K key;
  public final V value;
  public Pair(K k, V v) {
    key = k;
    value = v;
  }
} ///:~
