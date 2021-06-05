# java-SaferHashMap
A HashMap that never stores null values and never allows changes to stored map values.

## What is this?
SaferHashMap can store any key and any value as long as the value implements `ActuallyUsefulCloneable` interface. Note that only `put()` and `get()` methods have been fully implemented and tested, while other HashMap methods will still work, although not according to the specifications of SaferHashMap.

## Why is this here?
To practise writing JUnit 5 tests, method specifications (preconditions, postconditions), javadoc comments; to practise partioning test case inputs.

## Running the Program
#### Case 1: Using the class
```
import myutil.*;

SaferHashMap<String, MyArrayList<Double>> myHashMap = new SaferHashMap<>();
```
#### Case 2: Running tests
1. Download the repo
2. Open repo in IntelliJ
3. Right click `myutil` and click `Run 'Tests in ...'`
