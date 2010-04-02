package org.zhjh.galaxykit.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.MatchingCharacterPainter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.zhjh.galaxykit.options.GALPreferences;

public class GALEditor extends TextEditor {
    
    private GALSharedParser parser;
    
    public GALEditor() {
        super();
        parser = new GALSharedParser();
    }
    
    public GALSharedParser getSharedParser() {
	return parser;
    }
    
    @Override
    protected void initializeEditor() {
	super.initializeEditor();
	setSourceViewerConfiguration(new GALConfiguration(this));
    }

    @Override
    public void createPartControl(Composite parent) {
	super.createPartControl(parent);
	// character matching.
	final ISourceViewer viewer = getSourceViewer();
	if (viewer instanceof ITextViewerExtension2) {
	    final MatchingCharacterPainter painter = new MatchingCharacterPainter(viewer,
		    new DefaultCharacterPairMatcher(new char[] { '(',
			    ')', '[', ']', '{', '}' }));
	    painter.setColor(GALPreferences.getDefault().getCharacterMatchingColor());
	    ((ITextViewerExtension2) viewer).addPainter(painter);
	}
    }

    @Override
    protected void configureSourceViewerDecorationSupport(
	    SourceViewerDecorationSupport support) {
	super.configureSourceViewerDecorationSupport(support);
    }
    
    public void update(IDocument doc){
	parser.parse(doc);
    }
    
}
