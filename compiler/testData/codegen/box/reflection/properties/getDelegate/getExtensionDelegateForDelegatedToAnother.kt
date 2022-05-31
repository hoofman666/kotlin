// TARGET_BACKEND: JVM
// WITH_REFLECT

import kotlin.reflect.full.getExtensionDelegate
import kotlin.reflect.jvm.isAccessible

class A

val A.x: Int get() = 1
val A.y: Int by A::x

fun box(): String =
    if (A::y.apply { isAccessible = true }.getExtensionDelegate() == A::x) "OK" else "Fail"
