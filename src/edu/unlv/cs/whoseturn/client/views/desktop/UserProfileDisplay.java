package edu.unlv.cs.whoseturn.client.views.desktop;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.unlv.cs.whoseturn.client.UsersService;
import edu.unlv.cs.whoseturn.client.UsersServiceAsync;
import edu.unlv.cs.whoseturn.client.views.AbstractNavigationView;
import edu.unlv.cs.whoseturn.client.views.NavigationView;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

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
		AbsolutePanel userDisplayPanel = new AbsolutePanel();
		userDisplayPanel.setSize("1000px", "500px");

		Label labelTitle = new Label();
		labelTitle.setStyleName("SectionHeader");
		userDisplayPanel.add(labelTitle);
		labelTitle.setText("User Display");

		Label userLabel = new Label("Username:");
		userDisplayPanel.add(userLabel, 43, 66);

		Label emailLabel = new Label("Email:");
		userDisplayPanel.add(emailLabel, 65, 88);
		emailLabel.setSize("41px", "16px");

		final Label userNameValue = new Label("");
		userDisplayPanel.add(userNameValue, 129, 66);

		final Label emailValue = new Label("");
		userDisplayPanel.add(emailValue, 129, 88);

		List<String> titles = new ArrayList<String>();
		titles.add("Jackass");
		titles.add("Corner Stone");
		titles.add("Don't Cross The Streams");
		titles.add("Human Sacrifice");
		titles.add("Not The Thumb");
		titles.add("Six Minute Abs");
		titles.add("Pick Up Sticks");
		titles.add("Crapped Out");
		titles.add("Lucky No. 7");
		titles.add("Snow White");
		titles.add("Dwarf");
		titles.add("FML");
		titles.add("Statistically Speaking");
		titles.add("Saint");
		titles.add("Socialite");
		titles.add("Rookie");
		titles.add("Veteran");
		titles.add("Elite");
		titles.add("Whose Turn Master");
		titles.add("Team Cheater");
		titles.add("StormShadow");
		titles.add("MythBuster");

		final List<String> titlesFinal = titles;

		List<String> descriptions = new ArrayList<String>();
		descriptions.add("User submitted a turn with only himself");
		descriptions.add("Selected out of group of 4");
		descriptions.add("Not selected out of group of 4");
		descriptions.add("Selected out of group of 5");
		descriptions.add("Not selected out of group of 5");
		descriptions.add("Selected out of group of 6");
		descriptions.add("Not selected out of group of 6");
		descriptions.add("Selected out of group of 7");
		descriptions.add("Not selected out of group of 7");
		descriptions.add("Selected out of group of 8");
		descriptions.add("Not selected out of group of 8");
		descriptions.add("Selected out of group of more than 8");
		descriptions.add("Not selected out of group of more than 8");
		descriptions.add("User has no lies for 50 turns");
		descriptions.add("User is part of a turn with more than 10 people");
		descriptions.add("User has participated in 10 turns");
		descriptions.add("User has participated in 100 turns");
		descriptions.add("User has participated in 250 turns");
		descriptions.add("User has every badge except StormShadow and MythBusters");
		descriptions.add("Everyone in a turn was selected");
		descriptions.add("User is Chris Jones");
		descriptions.add("User is Matthew Sowders");

		final List<String> descriptionsFinal = descriptions;

		List<Image> images = new ArrayList<Image>();

		final Image jackass = new Image("images/badges/Jackass50x50trans.png");
		userDisplayPanel.add(jackass, 133, 133);
		jackass.setSize("50px", "50px");
		images.add(jackass);

		final Image cornerStone = new Image("images/badges/CornerStone50x50trans.png");
		userDisplayPanel.add(cornerStone, 206, 133);
		cornerStone.setSize("50px", "50px");
		images.add(cornerStone);

		final Image crossStreams = new Image("images/badges/DontCrossTheStreams50x50trans.png");
		userDisplayPanel.add(crossStreams, 275, 133);
		crossStreams.setSize("50px", "50px");
		images.add(crossStreams);

		final Image humanSacrifice = new Image("images/badges/HumanSacrifice50x50trans.png");
		userDisplayPanel.add(humanSacrifice, 342, 133);
		humanSacrifice.setSize("50px", "50px");
		images.add(humanSacrifice);

		final Image notTheThumb = new Image("images/badges/NotTheThumb50x50trans.png");
		userDisplayPanel.add(notTheThumb, 135, 201);
		notTheThumb.setSize("50px", "50px");
		images.add(notTheThumb);

		final Image sixMinuteAbs = new Image("images/badges/SixMinuteAbs50x50trans.png");
		userDisplayPanel.add(sixMinuteAbs, 206, 201);
		sixMinuteAbs.setSize("50px", "50px");
		images.add(sixMinuteAbs);

		final Image pickUpSticks = new Image("images/badges/PickUpSticks50x50trans.png");
		userDisplayPanel.add(pickUpSticks, 275, 201);
		pickUpSticks.setSize("50px", "50px");
		images.add(pickUpSticks);

		final Image crappedOut = new Image("images/badges/CrappedOut50x50trans.png");
		userDisplayPanel.add(crappedOut, 342, 201);
		crappedOut.setSize("50px", "50px");
		images.add(crappedOut);

		final Image luckyNo7 = new Image("images/badges/LuckNo750x50trans.png");
		userDisplayPanel.add(luckyNo7, 135, 274);
		luckyNo7.setSize("50px", "50px");
		images.add(luckyNo7);

		final Image snowWhite = new Image("images/badges/SnowWhite50x50trans.png");
		userDisplayPanel.add(snowWhite, 206, 274);
		snowWhite.setSize("50px", "50px");
		images.add(snowWhite);

		final Image dwarf = new Image("images/badges/Dwarf50x50trans.png");
		userDisplayPanel.add(dwarf, 275, 274);
		dwarf.setSize("50px", "50px");
		images.add(dwarf);

		final Image fML = new Image("images/badges/FML50x50trans.png");
		userDisplayPanel.add(fML, 342, 274);
		fML.setSize("50px", "50px");
		images.add(fML);

		final Image statSpeaking = new Image("images/badges/StatisticallySpeaking50x50trans.png");
		userDisplayPanel.add(statSpeaking, 133, 343);
		statSpeaking.setSize("50px", "50px");
		images.add(statSpeaking);

		final Image saint = new Image("images/badges/Saint50x50trans.png");
		userDisplayPanel.add(saint, 206, 343);
		saint.setSize("50px", "50px");
		images.add(saint);

		final Image socialite = new Image("images/badges/Socialite50x50trans.png");
		userDisplayPanel.add(socialite, 275, 343);
		socialite.setSize("50px", "50px");
		images.add(socialite);

		final Image rookie = new Image("images/badges/Rookie50x50.jpg");
		userDisplayPanel.add(rookie, 342, 343);
		rookie.setSize("50px", "50px");
		images.add(rookie);

		final Image veteran = new Image("images/badges/Veteran50x50.jpg");
		userDisplayPanel.add(veteran, 133, 416);
		veteran.setSize("50px", "50px");
		images.add(veteran);

		final Image elite = new Image("images/badges/Elite50x50.jpg");
		userDisplayPanel.add(elite, 206, 416);
		elite.setSize("50px", "50px");
		images.add(elite);

		final Image whoseTurnMaster = new Image("images/badges/WhoseTurnMaster.jpg");
		userDisplayPanel.add(whoseTurnMaster, 275, 416);
		whoseTurnMaster.setSize("50px", "50px");
		images.add(whoseTurnMaster);

		final Image teamCheater = new Image("images/badges/TeamCheater50x50trans.png");
		userDisplayPanel.add(teamCheater, 342, 416);
		teamCheater.setSize("50px", "50px");
		images.add(teamCheater);

		final Image stormShadow = new Image("images/badges/StormShadow50x50.jpg");
		userDisplayPanel.add(stormShadow, 406, 201);
		stormShadow.setSize("50px", "50px");
		images.add(stormShadow);

		final Image mythBusters = new Image("images/badges/MythBusters50x50.jpg");
		userDisplayPanel.add(mythBusters, 406, 133);
		mythBusters.setSize("50px", "50px");
		images.add(mythBusters);

		final List<Image> finalImages = images;

		usersService.getProfileInfo(userName, new AsyncCallback<List<String[]>>() {
			@Override
			public void onFailure(Throwable caught) {
				System.err.println(caught.getStackTrace());
			}

			@Override
			public void onSuccess(final List<String[]> results) {
				userNameValue.setText(results.get(0)[0]);
				emailValue.setText(results.get(0)[1]);

				for (int i = 0; i < results.get(1).length; i++) {
					String count = results.get(1)[i];
					Image currImage = finalImages.get(i);
					final String line1 = titlesFinal.get(i);
					final String line2 = descriptionsFinal.get(i);
					final String line3 = "Count: " + results.get(1)[i];
					imagePopupPopulation(count, currImage, line1, line2, line3);
				}

			}
		});

		return userDisplayPanel;
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

	/**
	 * Pops up a box upon click to display information. Reference:
	 * http://www.gwtapps
	 * .com/doc/html/com.google.gwt.user.client.ui.PopupPanel.html
	 */
	private static class MyPopup extends PopupPanel {

		public MyPopup(String titleText, String descriptionText, String countText, String badgeColor) {
			// PopupPanel's constructor takes 'auto-hide' as its boolean
			// parameter.
			// If this is set, the panel closes itself automatically when the
			// user
			// clicks outside of it.
			super(true);

			// PopupPanel is a SimplePanel, so you have to set it's widget
			// property to
			// whatever you want its contents to be.
			Panel labelPanel = new AbsolutePanel();
			Label title = new Label();
			title.setText(titleText);
			labelPanel.add(title);
			Label description = new Label();
			description.setText(descriptionText);
			labelPanel.add(description);
			Label count = new Label();
			count.setText(countText);
			labelPanel.add(count);

			if (!"".equals(badgeColor)) {
				Label badgeColorLabel = new Label();
				badgeColorLabel.setText(badgeColor);
				labelPanel.add(badgeColorLabel);
			}

			setWidget(labelPanel);
		}
	}

	private void imagePopupPopulation(String count, Image currImage, final String line1, final String line2, final String line3) {
		String badgeColor = "";
		if (Integer.parseInt(count) == 0) {
			currImage.setStyleName("notawarded");
			badgeColor = "";
		} else if (Integer.parseInt(count) <= 3) {
			currImage.setStyleName("imageBronze");
			badgeColor = "Bronze";
		} else if (Integer.parseInt(count) <= 6) {
			currImage.setStyleName("imageSilver");
			badgeColor = "Silver";
		} else if (Integer.parseInt(count) > 6) {
			currImage.setStyleName("imageGold");
			badgeColor = "Gold";
		}

		if (line1.equals("MythBuster") || line1.equals("StormShadow") || line1.equals("Rookie") || line1.equals("Veteran") || line1.equals("Elite") || line1.equals("Saint")
				|| line1.equals("WhoseTurnMaster")) {
			badgeColor = "";
		}
		final String resultBadgeColor = badgeColor;

		currImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MyPopup myPopup = new MyPopup(line1, line2, line3, resultBadgeColor);
				myPopup.setPopupPosition(event.getClientX(), event.getClientY());
				myPopup.setAnimationEnabled(true);
				myPopup.show();

			}
		});
	}
}
