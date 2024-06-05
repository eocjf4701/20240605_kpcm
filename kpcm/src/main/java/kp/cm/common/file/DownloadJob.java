package kp.cm.common.file;

public class DownloadJob extends Job {
	private int totalCount = 0 ;
	private int progressCount = 0;
	private boolean isEnd = false;
	private String zipFileName = "";
	private int fileCount = 0;
	private int progressFileIndex = 1;

	public void setProgressFileIndex(int progressFileIndex) {
		this.progressFileIndex = progressFileIndex;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public boolean getIsEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public String getZipFileName() {
		return zipFileName;
	}

	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

	public void setProgressCount(int progressCount) {
		this.progressCount = progressCount;
	}

	public void setTotalCount(int totolCount) {
		this.totalCount = totolCount;
	}

	public int getProgress(){
		int progress = 0;

		if(fileCount == 1){
			progress = (int)(((double)progressCount / (double)totalCount) * 100 / (fileCount)) + (int)((double)(progressFileIndex - 1) / (double)fileCount * 100);
		}else{
			progress = (int)((double)progressFileIndex / (double)fileCount * 100);
		}

		return progress;
	}
}
