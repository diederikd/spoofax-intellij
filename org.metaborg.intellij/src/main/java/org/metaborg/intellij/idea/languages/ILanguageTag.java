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

package org.metaborg.intellij.idea.languages;

import org.metaborg.core.language.*;

/**
 * Describes a tag that's used to associate a value with a particular {@link ILanguage} object.
 * @param <T> The type of value.
 */
@Deprecated
public interface ILanguageTag<T> {

    /**
     * Called when a language is activated.
     *
     * @param value The value of the tag.
     * @param language The language.
     * @param ideaLanguage The IDEA language.
     */
    default void onActivateLanguage(T value, ILanguage language, MetaborgIdeaLanguage ideaLanguage) {
        // Nothing to do.
    }

    /**
     * Called when a language is deactivated.
     *
     * @param value The value of the tag.
     * @param language The language.
     * @param ideaLanguage The IDEA language.
     */
    default void onDectivateLanguage(T value, ILanguage language, MetaborgIdeaLanguage ideaLanguage) {
        // Nothing to do.
    }

}
