/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mothershipattendance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import com.itextpdf.text.DocumentException;
import com.itextpdf.kernel.font.PdfFontFactory; 
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Text;
import com.itextpdf.io.font.FontConstants; 
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.layout.element.Paragraph; 
import com.itextpdf.layout.Document;
import java.io.FileWriter;
import java.util.Arrays;
import org.slf4j.LoggerFactory;


/**
 *
 * @author tylerfontana
 */
public class ExportTableDataToExternalFile {
    
    public static String[] BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES = {"Student Name", "Student ID", "Status", "Comments"};
    public static String[] BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES = {"Date", "Status", "Comments"};
    
    public String fileName = "";
    
    public File file;
    
    public ExportTableDataToExternalFile() {
        // Create A Reference.
    }
    
    /*
    public ExportTableDataToExternalFile(String exportType, RecordsSearchObject searchCriteria, String className, ArrayList<ByClassJTableObject> byClassObjectList, ArrayList<ByStudentJTableObject> byStudentObjectList, ArrayList<String> commentsList) {
        
        if (exportType.equalsIgnoreCase("Excel Spreadsheet")) {
            createXMLDocument(searchCriteria, className, byClassObjectList, byStudentObjectList, commentsList);
        }
        else if (exportType.equalsIgnoreCase("Word Document")) {
            
        }
        else if (exportType.equalsIgnoreCase("PDF Document")) {
            
        }
        else if (exportType.equalsIgnoreCase("Text File")) {
            
        }
        
    }*/
    
