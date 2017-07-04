package org.jenkinsci.plugins.android_lint;

import hudson.model.AbstractProject;
import hudson.plugins.analysis.core.ResultAction;
import hudson.plugins.analysis.core.AbstractProjectAction;
import hudson.plugins.analysis.core.PluginDescriptor;
import hudson.plugins.analysis.graph.BuildResultGraph;
import java.util.List;

/**
 * Entry point to visualize the trend graph in the project screen.
 * <p>
 * Drawing of the graph is delegated to the associated {@link ResultAction}.
 */
public class LintProjectAction extends AbstractProjectAction<ResultAction<LintResult>> {

    /**
     * Returns all the graphs.
     *
     * @return the graphs
     */
    public static List<BuildResultGraph> getAllGraphs() {
        return new LintProjectAction(null, null).getAvailableGraphs();
    }

    /**
     * Instantiates a new {@link LintProjectAction}.
     *
     * @param project The project that owns this action.
     */
    public LintProjectAction(final AbstractProject<?, ?> project) {
        this(project, LintResultAction.class);
    }

    /**
     * Instantiates a new {@link LintProjectAction}.
     *
     * @param project The project that owns this action.
     * @param type The result action type.
     */
    public LintProjectAction(final AbstractProject<?, ?> project,
            final Class<? extends ResultAction<LintResult>> type) {
        super(project, LintResultAction.class,
                Messages._AndroidLint_ProjectAction_Name(), Messages._AndroidLint_ProjectAction_TrendName(),
                LintDescriptor.PLUGIN_NAME,
                LintDescriptor.ACTION_ICON,
                LintDescriptor.RESULT_URL);
    }

    @Override
    public String getDisplayName() {
        return Messages.AndroidLint_ProjectAction_Name();
    }

    @Override
    public String getTrendName() {
        return Messages.AndroidLint_ProjectAction_TrendName();
    }

}
