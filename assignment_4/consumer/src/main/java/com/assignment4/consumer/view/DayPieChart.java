package com.assignment4.consumer.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Map;

public class DayPieChart extends ApplicationFrame {

    public DayPieChart(LocalDate day, Map<String, Long> myDayData) {
        super( day.toString() );
        setContentPane(createDemoPanel(myDayData ));
    }

    private static PieDataset createDataset(Map<String, Long> myDayData ) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(String activity: myDayData.keySet()){
            dataset.setValue(activity, myDayData.get(activity));
        }
        return dataset;
    }

    private static JFreeChart createChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Daily Activity",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public static JPanel createDemoPanel(Map<String, Long> myDayData ) {
        JFreeChart chart = createChart(createDataset(myDayData ) );
        return new ChartPanel( chart );
    }

//    public static void main( String[ ] args ) {
//        DayPieChart demo = new DayPieChart( "Daily activities" );
//        demo.setSize( 560 , 367 );
//        RefineryUtilities.centerFrameOnScreen( demo );
//        demo.setVisible( true );
//    }
}
