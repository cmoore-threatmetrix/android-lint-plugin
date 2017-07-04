package org.jenkinsci.plugins.android_lint;

import org.jenkinsci.Symbol;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.analysis.core.NullBuildHistory;
import hudson.plugins.analysis.core.PluginDescriptor;
import hudson.plugins.analysis.graph.DefaultGraphConfigurationView;
import hudson.plugins.analysis.graph.GraphConfiguration;
import org.kohsuke.stapler.Ancestor;
import org.kohsuke.stapler.StaplerProxy;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * Descriptor for the class {@link LintPublisher}.<br>
 * Used as a singleton.
 * <p>
 * The class is marked as public so that it can be accessed from views.
 */
@Symbol("androidLint")
@Extension(ordinal = 100)
public final class LintDescriptor extends PluginDescriptor implements StaplerProxy {

    /** Used in visible URLs. */
    public static final String PLUGIN_NAME = "androidLint";

    /** The URL of the result action. */
    public static final String RESULT_URL = PluginDescriptor.createResultUrlName(PLUGIN_NAME);

    /** Used to specify location of resources. */
    public static final String PLUGIN_ROOT = "/plugin/android-lint/";

    /** Icon to use for the result and project action. */
    static final String ACTION_ICON = PLUGIN_ROOT + "icons/android-24x24.png";

    public LintDescriptor() {
        super(LintPublisher.class);
    }

    /**
     * Returns the graph configuration screen.
     *
     * @param link
     *            the link to check
     * @param request
     *            stapler request
     * @param response
     *            stapler response
     * @return the graph configuration or <code>null</code>
     */
    public Object getDynamic(final String link, final StaplerRequest request, final StaplerResponse response) {
        if ("configureDefaults".equals(link)) {
            Ancestor ancestor = request.findAncestor(AbstractProject.class);
            if (ancestor.getObject() instanceof AbstractProject) {
                AbstractProject<?, ?> project = (AbstractProject<?, ?>)ancestor.getObject();
                return new DefaultGraphConfigurationView(
                        new GraphConfiguration(LintProjectAction.getAllGraphs()), project, PLUGIN_NAME,
                        new NullBuildHistory(),
                        project.getAbsoluteUrl() + "/descriptorByName/LintPublisher/configureDefaults/");
            }
        }
        return null;
    }

    @Override
    public String getDisplayName() {
        return Messages.AndroidLint_Publisher_Name();
    }

    @Override
    public String getPluginName() {
        return PLUGIN_NAME;
    }

    @Override
    public String getPluginRoot() {
        return PLUGIN_ROOT;
    }

    @Override
    public String getIconUrl() {
        return ACTION_ICON;
    }

    @Override
    public Object getTarget() {
        return this;
    }
}
