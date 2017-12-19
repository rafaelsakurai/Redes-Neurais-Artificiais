package util.plot;

import javax.swing.JPanel;

public class ContourPlotApplet extends JPanel {

    // Below, constants, i.e. "final static" data members:
    final static int NUMBER_COMPONENTS = 6;
    final static int MIN_X_STEPS = 2,
            MIN_Y_STEPS = 2,
            MAX_X_STEPS = 100,
            MAX_Y_STEPS = 100;
    final static String EOL
            = System.getProperty("line.separator");

    // Below, the six user-interface components:
    ContourPlot thePlot = new ContourPlot(MIN_X_STEPS, MIN_Y_STEPS);

    // Below, class data members read from the <APPLET> tag:
    static String contourValuesTitle, infoStrX, infoStrY,
            errParse, errLog, errComp, errEqual,
            errExpect, errEOF, errBounds;

    public ContourPlotApplet() {
        init();
    }
    
    //-------------------------------------------------------
    // "init" overrides "super.init()" and initializes the
    // applet by:
    //	1. getting parameters from the <APPLET> tag;
    // 2. setting layout to instance of "ContourPlotLayout";
    // 3. initializing and adding the six user-interface
    //		components, using the method "add()" which will
    //		also call "ContourPlotLayout.addLayoutComponent()".
    //-------------------------------------------------------
    public void init() {
        setLayout(new ContourPlotLayout());
        add("thePlot", thePlot);
    }

    //-------------------------------------------------------
    // "DrawTheContourPlot" does what its name says (in
    // reaction to a hit on the "Draw" button).
    // The guts of this method are in the "try" block which:
    // 1. gets the interpolation flag (for contour values);
    // 2. parses the data, i.e. the matrix of z values;
    // 3. draws the contour plot by calling the "paint()"
    //		method of the component "thePlot";
    //	4. displays the results, i.e. the number of rows and
    //		columns in the grid, an echo of the matrix of z
    //		values, and the list of contour values.
    // This method catches 2 exceptions, then finally (i.e.
    // regardless of exceptions) sends a completion message
    // to the Java console using "System.out.println()".
    //-------------------------------------------------------
    public ContourPlotApplet DrawTheContourPlot(String s) {
        try {
            thePlot.ParseZedMatrix(s);
            thePlot.paint(thePlot.getGraphics());
            s = thePlot.ReturnZedMatrix() + contourValuesTitle + EOL + thePlot.GetContourValuesString();
        } catch (Exception e) {
            e.printStackTrace();
//            thePlot.repaint();
        } finally {
            System.out.println("Exiting DrawTheContourPlot");
        }
        
        return this;
    }
}