    public boolean createTextDocumentByClass(String filePath, RecordsSearchObject searchCriteria, String className, ArrayList<ByClassJTableObject> byClassObjectList, ArrayList<String> commentsList) {
        
        DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDateTime now = LocalDateTime.now();  
        String currentDate = dtfOne.format(now);
        
        try {
            FileWriter myWriter = new FileWriter(filePath);
            
            myWriter.write("\nMothership Attendance (By Class Search) Attendance Records:\n\n");
            myWriter.write("Class:\t\t");
            myWriter.write(className);
            myWriter.write("\nDate:\t\t");
            myWriter.write(searchCriteria.getDate());
            myWriter.write("\nCreated:\t");
            myWriter.write(currentDate + "\n\n");
            
            List<List<String>> rowsList = new ArrayList<List<String>>();
            List<String> headersList = Arrays.asList("Entry", BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[0], BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[1], BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[2], BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[3]);
            List<String> list = new ArrayList<String>();

            for (int i = 0; i < byClassObjectList.size(); i++) {
                
                list = new ArrayList<String>();
                
                list.add("" + (i + 1));
                list.add(byClassObjectList.get(i).getStudentName());
                list.add("" + byClassObjectList.get(i).getStudentID());
                list.add(byClassObjectList.get(i).getStudentStatus());
                list.add(byClassObjectList.get(i).getComments());
                
                rowsList.add(list);
            }
            
            System.out.println("Size: " + rowsList.size());
            
            List<Integer> colAlignList = Arrays.asList(
                Block.DATA_CENTER, 
                Block.DATA_CENTER, 
                Block.DATA_CENTER,
                Block.DATA_CENTER,
                Block.DATA_CENTER);
            
            Board board = new Board(100);
            Table table = new Table(board, 100, headersList, rowsList);
            
            table.setColAlignsList(colAlignList);
            
            Block tableBlock = table.tableToBlocks();
            
            board.setInitialBlock(tableBlock);
            board.build();
            
            String tableString = board.getPreview();
            //System.out.println(tableString);

            myWriter.write(tableString + "\n\n");
            
            myWriter.write("\tComments By Entry: \n");
            
            for (int i = 0; i < commentsList.size(); i++) {
                myWriter.write("\n\t " + (i + 1) + ".\t");
                
                if (commentsList.get(i).equalsIgnoreCase("")) {
                    myWriter.write("(No Comments Available)");
                }
                else {
                    myWriter.write(commentsList.get(i));
                }
            }
            
            myWriter.close();
            
            return true;
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean createTextDocumentByStudent(String filePath, RecordsSearchObject searchCriteria, String className, ArrayList<ByStudentJTableObject> byStudentObjectList, ArrayList<String> commentsList) {
        
        DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDateTime now = LocalDateTime.now();  
        String currentDate = dtfOne.format(now);
        
        try {
            FileWriter myWriter = new FileWriter(filePath);
            
            myWriter.write("\nMothership Attendance (By Student Search) Attendance Records:\n\n");
            myWriter.write("Class:\t\t");
            myWriter.write(className);
            myWriter.write("\nStudent:\t");
            myWriter.write(searchCriteria.getStudentName());
            myWriter.write("\nCreated:\t");
            myWriter.write(currentDate + "\n\n");
            
            List<List<String>> rowsList = new ArrayList<List<String>>();
            List<String> headersList = Arrays.asList("Entry", BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[0], BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[1], BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[2]);
            List<String> list = new ArrayList<String>();

            for (int i = 0; i < byStudentObjectList.size(); i++) {
                
                list = new ArrayList<String>();
                
                list.add("" + (i + 1));
                list.add(byStudentObjectList.get(i).getDate());
                list.add(byStudentObjectList.get(i).getStudentStatus());
                list.add(byStudentObjectList.get(i).getComments());
                
                rowsList.add(list);
            }
            
            System.out.println("Size: " + rowsList.size());
            
            List<Integer> colAlignList = Arrays.asList(
                Block.DATA_CENTER, 
                Block.DATA_CENTER, 
                Block.DATA_CENTER,
                Block.DATA_CENTER);
            
            Board board = new Board(75);
            Table table = new Table(board, 75, headersList, rowsList);
            
            table.setColAlignsList(colAlignList);
            
            Block tableBlock = table.tableToBlocks();
            
            board.setInitialBlock(tableBlock);
            board.build();
            
            String tableString = board.getPreview();
            //System.out.println(tableString);

            myWriter.write(tableString + "\n\n");
            
            myWriter.write("\tComments By Entry: \n");
            
            for (int i = 0; i < commentsList.size(); i++) {
                myWriter.write("\n\t " + (i + 1) + ".\t");
                
                if (commentsList.get(i).equalsIgnoreCase("")) {
                    myWriter.write("(No Comments Available)");
                }
                else {
                    myWriter.write(commentsList.get(i));
                }
            }
            
            myWriter.close();
            
            return true;
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean createPDFDocumentByStudent(String filePath, RecordsSearchObject searchCriteria, String className, ArrayList<ByStudentJTableObject> byStudentObjectList, ArrayList<String> commentsList) {
        
        DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDateTime now = LocalDateTime.now();  
        String currentDate = dtfOne.format(now);
            
        FileOutputStream os;
        Document document;
        
        try {
            
            String filePDFNameConversion = filePath + ".pdf";
            PdfWriter writer = new PdfWriter(filePDFNameConversion);
            
            PdfDocument pdf = new PdfDocument(writer);
            
            document = new Document(pdf);
            
            os = new FileOutputStream(filePDFNameConversion);
            
            //PdfWriter.getInstance(document, os);
            
            Text titleLineText = new Text("Mothership Attendance (By Student Search) Attendance Records: ");
            
            PdfFont titleFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            PdfFont infoFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            
            titleLineText.setFont(titleFont);
            titleLineText.setFontSize(14);
            titleLineText.setFontColor(Color.BLACK);
            
            Text fieldOneTypeText = new Text("Class: ");
            
            fieldOneTypeText.setFont(titleFont);
            fieldOneTypeText.setFontSize(14);
            fieldOneTypeText.setFontColor(Color.BLACK);
            
            Text fieldOneInfoText = new Text(className);
            
            fieldOneInfoText.setFont(infoFont);
            fieldOneInfoText.setFontSize(14);
            fieldOneInfoText.setFontColor(Color.BLACK);
            
            Text fieldTwoTypeText = new Text("Student: ");
            
            fieldTwoTypeText.setFont(titleFont);
            fieldTwoTypeText.setFontSize(14);
            fieldTwoTypeText.setFontColor(Color.BLACK);
            
            Text fieldTwoInfoText = new Text(searchCriteria.getStudentName());
            
            fieldTwoInfoText.setFont(infoFont);
            fieldTwoInfoText.setFontSize(14);
            fieldTwoInfoText.setFontColor(Color.BLACK);
            
            Text fieldThreeTypeText = new Text("Created: ");
            
            fieldThreeTypeText.setFont(titleFont);
            fieldThreeTypeText.setFontSize(14);
            fieldThreeTypeText.setFontColor(Color.BLACK);
            
            Text fieldThreeInfoText = new Text(currentDate);
            
            fieldThreeInfoText.setFont(infoFont);
            fieldThreeInfoText.setFontSize(14);
            fieldThreeInfoText.setFontColor(Color.BLACK);
            
            
            Paragraph paragraph = new Paragraph();
            paragraph.add(titleLineText);
            paragraph.add(fieldOneTypeText);
            paragraph.add(fieldOneInfoText);
            paragraph.add(fieldTwoTypeText);
            paragraph.add(fieldTwoInfoText);
            paragraph.add(fieldThreeTypeText);
            paragraph.add(fieldThreeInfoText);
            
            document.add(paragraph);
            document.close();
            
            return true;
            //Paragraph titleParagraph = new Paragraph("Mothership Attendance (By Student Search) Attendance Records: ");
            //titleParagraph.setFont(titleFont);
            //titleParagraph.add(titleParagraph)
            
            //document.add(titleParagraph);
            
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException io) {
            io.printStackTrace();
        }
        
        return true;
        /*
        try {
            
            DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDateTime now = LocalDateTime.now();  
            String currentDate = dtfOne.format(now);
            
            XWPFDocument document = new XWPFDocument();
            
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun titleRun = paragraph.createRun();

            titleRun.setBold(true);
            titleRun.setFontFamily("Times New Roman");
            titleRun.setFontSize(12);

            titleRun.setText("Mothership Attendance (By Student Search) Attendance Records: ");
            titleRun.addBreak();
            titleRun.addBreak();
            
            XWPFRun fieldOne = paragraph.createRun();
            
            fieldOne.setBold(true);
            fieldOne.setFontFamily("Times New Roman");
            fieldOne.setFontSize(12);
            
            fieldOne.setText("Class:");
            fieldOne.addTab();

            XWPFRun fieldInfoOne = paragraph.createRun();
            
            fieldInfoOne.setFontFamily("Times New Roman");
            fieldInfoOne.setFontSize(12);
            fieldInfoOne.setBold(false);
            
            fieldInfoOne.addTab();
            fieldInfoOne.setText(className);
            fieldInfoOne.addBreak();
            
            XWPFRun fieldTwo = paragraph.createRun();
            
            fieldTwo.setBold(true);
            fieldTwo.setFontFamily("Times New Roman");
            fieldTwo.setFontSize(12);
            
            fieldTwo.setText("Student:");
            fieldTwo.addTab();

            XWPFRun fieldInfoTwo = paragraph.createRun();
            
            fieldInfoTwo.setFontFamily("Times New Roman");
            fieldInfoTwo.setFontSize(12);
            fieldInfoTwo.setBold(false);
            
            fieldInfoTwo.setText(searchCriteria.getStudentName());
            fieldInfoTwo.addBreak();
            
            XWPFRun fieldThree = paragraph.createRun();
            
            fieldThree.setBold(true);
            fieldThree.setFontFamily("Times New Roman");
            fieldThree.setFontSize(12);
            
            fieldThree.setText("Created:");
            fieldThree.addTab();

            XWPFRun fieldInfoThree = paragraph.createRun();
            
            fieldInfoThree.setFontFamily("Times New Roman");
            fieldInfoThree.setFontSize(12);
            fieldInfoThree.setBold(false);
            
            fieldInfoThree.setText(currentDate);
            fieldInfoThree.addBreak();
            
            XWPFTable table = document.createTable();
            table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
            table.setCellMargins(143, 0, 143, 0);
            
            XWPFTableRow tableTitleRow = table.getRow(0);
              
            tableTitleRow.addNewTableCell();
            tableTitleRow.addNewTableCell();

            for (int i = 0; i < BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES.length; i++) {
                
                if (i == 0) {
                    
                    XWPFParagraph titleCellParagraphOne = document.createParagraph();
                    
                    titleCellParagraphOne.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphOne.setIndentationLeft(100);
                    titleCellParagraphOne.setIndentationRight(100);
                    
                    XWPFRun titleCellRunOne = titleCellParagraphOne.createRun();
                    
                    titleCellRunOne.setFontFamily("Times New Roman");
                    titleCellRunOne.setFontSize(12);
                    titleCellRunOne.setColor("ffffff");
                    titleCellRunOne.setText(BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunOne.setBold(false);
                    
                    tableTitleRow.getCell(0).setParagraph(titleCellParagraphOne);
                    
                    tableTitleRow.getCell(0).setColor("00bc1c");
                    tableTitleRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
                else if (i == 1) {
                    XWPFParagraph titleCellParagraphTwo = document.createParagraph();
                    
                    titleCellParagraphTwo.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphTwo.setIndentationLeft(100);
                    titleCellParagraphTwo.setIndentationRight(100);
                    
                    XWPFRun titleCellRunTwo = titleCellParagraphTwo.createRun();
                    
                    titleCellRunTwo.setFontFamily("Times New Roman");
                    titleCellRunTwo.setFontSize(12);
                    titleCellRunTwo.setColor("ffffff");
                    titleCellRunTwo.setText(BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunTwo.setBold(false);
                    
                    tableTitleRow.getCell(1).setParagraph(titleCellParagraphTwo);
                    
                    tableTitleRow.getCell(1).setColor("ff0000");
                    tableTitleRow.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
                else if (i == 2) {
                    
                    XWPFParagraph titleCellParagraphThree = document.createParagraph();
                    
                    titleCellParagraphThree.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphThree.setIndentationLeft(100);
                    titleCellParagraphThree.setIndentationRight(100);
                    
                    XWPFRun titleCellRunThree = titleCellParagraphThree.createRun();
                    
                    titleCellRunThree.setFontFamily("Times New Roman");
                    titleCellRunThree.setFontSize(12);
                    titleCellRunThree.setColor("ffffff");
                    titleCellRunThree.setText(BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunThree.setBold(false);
                    
                    tableTitleRow.getCell(2).setParagraph(titleCellParagraphThree);
                    
                    tableTitleRow.getCell(2).setColor("000000");
                    tableTitleRow.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
            }
            
            for (int i = 0; i < byStudentObjectList.size(); i++) {
                
                XWPFTableRow tableDataRow = table.createRow();
                
                for (int j = 0; j < 3; j++) {
                    
                    List<XWPFParagraph> paragraphList = tableDataRow.getCell(j).getParagraphs();
                    XWPFParagraph dataCellParagraph = paragraphList.get(0);
                    
                    dataCellParagraph.setAlignment(ParagraphAlignment.CENTER);
                    dataCellParagraph.setIndentationLeft(100);
                    dataCellParagraph.setIndentationRight(100);
                    
                    XWPFRun dataCellRun = dataCellParagraph.createRun();

                    dataCellRun.setFontFamily("Times New Roman");
                    dataCellRun.setFontSize(12);
                    dataCellRun.setColor("000000");
                    dataCellRun.setBold(false);
                    
                    if (j == 0) {
                        dataCellRun.setText(byStudentObjectList.get(i).getDate());
                    }
                    else if (j == 1) {
                        dataCellRun.setText(byStudentObjectList.get(i).getStudentStatus());
                    }
                    else if (j == 2) {
                        dataCellRun.setText(commentsList.get(i));
                    }

                    tableDataRow.getCell(j).setParagraph(dataCellParagraph);
                    tableDataRow.getCell(j).setColor("ffffff");
                    tableDataRow.getCell(j).setVerticalAlignment(XWPFVertAlign.CENTER);
                }      
            }
            
            String fileToWordConversion = filePath + ".docx";
            file = new File(fileToWordConversion);

            FileOutputStream osWORD = new FileOutputStream(file);
            
            document.write(osWORD);
            document.close();
            osWORD.close();
            
            // Convert Word Document To PDF
            InputStream in = new FileInputStream(fileToWordConversion);
            XWPFDocument docTwo = new XWPFDocument(in);
            docTwo.createStyles();
            
            PdfOptions options = PdfOptions.create();
            
            
            String fileToPDFConversion = filePath + ".pdf";
            
            OutputStream osPDF = new FileOutputStream(new File(fileToPDFConversion));
            PdfConverter.getInstance().convert(docTwo, osPDF, options);
            
            docTwo.close();
            in.close();
            osPDF.close();
            
            return true;
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        */
    }
    
    public boolean createWordDocumentByClass(String filePath, RecordsSearchObject searchCriteria, String className, ArrayList<ByClassJTableObject> byClassObjectList, ArrayList<String> commentsList) {
        try {
            
            DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDateTime now = LocalDateTime.now();  
            String currentDate = dtfOne.format(now);
            
            XWPFDocument document = new XWPFDocument();
            
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun titleRun = paragraph.createRun();

            titleRun.setBold(true);
            titleRun.setFontFamily("Times New Roman");
            titleRun.setFontSize(12);

            titleRun.setText("Mothership Attendance (By Class Search) Attendance Records: ");
            titleRun.addBreak();
            titleRun.addBreak();
            
            XWPFRun fieldOne = paragraph.createRun();
            
            fieldOne.setBold(true);
            fieldOne.setFontFamily("Times New Roman");
            fieldOne.setFontSize(12);
            
            fieldOne.setText("Class:");
            fieldOne.addTab();

            XWPFRun fieldInfoOne = paragraph.createRun();
            
            fieldInfoOne.setFontFamily("Times New Roman");
            fieldInfoOne.setFontSize(12);
            fieldInfoOne.setBold(false);
            
            fieldInfoOne.addTab();
            fieldInfoOne.setText(className);
            fieldInfoOne.addBreak();
            
            XWPFRun fieldTwo = paragraph.createRun();
            
            fieldTwo.setBold(true);
            fieldTwo.setFontFamily("Times New Roman");
            fieldTwo.setFontSize(12);
            
            fieldTwo.setText("Date:");
            fieldTwo.addTab();

            XWPFRun fieldInfoTwo = paragraph.createRun();
            
            fieldInfoTwo.setFontFamily("Times New Roman");
            fieldInfoTwo.setFontSize(12);
            fieldInfoTwo.setBold(false);
            
            fieldInfoTwo.addTab();
            fieldInfoTwo.setText(searchCriteria.getDate());
            fieldInfoTwo.addBreak();
            
            XWPFRun fieldThree = paragraph.createRun();
            
            fieldThree.setBold(true);
            fieldThree.setFontFamily("Times New Roman");
            fieldThree.setFontSize(12);
            
            fieldThree.setText("Created:");
            fieldThree.addTab();

            XWPFRun fieldInfoThree = paragraph.createRun();
            
            fieldInfoThree.setFontFamily("Times New Roman");
            fieldInfoThree.setFontSize(12);
            fieldInfoThree.setBold(false);
            
            fieldInfoThree.setText(currentDate);
            fieldInfoThree.addBreak();
            
            XWPFTable table = document.createTable();
            table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
            table.setCellMargins(143, 0, 143, 0);
            
            XWPFTableRow tableTitleRow = table.getRow(0);
              
            tableTitleRow.addNewTableCell();
            tableTitleRow.addNewTableCell();
            tableTitleRow.addNewTableCell();

            for (int i = 0; i < BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES.length; i++) {
                
                if (i == 0) {
                    
                    XWPFParagraph titleCellParagraphOne = document.createParagraph();
                    
                    titleCellParagraphOne.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphOne.setIndentationLeft(100);
                    titleCellParagraphOne.setIndentationRight(100);
                    
                    XWPFRun titleCellRunOne = titleCellParagraphOne.createRun();
                    
                    titleCellRunOne.setFontFamily("Times New Roman");
                    titleCellRunOne.setFontSize(12);
                    titleCellRunOne.setColor("ffffff");
                    titleCellRunOne.setText(BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunOne.setBold(false);
                    
                    tableTitleRow.getCell(0).setParagraph(titleCellParagraphOne);
                    
                    tableTitleRow.getCell(0).setColor("0024ea");
                    tableTitleRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
                else if (i == 1) {
                    XWPFParagraph titleCellParagraphTwo = document.createParagraph();
                    
                    titleCellParagraphTwo.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphTwo.setIndentationLeft(100);
                    titleCellParagraphTwo.setIndentationRight(100);
                    
                    XWPFRun titleCellRunTwo = titleCellParagraphTwo.createRun();
                    
                    titleCellRunTwo.setFontFamily("Times New Roman");
                    titleCellRunTwo.setFontSize(12);
                    titleCellRunTwo.setColor("ffffff");
                    titleCellRunTwo.setText(BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunTwo.setBold(false);
                    
                    tableTitleRow.getCell(1).setParagraph(titleCellParagraphTwo);
                    
                    tableTitleRow.getCell(1).setColor("8b00ea");
                    tableTitleRow.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
                else if (i == 2) {
                    
                    XWPFParagraph titleCellParagraphThree = document.createParagraph();
                    
                    titleCellParagraphThree.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphThree.setIndentationLeft(100);
                    titleCellParagraphThree.setIndentationRight(100);
                    
                    XWPFRun titleCellRunThree = titleCellParagraphThree.createRun();
                    
                    titleCellRunThree.setFontFamily("Times New Roman");
                    titleCellRunThree.setFontSize(12);
                    titleCellRunThree.setColor("ffffff");
                    titleCellRunThree.setText(BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunThree.setBold(false);
                    
                    tableTitleRow.getCell(2).setParagraph(titleCellParagraphThree);
                    
                    tableTitleRow.getCell(2).setColor("ff0000");
                    tableTitleRow.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
                else if (i == 3) {
                    
                    XWPFParagraph titleCellParagraphFour = document.createParagraph();
                    
                    titleCellParagraphFour.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphFour.setIndentationLeft(100);
                    titleCellParagraphFour.setIndentationRight(100);
                    
                    XWPFRun titleCellRunFour = titleCellParagraphFour.createRun();
                    
                    titleCellRunFour.setFontFamily("Times New Roman");
                    titleCellRunFour.setFontSize(12);
                    titleCellRunFour.setColor("ffffff");
                    titleCellRunFour.setText(BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunFour.setBold(false);
                    
                    tableTitleRow.getCell(3).setParagraph(titleCellParagraphFour);
                    
                    tableTitleRow.getCell(3).setColor("000000");
                    tableTitleRow.getCell(3).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
            }
            
            for (int i = 0; i < byClassObjectList.size(); i++) {
                
                XWPFTableRow tableDataRow = table.createRow();
                
                for (int j = 0; j < 4; j++) {
                    
                    List<XWPFParagraph> paragraphList = tableDataRow.getCell(j).getParagraphs();
                    XWPFParagraph dataCellParagraph = paragraphList.get(0);
                    
                    dataCellParagraph.setAlignment(ParagraphAlignment.CENTER);
                    dataCellParagraph.setIndentationLeft(100);
                    dataCellParagraph.setIndentationRight(100);
                    
                    XWPFRun dataCellRun = dataCellParagraph.createRun();

                    dataCellRun.setFontFamily("Times New Roman");
                    dataCellRun.setFontSize(12);
                    dataCellRun.setColor("000000");
                    dataCellRun.setBold(false);
                    
                    if (j == 0) {
                        dataCellRun.setText(byClassObjectList.get(i).getStudentName());
                    }
                    else if (j == 1) {
                        dataCellRun.setText("" + byClassObjectList.get(i).getStudentID());
                    }
                    else if (j == 2) {
                        dataCellRun.setText(byClassObjectList.get(i).getStudentStatus());
                    }
                    else if (j == 3) {
                        dataCellRun.setText(commentsList.get(i));
                    }

                    tableDataRow.getCell(j).setParagraph(dataCellParagraph);
                    tableDataRow.getCell(j).setColor("ffffff");
                    tableDataRow.getCell(j).setVerticalAlignment(XWPFVertAlign.CENTER);
                }      
            }
            
            file = new File(filePath);

            FileOutputStream os = new FileOutputStream(file);
            document.write(os);
            document.close();
            os.close();
            
            return true;
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean createWordDocumentByStudent(String filePath, RecordsSearchObject searchCriteria, String className, ArrayList<ByStudentJTableObject> byStudentObjectList, ArrayList<String> commentsList) {
        try {
            
            DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDateTime now = LocalDateTime.now();  
            String currentDate = dtfOne.format(now);
            
            XWPFDocument document = new XWPFDocument();
            
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun titleRun = paragraph.createRun();

            titleRun.setBold(true);
            titleRun.setFontFamily("Times New Roman");
            titleRun.setFontSize(12);

            titleRun.setText("Mothership Attendance (By Student Search) Attendance Records: ");
            titleRun.addBreak();
            titleRun.addBreak();
            
            XWPFRun fieldOne = paragraph.createRun();
            
            fieldOne.setBold(true);
            fieldOne.setFontFamily("Times New Roman");
            fieldOne.setFontSize(12);
            
            fieldOne.setText("Class:");
            fieldOne.addTab();

            XWPFRun fieldInfoOne = paragraph.createRun();
            
            fieldInfoOne.setFontFamily("Times New Roman");
            fieldInfoOne.setFontSize(12);
            fieldInfoOne.setBold(false);
            
            fieldInfoOne.addTab();
            fieldInfoOne.setText(className);
            fieldInfoOne.addBreak();
            
            XWPFRun fieldTwo = paragraph.createRun();
            
            fieldTwo.setBold(true);
            fieldTwo.setFontFamily("Times New Roman");
            fieldTwo.setFontSize(12);
            
            fieldTwo.setText("Student:");
            fieldTwo.addTab();

            XWPFRun fieldInfoTwo = paragraph.createRun();
            
            fieldInfoTwo.setFontFamily("Times New Roman");
            fieldInfoTwo.setFontSize(12);
            fieldInfoTwo.setBold(false);
            
            fieldInfoTwo.setText(searchCriteria.getStudentName());
            fieldInfoTwo.addBreak();
            
            XWPFRun fieldThree = paragraph.createRun();
            
            fieldThree.setBold(true);
            fieldThree.setFontFamily("Times New Roman");
            fieldThree.setFontSize(12);
            
            fieldThree.setText("Created:");
            fieldThree.addTab();

            XWPFRun fieldInfoThree = paragraph.createRun();
            
            fieldInfoThree.setFontFamily("Times New Roman");
            fieldInfoThree.setFontSize(12);
            fieldInfoThree.setBold(false);
            
            fieldInfoThree.setText(currentDate);
            fieldInfoThree.addBreak();
            
            XWPFTable table = document.createTable();
            table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
            table.setCellMargins(143, 0, 143, 0);
            
            XWPFTableRow tableTitleRow = table.getRow(0);
              
            tableTitleRow.addNewTableCell();
            tableTitleRow.addNewTableCell();

            for (int i = 0; i < BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES.length; i++) {
                
                if (i == 0) {
                    
                    XWPFParagraph titleCellParagraphOne = document.createParagraph();
                    
                    titleCellParagraphOne.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphOne.setIndentationLeft(100);
                    titleCellParagraphOne.setIndentationRight(100);
                    
                    XWPFRun titleCellRunOne = titleCellParagraphOne.createRun();
                    
                    titleCellRunOne.setFontFamily("Times New Roman");
                    titleCellRunOne.setFontSize(12);
                    titleCellRunOne.setColor("ffffff");
                    titleCellRunOne.setText(BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunOne.setBold(false);
                    
                    tableTitleRow.getCell(0).setParagraph(titleCellParagraphOne);
                    
                    tableTitleRow.getCell(0).setColor("00bc1c");
                    tableTitleRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
                else if (i == 1) {
                    XWPFParagraph titleCellParagraphTwo = document.createParagraph();
                    
                    titleCellParagraphTwo.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphTwo.setIndentationLeft(100);
                    titleCellParagraphTwo.setIndentationRight(100);
                    
                    XWPFRun titleCellRunTwo = titleCellParagraphTwo.createRun();
                    
                    titleCellRunTwo.setFontFamily("Times New Roman");
                    titleCellRunTwo.setFontSize(12);
                    titleCellRunTwo.setColor("ffffff");
                    titleCellRunTwo.setText(BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunTwo.setBold(false);
                    
                    tableTitleRow.getCell(1).setParagraph(titleCellParagraphTwo);
                    
                    tableTitleRow.getCell(1).setColor("ff0000");
                    tableTitleRow.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
                else if (i == 2) {
                    
                    XWPFParagraph titleCellParagraphThree = document.createParagraph();
                    
                    titleCellParagraphThree.setAlignment(ParagraphAlignment.CENTER);
                    titleCellParagraphThree.setIndentationLeft(100);
                    titleCellParagraphThree.setIndentationRight(100);
                    
                    XWPFRun titleCellRunThree = titleCellParagraphThree.createRun();
                    
                    titleCellRunThree.setFontFamily("Times New Roman");
                    titleCellRunThree.setFontSize(12);
                    titleCellRunThree.setColor("ffffff");
                    titleCellRunThree.setText(BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);
                    titleCellRunThree.setBold(false);
                    
                    tableTitleRow.getCell(2).setParagraph(titleCellParagraphThree);
                    
                    tableTitleRow.getCell(2).setColor("000000");
                    tableTitleRow.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
                }
            }
            
            for (int i = 0; i < byStudentObjectList.size(); i++) {
                
                XWPFTableRow tableDataRow = table.createRow();
                
                for (int j = 0; j < 3; j++) {
                    
                    List<XWPFParagraph> paragraphList = tableDataRow.getCell(j).getParagraphs();
                    XWPFParagraph dataCellParagraph = paragraphList.get(0);
                    
                    dataCellParagraph.setAlignment(ParagraphAlignment.CENTER);
                    dataCellParagraph.setIndentationLeft(100);
                    dataCellParagraph.setIndentationRight(100);
                    
                    XWPFRun dataCellRun = dataCellParagraph.createRun();

                    dataCellRun.setFontFamily("Times New Roman");
                    dataCellRun.setFontSize(12);
                    dataCellRun.setColor("000000");
                    dataCellRun.setBold(false);
                    
                    if (j == 0) {
                        dataCellRun.setText(byStudentObjectList.get(i).getDate());
                    }
                    else if (j == 1) {
                        dataCellRun.setText(byStudentObjectList.get(i).getStudentStatus());
                    }
                    else if (j == 2) {
                        dataCellRun.setText(commentsList.get(i));
                    }

                    tableDataRow.getCell(j).setParagraph(dataCellParagraph);
                    tableDataRow.getCell(j).setColor("ffffff");
                    tableDataRow.getCell(j).setVerticalAlignment(XWPFVertAlign.CENTER);
                }      
            }
            
            file = new File(filePath);

            FileOutputStream os = new FileOutputStream(file);
            document.write(os);
            document.close();
            os.close();
            
            return true;
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public boolean createXMLDocumentByStudent(String filePath, RecordsSearchObject searchCriteria, String className, ArrayList<ByStudentJTableObject> byStudentObjectList, ArrayList<String> commentsList) {
        
        try {
            
            DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            //DateTimeFormatter dtfTwo = DateTimeFormatter.ofPattern("dd-MM-yyyy");  

            LocalDateTime now = LocalDateTime.now();  
            String currentDate = dtfOne.format(now);

            //fileName = currentDate + "_By_Student_Search_Attendance_Records_" + searchCriteria.getStudentName();
            currentDate = dtfOne.format(now);

            Workbook excelWorkbook = new XSSFWorkbook();
            Sheet excelSheet = excelWorkbook.createSheet("MotherShip_Attendance_Record_Search");
            
            excelSheet.getPrintSetup().setLandscape(true);
            excelSheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A5_PAPERSIZE); 
            
            excelSheet.setFitToPage(true);
            PrintSetup ps = excelSheet.getPrintSetup();
            ps.setFitWidth( (short) 1);
            ps.setFitHeight( (short) 0);

            Font titleFont = excelWorkbook.createFont();
            titleFont.setFontHeightInPoints((short) 14);
            titleFont.setFontName("Times New Roman");
            titleFont.setBold(true);

            Font headerFont = excelWorkbook.createFont();
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setFontName("Times New Roman");
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            Font cellFont = excelWorkbook.createFont();
            cellFont.setFontHeightInPoints((short) 14);
            cellFont.setFontName("Times New Roman");

            CellStyle excelWorkbookHeaderStyleDate = excelWorkbook.createCellStyle();
            excelWorkbookHeaderStyleDate.setFont(headerFont);
            excelWorkbookHeaderStyleDate.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            excelWorkbookHeaderStyleDate.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            excelWorkbookHeaderStyleDate.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookHeaderStyleStatus = excelWorkbook.createCellStyle();
            excelWorkbookHeaderStyleStatus.setFont(headerFont);
            excelWorkbookHeaderStyleStatus.setFillForegroundColor(IndexedColors.RED.getIndex());
            excelWorkbookHeaderStyleStatus.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            excelWorkbookHeaderStyleStatus.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookHeaderStyleComments = excelWorkbook.createCellStyle();
            excelWorkbookHeaderStyleComments.setFont(headerFont);
            excelWorkbookHeaderStyleComments.setFillForegroundColor(IndexedColors.BLACK.getIndex());
            excelWorkbookHeaderStyleComments.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            excelWorkbookHeaderStyleComments.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookDataCellStyle = excelWorkbook.createCellStyle();
            excelWorkbookDataCellStyle.setFont(cellFont);
            excelWorkbookDataCellStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookTitleCellStyle = excelWorkbook.createCellStyle();
            excelWorkbookTitleCellStyle.setFont(titleFont);
            excelWorkbookTitleCellStyle.setAlignment(HorizontalAlignment.LEFT);

            CellStyle excelWorkbookSearchCriteriaFieldStyle = excelWorkbook.createCellStyle();
            excelWorkbookSearchCriteriaFieldStyle.setFont(titleFont);
            excelWorkbookSearchCriteriaFieldStyle.setAlignment(HorizontalAlignment.LEFT);

            CellStyle excelWorkbookSearchCriteriaDataStyle = excelWorkbook.createCellStyle();
            excelWorkbookSearchCriteriaDataStyle.setFont(cellFont);
            excelWorkbookSearchCriteriaDataStyle.setAlignment(HorizontalAlignment.LEFT);

            Row titleRow = excelSheet.createRow(2);
            Cell titleCell = titleRow.createCell(1);

            excelSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 15));
            titleCell.setCellStyle(excelWorkbookTitleCellStyle);
            titleCell.setCellValue("Mothership Attendance (By Student Search) Attendance Records:");

            Row emptyRowOne = excelSheet.createRow(3);
            excelSheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 15));  

            Row searchCriteraRowOne = excelSheet.createRow(4);
            Cell classTitleCellOne = searchCriteraRowOne.createCell(1);
            Cell classTitleCellTwo = searchCriteraRowOne.createCell(3);

            excelSheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 15));

            classTitleCellOne.setCellStyle(excelWorkbookSearchCriteriaFieldStyle);
            classTitleCellTwo.setCellStyle(excelWorkbookSearchCriteriaDataStyle);

            classTitleCellOne.setCellValue("Class:");
            classTitleCellTwo.setCellValue(className);

            Row searchCriteraRowTwo = excelSheet.createRow(5);
            Cell studentNameCellOne = searchCriteraRowTwo.createCell(1);
            Cell studentNameCellTwo = searchCriteraRowTwo.createCell(3);

            excelSheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 15));

            studentNameCellOne.setCellStyle(excelWorkbookSearchCriteriaFieldStyle);
            studentNameCellTwo.setCellStyle(excelWorkbookSearchCriteriaDataStyle);

            studentNameCellOne.setCellValue("Student:");
            studentNameCellTwo.setCellValue(searchCriteria.getStudentName());

            Row searchCriteraRowThree = excelSheet.createRow(6);
            Cell creationDateCellOne = searchCriteraRowThree.createCell(1);
            Cell creationDateCellTwo = searchCriteraRowThree.createCell(3);

            excelSheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(6, 6, 3, 15));

            creationDateCellOne.setCellStyle(excelWorkbookSearchCriteriaFieldStyle);
            creationDateCellTwo.setCellStyle(excelWorkbookSearchCriteriaDataStyle);

            creationDateCellOne.setCellValue("Created:");
            creationDateCellTwo.setCellValue(currentDate);

            Row emptyRowThree = excelSheet.createRow(7);
            excelSheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 15));

            Row headerRow = excelSheet.createRow(8);
            int[] cells = {1, 4, 5};
            
            for (int i = 0; i < BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES.length; i++) {
                Cell cell = headerRow.createCell(cells[i]);
                cell.setCellValue(BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);

                if (i == 0) {
                    cell.setCellStyle(excelWorkbookHeaderStyleDate);
                }
                else if (i == 1) {
                    cell.setCellStyle(excelWorkbookHeaderStyleStatus);
                }
                else if (i == 2) {
                    cell.setCellStyle(excelWorkbookHeaderStyleComments);
                }  
            }

            excelSheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(8, 8, 3, 4));
            excelSheet.addMergedRegion(new CellRangeAddress(8, 8, 5, 15));

            int rowNum = 9;

            for (int i = 0; i < byStudentObjectList.size(); i++) {

                Row row = excelSheet.createRow(rowNum);

                row.createCell(1).setCellValue(byStudentObjectList.get(i).getDate());
                row.createCell(3).setCellValue(byStudentObjectList.get(i).getStudentStatus());
                row.createCell(5).setCellValue(commentsList.get(i));

                row.getCell(1).setCellStyle(excelWorkbookDataCellStyle);
                row.getCell(3).setCellStyle(excelWorkbookDataCellStyle);
                row.getCell(5).setCellStyle(excelWorkbookDataCellStyle);

                excelSheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 1, 2));
                excelSheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 3, 4));
                excelSheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 5, 15));

                rowNum++;
            }

            /*
            for (int i = 0; i < BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES.length; i++) {
                excelSheet.autoSizeColumn(i);
            }*/

            file = new File(filePath);
            file.createNewFile();
            
            boolean fileExists = true;
            int fileNumber = 1;

            System.out.println("We Are Getting Here in ExportTabeData");
            
            /*
            while (fileExists == true) {
                if (file.exists() == true) {
                    file = new File(fileName + "_" +  fileNumber + ".xlsx");
                    fileNumber++;
                }
                else {
                    file.createNewFile();
                    fileExists = false;
                }
            }*/

            FileOutputStream os = new FileOutputStream(file);
            excelWorkbook.write(os);

            os.close();
            excelWorkbook.close();
            
            return true;
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
    
    public boolean createXMLDocumentByClass(String filePath, RecordsSearchObject searchCriteria, String className, ArrayList<ByClassJTableObject> byClassObjectList, ArrayList<String> commentsList) {

        try {
            DateTimeFormatter dtfOne = DateTimeFormatter.ofPattern("MM/dd/yyyy");  

            LocalDateTime now = LocalDateTime.now();  
            String currentDate = dtfOne.format(now);

            currentDate = dtfOne.format(now);

            Workbook excelWorkbook = new XSSFWorkbook();
            Sheet excelSheet = excelWorkbook.createSheet("MotherShip_Attendance_Record_Search");

            excelSheet.getPrintSetup().setLandscape(true);
            excelSheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A5_PAPERSIZE); 
            
            excelSheet.setFitToPage(true);
            PrintSetup ps = excelSheet.getPrintSetup();
            ps.setFitWidth( (short) 1);
            ps.setFitHeight( (short) 0);
            
            Font titleFont = excelWorkbook.createFont();
            titleFont.setFontHeightInPoints((short) 14);
            titleFont.setFontName("Times New Roman");
            titleFont.setBold(true);

            Font headerFont = excelWorkbook.createFont();
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setFontName("Times New Roman");
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            Font cellFont = excelWorkbook.createFont();
            cellFont.setFontHeightInPoints((short) 14);
            cellFont.setFontName("Times New Roman");

            CellStyle excelWorkbookHeaderStyleStudentName = excelWorkbook.createCellStyle();
            excelWorkbookHeaderStyleStudentName.setFont(headerFont);
            excelWorkbookHeaderStyleStudentName.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            excelWorkbookHeaderStyleStudentName.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            excelWorkbookHeaderStyleStudentName.setAlignment(HorizontalAlignment.CENTER);
            
            CellStyle excelWorkbookHeaderStyleStudentID = excelWorkbook.createCellStyle();
            excelWorkbookHeaderStyleStudentID.setFont(headerFont);
            excelWorkbookHeaderStyleStudentID.setFillForegroundColor(IndexedColors.PLUM.getIndex());
            excelWorkbookHeaderStyleStudentID.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            excelWorkbookHeaderStyleStudentID.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookHeaderStyleStatus = excelWorkbook.createCellStyle();
            excelWorkbookHeaderStyleStatus.setFont(headerFont);
            excelWorkbookHeaderStyleStatus.setFillForegroundColor(IndexedColors.RED.getIndex());
            excelWorkbookHeaderStyleStatus.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            excelWorkbookHeaderStyleStatus.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookHeaderStyleComments = excelWorkbook.createCellStyle();
            excelWorkbookHeaderStyleComments.setFont(headerFont);
            excelWorkbookHeaderStyleComments.setFillForegroundColor(IndexedColors.BLACK.getIndex());
            excelWorkbookHeaderStyleComments.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            excelWorkbookHeaderStyleComments.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookDataCellStyle = excelWorkbook.createCellStyle();
            excelWorkbookDataCellStyle.setFont(cellFont);
            excelWorkbookDataCellStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle excelWorkbookTitleCellStyle = excelWorkbook.createCellStyle();
            excelWorkbookTitleCellStyle.setFont(titleFont);
            excelWorkbookTitleCellStyle.setAlignment(HorizontalAlignment.LEFT);

            CellStyle excelWorkbookSearchCriteriaFieldStyle = excelWorkbook.createCellStyle();
            excelWorkbookSearchCriteriaFieldStyle.setFont(titleFont);
            excelWorkbookSearchCriteriaFieldStyle.setAlignment(HorizontalAlignment.LEFT);

            CellStyle excelWorkbookSearchCriteriaDataStyle = excelWorkbook.createCellStyle();
            excelWorkbookSearchCriteriaDataStyle.setFont(cellFont);
            excelWorkbookSearchCriteriaDataStyle.setAlignment(HorizontalAlignment.LEFT);

            Row titleRow = excelSheet.createRow(2);
            Cell titleCell = titleRow.createCell(1);

            excelSheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 17));
            titleCell.setCellStyle(excelWorkbookTitleCellStyle);
            titleCell.setCellValue("Mothership Attendance (By Class Search) Attendance Records:");

            Row emptyRowOne = excelSheet.createRow(3);
            excelSheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 17));  

