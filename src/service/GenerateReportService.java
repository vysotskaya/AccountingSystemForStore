package service;

import entity.Record;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;

import javax.swing.*;
import java.io.File;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by User on 12.05.2015.
 */
public class GenerateReportService {

    private static ObjectFactory factory = null;
    private static WordprocessingMLPackage wordMLPackage = null;

    public static void generateReport(String periodBegin, String periodEnd, List records)
            throws Docx4JException, InvalidFormatException {
        try {
            wordMLPackage = WordprocessingMLPackage.createPackage();
            factory = Context.getWmlObjectFactory();

            Tbl table = createTableWithContent(records);
            addBorders(table);

            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "Отчёт за период: c "
                + periodBegin + " по " +  periodEnd);
            wordMLPackage.getMainDocumentPart().addObject(table);

            try {
                JFrame jFrame = new JFrame();
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showSaveDialog(jFrame);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    wordMLPackage.save(new java.io.File(file.getAbsolutePath()));
                }
            } catch (Docx4JException e) {
                throw e;
            }
        } catch (InvalidFormatException e) {
           throw e;
        }
    }

    private static Tbl createTableWithContent(List records) {
        Tbl table = factory.createTbl();
        Tr tableRow = factory.createTr();

        addTableCell(tableRow, "Маркировка");
        addTableCell(tableRow, "Наименование");
        addTableCell(tableRow, "Количество");
        addTableCell(tableRow, "Срок хранения");
        addTableCell(tableRow, "Ответственный");
        addTableCell(tableRow, "Таможенный режим");
        table.getContent().add(tableRow);

        Record record = null;
        for (Object o : records) {
            record = (Record) o;
            tableRow = factory.createTr();
            addTableCell(tableRow, record.getProduct().getProduct_marking());
            addTableCell(tableRow, record.getProduct().getProduct_name());
            addTableCell(tableRow, record.getProduct().getAcount() + " " + record.getProduct().getMeasuring_unit());
            addTableCell(tableRow, record.getRetention_limit());
            addTableCell(tableRow, record.getEmployee().getEmployee_name());
            addTableCell(tableRow, record.getProduct().getCustomsRegimeType().getRegime_name());
            table.getContent().add(tableRow);
        }


        return table;
    }

    private static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);

        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }

    private static void addTableCell(Tr tableRow, String content) {
        Tc tableCell = factory.createTc();
        tableCell.getContent().add(
                wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tableRow.getContent().add(tableCell);
    }
}
