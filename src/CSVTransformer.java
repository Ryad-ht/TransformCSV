import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVTransformer {

    public void transformFrenchToAngloSaxon(String inputFilePath, String outputFilePath) throws Exception {
        try (
                Reader in = new FileReader(inputFilePath);
                Writer out = new FileWriter(outputFilePath);
                CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withDelimiter(';'));
                CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withDelimiter(','))
        ) {
            for (CSVRecord record : parser) {
                for (int i = 0; i < record.size(); i++) {
                    String value = record.get(i).replace(',', '.');
                    printer.print(value);
                }
                printer.println();
            }
        }
    }
}
