package myutil;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;

/**
 * This is a HashMap that never stores values that are
 * null and never allows changes to stored map values. All
 * methods return values that cannot mutate the internal
 * mapping.
*/
class SaferHashMap<K, V extends ActuallyUsefulCloneable> extends HashMap<K, V> {
  /**
   * Associate the specified value with the specified key in this map.
   * Map must not already contain a mapping for the key.
   *
   * @param key unmapped key with which the specified value is to be associated
   * @param value value != null to be associated with the specified key
   * @return a new copy of the the previous value associated with key,
   *         or null if there was no mapping for key
  */
  @Override
  public V put(K key, @NotNull V value) {
    if (super.containsKey(key)) throw
            new RuntimeException("Key " + key + " was already mapped. Cannot override value in SaferHashMap.");

//  store previous value associated with key, which should always be null because of no override
    V previousValue = super.put(key, (V) value.clone());

    assert previousValue == null : "Key was already mapped";
    return null;
  }

  /**
   * Return a copy of the value to which the specified key is mapped,
   * or null if there is no mapping for the key.
   *
   * @param key the key whose associated value is to be returned
   * @return a new copy of the the previous value associated with key,
   *         or null if there was no mapping for key
   */
  @Override
  public V get(Object key) {
//  return the value which the specified key is mapped to, or null if there is no mapping for the key
    V previousValue = super.get(key);

    if (previousValue != null) return (V) previousValue.clone();
    assert !super.containsKey(key) : "Key stored null value";
    return null;
  }

}
