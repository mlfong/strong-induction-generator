package org.stronginduction;

/*
 <APPLET 
 ARCHIVE="AT-web-presentation-imp.jar" 
 CODE="ImpViewerApplet" 
 WIDTH=900
 HEIGHT=600>
 </APPLET>
 */
import java.awt.*;
import java.applet.*;
import java.util.*;

public class SIViewer extends Applet
{
    private static final long serialVersionUID = 4525020067022327939L;

    @SuppressWarnings({ "static-access", "unused" })
    public void init()
    {
        setLayout(new BorderLayout());
        Window[] all = Window.getWindows();
        ArrayList<Window> allList = new ArrayList<Window>();
        for (Window window : all)
        {
            allList.add(window);
        }
        String[] args = {};
        StrongInduction iv = new StrongInduction();
        iv.main(args);

        all = Window.getWindows();
        for (Window window : all)
        {
            if (!allList.contains(window) && window.isVisible())
            {
                if (window instanceof Frame)
                {
                    Frame f = (Frame) window;
                    Component[] allComp = f.getComponents();
                    Component c = f.getComponents()[0];
                    f.remove(c);
                    f.setVisible(false);
                    add(c);
                    validate();
                }
            }
        }
    }
}