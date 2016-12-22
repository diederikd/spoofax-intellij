/*
 * Copyright © 2015-2016
 *
 * This file is part of Spoofax for IntelliJ.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.metaborg.intellij.idea.parsing.elements;


import org.metaborg.intellij.idea.languages.MetaborgIdeaLanguage;

/**
 * Element type for an identifier in a Metaborg language.
 */
public class MetaborgIdentifierElementType extends SpoofaxElementType {

    /**
     * Initializes a new instance of the {@link MetaborgIdentifierElementType} class.
     *
     * @param language The language.
     */
    public MetaborgIdentifierElementType(final MetaborgIdeaLanguage language) {
        super(language, "SPOOFAX_IDENTIFIER_TYPE");
    }

}