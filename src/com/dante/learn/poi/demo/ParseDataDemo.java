package com.dante.learn.poi.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParseDataDemo {
	private final static String path = "D:/Nguyen/test/poi/test_write.xlsx";

	public static void main(String[] args) {
		readFile();
	}
	
	public static void readFile() {
		try {
			FileInputStream excelFile = new FileInputStream(new File(path));
			XSSFWorkbook workBook = new XSSFWorkbook(excelFile);
			Sheet sheet = workBook.getSheetAt(0);
			
			for(Iterator<Row> rowIterator = sheet.iterator();rowIterator.hasNext();) {
				Row row = rowIterator.next();
				
				for(Iterator<Cell> cellIterator = row.iterator(); cellIterator.hasNext();) {
					Cell cell = cellIterator.next();
					if(cell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(cell.getStringCellValue() + " - ");
					}else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(cell.getNumericCellValue() + " - ");
					}
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void writeFile() {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("My first sheet");
		Object[][] arrays = { { "Hero", "Type", "Dame" },
				{ "Zed", "assassin", 100 }, { "Ezreal", "MarkMan", 80 },
				{ "Garen", "Tanker", 50 }, { "Ahri", "Mage", 80 }, };

		int rowNumber = 0;
		System.out.println("Creating excel...");

		for (Object[] myRow : arrays) {
			XSSFRow row = sheet.createRow(rowNumber++);
			int colNumber = 0;
			for (Object myColAtRow : myRow) {
				XSSFCell cells = row.createCell(colNumber++);

				if (myColAtRow instanceof String) {
					cells.setCellValue((String) myColAtRow);
				} else if (myColAtRow instanceof Integer) {
					cells.setCellValue((Integer) myColAtRow);
				}
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			workBook.write(outputStream);
//			workBook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 System.out.println("Done");
	}
}
