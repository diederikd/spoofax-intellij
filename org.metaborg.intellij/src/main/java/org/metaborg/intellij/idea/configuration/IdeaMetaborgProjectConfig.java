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

package org.metaborg.intellij.idea.configuration;

import com.google.inject.*;
import com.intellij.openapi.components.*;
import org.jetbrains.annotations.*;
import org.metaborg.intellij.configuration.*;
import org.metaborg.intellij.idea.*;
import org.metaborg.intellij.logging.*;
import org.metaborg.util.log.*;

/**
 * Project-level configuration of the plugin.
 */
@State(
        name = IMetaborgProjectConfig.CONFIG_NAME,
        storages = {
                @Storage(file = StoragePathMacros.PROJECT_FILE),
                @Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/" + IMetaborgProjectConfig.CONFIG_FILE,
                        scheme = StorageScheme.DIRECTORY_BASED)
        }
)
public final class IdeaMetaborgProjectConfig implements IMetaborgProjectConfig, ProjectComponent,
        PersistentStateComponent<MetaborgProjectConfigState> {

    // Don't initialize fields that depend on the state here. Initialize in loadState().
    private MetaborgProjectConfigState state;
    @InjectLogger
    private ILogger logger;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.state.myName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String value) {
        this.state.myName = value;
    }

    /**
     * This instance is created by IntelliJ's plugin system.
     * Do not call this constructor manually.
     */
    public IdeaMetaborgProjectConfig() {
        SpoofaxIdeaPlugin.injector().injectMembers(this);
        // Don't initialize fields that depend on the state here. Initialize in loadState().
        loadState(new MetaborgProjectConfigState());
    }

    @Inject
    @SuppressWarnings("unused")
    private void inject() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponent() {
        // Occurs when the application is starting.

        this.logger.debug("Loading Spoofax plugin project-wide config.");
        this.logger.info("Loaded Spoofax plugin project-wide config.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disposeComponent() {
        // Occurs when the application is quitting.
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public String getComponentName() {
        return IdeaMetaborgProjectConfig.class.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public MetaborgProjectConfigState getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     *
     * This method is only called if the configuration has changed.
     */
    @Override
    public void loadState(final MetaborgProjectConfigState state) {
        this.state = state;
        this.logger.info("Restored project configuration.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void projectOpened() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void projectClosed() {

    }
}
