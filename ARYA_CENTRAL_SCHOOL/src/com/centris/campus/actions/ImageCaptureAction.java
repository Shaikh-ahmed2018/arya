/**
 * 
 */
package com.centris.campus.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.xml.datatype.DatatypeConstants.Field;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.lti.civil.CaptureDeviceInfo;
import com.lti.civil.CaptureException;
import com.lti.civil.CaptureObserver;
import com.lti.civil.CaptureStream;
import com.lti.civil.CaptureSystem;
import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.DefaultCaptureSystemFactorySingleton;
import com.lti.civil.Image;
import com.lti.civil.awt.AWTImageConverter;
import com.sun.image.codec.jpeg.*;



/**
 * @author sathish
 *
 */
public class ImageCaptureAction extends  DispatchAction implements CaptureObserver {
	private static final Logger logger = Logger.getLogger(ImageCaptureAction.class);
	
	
	JButton start = null;
	JButton shot = null;
	JButton stop = null;
	CaptureStream captureStream = null;
	boolean takeShot=false;
	
	public ImageCaptureAction() {
		
		
	
		CaptureSystemFactory factory = DefaultCaptureSystemFactorySingleton.instance();
		CaptureSystem system;
		try {
		system = factory.createCaptureSystem();
		system.init();
		
		System.out.println("system="+system.getCaptureDeviceInfoList());
		List<?> list = system.getCaptureDeviceInfoList();
		int i = 0;
		if (i < list.size()) {
		CaptureDeviceInfo info = (CaptureDeviceInfo) list.get(i);
		System.out.println((new StringBuilder()).append("Device ID ").append(i).append(": ").append(info.getDeviceID()).toString());
		System.out.println((new StringBuilder()).append("Description ").append(i).append(": ").append(info.getDescription()).toString());
		captureStream = system.openCaptureDeviceStream(info.getDeviceID());
		captureStream.setObserver(ImageCaptureAction.this);
		}
		} catch (CaptureException ex) {
		ex.printStackTrace();
		}
		//UI work of the program
		JFrame frame = new JFrame();
		frame.setSize(7000, 800);
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		start = new JButton("Start");
		stop = new JButton("Stop");
		shot = new JButton("Shot");
		panel.add(start);
		panel.add(stop);
		panel.add(shot);
		panel.revalidate();
		start.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
		captureStream.start();
		} catch (CaptureException ex) {
		ex.printStackTrace();
		}
		}
		
		});
		stop.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		try {
		captureStream.stop();
		} catch (CaptureException ex) {
		ex.printStackTrace();
		}
		}
		});
		shot.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		takeShot=true;
		}
		});
		}

	@Override
	public void onError(CaptureStream stream, CaptureException ce) {
		System.out.println("Error!");
		
	}

	@Override
	public void onNewImage(CaptureStream stream, Image image) {
		if(!takeShot) return;
		takeShot=false;
		System.out.println("New Image Captured");
		byte bytes[] = null;
		try {
		if (image == null) {
		bytes = null;
		return;
		}
		try {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JPEGImageEncoder jpeg =JPEGCodec.createJPEGEncoder(os);
		jpeg.encode(AWTImageConverter.toBufferedImage(image));
		os.close();
		bytes = os.toByteArray();
		} catch (IOException e) {
		e.printStackTrace();
		bytes = null;
		} catch (Throwable t) {
		t.printStackTrace();
		bytes = null;
		}
		if (bytes == null) {
		return;
		}
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		File file = new File("/img" + Calendar.getInstance().getTimeInMillis() + ".jpg");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.close();
		BufferedImage myImage = ImageIO.read(file);
		shot.setText("");
		shot.setIcon(new ImageIcon(myImage));
		shot.revalidate();
		} catch (IOException ex) {
		ex.printStackTrace();
		}
		
	}
	
	public ActionForward ImageCapture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in ImageCaptureAction: ImageCapture Starting");
		try {
			
			
			
			ImageCaptureAction image=new ImageCaptureAction();
			
			
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ImageCaptureAction : ImageCapture Ending");
		
		
		return null;
	}
	public static void main(String args[])
			throws Exception {
		ImageCaptureAction image=new ImageCaptureAction();
			}
	
	
}