package com.centris.campus.util;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.centris.campus.vo.LeaveDetailsReportVo;



public class LeaveReportXLS {


		 int rownum = 0;
		    HSSFSheet firstSheet;
		    Collection<File> files;
		    HSSFWorkbook workbook;
		    File exactFile;

		    CellStyle styleAbsent=null;
		    CellStyle stylePresent=null;
		    CellStyle styleWeekOff=null;
		    CellStyle styleNone=null;
		    CellStyle styleHoliday=null;
		    CellStyle styleHeader=null;
		    Font fontAbsent=null;
		    Font fontPresent=null;
		    Font fontWeekOff=null;
		    Font fontNone=null;
		    Font fontHoliday=null;
		    Font fontHeader=null;
		    
		   
		 
		    public CellStyle AbsentCss() {
		    	if(styleAbsent==null)
				 styleAbsent = workbook.createCellStyle();
		    	if(fontAbsent==null)
				 fontAbsent = workbook.createFont();
		    	fontAbsent.setColor(HSSFColor.RED.index);
				styleAbsent.setFont(fontAbsent);
				styleAbsent.setAlignment(styleAbsent.ALIGN_CENTER);
				styleAbsent.setBorderBottom((short) 1);
				styleAbsent.setBorderTop((short) 1);
				styleAbsent.setBorderLeft((short) 1);
				styleAbsent.setBorderRight((short) 1);
				return styleAbsent;
			}

			public CellStyle PresentCss() {
				if(stylePresent==null)
					stylePresent = workbook.createCellStyle();
				if(fontPresent==null)
				 fontPresent = workbook.createFont();
				fontPresent.setColor(HSSFColor.GREEN.index);
				stylePresent.setFont(fontPresent);
				stylePresent.setAlignment(stylePresent.ALIGN_CENTER);
				stylePresent.setBorderBottom((short) 1);
				stylePresent.setBorderTop((short) 1);
				stylePresent.setBorderLeft((short) 1);
				stylePresent.setBorderRight((short) 1);
				return stylePresent;
			}

			public CellStyle WeekOffCss() {
				if(styleWeekOff==null)
				styleWeekOff = workbook.createCellStyle();
				if(fontWeekOff==null)
				 fontWeekOff = workbook.createFont();
				fontWeekOff.setColor(HSSFColor.BLUE.index);
				styleWeekOff.setFont(fontWeekOff);
				styleWeekOff.setAlignment(styleWeekOff.ALIGN_CENTER);
				styleWeekOff.setBorderBottom((short) 1);
				styleWeekOff.setBorderTop((short) 1);
				styleWeekOff.setBorderLeft((short) 1);
				styleWeekOff.setBorderRight((short) 1);
				return styleWeekOff;
			}
			
			public CellStyle LeaveCss() {
				if(styleWeekOff==null)
				styleWeekOff = workbook.createCellStyle();
				if(fontWeekOff==null)
				 fontWeekOff = workbook.createFont();
				fontWeekOff.setColor(HSSFColor.LAVENDER.index);
				styleWeekOff.setFont(fontWeekOff);
				styleWeekOff.setAlignment(styleWeekOff.ALIGN_CENTER);
				styleWeekOff.setBorderBottom((short) 1);
				styleWeekOff.setBorderTop((short) 1);
				styleWeekOff.setBorderLeft((short) 1);
				styleWeekOff.setBorderRight((short) 1);
				return styleWeekOff;
			}

			public CellStyle NoneCss() {
				if(styleNone==null)
				 styleNone = workbook.createCellStyle();
				if(fontNone==null)
				 fontNone = workbook.createFont();
				fontNone.setColor(HSSFColor.BLACK.index);
				styleNone.setFont(fontNone);
				styleNone.setAlignment(styleNone.ALIGN_CENTER);
				styleNone.setBorderBottom((short) 1);
				styleNone.setBorderTop((short) 1);
				styleNone.setBorderLeft((short) 1);
				styleNone.setBorderRight((short) 1);

				return styleNone;
			}

			public CellStyle HeaderCss() {
				if(styleHeader==null)
				styleHeader = workbook.createCellStyle();
				if(fontHeader==null)
				 fontHeader = workbook.createFont();
				fontHeader.setColor(HSSFColor.BLACK.index);
				fontHeader.setBoldweight(Font.BOLDWEIGHT_BOLD);
				styleHeader.setFont(fontHeader);
			
				styleHeader.setAlignment(styleHeader.ALIGN_CENTER);
				styleHeader.setBorderBottom((short) 1);
				styleHeader.setBorderTop((short) 1);
				styleHeader.setBorderLeft((short) 1);
				styleHeader.setBorderRight((short) 1);
				styleHeader.setFillBackgroundColor(HSSFColor.GREY_80_PERCENT.index);
				styleHeader.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
				return styleHeader;
			}
		    
