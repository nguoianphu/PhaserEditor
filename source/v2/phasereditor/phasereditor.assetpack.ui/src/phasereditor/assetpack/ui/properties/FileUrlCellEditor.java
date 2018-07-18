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

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import phasereditor.assetpack.core.AssetModel;
import phasereditor.assetpack.core.AssetPackModel;
import phasereditor.assetpack.ui.AssetPackUI;

/**
 * @author arian
 *
 */
public class FileUrlCellEditor extends DialogCellEditor {

	private AssetModel _asset;
	private Function<AssetModel, String> _getUrl;
	private Supplier<List<IFile>> _discoverFiles;
	private String _dialogTitle;
	private String _currentValue;

	public FileUrlCellEditor(Composite parent, AssetModel asset, Function<AssetModel, String> getUrl,
			Supplier<List<IFile>> discoverFiles, String dialogTitle) {
		super(parent);
		_asset = asset;
		_getUrl = getUrl;
		_discoverFiles = discoverFiles;
		_dialogTitle = dialogTitle;
		_currentValue = getUrl.apply(asset);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		AssetPackModel pack = _asset.getPack();

		IFile urlFile = _asset.getFileFromUrl(_getUrl.apply(_asset));

		List<IFile> files = _discoverFiles.get();

		String result = AssetPackUI.browseAssetFile(pack, _dialogTitle /* "atlas JSON/XML" */, urlFile, files,
				cellEditorWindow.getShell(), null);

		if (result == null) {
			result = _currentValue;
		}

		IFile file = _asset.getFileFromUrl(result);

		return file;
	}

}
