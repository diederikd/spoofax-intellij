/*
 * Copyright © 2015-2016
 *
 * This file is part of Spoofax for IntelliJ.
 *
 * Spoofax for IntelliJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoofax for IntelliJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Spoofax for IntelliJ.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.metaborg.intellij.idea.parsing;

import com.intellij.lexer.*;
import com.intellij.openapi.editor.colors.*;
import com.intellij.openapi.editor.markup.*;
import com.intellij.openapi.fileTypes.*;
import com.intellij.psi.tree.*;
import org.metaborg.core.style.*;
import org.metaborg.intellij.idea.parsing.elements.*;

import javax.annotation.*;
import java.awt.*;
import java.util.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.*;

/**
 * Highlighter for Spoofax languages.
 */
public final class SpoofaxSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private final Map<IStyle, TextAttributesKey[]> styleMap = new HashMap<>();
    private final Lexer lexer;

    /**
     * Initializes a new instance of the {@link SpoofaxSyntaxHighlighter} class.
     *
     * @param lexer The lexer to use for highlighting.
     */
    public SpoofaxSyntaxHighlighter(final Lexer lexer) {
        super();
        this.lexer = lexer;
    }

    /**
     * Gets the highlighting lexer.
     *
     * @return The highlighting lexer.
     */
    @Override
    public Lexer getHighlightingLexer() {
        return this.lexer;
    }

    /**
     * Gets the highlights for the specified token type.
     *
     * @param tokenType The token type.
     * @return The text attributes for the token type.
     */
    @Override
    public TextAttributesKey[] getTokenHighlights(final IElementType tokenType) {
        if (!(tokenType instanceof SpoofaxTokenType))
            return EMPTY_KEYS;

        // FIXME: Use fixed categorized styles, so we don't have to use createTextAttributesKey.
        return getTextAttributesKeyForStyle(((SpoofaxTokenType)tokenType).getStyle());
    }

    /**
     * Gets the text attributes for the specified style.
     *
     * @param style The style.
     * @return The text attributes.
     */
    private TextAttributesKey[] getTextAttributesKeyForStyle(final IStyle style) {
        @Nullable TextAttributesKey[] attributes = this.styleMap.getOrDefault(style, null);
        if (attributes == null) {
            final String name = "STYLE_" + style.hashCode();
            @SuppressWarnings("deprecation") final TextAttributesKey attribute = createTextAttributesKey(
                    name,
                    new TextAttributes(
                            style.color(),
                            style.backgroundColor(),
                            null,
                            (style.underscore() ? EffectType.LINE_UNDERSCORE : null),
                            (style.bold() ? Font.BOLD : Font.PLAIN)
                                    + (style.italic() ? Font.ITALIC : Font.PLAIN)
                    )
            );
            attributes = new TextAttributesKey[]{attribute};

            this.styleMap.put(style, attributes);
        }
        return attributes;
    }
}
