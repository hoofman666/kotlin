// FIR_IDENTICAL
// !LANGUAGE: +ForbidOptInMarkersWithParameters

@RequiresOptIn
annotation class <!OPT_IN_MARKER_WITH_PARAMETERS_ERROR!>WithParameter<!>(val value: String)
