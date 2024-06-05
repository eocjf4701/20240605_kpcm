package kp.cm.common.file;

import java.util.List;
import java.util.Map;

public interface DownloadService {
	public List<Map<String, Object>> getDownloadListCount(Map<String, Object> parmaMap) throws Exception;
	public List<Map<String, Object>> getDownloadList(Map<String, Object> parmaMap) throws Exception;
}
