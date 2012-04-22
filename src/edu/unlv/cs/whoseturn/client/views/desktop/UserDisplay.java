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
        
                Label labelPlaceHolder = new Label();
                panel.add(labelPlaceHolder);
                labelPlaceHolder.setText("User Display");
        
        Label userLabel = new Label("User:");
        panel.add(userLabel, 75, 37);
        
        Label emailLabel = new Label("Email:");
        panel.add(emailLabel, 65, 59);
        emailLabel.setSize("41px", "16px");
        
        Label userNameValue = new Label("UserNameValue");
        panel.add(userNameValue, 129, 37);
        userNameValue.setSize("56px", "16px");
        
        Label lblUseremailvalue = new Label("UserEmailValue");
        panel.add(lblUseremailvalue, 129, 59);
        lblUseremailvalue.setSize("56px", "16px");
        
        Image cornerStoneImage = new Image("images/badges/CornerStone50x50.jpg");
        cornerStoneImage.setAltText("Corner Stone");
        panel.add(cornerStoneImage, 129, 91);
        cornerStoneImage.setSize("50px", "50px");
                
                Image jackass = new Image("images/badges/JackAss50x50.jpg");
                jackass.setAltText("Jack Ass");
                panel.add(jackass, 129, 159);
                jackass.setSize("50px", "50px");
                
                Image snowWhite = new Image("images/badges/SnowWhite50x50.jpg");
                snowWhite.setAltText("Snow White");
                panel.add(snowWhite, 129, 232);
                snowWhite.setSize("50px", "50px");
                
                Image image_3 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_3, 129, 301);
                image_3.setSize("50px", "50px");
                
                Image image_4 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_4, 129, 374);
                image_4.setSize("50px", "50px");
                
                Image crappedOut = new Image("images/badges/CrappedOut50x50.jpg");
                crappedOut.setAltText("Crapped Out - Holy Crap!");
                panel.add(crappedOut, 202, 91);
                crappedOut.setSize("50px", "50px");
                
                Image mythBusters = new Image("images/badges/MythBusters50x50.jpg");
                mythBusters.setAltText("Myth Busters");
                panel.add(mythBusters, 202, 159);
                mythBusters.setSize("50px", "50px");
                
                Image socialite = new Image("images/badges/Socialite50x50.jpg");
                socialite.setAltText("Socialite");
                panel.add(socialite, 202, 232);
                socialite.setSize("50px", "50px");
                
                Image image_8 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_8, 202, 301);
                image_8.setSize("50px", "50px");
                
                Image image_9 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_9, 202, 374);
                image_9.setSize("50px", "50px");
                
                Image fML = new Image("images/badges/FML50x50.jpg");
                fML.setAltText("FML");
                panel.add(fML, 271, 91);
                fML.setSize("50px", "50px");
                
                Image saint = new Image("images/badges/Saint50x50.jpg");
                saint.setAltText("Saint");
                panel.add(saint, 271, 159);
                saint.setSize("50px", "50px");
                
                Image stormShadow = new Image("images/badges/StormShadow50x50.jpg");
                stormShadow.setAltText("Storm Shadow");
                panel.add(stormShadow, 271, 232);
                stormShadow.setSize("50px", "50px");
                
                Image image_13 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_13, 271, 301);
                image_13.setSize("50px", "50px");
                
                Image image_14 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_14, 271, 374);
                image_14.setSize("50px", "50px");
                
                Image humanSacrifice = new Image("images/badges/HumanSacrifice50x50.jpg");
                humanSacrifice.setAltText("Human Sacrifice");
                panel.add(humanSacrifice, 338, 91);
                humanSacrifice.setSize("50px", "50px");
                
                Image sixMinuteAbs = new Image("images/badges/SixMinuteAbs50x50.jpg");
                sixMinuteAbs.setAltText("Six Minute Abs");
                panel.add(sixMinuteAbs, 338, 159);
                sixMinuteAbs.setSize("50px", "50px");
                
                Image teamCheater = new Image("images/badges/TeamCheater50x50.jpg");
                teamCheater.setAltText("Team Cheater");
                panel.add(teamCheater, 338, 232);
                teamCheater.setSize("50px", "50px");
                
                Image image_18 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_18, 338, 301);
                image_18.setSize("50px", "50px");
                
                Image image_19 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_19, 338, 374);
                image_19.setSize("50px", "50px");

        return panel;
    }
}
