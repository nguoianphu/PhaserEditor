// The MIT License (MIT)
//
// Copyright (c) 2015, 2018 Arian Fornaris
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions: The above copyright notice and this permission
// notice shall be included in all copies or substantial portions of the
// Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.
package phasereditor.assetpack.ui.properties;

import static java.lang.System.out;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import phasereditor.assetpack.core.AssetType;
import phasereditor.assetpack.core.TilemapAssetModel;
import phasereditor.assetpack.ui.preview.TilemapCSVAssetPreviewComp;
import phasereditor.ui.properties.FormPropertySection;

/**
 * @author arian
 *
 */
public class TilemapCSVPreviewSection extends FormPropertySection<TilemapAssetModel> {

	private TilemapCSVAssetPreviewComp _preview;

	public TilemapCSVPreviewSection() {
		super("Tilemap CSV Preview");

		setFillSpace(true);
	}

	@Override
	public boolean supportThisNumberOfModels(int number) {
		return number == 1;
	}

	@Override
	public boolean canEdit(Object obj) {
		return obj instanceof TilemapAssetModel && ((TilemapAssetModel) obj).getType() == AssetType.tilemapCSV;
	}

	@Override
	public Control createContent(Composite parent) {
		
		out.println(getClass().getSimpleName() + " -> createContent()");
		
		var preview = new TilemapCSVAssetPreviewComp(parent, 0);
		
		addUpdate(() -> {
			out.println("addUpdate");
			preview.setModel(getModels().get(0));
		});

		_preview = preview;

		return preview;
	}

	@Override
	public void fillToolbar(ToolBarManager manager) {
		_preview.fillToolBar(manager);
	}

}
