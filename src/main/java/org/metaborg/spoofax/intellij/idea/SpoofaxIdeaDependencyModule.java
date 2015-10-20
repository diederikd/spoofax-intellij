package org.metaborg.spoofax.intellij.idea;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.intellij.lang.ParserDefinition;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.projectRoots.SdkType;
import org.metaborg.core.project.IProjectService;
import org.metaborg.core.syntax.IParserConfiguration;
import org.metaborg.spoofax.core.syntax.JSGLRParserConfiguration;
import org.metaborg.spoofax.intellij.SpoofaxIntelliJDependencyModule;
import org.metaborg.spoofax.intellij.factories.*;
import org.metaborg.spoofax.intellij.idea.gui.BuilderMenu;
import org.metaborg.spoofax.intellij.idea.languages.*;
import org.metaborg.spoofax.intellij.idea.model.IntelliJProject;
import org.metaborg.spoofax.intellij.idea.model.SpoofaxModuleBuilder;
import org.metaborg.spoofax.intellij.idea.model.SpoofaxModuleType;
import org.metaborg.spoofax.intellij.idea.project.LanguageImplEditor;
import org.metaborg.spoofax.intellij.idea.project.LanguageImplPanel;
import org.metaborg.spoofax.intellij.idea.project.LanguageImplTableModel;
import org.metaborg.spoofax.intellij.idea.project.SpoofaxFileEditorManagerListener;
import org.metaborg.spoofax.intellij.sdk.SpoofaxSdkType;

/**
 * The Guice dependency injection module for the Spoofax IDEA plugin.
 */
public final class SpoofaxIdeaDependencyModule extends SpoofaxIntelliJDependencyModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        super.configure();

        bind(IIdeaAttachmentManager.class).to(IdeaAttachmentManager.class).in(Singleton.class);
        bind(IIdeaLanguageManager.class).to(IdeaLanguageManagerImpl.class).in(Singleton.class);
        bind(SpoofaxModuleBuilder.class).in(Singleton.class);
        bind(ILexerParserManager.class).to(LexerParserManager.class).in(Singleton.class);

        bind(SpoofaxSyntaxHighlighterFactory.class);
        bind(IParserConfiguration.class).toInstance(new JSGLRParserConfiguration(
            /* implode    */ true,
            /* recovery   */ true,
            /* completion */ false,
            /* timeout    */ 30000));

        install(new FactoryModuleBuilder()
                        .implement(Lexer.class, SpoofaxLexer.class)
                        .build(IHighlightingLexerFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(Lexer.class, CharacterLexer.class)
                        .build(ICharacterLexerFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(ParserDefinition.class, SpoofaxParserDefinition.class)
                        .build(IParserDefinitionFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(IntelliJProject.class, IntelliJProject.class)
                        .build(IProjectFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(LanguageImplTableModel.class, LanguageImplTableModel.class)
                        .build(ILanguageImplTableModelFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(LanguageImplPanel.class, LanguageImplPanel.class)
                        .build(ILanguageImplPanelFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(LanguageImplEditor.class, LanguageImplEditor.class)
                        .build(ILanguageImplEditorFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(SpoofaxFileEditorManagerListener.class, SpoofaxFileEditorManagerListener.class)
                        .build(ISpoofaxFileEditorManagerListenerFactory.class));
        install(new FactoryModuleBuilder()
                        .implement(BuilderMenu.class, BuilderMenu.class)
                        .build(IBuilderMenuFactory.class));
        install(new IntelliJExtensionProviderFactory().provide(SpoofaxSdkType.class, SdkType.EP_NAME.getName()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bindProject() {
        bind(IntelliJProjectService.class).in(Singleton.class);
        bind(IProjectService.class).to(IntelliJProjectService.class).in(Singleton.class);
        bind(IIntelliJProjectService.class).to(IntelliJProjectService.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    private SpoofaxModuleType provideModuleType() {
        return (SpoofaxModuleType)ModuleTypeManager.getInstance().findByID(SpoofaxModuleType.ID);
    }
}
