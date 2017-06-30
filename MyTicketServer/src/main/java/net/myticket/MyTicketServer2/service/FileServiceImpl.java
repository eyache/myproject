package net.myticket.MyTicketServer2.service;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:myticket.properties")
public class FileServiceImpl implements FileService {
	
	@Value("${ticket.basedir}")
	private String baseDir;
	@Value("${ticket.image}")
	private String imageDir;
	@Value("${ticket.thumbnail}")
	private String thumbnailDir;

	public FileServiceImpl() {
	}

	@Override
	public boolean saveFile(String fileName, String filePath) throws Exception {

		manageDirectories();
		BufferedImage image = ImageIO.read(new File(filePath));
		BufferedImage thumbnail = createThumbnail(image);
		
		boolean result = ImageIO.write(image, "jpg", new File(baseDir + "\\image\\" + fileName));
		if(result)
			result = ImageIO.write(thumbnail, "jpg", new File(baseDir + "\\thumbnail\\" + fileName));
		
		return result;
	}

	@Override
	public boolean updateFile(String fileName, String filePath) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFileById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFile(long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	private BufferedImage createThumbnail(BufferedImage image) throws Exception {
		
		//BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		//img.createGraphics().drawImage(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH),0,0,null);
		//return img;
		return scale(image, 0.2);
	}
	
	private BufferedImage scale(BufferedImage source,double ratio) {
	  int w = (int) (source.getWidth() * ratio);
	  int h = (int) (source.getHeight() * ratio);
	  BufferedImage bi = getCompatibleImage(w, h);
	  Graphics2D g2d = bi.createGraphics();
	  double xScale = (double) w / source.getWidth();
	  double yScale = (double) h / source.getHeight();
	  AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
	  g2d.drawRenderedImage(source, at);
	  g2d.dispose();
	  return bi;
	}

	private BufferedImage getCompatibleImage(int w, int h) {
	  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	  GraphicsDevice gd = ge.getDefaultScreenDevice();
	  GraphicsConfiguration gc = gd.getDefaultConfiguration();
	  BufferedImage image = gc.createCompatibleImage(w, h);
	  return image;
	}
	
	private void manageDirectories() {
		if(baseDir != null){	
			File dir = new File(baseDir);
			if(!dir.exists()){
				File image = new File(baseDir+"\\"+imageDir);
				image.mkdirs();
				File thumbnail = new File(baseDir+"\\"+thumbnailDir);
				thumbnail.mkdirs();
			}
		}
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public String getThumbnailDir() {
		return thumbnailDir;
	}

	public void setThumbnailDir(String thumbnailDir) {
		this.thumbnailDir = thumbnailDir;
	}

}
