package myutil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester class for SaferHashMap
 * Test strategy for each method:
 *
 * Test Numbers
 * 1. put: String x ArrayList<Double> -> null
 *      1. partition for String
 *              1. null
 *              2. key existing in mapping
 *              3. key not in mapping
 *      2. partition ArrayList into
 *              1. contains null values only
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

        varDouble = 3.0;

        arrList3 = new MyArrayList<>();
        arrList3.add(varDouble);
    }

    @Test
    void mapWithNullKey() {
        hashMap.put(null, arrList1);
        assertEquals(arrList1, hashMap.get(null));
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
        assertEquals(arrList1, hashMap.get("testKey"));
    }

//    Test number 1.2.1 x 1.3.2
    @Test
    void mutateArrayListValuesContainsNull() {
        hashMap.put("testKey", arrList2);
        // mutate contents
        arrList2.set(0, 1.0);
        MyArrayList<Double> expected = new MyArrayList<>();
        expected.add(null);
        // check mapping
        assertEquals(expected, hashMap.get("testKey"));
    }

//    Test number 1.2.2 x 1.3.2
    @Test
    void mutateArrayListValuesContainsReference() {
        hashMap.put("testKey", arrList3);
        // mutate contents
        varDouble = -1.0;
        MyArrayList<Double> expected = new MyArrayList<>();
        expected.add(3.0);
        assert expected.contains(3.0);
        // check mapping
        assertEquals(expected, hashMap.get("testKey"));
    }

    @Test
    void setNewArrayListAfterMapping() {
        hashMap.put("testKey", arrList1);
        arrList1 = new MyArrayList<>();
        MyArrayList<Double> expected = new MyArrayList<>();
        expected.add(1.0);
        assertEquals(expected, hashMap.get("testKey"));
    }

    @Test
    void mapKeyToNull() {
        assertThrows(Exception.class, () -> hashMap.put("testKey", null));
    }

    @Test
    void getValueOfNonExistingKey() {
        assertNull(hashMap.get("definitelyNotKey"));
    }

    @Test
    void mutateArrayListGetValues() {
        hashMap.put("testKey", arrList1);
        MyArrayList<Double> newArrayList = hashMap.get("testKey");
        newArrayList.add(-1.0);
        assertEquals(arrList1, hashMap.get("testKey"));
    }

    @Test
    void setNewArrayListAfterGetValues() {
        hashMap.put("testKey", arrList1);
        MyArrayList<Double> newArrayList = hashMap.get("testKey");
        newArrayList = new MyArrayList<>();
        assertEquals(arrList1, hashMap.get("testKey"));
    }
}
