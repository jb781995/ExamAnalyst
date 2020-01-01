package newExam.models;


	
	import javax.swing.JFrame;

	import org.jfree.chart.ChartFactory;
	import org.jfree.chart.ChartPanel;
	import org.jfree.chart.JFreeChart;
	import org.jfree.chart.plot.PlotOrientation;
	import org.jfree.data.category.CategoryDataset;
	import org.jfree.data.category.DefaultCategoryDataset;

	public class DemoGraph extends JFrame {

	   private static final long serialVersionUID = 1L;

	   public DemoGraph(String applicationTitle, String chartTitle) {
	        super(applicationTitle);

	        // based on the dataset we create the chart
	        JFreeChart pieChart = ChartFactory.createBarChart(chartTitle, "Topic Name", "Score", createDataset(),PlotOrientation.VERTICAL, true, true, false);

	        // Adding chart into a chart panel
	        ChartPanel chartPanel = new ChartPanel(pieChart);
	      
	        // settind default size
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	      
	        // add to contentPane
	        setContentPane(chartPanel);
	    }
	  
	   private CategoryDataset createDataset() {
	     
	      // row keys...
	      final String firefox = "Wrong";
	      final String chrome = "Correct";
	      final String iexplorer = "UnAttempted";

	      // column keys...
	      final String speed = "Array";
	      final String popular = "LinkedList";
	      final String response = "Stack";
	      final String osindependent = "Queue";
	      //final String features = "Features";

	      // create the dataset...
	      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	      dataset.addValue(1.0, firefox, speed);
	      dataset.addValue(4.0, firefox, popular);
	      dataset.addValue(3.0, firefox, response);
	      dataset.addValue(5.0, firefox, osindependent);
	     // dataset.addValue(5.0, firefox, features);

	      dataset.addValue(5.0, chrome, speed);
	      dataset.addValue(7.0, chrome, popular);
	      dataset.addValue(6.0, chrome, response);
	      dataset.addValue(8.0, chrome, osindependent);
	     // dataset.addValue(4.0, chrome, features);

	      dataset.addValue(4.0, iexplorer, speed);
	      dataset.addValue(3.0, iexplorer, popular);
	      dataset.addValue(2.0, iexplorer, response);
	      dataset.addValue(3.0, iexplorer, osindependent);
	      //dataset.addValue(6.0, iexplorer, features);
	     
	      return dataset;
	     
	  }

	   public static void main(String[] args) {
		   DemoGraph chart = new DemoGraph("Exam Statistics", "This is Your Statistics");
	      chart.pack();
	      chart.setVisible(true);
	   }
	}
	


