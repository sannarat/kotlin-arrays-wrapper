## Usage Examples

### 1. Sorting an array
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
