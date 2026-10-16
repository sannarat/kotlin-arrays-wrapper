import kotlin.test.*

class ArraysWrapperTest {

    // 1. Сортировка возвращает правильный массив и не меняет оригинал
    @Test
    fun testSortedCopyAscendingAndPreservesOriginal() {
        val original = intArrayOf(3, 1, 2)
        val sorted = sortedCopy(original)

        assertContentEquals(intArrayOf(1, 2, 3), sorted)
        assertContentEquals(intArrayOf(3, 1, 2), original, "Original array must not be modified")
    }

    // 2. Сортировка по убыванию
    @Test
    fun testSortedCopyDescending() {
        val original = intArrayOf(3, 1, 2)
        val sorted = sortedCopy(original, descending = true)

        assertContentEquals(intArrayOf(3, 2, 1), sorted)
    }

    // 3. Изменение копии не затрагивает оригинал
    @Test
    fun testCopyMutationDoesNotAffectOriginal() {
        val original = intArrayOf(10, 20, 30)
        val copy = sortedCopy(original)
        copy[0] = 999

        assertEquals(10, original[0], "Original array must retain its values")
    }

    // 4. Пустой массив
    @Test
    fun testEmptyArray() {
        val empty = intArrayOf()
        val sorted = sortedCopy(empty)
        assertContentEquals(intArrayOf(), sorted)

        val result = search(sorted, target = 42)
        assertEquals(SearchResult.Missing(0), result)
    }

    // 5. Дубликаты: должен вернуться любой корректный индекс
    @Test
    fun testSearchWithDuplicates() {
        val array = intArrayOf(1, 2, 2, 2, 3)
        val result = search(array, target = 2)

        assertTrue(result is SearchResult.Found)
        assertEquals(2, array[result.index])
    }

    // 6. Точки вставки для отсутствующих элементов
    @Test
    fun testSearchMissingInsertionPoints() {
        val array = intArrayOf(10, 20, 30)

        // До первого элемента (в начало)
        assertEquals(SearchResult.Missing(0), search(array, 5))
        // Между элементами
        assertEquals(SearchResult.Missing(2), search(array, 25))
        // После последнего элемента (в конец)
        assertEquals(SearchResult.Missing(3), search(array, 40))
    }

    // 7. Поиск по неотсортированному массиву выбрасывает ошибку
    @Test
    fun testSearchUnsortedThrowsException() {
        val unsorted = intArrayOf(1, 3, 2)
        val error = assertFailsWith<IllegalArgumentException> {
            search(unsorted, target = 2)
        }
        assertTrue(error.message!!.contains("must be sorted in ascending order"))
    }

    // 8. Срез диапазона: валидные границы и ошибки
    @Test
    fun testSliceCopy() {
        val array = intArrayOf(9, 8, 7, 6)

        // Обычный срез
        assertContentEquals(intArrayOf(8, 7), sliceCopy(array, 1, 3))

        // Пустой диапазон (from == to)
        assertContentEquals(intArrayOf(), sliceCopy(array, 2, 2))

        // Некорректные границы
        assertFailsWith<IllegalArgumentException> { sliceCopy(array, -1, 2) }
        assertFailsWith<IllegalArgumentException> { sliceCopy(array, 3, 2) }
        assertFailsWith<IllegalArgumentException> { sliceCopy(array, 0, 10) }
    }
}