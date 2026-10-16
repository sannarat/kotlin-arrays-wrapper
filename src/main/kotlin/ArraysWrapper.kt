import java.util.Arrays

sealed interface SearchResult {
    data class Found(val index: Int) : SearchResult
    data class Missing(val insertionPoint: Int) : SearchResult
}

fun sortedCopy(values: IntArray,
               descending: Boolean = false)
: IntArray {
    val copy = values.clone()
    Arrays.sort(copy)

    if(descending){
        copy.reverse()
    }
    return copy
}

fun search(sortedValues: IntArray,
           target: Int)
: SearchResult {
    for(i in 0 until sortedValues.size - 1){
        require(sortedValues[i] <= sortedValues[i+1]) {"Array must be sorted in ascending order, but found ${sortedValues[i]} > ${sortedValues[i + 1]} at index $i"}
    }

    val rawResult = Arrays.binarySearch(sortedValues, target)

    return if (rawResult >= 0){
        SearchResult.Found(rawResult)
    } else {
        val insertionPoint = -rawResult - 1
        SearchResult.Missing(insertionPoint)
    }
}

fun sliceCopy(values: IntArray,
              fromInclusive: Int,
              toExclusive: Int)
: IntArray{
    require(fromInclusive >= 0){"fromInclusive ($fromInclusive) must be non-negative"}
    require(fromInclusive <= toExclusive){"fromInclusive ($fromInclusive) cannot be greater than toExclusive ($toExclusive)" }
    require(toExclusive <= values.size){"toExclusive ($toExclusive) cannot be greater than array size (${values.size})"}

    return Arrays.copyOfRange(values, fromInclusive, toExclusive)
}