package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag

internal class ComplexTest {

    private fun assertApproxEquals(expected: Complex, actual: Complex, eps: Double) {
        assertEquals(expected.re, actual.re, eps)
        assertEquals(expected.im, actual.im, eps)
    }

    @Test
    @Tag("2")
    fun plus() {
        assertApproxEquals(Complex("4-2i"), Complex("1+2i") + Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("-0.9-11111i"), Complex("-1+0i") + Complex("0.1-11111i"), 1e-10)
        assertApproxEquals(Complex("-14.6+11.2i"), Complex("-12+0.2i") + Complex("-2.6+11i"), 1e-10)
    }

    @Test
    @Tag("2")
    operator fun unaryMinus() {
        assertApproxEquals(Complex(1.0, -2.0), -Complex(-1.0, 2.0), 1e-10)
        assertApproxEquals(Complex(-1.0, -2.0), -Complex(1.0, 2.0), 1e-10)
        assertApproxEquals(Complex(1234.5678, -9.101112), -Complex(-1234.5678, 9.101112), 1e-10)
    }

    @Test
    @Tag("2")
    fun minus() {
        assertApproxEquals(Complex("2-6i"), Complex("3-4i") - Complex("1+2i"), 1e-10)
        assertApproxEquals(Complex("-9.4-10.8i"), Complex("-12+0.2i") - Complex("-2.6+11i"), 1e-10)
    }

    @Test
    @Tag("4")
    fun times() {
        assertApproxEquals(Complex("11+2i"), Complex("1+2i") * Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("15-27i"), Complex("3+3i") * Complex("-2-7i"), 1e-10)
        assertApproxEquals(Complex("29-132.52i"), Complex("-12+0.2i") * Complex("-2.6+11i"), 1e-10)
    }

    @Test
    @Tag("4")
    fun div() {
        assertApproxEquals(Complex("1+2i"), Complex("11+2i") / Complex("3-4i"), 1e-10)
        assertApproxEquals(Complex("0.368+1.024i"), Complex("-12+2i") / Complex("-2+11i"), 1e-10)
        assertThrows(IllegalArgumentException::class.java) { Complex("2+2i").div(Complex("0+0i")) }
    }

    @Test
    @Tag("2")
    fun equals() {
        assertApproxEquals(Complex(1.0, 2.0), Complex("1+2i"), 1e-12)
        assertApproxEquals(Complex(1.0, 0.0), Complex(1.0), 1e-12)
        assertApproxEquals(Complex(-1.0, 2.0), Complex("-1+2i"), 1e-12)
        assertApproxEquals(Complex(-1.1111, -2.3), Complex("-1.1111-2.3i"), 1e-12)
        assertApproxEquals(Complex(-121212.0, 0.0), Complex("-121212-0i"), 1e-12)
        assertApproxEquals(Complex(0.0, -2.0), Complex("0-2i"), 1e-12)
        assertApproxEquals(Complex(12.0, 2.212), Complex("12+2.212i"), 1e-12)
        assertThrows(IllegalArgumentException::class.java) { Complex("--12+2i") }
        assertThrows(IllegalArgumentException::class.java) { Complex("1--2i") }
        assertThrows(IllegalArgumentException::class.java) { Complex("1-2") }
        assertThrows(IllegalArgumentException::class.java) { Complex("1- 2i") }
        assertThrows(IllegalArgumentException::class.java) { Complex("1 - 2i") }
        assertThrows(IllegalArgumentException::class.java) { Complex(" 1-2i") }
        assertThrows(IllegalArgumentException::class.java) { Complex("+1-2i") }
    }
}