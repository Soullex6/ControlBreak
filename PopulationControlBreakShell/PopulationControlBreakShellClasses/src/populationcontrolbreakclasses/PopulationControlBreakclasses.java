package populationcontrolbreakclasses;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import dao.LineSequential;

public class PopulationControlBreakclasses {

    static String inFile = "E:/Bus programming/dataFiles/PopulationMaster.dat";  
    static String inFileStreamName = "populationMaster";
    static String ioMode1 = "input";
    static String outFile = "E:/Bus programming/PopulationControlBreakShell/PopulationControlBreakShell/PopulationControlBreakListing.out";
    static String outFileStreamName = "populationList";
    static String ioMode2 = "output";
    static SimpleDateFormat dateFormatStandard = new SimpleDateFormat("M/d/YYYY");
    static Date today = new Date();
    static String heading1String = makespaces.moveSpaces(19) + "Population Listing" + makespaces.moveSpaces(16) + dateFormatStandard.format(today) + makespaces.moveSpaces(22);
    static String heading2String = "Town Number " + "Town Name" + makespaces.moveSpaces(12) + "County Number  " + "Population" + makespaces.moveSpaces(23);
    static int[] townRecordMarksIn = {3, 23, 25, 27, 33};
    static int[] townRecordMarksOut = {5, 12, 32, 38, 40, 49, 56};
    static String blankRecord = makespaces.moveSpaces(80);

    static Town town = new Town();
    static String inputLine;
    static StringBuilder townRecordOut;
    static String townNumberString;
    static String townName;
    static String countyNumberString;
    static String populationString;
    static int controlBreakCountyNumber;
    static int totalPopulation;
    static StringBuilder summaryRecordOut;
    static String totalPopulationString;
    static int[] summaryRecordMarksOut = {23, 41, 42, 47, 56};
    static DecimalFormat totalPopulationFormat1 = new DecimalFormat("#,###,##0");
    static String totalPopulationFormat2 = "%1$9s";
    static String summaryString = "County Population ";

    public static void main(String[] args) {
        initialization();

        setControlBreakField();
        while ((inputLine = LineSequential.read(inFileStreamName)) != null) {
            moveFields();
        }
        performControlBreak();

        terminationRoutine();
    }

    private static void initialization() {
        LineSequential.open(inFile, inFileStreamName, ioMode1);
        LineSequential.open(outFile, outFileStreamName, ioMode2);
        writeHeadings();
    }

    private static void writeHeadings() {
        LineSequential.write(outFileStreamName, blankRecord);
        LineSequential.write(outFileStreamName, heading1String);
        LineSequential.write(outFileStreamName, blankRecord);
        LineSequential.write(outFileStreamName, heading2String);
        LineSequential.write(outFileStreamName, blankRecord);

    }

    static void setControlBreakField() {
        inputLine = LineSequential.read(inFileStreamName);
        townRecordOut = new StringBuilder(blankRecord);
        initializeTownFields();
        makeTownRecord();
        LineSequential.write(outFileStreamName, townRecordOut.toString());
        controlBreakCountyNumber = town.getCountyNumber();
        totalPopulation = town.getPopulation();

    }

    private static void moveFields() {
        
        townRecordOut = new StringBuilder(blankRecord);
        initializeTownFields();
        if(controlBreakCountyNumber != town.getCountyNumber()){
            performControlBreak();
        }
        makeTownRecord();
        LineSequential.write(outFileStreamName, townRecordOut.toString());
        totalPopulation += town.getPopulation();

    }

    private static void initializeTownFields() {
        town.setTownNumber(Integer.valueOf(inputLine.substring(0, townRecordMarksIn[0])));
        town.setTownName(inputLine.substring(townRecordMarksIn[0], townRecordMarksIn[1]));
        town.setCountyNumber(Integer.valueOf(inputLine.substring(townRecordMarksIn[1], townRecordMarksIn[2])));
        town.setRegionNumber(Integer.valueOf(inputLine.substring(townRecordMarksIn[2], townRecordMarksIn[3])));
        town.setPopulation(Integer.valueOf(inputLine.substring(townRecordMarksIn[3], townRecordMarksIn[4])));
    }

    private static void makeTownRecord() {
        townNumberString = String.format(townNumberFormat, town.getTownNumber());
        townName = town.getTownName();
        countyNumberString = String.format(countyNumberFormat, town.getCountyNumber());
        populationString = populationFormat1.format(town.getPopulation());
        populationString = String.format(populationFormat2, populationString);

        townRecordOut.replace(0, townRecordMarksOut[0], townNumberString);
        townRecordOut.replace(townRecordMarksOut[1], townRecordMarksOut[2], townName);
        townRecordOut.replace(townRecordMarksOut[3], townRecordMarksOut[4], countyNumberString);
        townRecordOut.replace(townRecordMarksOut[5], townRecordMarksOut[6], populationString);
    }

    static void performControlBreak() {
        makeSummaryLine();
        LineSequential.write(outFileStreamName, summaryRecordOut.toString());
        LineSequential.write(outFileStreamName, blankRecord);
        controlBreakCountyNumber = town.getCountyNumber();
        totalPopulation = 0;

    }

    static void makeSummaryLine() {
        summaryRecordOut = new StringBuilder(blankRecord);
        countyNumberString = String.format(countyNumberFormat, controlBreakCountyNumber);
        totalPopulationString = totalPopulationFormat1.format(totalPopulation);
        totalPopulationString = String.format(totalPopulationFormat2, totalPopulationString);

        summaryRecordOut.replace(summaryRecordMarksOut[0], summaryRecordMarksOut[1], summaryString);
        summaryRecordOut.replace(summaryRecordMarksOut[1], summaryRecordMarksOut[2], countyNumberString);
        summaryRecordOut.replace(summaryRecordMarksOut[3], summaryRecordMarksOut[4], totalPopulationString);
    }

    private static void terminationRoutine() {
        LineSequential.close(inFileStreamName, ioMode1);
        LineSequential.close(outFileStreamName, ioMode2);
        System.out.println("YUP");

    }

    
}
