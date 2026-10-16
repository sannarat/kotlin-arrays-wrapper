## Usage Examples

# Kotlin Wrapper for java.util.Arrays

A Kotlin/JVM command-line application and wrapper library around `java.util.Arrays` focusing on Java interoperability, defensive copying, precondition checks, and explicit result typing.

## Prerequisites
- JDK 21
- Gradle (use the included `./gradlew` / `gradlew.bat` wrapper)

## Build and Run

### Run Automated Tests
```bash
./gradlew test
```

Ascending:
```bash
./gradlew run --args='sort 3,1,2'
# Output: 1,2,3

./gradlew run --args='sort 3,1,2 --desc'
# Output: 3,2,1

./gradlew run --args='search 10,20,30 20'
# Output: Found at index: 1

./gradlew run --args='search 10,20,30 25'
# Output: Missing. Insertion position: 2

./gradlew run --args='slice 9,8,7,6 1 3'
# Output: 8,7
```
