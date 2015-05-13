import dao.DAOFactory;
import entity.Product;
import entity.Record;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.*;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created by User on 12.05.2015.
 */
public class ReportGenerateTest {

    private ObjectFactory factory = null;
    private WordprocessingMLPackage wordMLPackage = null;

    //@Ignore
    @Test
    public void reportGenerateTest() {
        try {
            wordMLPackage = WordprocessingMLPackage.createPackage();
            factory = Context.getWmlObjectFactory();

            Tbl table = createTableWithContent();
            addBorders(table);

            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "Отчёт за период");
            wordMLPackage.getMainDocumentPart().addObject(table);

            try {
                wordMLPackage.save(new java.io.File("reports/table.docx"));
            } catch (Docx4JException e) {
                e.printStackTrace();
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        assertTrue(true);
    }

    private Tbl createTableWithContent() {
        Tbl table = factory.createTbl();
        Tr tableRow = factory.createTr();

        List records = DAOFactory.getFactory().getRecordDAO().read();

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


    private void addTableCell(Tr tableRow, String content) {
        Tc tableCell = factory.createTc();
        tableCell.getContent().add(
                wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tableRow.getContent().add(tableCell);
    }

}