            Row searchCriteraRowOne = excelSheet.createRow(4);
            Cell classTitleCellOne = searchCriteraRowOne.createCell(1);
            Cell classTitleCellTwo = searchCriteraRowOne.createCell(3);

            excelSheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 17));

            classTitleCellOne.setCellStyle(excelWorkbookSearchCriteriaFieldStyle);
            classTitleCellTwo.setCellStyle(excelWorkbookSearchCriteriaDataStyle);

            classTitleCellOne.setCellValue("Class:");
            classTitleCellTwo.setCellValue(className);

            Row searchCriteraRowTwo = excelSheet.createRow(5);
            Cell studentNameCellOne = searchCriteraRowTwo.createCell(1);
            Cell studentNameCellTwo = searchCriteraRowTwo.createCell(3);

            excelSheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 17));

            studentNameCellOne.setCellStyle(excelWorkbookSearchCriteriaFieldStyle);
            studentNameCellTwo.setCellStyle(excelWorkbookSearchCriteriaDataStyle);

            studentNameCellOne.setCellValue("Date:");
            studentNameCellTwo.setCellValue(searchCriteria.getDate());

            Row searchCriteraRowThree = excelSheet.createRow(6);
            Cell creationDateCellOne = searchCriteraRowThree.createCell(1);
            Cell creationDateCellTwo = searchCriteraRowThree.createCell(3);

            excelSheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(6, 6, 3, 17));

            creationDateCellOne.setCellStyle(excelWorkbookSearchCriteriaFieldStyle);
            creationDateCellTwo.setCellStyle(excelWorkbookSearchCriteriaDataStyle);

            creationDateCellOne.setCellValue("Created:");
            creationDateCellTwo.setCellValue(currentDate);

            Row emptyRowThree = excelSheet.createRow(7);
            excelSheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 17));

            Row headerRow = excelSheet.createRow(8);
            int[] cells = {1, 3, 5, 7};
            
            for (int i = 0; i < BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES.length; i++) {
                Cell cell = headerRow.createCell(cells[i]);
                cell.setCellValue(BY_CLASS_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES[i]);

                if (i == 0) {
                    cell.setCellStyle(excelWorkbookHeaderStyleStudentName);
                }
                else if (i == 1) {
                    cell.setCellStyle(excelWorkbookHeaderStyleStudentID);
                }
                else if (i == 2) {
                    cell.setCellStyle(excelWorkbookHeaderStyleStatus);
                }  
                else if (i == 3) {
                    cell.setCellStyle(excelWorkbookHeaderStyleComments);
                }  
            }

            excelSheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 2));
            excelSheet.addMergedRegion(new CellRangeAddress(8, 8, 3, 4));
            excelSheet.addMergedRegion(new CellRangeAddress(8, 8, 5, 6));
            excelSheet.addMergedRegion(new CellRangeAddress(8, 8, 7, 17));

            int rowNum = 9;

            for (int i = 0; i < byClassObjectList.size(); i++) {

                Row row = excelSheet.createRow(rowNum);

                row.createCell(1).setCellValue(byClassObjectList.get(i).getStudentName());
                row.createCell(3).setCellValue(byClassObjectList.get(i).getStudentID());
                row.createCell(5).setCellValue(byClassObjectList.get(i).getStudentStatus());
                row.createCell(7).setCellValue(commentsList.get(i));

                row.getCell(1).setCellStyle(excelWorkbookDataCellStyle);
                row.getCell(3).setCellStyle(excelWorkbookDataCellStyle);
                row.getCell(5).setCellStyle(excelWorkbookDataCellStyle);
                row.getCell(7).setCellStyle(excelWorkbookDataCellStyle);

                excelSheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 1, 2));
                excelSheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 3, 4));
                excelSheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 5, 6));
                excelSheet.addMergedRegion(new CellRangeAddress((rowNum), (rowNum), 7, 17));

                rowNum++;
            }

            /*
            for (int i = 0; i < BY_STUDENT_ATTENDANCE_RECORD_SEARCH_COLUMN_NAMES.length; i++) {
                excelSheet.autoSizeColumn(i);
            }*/

            file = new File(filePath);
            
            if (file.exists() == false) {
                file.createNewFile();
            }
            else {
                file.delete();
                file.createNewFile();
            }

            FileOutputStream os = new FileOutputStream(file);
            excelWorkbook.write(os);

            os.close();
            excelWorkbook.close();
            
            System.out.println("Getting Here in Export Table Data");
            
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }

        return true;
    }
}
