/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util.plot;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContourPlot1 extends Frame
{
  int ncont = 9; // contour lines zz normalized
  double dzz = 1.0/(ncont+1);  // first is dzz
    Color cont_color[] = {Color.black, Color.gray, Color.magenta,
                          Color.cyan, Color.blue, Color.green,
                          Color.yellow, Color.orange, Color.red};
  int width=800, height=800;
  double xp[] = new double[4]; // reused
  double yp[] = new double[4];
  double xx[];
  double yy[];
  double zz[];
  int n_input;
  double xmin, xmax, ymin, ymax, zmin, zmax;
    int nxx=0, nyy=0, nxy=0; // index i*nxx+j
  int n=0;
  boolean connect;
  boolean debug = false;
  
  ContourPlot1()
  {
    setTitle("ContourPlot1");
    try{
      read_data();
    }
    catch(IOException exception)
    {
    }
    setSize(width,height);
    setBackground(Color.white);
    setForeground(Color.black);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    });
    setVisible(true);
    n=0;
    connect=false;
    this.addMouseListener (new mousePressHandler());
    repaint();

  } // end ContourPlot1

  class mousePressHandler extends MouseAdapter
  {
    public void mousePressed (MouseEvent e)
    {
      int x, y, z, b;
      x = e.getX();
      y = e.getY();
      b = e.getButton();
      if(b==3 && n>0)
      {
        connect=true;
      }
      requestFocus();
      System.out.println("at x="+x+"   y="+y+"   b="+b); // debug print
      repaint();
    }
  } // end mousePressHandler

  int scalex(double a)
  {
      return (int)((double)(width-20)*a)+10;
  } // end scalex

  int scaley(double a)
  {
      return (int)((double)(height-60)*(a))+50;
  } // end scaley

  public void paint(Graphics g)
  {
    double x1, y1, x2, y2, z11, z12, z21, z22;
    double z, nearz;
    boolean pt1, pt2;
    String fstring;

    g.setColor(Color.black);
    for(int i=0; i<nxy; i++) // data point location
    {
	g.drawOval(scalex(xx[i]), scaley(yy[i]), 2, 2);
    }

    // height color scale
    g.setColor(Color.black);
    g.drawString("contour  xmin=", 50, 40);
    fstring = Double.toString(xmin);
    if(fstring.length()>6)
       fstring = fstring.substring(0,6);
    g.drawString(fstring, 145, 40);
    g.drawString("xmax=",220, 40);
    fstring = Double.toString(xmax);
    if(fstring.length()>6)
       fstring = fstring.substring(0,6);
    g.drawString(fstring, 260, 40);
    g.drawString("ymin=",340, 40);
    fstring = Double.toString(ymin);
    if(fstring.length()>6)
       fstring = fstring.substring(0,6);
    g.drawString(fstring, 380, 40);
    g.drawString("ymax=",460, 40);
    fstring = Double.toString(ymax);
    if(fstring.length()>6)
       fstring = fstring.substring(0,6);
    g.drawString(fstring, 500, 40);
    g.drawString("zmin=",580, 40);
    fstring = Double.toString(zmin);
    if(fstring.length()>6)
       fstring = fstring.substring(0,6);
    g.drawString(fstring, 620, 40);
    g.drawString("zmax=",700, 40);
    fstring = Double.toString(zmax);
    if(fstring.length()>6)
       fstring = fstring.substring(0,6);
    g.drawString(fstring, 740, 40);

    for(int i=0; i<ncont; i++)
    {
      g.setColor(cont_color[i]);
      g.fillRect(10,50+20*i,30,15);
    }
    g.setColor(Color.black);
    z = dzz;
    for(int i=0; i<ncont; i++)
    {
      fstring = Double.toString(z*(zmax-zmin)+zmin);
      if(fstring.length()>6)
         fstring = fstring.substring(0,6);
      g.drawString(fstring, 50, 62+20*i);
      z = z + dzz;
    }

    z = dzz;
    for(int k=0; k<ncont; k++) // draw each contour
    {
      if(debug) System.out.println("contour z="+z);
      n = 0;
      g.setColor(cont_color[k]); // color change
      for(int i=0; i<nxx-1; i++)
      {
        for(int j=0; j<nyy-1; j++)
        {
	  pt1 = false;
          pt2 = false;
	  x1 = xx[i*nyy+j];
          x2 = xx[(i+1)*nyy+j];
	  y1 = yy[i*nyy+j];
	  y2 = yy[i*nyy+j+1];
          z11 = zz[i*nyy+j];       // x1,y1
          z12 = zz[i*nyy+j+1];     // x1,y2
          z21 = zz[(i+1)*nyy+j];   // x2,y1
          z22 = zz[(i+1)*nyy+j+1]; // x2,y2
	  //
	  //  y2  z12 -- z -- z22    only a or b or neither
	  //       |   seg4    |
	  //       |           |
	  //       z           z
	  //     seg 1       seg 3
	  //       |           |
	  //  y1  z11 -- z -- z21     y2 > y1
	  //           seg2
	  //       x1        x2       x2 > x1
	  //
          if(z11>=z && z>=z12) // segment 1a
	  {
	      xp[n] = x1;
	      yp[n] = ((z11-z)/(z11-z12))*(y2-y1)+y1;
              n++;
              pt1=true;
	  }
          if(z12>=z && z>=z11) // segment 1b
	  {
	      xp[n] = x1;
	      yp[n] = ((z-z11)/(z12-z11))*(y2-y1)+y1;
              n++;
              pt1=true;
	  }
          if(z11>=z && z>=z21) // segment 2a
	  {
	      yp[n] = y1;
	      xp[n] = ((z11-z)/(z11-z21))*(x2-x1)+x1;
              n++;
              if(pt1) pt2=true;
              else    pt1=true;
	  }
          if(z21>=z && z>=z11) // segment2b
	  {
	      yp[n] = y1;
	      xp[n] = ((z-z11)/(z21-z11))*(x2-x1)+x1;
              n++;
              if(pt1) pt2=true;
              else    pt1=true;
	  }
          if(pt2)
            g.drawLine(scalex(xp[n-2]), scaley(yp[n-2]),
                       scalex(xp[n-1]), scaley(yp[n-1]));
          pt2=false;
          if(z21>=z && z>=z22) // segment 3a
	  {
	      xp[n] = x2;
	      yp[n] = ((z21-z)/(z21-z22))*(y2-y1)+y1;
              n++;
              if(pt1) pt2=true;
              else    pt1=true;
	  }
          if(z22>=z && z>=z21) // segment 3b
	  {
	      xp[n] = x2;
	      yp[n] = ((z-z21)/(z22-z21))*(y2-y1)+y1;
              n++;
              if(pt1) pt2=true;
              else    pt1=true;
	  }
          if(pt2)
            g.drawLine(scalex(xp[n-2]), scaley(yp[n-2]),
                       scalex(xp[n-1]), scaley(yp[n-1]));
          pt2=false;
          if(z22>=z && z>=z12) // segment 4a
	  {
	      yp[n] = y2;
	      xp[n] = ((z-z12)/(z22-z12))*(x2-x1)+x1;
              n++;
              if(pt1) pt2=true;
              else    pt1=true;
	  }
          if(z12>=z && z>=z22) // segment 4b
	  {
	      yp[n] = y2;
	      xp[n] = ((z12-z)/(z12-z22))*(x2-x1)+x1;
              n++;
              if(pt1) pt2=true;
              else    pt1=true;
	  }
          if(pt2)
            g.drawLine(scalex(xp[n-2]), scaley(yp[n-2]),
                       scalex(xp[n-1]), scaley(yp[n-1]));
          n = 0;
	} // end j
      } // end i
      z = z + dzz; // move contour height
    } // end k
  } // end paint

  void read_data() throws IOException // big ugly input method
  {
    try
    {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String input_line;
      double amount;
      int len;
      int index;
      int last;
   
      System.out.println("read_data running");
      input_line = in.readLine(); // nxx nyy 
      System.out.println("input: "+input_line);
      len = input_line.length();
      index = 0; // nxx
      if(debug) System.out.println("index="+index);
      last = input_line.indexOf(' ',index);
      if(debug) System.out.println("last="+last);
      if(debug) System.out.println(input_line.substring(index,last)+
			  "| len="+(input_line.substring(index,last).length()));
      nxx = Integer.parseInt(input_line.substring(index,last));
      System.out.println("nxx="+nxx);
      index = input_line.indexOf(' ',index); // nyy
      if(debug) System.out.println("index="+index);
      last = input_line.indexOf(' ',index+1);
      if(debug) System.out.println("last="+last);
      if(debug) System.out.println(input_line.substring(index+1,last));
      nyy = Integer.parseInt(input_line.substring(index+1,last));
      System.out.println("nyy="+nyy);
      nxy = nxx*nyy;
      System.out.println("about to allocate "+(3*(nxy+1))+" doubles");
      xx = new double[nxy+1];
      yy = new double[nxy+1];
      zz = new double[nxy+1];
      n_input = 0;

      // read data points, last index varies by 1
      input_line = in.readLine(); // tested for short in 'while'
      while(input_line.length()>6) // exit on blank or short line
      {
	if(debug && n_input==0) System.out.println("input: "+input_line);
        len = input_line.length();
        index = 0; // first x
        if(debug && n_input==0) System.out.println("index="+index);
        last = input_line.indexOf(' ',index+1);
        if(debug && n_input==0) System.out.println("last="+last);
        amount = Double.parseDouble(input_line.substring(index,last));
        if(debug && n_input==0) System.out.println("amount="+amount);
        xx[n_input] = amount;
        index = last+1; // second y
        if(debug && n_input==0) System.out.println("index="+index);
        last = input_line.indexOf(' ',index);
        if(debug && n_input==0) System.out.println("last="+last);
        amount = Double.parseDouble(input_line.substring(index,last));
        if(debug && n_input==0) System.out.println("amount="+amount);
        yy[n_input] = amount;
        index = last+1; // third z
        if(debug && n_input==0) System.out.println("index="+index);
        last = input_line.indexOf(' ',index);
        if(last<index) last=len;
        if(debug && n_input==0) System.out.println("last="+last);
        amount = Double.parseDouble(input_line.substring(index,last));
        if(debug && n_input==0) System.out.println("amount="+amount);
        zz[n_input] = amount;

        if(n_input==0)
	{
	  xmin = xx[n_input];
	  xmax = xx[n_input];
	  ymin = yy[n_input];
	  ymax = yy[n_input];
	  zmin = zz[n_input];
	  zmax = zz[n_input];
          System.out.println("first x="+xmin+", y="+ymin+", z="+zmin);
	}
	else
	{
	  xmin = Math.min(xmin,xx[n_input]);
	  xmax = Math.max(xmax,xx[n_input]);
	  ymin = Math.min(ymin,yy[n_input]);
	  ymax = Math.max(ymax,yy[n_input]);
	  zmin = Math.min(zmin,zz[n_input]);
	  zmax = Math.max(zmax,zz[n_input]);
	}
        n_input++;
        if(n_input>=nxy) break;
        input_line = in.readLine(); // done with this line, read next
      } // end while
    } // end try
    catch(IOException exception)
    {
      System.out.println(exception);
    }
    System.out.println("read_data finished, n_input="+n_input);
    System.out.println("xmin="+xmin+", xmax="+xmax+", last="+xx[nxy-1]);
    System.out.println("ymin="+ymin+", ymax="+ymax+", last="+yy[nxy-1]);
    System.out.println("zmin="+zmin+", zmax="+zmax+", last="+zz[nxy-1]);
    
    for(int i=0; i<nxy; i++)
    {
      xx[i] = (xx[i]-xmin)/(xmax-xmin);
      yy[i] = (yy[i]-ymin)/(ymax-ymin);
      zz[i] = (zz[i]-zmin)/(zmax-zmin);
    }
  } // end read_data
 
  public static void main(String args[])
  {

    new ContourPlot1();
  }
}
