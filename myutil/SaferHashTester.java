package myutil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tester class for SaferHashMap
 * Test strategy for each method:
 *
 * 1. put: String x ArrayList<Double> -> null
 *      1. partition for String
 *              1. null
 *              2. key existing in mapping
 *              3. key not in mapping
 *      2. partition ArrayList into
 *              1. contains null value only
 *              2. contains references only
 *              3. is null
 *      3. partition mutation of ArrayList into
 *              1. set to new ArrayList after mapping
 *              2. mutate ArrayList contents after mapping
 *
 * 2. get: Object -> ArrayList<Double>
 *      1. partition object
 *              1. null
 *              2. key existing in mapping
 *              3. key not in mapping
 *      2. partition mutation of returned value into
 *              1. set to new ArrayList, then check value in mapping
 *              2. mutate ArrayList contents, then check value in mapping
 */
public class SaferHashTester {
    SaferHashMap<String, MyArrayList<Double>> hashMap;
    MyArrayList<Double> arrList1;
    MyArrayList<Double> arrList2;
    MyArrayList<Double> arrList3;
    Double varDouble;

    /**
     * Set up each field as follows before each @Test:
     * hashMap =  **empty map**
     * arrList1 = {1.0}
     * arrList2 = {null}
     * varDouble = 3.0
     * arrList3 = {varDouble}
     */
    @BeforeEach
    void setup() {
        hashMap = new SaferHashMap<>();

        arrList1 = new MyArrayList<>();
        arrList1.add(1.0);

        arrList2 = new MyArrayList<>();
        arrList2.add(null);

        arrList3 = new MyArrayList<>();
        arrList3.add(varDouble);

        varDouble = 3.0;
    }

    @Test
    void mapKeyToNull() {
        assertThrows(Exception.class, () -> hashMap.put("testKey", null));
    }

    @Test
    void mapExistingKey() {
        assertThrows(RuntimeException.class, () -> {
            hashMap.put("testKey", arrList1);
            hashMap.put("testKey", arrList2);
        });
    }

    @Test
    void mapNonExistingKey() {
        hashMap.put("testKey", arrList1);
        assertEquals(hashMap.get("testKey"), arrList1);
    }

    @Test
    void getValueOfMappedKey() {

    }
}
