package io.github.tomhula.jecnaapi.util

import io.github.tomhula.jecnaapi.data.student.Student
import io.github.tomhula.jecnaapi.parser.ParseException
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber

/**
 * @return [Month] corresponding to this number.
 * @see [Month]
 */
fun Int.month(): Month = Month(this)

/**
 * Maps any [ClosedRange] to an [IntRange] using [mappingFunction].
 */
fun <T : Comparable<T>> ClosedRange<T>.mapToIntRange(mappingFunction: (T) -> Int): IntRange
{
    val startMapped = mappingFunction(start)
    val endMapped = mappingFunction(endInclusive)

    return startMapped..endMapped
}

/**
 * Sets all [elements] to the [MutableList].
 * Shorthand for
 * ```
 * mutableList.clear()
 * mutableList.addAll(elements)
 * ```
 */
fun <T> MutableCollection<T>.setAll(elements: Iterable<T>)
{
    clear()
    addAll(elements)
}

/**
 * Returns whether there are any elements, that have the same result of [selector] function.
 */
fun <T, R> Iterable<T>.hasDuplicate(selector: (T) -> R): Boolean
{
    val set = HashSet<R>()

    for (element in this)
        if (!set.add(selector(element)))
            return true

    return false
}

/**
 * @return The next [day][DayOfWeek] after this one.
 */
fun DayOfWeek.next(): DayOfWeek = DayOfWeek(this.isoDayNumber % 7 + 1)

/**
 * Validates that the student is in 4th grade based on their class name.
 * 
 * @param student The student to validate
 * @throws ParseException if the student is not in 4th grade or class name is null
 */
fun validateStudentIn4thGrade(student: Student)
{
    val className = student.className

    if (className == null || !className.contains("4"))
    {
        throw ParseException("Student must be in 4th grade")
    }
}

