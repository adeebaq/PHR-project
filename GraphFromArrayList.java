package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphFromArrayList {

    public static void main(String[] args) {
        // Example data
        ArrayList<Double> values = new ArrayList<>();
        values.add(2.5);
        values.add(3.0);
        values.add(4.5);
        values.add(6.0);
        values.add(5.5);
        values.add(7.2);
        values.add(8.1);

        // Create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < values.size(); i++) {
            dataset.addValue(values.get(i), "Values", String.valueOf(i));
        }

        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "ArrayList<Double> Graph",  // Chart title
                "Index",                    // X-axis label
                "Value",                    // Y-axis label
                dataset
        );

        // Save chart as PNG
        try {
            ChartUtils.saveChartAsPNG(new File("arraylist_graph.png"), chart, 800, 600);
            System.out.println("Graph saved as arraylist_graph.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
