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
        panel.add(userLabel, 65, 37);
        
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
        panel.add(cornerStoneImage, 133, 133);
        cornerStoneImage.setSize("50px", "50px");
                
                Image jackass = new Image("images/badges/JackAss50x50.jpg");
                jackass.setAltText("Jack Ass");
                panel.add(jackass, 133, 201);
                jackass.setSize("50px", "50px");
                
                Image snowWhite = new Image("images/badges/SnowWhite50x50.jpg");
                snowWhite.setAltText("Snow White");
                panel.add(snowWhite, 133, 274);
                snowWhite.setSize("50px", "50px");
                
                Image liar = new Image("images/badges/Liar50x50.jpg");
                liar.setAltText("Liar");
                panel.add(liar, 133, 343);
                liar.setSize("50px", "50px");
                
                Image image_4 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_4, 133, 416);
                image_4.setSize("50px", "50px");
                
                Image crappedOut = new Image("images/badges/CrappedOut50x50.jpg");
                crappedOut.setAltText("Crapped Out - Holy Crap!");
                panel.add(crappedOut, 206, 133);
                crappedOut.setSize("50px", "50px");
                
                Image mythBusters = new Image("images/badges/MythBusters50x50.jpg");
                mythBusters.setAltText("Myth Busters");
                panel.add(mythBusters, 206, 201);
                mythBusters.setSize("50px", "50px");
                
                Image socialite = new Image("images/badges/Socialite50x50.jpg");
                socialite.setAltText("Socialite");
                panel.add(socialite, 206, 274);
                socialite.setSize("50px", "50px");
                
                Image image_8 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_8, 206, 343);
                image_8.setSize("50px", "50px");
                
                Image image_9 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_9, 206, 416);
                image_9.setSize("50px", "50px");
                
                Image fML = new Image("images/badges/FML50x50.jpg");
                fML.setAltText("FML");
                panel.add(fML, 275, 133);
                fML.setSize("50px", "50px");
                
                Image saint = new Image("images/badges/Saint50x50.jpg");
                saint.setAltText("Saint");
                panel.add(saint, 275, 201);
                saint.setSize("50px", "50px");
                
                Image stormShadow = new Image("images/badges/StormShadow50x50.jpg");
                stormShadow.setAltText("Storm Shadow");
                panel.add(stormShadow, 275, 274);
                stormShadow.setSize("50px", "50px");
                
                Image image_13 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_13, 275, 343);
                image_13.setSize("50px", "50px");
                
                Image image_14 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_14, 275, 416);
                image_14.setSize("50px", "50px");
                
                Image humanSacrifice = new Image("images/badges/HumanSacrifice50x50.jpg");
                humanSacrifice.setAltText("Human Sacrifice");
                panel.add(humanSacrifice, 342, 133);
                humanSacrifice.setSize("50px", "50px");
                
                Image sixMinuteAbs = new Image("images/badges/SixMinuteAbs50x50.jpg");
                sixMinuteAbs.setAltText("Six Minute Abs");
                panel.add(sixMinuteAbs, 342, 201);
                sixMinuteAbs.setSize("50px", "50px");
                
                Image teamCheater = new Image("images/badges/TeamCheater50x50.jpg");
                teamCheater.setAltText("Team Cheater");
                panel.add(teamCheater, 342, 274);
                teamCheater.setSize("50px", "50px");
                
                Image image_18 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_18, 342, 343);
                image_18.setSize("50px", "50px");
                
                Image image_19 = new Image("images/badges/CornerStone50x50.jpg");
                panel.add(image_19, 342, 416);
                image_19.setSize("50px", "50px");
                
                Label lblSubmitCount = new Label("Submit Count:");
                panel.add(lblSubmitCount, 20, 81);
                lblSubmitCount.setSize("86px", "16px");
                
                Label lblSubmitCount_1 = new Label("Submit Count");
                panel.add(lblSubmitCount_1, 129, 81);
                lblSubmitCount_1.setSize("127px", "16px");
                
                Label lblLieCount = new Label("Lie Count:");
                panel.add(lblLieCount, 42, 103);
                lblLieCount.setSize("64px", "16px");
                
                Label lblLieCount_1 = new Label("Lie Count");
                panel.add(lblLieCount_1, 129, 103);
                lblLieCount_1.setSize("127px", "16px");

        return panel;
    }
}
