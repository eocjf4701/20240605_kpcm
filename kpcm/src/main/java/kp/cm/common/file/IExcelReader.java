package kp.cm.common.file;

import java.io.IOException;

public interface IExcelReader {
	public String readAndWriteFile(String[] sheetInfo, String fileName, String filePath, String rootDirectory)  throws IOException;
}