			public CellStyle HolidayCss() {
				if(styleHoliday==null)
				 styleHoliday = workbook.createCellStyle();
				if(fontHoliday==null)
				 fontHoliday = workbook.createFont();
				fontHoliday.setColor(HSSFColor.BLACK.index);
				styleHoliday.setFont(fontHoliday);
				styleHoliday.setAlignment(styleHoliday.ALIGN_CENTER);
				styleHoliday.setBorderBottom((short) 1);
				styleHoliday.setBorderTop((short) 1);
				styleHoliday.setBorderLeft((short) 1);
				styleHoliday.setBorderRight((short) 1);
				return styleHoliday;
			}
			
		 public void download(ArrayList<LeaveDetailsReportVo> LeaveVo,
					String path) throws Exception {
				
			 System.out.println(".........Create Excel File Start........");
			 
			 workbook = new HSSFWorkbook();
		     firstSheet = workbook.createSheet("Leave Report");
		     Row headerRow = firstSheet.createRow(rownum);
		     headerRow.setHeightInPoints(15);
		        Cell cellh0= headerRow.createCell(0);
		        cellh0.setCellValue("SNO");
		        cellh0.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(0,(short)1500);
		        
		        Cell cellh1= headerRow.createCell(1);
		        cellh1.setCellValue("Teacher ID");
		        cellh1.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(1,(short)3000);
		        
		        Cell cellh2= headerRow.createCell(2);
		        cellh2.setCellValue("Teacher Name");
		        cellh2.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(2,(short)5000);
		       
		        
		        Cell cellh3= headerRow.createCell(3);
		        cellh3.setCellValue("Total Leaves");
		        cellh3.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(3,(short)5000);
		        
		        Cell cellh4= headerRow.createCell(4);
		        cellh4.setCellValue("JAN");
		        cellh4.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(4,(short)3000);
		        
		        Cell cellh5= headerRow.createCell(5);
		        cellh5.setCellValue("JAN");
		        cellh5.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(5,(short)3000);
		        
		        Cell cellh6= headerRow.createCell(6);
		        cellh6.setCellValue("JAN");
		        cellh6.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(6,(short)3000);
		        
		       firstSheet.addMergedRegion(new CellRangeAddress(0,0,4,6));
		        
		        Cell cellh7= headerRow.createCell(7);
		        cellh7.setCellValue("FEB");
		        cellh7.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(7,(short)3000);
		        
		        Cell cellh8= headerRow.createCell(8);
		        cellh8.setCellValue("FEB");
		        cellh8.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(8,(short)3000);
		        
		        Cell cellh9= headerRow.createCell(9);
		        cellh9.setCellValue("FEB");
		        cellh9.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(9,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,7,9));
		        
		        Cell cellh10= headerRow.createCell(10);
		        cellh10.setCellValue("MARCH");
		        cellh10.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(10,(short)3000);
		        
		        Cell cellh11= headerRow.createCell(11);
		        cellh11.setCellValue("MARCH");
		        cellh11.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(11,(short)3000);
		        
