package edu.unlv.cs.whoseturn.client.views.desktop;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Displays a User's profile.
 */
public class UserProfileDisplay extends AbstractNavigationView implements NavigationView {

	/**
	 * The user service.
	 */
	private final UsersServiceAsync usersService = GWT.create(UsersService.class);

	/**
	 * The user name of a user we'll be displaying.
	 */
	private String userName;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public final Widget bodyAsWidget() {
		AbsolutePanel panel = new AbsolutePanel();
		panel.setSize("1000px", "500px");

		Label labelPlaceHolder = new Label();
		labelPlaceHolder.setStyleName("SectionHeader");
		panel.add(labelPlaceHolder);
		labelPlaceHolder.setText("User Display");

		Label userLabel = new Label("Username:");
		panel.add(userLabel, 43, 66);

		Label emailLabel = new Label("Email:");
		panel.add(emailLabel, 65, 88);
		emailLabel.setSize("41px", "16px");

		final Label userNameValue = new Label("");
		panel.add(userNameValue, 129, 66);

		final Label emailValue = new Label("");
		panel.add(emailValue, 129, 88);

		Image cornerStone = new Image("images/CornerStone50x50trans.png");
		cornerStone.setAltText("Corner Stone");
		panel.add(cornerStone, 206, 133);
		cornerStone.setSize("50px", "50px");

		Image snowWhite = new Image("images/badges/SnowWhite50x50.jpg");
		snowWhite.setAltText("Snow White");
		panel.add(snowWhite, 206, 274);
		snowWhite.setSize("50px", "50px");

		Image veteran = new Image("images/badges/Veteran50x50.jpg");
		panel.add(veteran, 133, 416);
		veteran.setSize("50px", "50px");

		Image crappedOut = new Image("images/badges/CrappedOut50x50.jpg");
		crappedOut.setAltText("Crapped Out - Holy Crap!");
		panel.add(crappedOut, 342, 201);
		crappedOut.setSize("50px", "50px");

		Image mythBusters = new Image("images/badges/MythBusters50x50.jpg");
		mythBusters.setAltText("Myth Busters");
		panel.add(mythBusters, 406, 133);
		mythBusters.setSize("50px", "50px");

		Image socialite = new Image("images/badges/Socialite50x50.jpg");
		socialite.setAltText("Socialite");
		panel.add(socialite, 275, 343);
		socialite.setSize("50px", "50px");

		Image elite = new Image("images/badges/Elite50x50.jpg");
		panel.add(elite, 206, 416);
		elite.setSize("50px", "50px");

		Image fML = new Image("images/badges/FML50x50.jpg");
		fML.setAltText("FML");
		panel.add(fML, 342, 274);
		fML.setSize("50px", "50px");

		Image saint = new Image("images/badges/Saint50x50.jpg");
		saint.setAltText("Saint");
		panel.add(saint, 206, 343);
		saint.setSize("50px", "50px");

		Image stormShadow = new Image("images/badges/StormShadow50x50.jpg");
		stormShadow.setAltText("Storm Shadow");
		panel.add(stormShadow, 406, 201);
		stormShadow.setSize("50px", "50px");

		Image dwarf = new Image("images/badges/Dwarf50x50.jpg");
		panel.add(dwarf, 275, 274);
		dwarf.setSize("50px", "50px");

		Image luckyNo7 = new Image("images/badges/LuckNo750x50.jpg");
		panel.add(luckyNo7, 135, 274);
		luckyNo7.setSize("50px", "50px");

		Image humanSacrifice = new Image("images/badges/HumanSacrifice50x50.jpg");
		humanSacrifice.setAltText("Human Sacrifice");
		panel.add(humanSacrifice, 342, 133);
		humanSacrifice.setSize("50px", "50px");

		Image sixMinuteAbs = new Image("images/badges/SixMinuteAbs50x50.jpg");
		sixMinuteAbs.setAltText("Six Minute Abs");
		panel.add(sixMinuteAbs, 206, 201);
		sixMinuteAbs.setSize("50px", "50px");

		Image teamCheater = new Image("images/badges/TeamCheater50x50.jpg");
		teamCheater.setAltText("Team Cheater");
		panel.add(teamCheater, 342, 416);
		teamCheater.setSize("50px", "50px");

		Image rookie = new Image("images/badges/Rookie50x50.jpg");
		panel.add(rookie, 342, 343);
		rookie.setSize("50px", "50px");

		Image notTheThumb = new Image("images/badges/NotTheThumb50x50.jpg");
		panel.add(notTheThumb, 135, 201);
		notTheThumb.setSize("50px", "50px");

		Image pickUpSticks = new Image("images/badges/PickUpSticks50x50.jpg");
		panel.add(pickUpSticks, 275, 201);
		pickUpSticks.setSize("50px", "50px");

		Image crossStreams = new Image("images/badges/DontCrossTheStreams50x50.jpg");
		panel.add(crossStreams, 275, 133);
		crossStreams.setSize("50px", "50px");

		Image statSpeaking = new Image("images/badges/StatisticallySpeaking50x50.jpg");
		panel.add(statSpeaking, 133, 343);
		statSpeaking.setSize("50px", "50px");

		Image whoseTurnMaster = new Image("images/badges/WhoseTurnMaster.jpg");
		panel.add(whoseTurnMaster, 275, 416);
		whoseTurnMaster.setSize("50px", "50px");

		Image jackass = new Image("images/badges/JackAss50x50.jpg");
		panel.add(jackass, 133, 133);
		jackass.setAltText("Jack Ass");
		jackass.setSize("50px", "50px");

		usersService.getProfileInfo(userName, new AsyncCallback<List<String[]>>() {
			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught.getStackTrace());
			}

			@Override
			public void onSuccess(List<String[]> result) {
				System.out.println(result.get(0)[0]);
				userNameValue.setText(result.get(0)[0]);
				emailValue.setText(result.get(0)[1]);
			}
		});

		return panel;
	}

	/**
	 * Set the user name, so we know which record to edit.
	 * 
	 * @param userName
	 *            The username.
	 */
	public void setUsername(String userName) {
		this.userName = userName;
	}
}
