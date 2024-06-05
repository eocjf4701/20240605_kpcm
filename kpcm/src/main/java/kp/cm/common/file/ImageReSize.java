package kp.cm.common.file;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class ImageReSize {
/*	private static final int IMG_WIDTH = 100;
	private static final int IMG_HEIGHT = 100;*/

	/**
	 * 이미지를 리싸이징 한다.
	 * @param imgPath :이미지패스
	 * @param imgType : 이미지타입 jpg, png
	 * @param hint : 힌트 여부
	 * @param IMG_WIDTH : 가로크기
	 * @param IMG_HEIGHT : 세로크기
	 */
	public static void createReSize(String imgPath,String imgType,boolean hint,int IMG_WIDTH, int IMG_HEIGHT) {
		try{
			BufferedImage originalImage = ImageIO.read(new File(imgPath));
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			if(hint){
				BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type,IMG_WIDTH,IMG_HEIGHT);
				ImageIO.write(resizeImageHintJpg, imgType, new File(imgPath));

			}else{
				BufferedImage resizeImageJpg = resizeImage(originalImage, type,IMG_WIDTH,IMG_HEIGHT);
				ImageIO.write(resizeImageJpg,imgType, new File(imgPath));

			}

		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

    private static BufferedImage resizeImage(BufferedImage originalImage, int type,int IMG_WIDTH, int IMG_HEIGHT){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();

	return resizedImage;
    }


	 private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type,int IMG_WIDTH, int IMG_HEIGHT){

			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();
			g.setComposite(AlphaComposite.Src);

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

			return resizedImage;
		    }

}
