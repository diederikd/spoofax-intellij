/*
 * Copyright © 2015-2015
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

package org.metaborg.spoofax.idea.vfs;

import com.intellij.ide.highlighter.ArchiveFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.metaborg.idea.vfs.MetaborgFileType;

import javax.swing.*;
import java.util.Collections;

/**
 * The file type for Spoofax artifacts.
 */
public class SpoofaxArtifactFileType extends ArchiveFileType implements MetaborgFileType {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "SPOOFAX_ARTIFACT";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Spoofax artifact";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultExtension() {
        return getExtensions().iterator().next();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Icon getIcon() {
        // TODO: Artifact icon.
        return super.getIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<String> getExtensions() {
        return Collections.singletonList("spoofax-language");
    }
}
