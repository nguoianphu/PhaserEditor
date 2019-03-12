// The MIT License (MIT)
//
// Copyright (c) 2015, 2019 Arian Fornaris
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
package phasereditor.atlas.ui;

import static java.lang.System.out;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.json.JSONObject;

/**
 * @author arian
 *
 */
public class TextureAtlasSplitter {
	private String _jsonFileName;
	private String _pngFileName;
	private File _outputFolder;

	public TextureAtlasSplitter(String jsonFileName) {
		_jsonFileName = jsonFileName;

		_pngFileName = jsonFileName.substring(0, jsonFileName.length() - 5) + ".png";
		_outputFolder = new File(new File(_jsonFileName).getParentFile(), "output");
		_outputFolder.mkdirs();
	}

	public void split() throws Exception {
		var jsonFilePath = Paths.get(_jsonFileName);

		var packedTextureImg = ImageIO.read(new File(_pngFileName));

		// at the moment, only use the JSON Array format
		var jsonData = new JSONObject(jsonFilePath.toFile());
		var framesData = jsonData.getJSONArray("frames");

		for (var i = 0; i < framesData.length(); i++) {
			var itemData = framesData.getJSONObject(i);
			var filename = itemData.getString("filename");
			var frame = itemData.getJSONObject("frame");
			var sourceSize = itemData.getJSONObject("sourceSize");
			var spriteSourceSize = itemData.getJSONObject("spriteSourceSize");

			var img = new BufferedImage(sourceSize.getInt("w"), sourceSize.getInt("h"), BufferedImage.TYPE_INT_ARGB);
			var g2 = img.createGraphics();

			var sx1 = frame.getInt("x");
			var sy1 = frame.getInt("y");
			var sx2 = sx1 + frame.getInt("w");
			var sy2 = sy1 + frame.getInt("h");

			var dx1 = spriteSourceSize.getInt("x");
			var dy1 = spriteSourceSize.getInt("y");
			var dx2 = dx1 + spriteSourceSize.getInt("w");
			var dy2 = dy1 + spriteSourceSize.getInt("h");

			g2.drawImage(packedTextureImg, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);

			g2.dispose();

			ImageIO.write(img, "png", new File(_outputFolder, filename + ".png"));
		}
	}

	public static void main(String[] args) throws Exception {
		var jsonFilePath = args[0];

		var splitter = new TextureAtlasSplitter(jsonFilePath);
		splitter.split();
		out.println("Done!");
	}
}