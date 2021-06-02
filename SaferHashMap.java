/**
 * This is a HashMap that never stores values that are
 * null and never allows changes to stored map values. All
 * methods return types that are separate from the internal
 * representation.
*/
class SaferHashMap extends HashMap<K, V> {
  /**
   * Associates the specified value with the specified key in this map.
   * Map must not already contain a mapping for the key.
   *
   * @param key key with which the specified value is to be associated
   *        and which is not already in the map
   * @param value value != null to be associated with the specified key
   * @return a new copy of the the previous value associated with key,
   *         or null if there was no mapping for key
  */
  @Override
  public V put(K key, @NonNull V value) {
    if (value == null)
      throw new RunTimeException("Key " + key + " was set to null. Cannot pass null value into SaferHashMap.");

    if (super.containsKey(key))
      throw new RunTimeException("Key " + key + " was already mapped. Cannot override value in SaferHashMap.");
    V previousValue = super.put(key, value.clone());
    if (previousValue != null) return previousValue.clone();
    return null;
  }

}
