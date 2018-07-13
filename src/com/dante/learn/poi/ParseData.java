//package com.dante.learn.poi;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.InputStream;
//import java.security.Key;
//import java.security.spec.KeySpec;
//import java.util.Iterator;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.codec.DecoderException;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.Hex;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//public class ParseData {
//
//	private static final String REACT_PUBLIC_RESOURCES_DATA_JSON =
//		"/react/public/resources/data.json";
//
//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) {
//
//		InputStream is = ParseData.class.getResourceAsStream("/data.xlsx");
//
//		@SuppressWarnings("resource")
//		Workbook workbook = null;
//
//		try {
//			workbook = new XSSFWorkbook(is);
//			Sheet datatypeSheet = workbook.getSheetAt(0);
//			Iterator<Row> iterator = datatypeSheet.iterator();
//
//			// Skip header row
//			iterator.next();
//
//			JSONObject jsonObject = new JSONObject();
//			JSONArray jsonArray = new JSONArray();
//
//			while (iterator.hasNext()) {
//
//				Row row = iterator.next();
//
//				JSONObject rowJSONObj = new JSONObject();
//				rowJSONObj.put("date", getCellValue(row.getCell(1)));
//				rowJSONObj.put("time", getCellValue(row.getCell(2)));
//				rowJSONObj.put("board", getCellValue(row.getCell(3)));
//				rowJSONObj.put("violation", getCellValue(row.getCell(4)));
//				rowJSONObj.put("location", getCellValue(row.getCell(5)));
//
//				jsonArray.add(rowJSONObj);
//			}
//
//			String updateDate =
//				(String) ((JSONObject) jsonArray.get(0)).get("date");
//			System.out.println(
//				"Num items:" + jsonArray.size() + ", " + updateDate);
//			jsonObject.put("date", updateDate);
//			jsonObject.put("items", jsonArray);
//
//			String projectDir = System.getProperty("user.dir");
//			File outFile =
//				new File(projectDir + REACT_PUBLIC_RESOURCES_DATA_JSON);
//			File parentDir = outFile.getParentFile();
//			parentDir.mkdirs();
//
//			FileWriter fileWriter = new FileWriter(outFile);
//			fileWriter.write(encryptData(jsonObject.toJSONString()));
//			fileWriter.flush();
//			fileWriter.close();
//			System.out.println("Out file: " + outFile.getAbsolutePath());
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				is.close();
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			try {
//				if (workbook != null) {
//					workbook.close();
//				}
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@SuppressWarnings("deprecation")
//	private static String getCellValue(Cell cell) {
//
//		// cell.setCellType(Cell.CELL_TYPE_STRING);
//		if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
//			DataFormatter df = new DataFormatter();
//			String value = df.formatCellValue(cell);
//			return value;
//		}
//
//		return cell.getStringCellValue();
//	}
//
//	private static String encryptData(String data)
//		throws Exception {
//
//		String keyValue = "asd*(#X@4a";
//		SecretKeyFactory factory =
//			SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		KeySpec spec = new PBEKeySpec(
//			keyValue.toCharArray(), hex("dc0da04af8fee58593442bf834b30739"),
//			1000, 128);
//
//		Key key =
//			new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
//		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		c.init(
//			Cipher.ENCRYPT_MODE, key,
//			new IvParameterSpec(hex("dc0da04af8fee58593442bf834b30739")));
//
//		byte[] encVal = c.doFinal(data.getBytes());
//		String base64EncodedEncryptedData =
//			new String(Base64.encodeBase64(encVal));
//		System.out.println(base64EncodedEncryptedData);
//		return base64EncodedEncryptedData;
//	}
//
//	public static String hex(byte[] bytes) {
//
//		return Hex.encodeHexString(bytes);
//	}
//
//	public static byte[] hex(String str) {
//
//		try {
//			return Hex.decodeHex(str.toCharArray());
//		}
//		catch (DecoderException e) {
//			throw new IllegalStateException(e);
//		}
//	}
//}
