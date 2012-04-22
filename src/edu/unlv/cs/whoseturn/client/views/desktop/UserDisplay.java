package edu.unlv.cs.whoseturn.client.views.desktop;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Displays a User's profile.
 */
public class UserDisplay extends AbstractNavigationView implements
        NavigationView {

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public final Widget bodyAsWidget() {
        AbsolutePanel panel = new AbsolutePanel();
        panel.setSize("1000px", "500px");
        
        Image image = new Image("images/badges/CornerStone50x50.jpg");
        panel.add(image, 53, 60);
        image.setSize("50px", "50px");
        
                Label labelPlaceHolder = new Label();
                panel.add(labelPlaceHolder);
                labelPlaceHolder.setText("User Display");
                
                Image image_1 = new Image("images/badges/JackAss50x50.jpg");
                panel.add(image_1, 53, 128);
                image_1.setSize("50px", "50px");
                
                Image image_2 = new Image("images/badges/SnowWhite50x50.jpg");
                panel.add(image_2, 53, 201);
                image_2.setSize("50px", "50px");
                
                Image image_3 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_3, 53, 270);
                image_3.setSize("50px", "50px");
                
                Image image_4 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_4, 53, 343);
                image_4.setSize("50px", "50px");
                
                Image image_5 = new Image("images/badges/CrappedOut50x50.jpg");
                panel.add(image_5, 126, 60);
                image_5.setSize("50px", "50px");
                
                Image image_6 = new Image("images/badges/MythBusters50x50.jpg");
                panel.add(image_6, 126, 128);
                image_6.setSize("50px", "50px");
                
                Image image_7 = new Image("images/badges/Socialite50x50.jpg");
                panel.add(image_7, 126, 201);
                image_7.setSize("50px", "50px");
                
                Image image_8 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_8, 126, 270);
                image_8.setSize("50px", "50px");
                
                Image image_9 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_9, 126, 343);
                image_9.setSize("50px", "50px");
                
                Image image_10 = new Image("images/badges/FML50x50.jpg");
                panel.add(image_10, 195, 60);
                image_10.setSize("50px", "50px");
                
                Image image_11 = new Image("images/badges/Saint50x50.jpg");
                panel.add(image_11, 195, 128);
                image_11.setSize("50px", "50px");
                
                Image image_12 = new Image("images/badges/TeamCheater50x50.jpg");
                panel.add(image_12, 195, 201);
                image_12.setSize("50px", "50px");
                
                Image image_13 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_13, 195, 270);
                image_13.setSize("50px", "50px");
                
                Image image_14 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_14, 195, 343);
                image_14.setSize("50px", "50px");
                
                Image image_15 = new Image("images/badges/HumanSacrifice50x50.jpg");
                panel.add(image_15, 262, 60);
                image_15.setSize("50px", "50px");
                
                Image image_16 = new Image("images/badges/SixMinuteAbs50x50.jpg");
                panel.add(image_16, 262, 128);
                image_16.setSize("50px", "50px");
                
                Image image_17 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_17, 262, 201);
                image_17.setSize("50px", "50px");
                
                Image image_18 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_18, 262, 270);
                image_18.setSize("50px", "50px");
                
                Image image_19 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_19, 262, 343);
                image_19.setSize("50px", "50px");

        return panel;
    }
}
