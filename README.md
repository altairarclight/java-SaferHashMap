# java-SaferHashMap
A HashMap that never stores null values and never allows changes to stored map values.

## What is this?
SaferHashMap can store any key and any value as long as the value implements `ActuallyUsefulCloneable` interface. Note that only `put()` and `get()` methods have been fully implemented and tested, while other HashMap methods will still work, although not according to the specifications of SaferHashMap.

## Why is this here?
To practise writing JUnit 5 tests, method specifications (preconditions, postconditions), javadoc comments; to practise partioning test case inputs.
