package com.centris.campus.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class CustomizableStudentReportExcell {
	
		HSSFSheet firstSheet;
	    Collection<File> files;
	    HSSFWorkbook workbook;
	    File exactFile;
	    
	CellStyle styleHeader=null;
	CellStyle stylemainHeader=null;
	
	
	Font fontHeader=null;
	Font fontmainHeader=null;
	public CellStyle MainHeaderCss() {
		if (stylemainHeader == null)
			stylemainHeader = workbook.createCellStyle();
		if (fontmainHeader == null)
			fontmainHeader = workbook.createFont();
		fontmainHeader.setFontName(HSSFFont.FONT_ARIAL);
		fontmainHeader.setFontHeightInPoints((short) 13);
		fontmainHeader.setColor(HSSFColor.GREEN.index);
		fontmainHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		stylemainHeader.setFont(fontmainHeader);
		stylemainHeader.setAlignment(stylemainHeader.ALIGN_CENTER);
		stylemainHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		return stylemainHeader;
	}
	
	public CellStyle HeaderCss() {
		if(styleHeader==null)
		styleHeader = workbook.createCellStyle();
		if(fontHeader==null)
		 fontHeader = workbook.createFont();
		fontHeader.setColor(HSSFColor.BLACK.index);
		fontHeader.setBoldweight(Font.BOLDWEIGHT_BOLD);
		styleHeader.setFont(fontHeader);
		styleHeader.setWrapText(true);
		styleHeader.setAlignment(styleHeader.ALIGN_CENTER);
		styleHeader.setBorderBottom((short) 1);
		styleHeader.setBorderTop((short) 1);
		styleHeader.setBorderLeft((short) 1);
		styleHeader.setBorderRight((short) 1);
		styleHeader.setFillBackgroundColor(HSSFColor.GREY_80_PERCENT.index);
		return styleHeader;
	}
	
   
    
    public void download(ArrayList<HashMap<String, String>> fullstudentList, String[] fielldArray, String path, String[] labelArray) throws Exception{
    	try{
    	workbook = new HSSFWorkbook();
        firstSheet = workbook.createSheet("Student List");
        Row rowHeader = firstSheet.createRow(0);
        
        for(int k=0;k<labelArray.length;k++){
    		
   		 Cell cell0= rowHeader.createCell(k);
   		 cell0.setCellValue(labelArray[k]);
   		 cell0.setCellStyle(HeaderCss());
   		 firstSheet.setColumnWidth(k,(short)5000);
   		 
   	 }
        
        
        for(int i=0;i<fullstudentList.size();i++){
        	 
        	 Row row = firstSheet.createRow(i+1);
        	 for(int j=0;j<fielldArray.length;j++){
        		
        		 Cell cell= row.createCell(j);
        		 cell.setCellValue(fullstudentList.get(i).get(fielldArray[j]));
        	 }
        }
        
       
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
    }

	   
  
    FileOutputStream fos1 = null;
    try {
    	
        fos1=new FileOutputStream(new File(path));
        HSSFCellStyle hsfstyle=workbook.createCellStyle();
        workbook.write(fos1);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
        
    	
    }

}