		        Cell cellh12= headerRow.createCell(12);
		        cellh12.setCellValue("MARCH");
		        cellh12.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(12,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,10,12));
		        
		        Cell cellh13= headerRow.createCell(13);
		        cellh13.setCellValue("APRIL");
		        cellh13.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(13,(short)3000);
		        
		        Cell cellh14= headerRow.createCell(14);
		        cellh14.setCellValue("APRIL");
		        cellh14.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(14,(short)3000);
		        
		        Cell cellh15= headerRow.createCell(15);
		        cellh15.setCellValue("APRIL");
		        cellh15.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(15,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,13,15));

		        Cell cellh16= headerRow.createCell(16);
		        cellh16.setCellValue("MAY");
		        cellh16.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(16,(short)3000);
		        
		        Cell cellh17= headerRow.createCell(17);
		        cellh17.setCellValue("MAY");
		        cellh17.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(17,(short)3000);
		        
		        Cell cellh18= headerRow.createCell(18);
		        cellh18.setCellValue("MAY");
		        cellh18.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(18,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,16,18));

		        Cell cellh19= headerRow.createCell(19);
		        cellh19.setCellValue("JUNE");
		        cellh19.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(19,(short)3000);
		        
		        Cell cellh20= headerRow.createCell(20);
		        cellh20.setCellValue("JUNE");
		        cellh20.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(20,(short)3000);
		        
		        Cell cellh21= headerRow.createCell(21);
		        cellh21.setCellValue("JUNE");
		        cellh21.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(21,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,19,21));

		        Cell cellh22= headerRow.createCell(22);
		        cellh22.setCellValue("JULY");
		        cellh22.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(22,(short)3000);
		        
		        Cell cellh23= headerRow.createCell(23);
		        cellh23.setCellValue("JULY");
		        cellh23.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(23,(short)3000);
		        
		        Cell cellh24= headerRow.createCell(24);
		        cellh24.setCellValue("JULY");
		        cellh24.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(24,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,22,24));

		        Cell cellh25= headerRow.createCell(25);
		        cellh25.setCellValue("AUG");
		        cellh25.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(25,(short)3000);
		        
		        Cell cellh26= headerRow.createCell(26);
		        cellh26.setCellValue("AUG");
		        cellh26.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(26,(short)3000);
		        
		        Cell cellh27= headerRow.createCell(27);
		        cellh27.setCellValue("AUG");
		        cellh27.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(27,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,25,27));
		        
		        Cell cellh28= headerRow.createCell(28);
		        cellh28.setCellValue("SEP");
		        cellh28.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(28,(short)3000);
		        
		        Cell cellh29= headerRow.createCell(29);
		        cellh29.setCellValue("SEP");
		        cellh29.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(29,(short)3000);
		        
		        Cell cellh30= headerRow.createCell(30);
		        cellh30.setCellValue("SEP");
		        cellh30.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(30,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,28,30));

		        Cell cellh31= headerRow.createCell(31);
		        cellh31.setCellValue("OCT");
		        cellh31.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(31,(short)3000);
		        
		        Cell cellh32= headerRow.createCell(32);
		        cellh32.setCellValue("OCT");
		        cellh32.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(32,(short)3000);
		        
		        Cell cellh33= headerRow.createCell(33);
		        cellh33.setCellValue("OCT");
		        cellh33.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(31,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,31,33));

		        Cell cellh34= headerRow.createCell(34);
		        cellh34.setCellValue("NOV");
		        cellh34.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(34,(short)3000);
		        
		        Cell cellh35= headerRow.createCell(35);
		        cellh35.setCellValue("NOV");
		        cellh35.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(35,(short)3000);
		        
		        Cell cellh36= headerRow.createCell(36);
		        cellh36.setCellValue("NOV");
		        cellh36.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(36,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,34,36));

		        Cell cellh37= headerRow.createCell(37);
		        cellh37.setCellValue("DEC");
		        cellh37.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(37,(short)3000);
		        
		        Cell cellh38= headerRow.createCell(38);
		        cellh38.setCellValue("DEC");
		        cellh38.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(38,(short)3000);
		        
		        Cell cellh39= headerRow.createCell(39);
		        cellh39.setCellValue("DEC");
		        cellh39.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(39,(short)3000);
		        
		        firstSheet.addMergedRegion(new CellRangeAddress(0,0,37,39));

		        Cell cellh40= headerRow.createCell(40);
		        cellh40.setCellValue("Balance As On");
		        cellh40.setCellStyle(HeaderCss()); 
		        firstSheet.setColumnWidth(40,(short)5000);
		        
		        rownum=1; int q=0;
		        Row row1 = firstSheet.createRow(rownum);
		        Cell cell01 = row1.createCell(q++);
                cell01.setCellValue("");
                cell01.setCellStyle(NoneCss()); 
                
                Cell cell02 = row1.createCell(q++);
                cell02.setCellValue("");
                cell02.setCellStyle(NoneCss()); 
                
                Cell cell03 = row1.createCell(q++);
                cell03.setCellValue("");
                cell03.setCellStyle(NoneCss()); 
                
                Cell cell04 = row1.createCell(q++);
                cell04.setCellValue("");
                cell04.setCellStyle(NoneCss()); 
                
                for(int k=0; k<12;k++){
                Cell cell05 = row1.createCell(q++);
                cell05.setCellValue("PL");
                cell05.setCellStyle(HeaderCss()); 
                
                Cell cell06 = row1.createCell(q++);
                cell06.setCellValue("CL");
                cell06.setCellStyle(HeaderCss()); 
                
                Cell cell07 = row1.createCell(q++);
                cell07.setCellValue("SL");
                cell07.setCellStyle(HeaderCss()); 
                }     
		        rownum=2;
		     
			  try {
				  
		          for (int j = 0; j < LeaveVo.size(); j++) {
		        	  
		        	
		              Row row = firstSheet.createRow(rownum);
		              LeaveDetailsReportVo vo   = LeaveVo.get(j);
		              
		             
		               int i=0;
		               
		                        
		               System.out.println("Empname" +vo.getTeachername() + "sno" +vo.getSno() + "empid" +vo.getTeacherId());
		                  Cell cell0 = row.createCell(i++);
		                  cell0.setCellValue(j+1);
		                  cell0.setCellStyle(NoneCss()); 
		                  
		                  Cell cell1 = row.createCell(i++);
		                  cell1.setCellValue((vo.getTeacherId()));
		                  cell1.setCellStyle(NoneCss()); 
		                  
		                  Cell cell2 = row.createCell(i++);
		                  cell2.setCellValue(vo.getTeachername());
		                  cell2.setCellStyle(NoneCss()); 
		     
		                  Cell cell3 = row.createCell(i++);
		                  cell3.setCellValue(vo.getTotal());
		                  cell3.setCellStyle(NoneCss());
		                  
		                  Cell cell4 = row.createCell(i++);
		                  cell4.setCellValue(vo.getJan().get(0).getPljan());
		                  cell4.setCellStyle(NoneCss()); 
		                  
		                  Cell cell5 = row.createCell(i++);
		                  cell5.setCellValue(vo.getJan().get(0).getCljan());
		                  cell5.setCellStyle(NoneCss()); 
		                  
		                  Cell cell6 = row.createCell(i++);
		                  cell6.setCellValue(vo.getJan().get(0).getSljan());
		                  cell6.setCellStyle(NoneCss()); 
		               
		                  Cell cell7 = row.createCell(i++);
		                  cell7.setCellValue(vo.getFeb().get(0).getPlfeb());
		                  cell7.setCellStyle(NoneCss()); 
		                  
		                  Cell cell8 = row.createCell(i++);
		                  cell8.setCellValue(vo.getFeb().get(0).getClfeb());
		                  cell8.setCellStyle(NoneCss()); 
		                  
		                  Cell cell9 = row.createCell(i++);
		                  cell9.setCellValue(vo.getFeb().get(0).getSlfeb());
		                  cell9.setCellStyle(NoneCss()); 
		                  
		                  Cell cell10 = row.createCell(i++);
		                  cell10.setCellValue(vo.getMar().get(0).getPlmar());
		                  cell10.setCellStyle(NoneCss()); 
		                  
		                  Cell cell11 = row.createCell(i++);
		                  cell11.setCellValue(vo.getMar().get(0).getClmar());
		                  cell11.setCellStyle(NoneCss()); 
		                  
		                  Cell cell12 = row.createCell(i++);
		                  cell12.setCellValue(vo.getMar().get(0).getSlmar());
		                  cell12.setCellStyle(NoneCss()); 
		                  
		                  
		                  Cell cell13 = row.createCell(i++);
		                  cell13.setCellValue(vo.getAprl().get(0).getPlaprl());
		                  cell13.setCellStyle(NoneCss()); 
		                  
		                  Cell cell14 = row.createCell(i++);
		                  cell14.setCellValue(vo.getAprl().get(0).getClaprl());
		                  cell14.setCellStyle(NoneCss()); 
		                  
		                  Cell cell15 = row.createCell(i++);
		                  cell15.setCellValue(vo.getAprl().get(0).getSlaprl());
		                  cell15.setCellStyle(NoneCss()); 
		             
		                  
		                  Cell cell16 = row.createCell(i++);
		                  cell16.setCellValue(vo.getMay().get(0).getPlmay());
		                  cell16.setCellStyle(NoneCss()); 
		                  
		                  Cell cell17 = row.createCell(i++);
		                  cell17.setCellValue(vo.getMay().get(0).getClmay());
		                  cell17.setCellStyle(NoneCss()); 
		                  
		                  Cell cell18 = row.createCell(i++);
		                  cell18.setCellValue(vo.getMay().get(0).getSlmay());
		                  cell18.setCellStyle(NoneCss()); 
		                  
		                  Cell cell19 = row.createCell(i++);
		                  cell19.setCellValue(vo.getJune().get(0).getPljune());
		                  cell19.setCellStyle(NoneCss()); 
		                  
		                  Cell cell20 = row.createCell(i++);
		                  cell20.setCellValue(vo.getJune().get(0).getCljune());
		                  cell20.setCellStyle(NoneCss()); 
		                  
		                  Cell cell21 = row.createCell(i++);
		                  cell21.setCellValue(vo.getJune().get(0).getSljune());
		                  cell21.setCellStyle(NoneCss()); 
		                  
		                  Cell cell22 = row.createCell(i++);
		                  cell22.setCellValue(vo.getJuly().get(0).getPljuly());
		                  cell22.setCellStyle(NoneCss()); 
		                  
		                  Cell cell23 = row.createCell(i++);
		                  cell23.setCellValue(vo.getJuly().get(0).getCljuly());
		                  cell23.setCellStyle(NoneCss()); 
		                  
		                  Cell cell24 = row.createCell(i++);
		                  cell24.setCellValue(vo.getJuly().get(0).getSljuly());
		                  cell24.setCellStyle(NoneCss()); 
		                  
		                  Cell cell25 = row.createCell(i++);
		                  cell25.setCellValue(vo.getAug().get(0).getPlaug());
		                  cell25.setCellStyle(NoneCss()); 
		                  
		                  Cell cell26 = row.createCell(i++);
		                  cell26.setCellValue(vo.getAug().get(0).getClaug());
		                  cell26.setCellStyle(NoneCss()); 
		                  
		                  Cell cell27 = row.createCell(i++);
		                  cell27.setCellValue(vo.getAug().get(0).getSlaug());
		                  cell27.setCellStyle(NoneCss()); 
		                  
		                  Cell cell28 = row.createCell(i++);
		                  cell28.setCellValue(vo.getSep().get(0).getPlsep());
		                  cell28.setCellStyle(NoneCss()); 
		                  
		                  Cell cell29 = row.createCell(i++);
		                  cell29.setCellValue(vo.getSep().get(0).getClsep());
		                  cell29.setCellStyle(NoneCss()); 
		                  
		                  Cell cell30 = row.createCell(i++);
		                  cell30.setCellValue(vo.getSep().get(0).getSlsep());
		                  cell30.setCellStyle(NoneCss()); 
		                  
		                  Cell cell31 = row.createCell(i++);
		                  cell31.setCellValue(vo.getOct().get(0).getPloct());
		                  cell31.setCellStyle(NoneCss()); 
		                  
		                  Cell cell32 = row.createCell(i++);
		                  cell32.setCellValue(vo.getOct().get(0).getCloct());
		                  cell32.setCellStyle(NoneCss()); 
		                  
		                  Cell cell33 = row.createCell(i++);
		                  cell33.setCellValue(vo.getOct().get(0).getSloct());
		                  cell33.setCellStyle(NoneCss()); 
		                  
		                  
		                  Cell cell34 = row.createCell(i++);
		                  cell34.setCellValue(vo.getNov().get(0).getPlnov());
		                  cell34.setCellStyle(NoneCss()); 
		                  
		                  Cell cell35 = row.createCell(i++);
		                  cell35.setCellValue(vo.getNov().get(0).getClnov());
		                  cell35.setCellStyle(NoneCss()); 
		                  
		                  Cell cell36 = row.createCell(i++);
		                  cell36.setCellValue(vo.getNov().get(0).getSlnov());
		                  cell36.setCellStyle(NoneCss()); 
		                  
		                  Cell cell37 = row.createCell(i++);
		                  cell37.setCellValue(vo.getDec().get(0).getPldec());
		                  cell37.setCellStyle(NoneCss()); 
		                  
		                  Cell cell38 = row.createCell(i++);
		                  cell38.setCellValue(vo.getDec().get(0).getCldec());
		                  cell38.setCellStyle(NoneCss()); 
		                  
		                  Cell cell39 = row.createCell(i++);
		                  cell39.setCellValue(vo.getDec().get(0).getSldec());
		                  cell39.setCellStyle(NoneCss()); 
		                  
		                  Cell cell40 = row.createCell(i++);
		                  cell40.setCellValue(vo.getBalance());
		                  cell40.setCellStyle(NoneCss());
		                  
		                  
		              rownum++;
		          }

		      } catch (Exception e) {
		          e.printStackTrace();
		      } finally {
		      }

			   
		    
		      FileOutputStream fos1 = null;
		      try {
		          fos1=new FileOutputStream(new File(path));
		          HSSFCellStyle hsfstyle=workbook.createCellStyle();
		         System.out.println("fos1" +path  );
		          workbook.write(fos1);
		      } catch (Exception e) {
		          e.printStackTrace();
		      }
		    
		   
		}

		
	}


