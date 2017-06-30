package net.myticket.MyTicketServer2.service;

public interface FileService {
	public boolean saveFile(String fileName, String filePath) throws Exception; 
	 public boolean updateFile(String fileName, String filePath) throws Exception; 
	 public String getFileById(long id) throws Exception;  
	 //public List<String> getFileList() throws Exception;  
	 public boolean deleteFile(long id) throws Exception;
	 public void setBaseDir(String value);
	 public String getBaseDir();
	 public void setImageDir(String value);
	 public String getImageDir();
	 public void setThumbnailDir(String value);
	 public String getThumbnailDir();
}
