package com.virtlink.editorservices.intellij.psi

import com.intellij.lang.Language
import com.intellij.psi.tree.IElementType

/**
 * Element type for tokens.
 */
class AesiTokenType(val scope: String, language: Language)
    : IElementType(scope, language)