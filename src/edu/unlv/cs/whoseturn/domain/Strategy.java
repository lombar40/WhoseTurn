package edu.unlv.cs.whoseturn.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.KeyFactory;

/**
 * Strategy domain object. 
 */
@PersistenceCapable
public class Strategy {

    /**
     * Primary key for the strategy.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String keyString;

    /**
     * Name of the strategy.
     */
    @Persistent
    private String strategyName;

    /**
     * An id for the strategy. Used for finding it.
     */
    @Persistent
    private Integer strategyId;

    /**
     * Delete status.
     */
    @Persistent
    private Boolean deleted;

    // Getters and Setters.
    /**
     * Gets the key string.
     * 
     * @return key string.
     */
    public final String getKeyString() {
        return keyString;
    }

    /**
     * Sets the key string.
     * 
     * @param keyString The key string.
     */
    public final void setKeyString(final String keyString) {
        this.keyString = keyString;
    }

    /**
     * Gets the strategy name.
     * 
     * @return the strategy name.
     */
    public final String getStrategyName() {
        return strategyName;
    }

    /**
     * Sets the strategy name.
     * 
     * @param strategyName The strategy name.
     */
    public final void setStrategyName(final String strategyName) {
        this.strategyName = strategyName;
    }

    /**
     * Gets the deleted state.
     * 
     * @return true for deleted.
     */
    public final Boolean getDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted state.
     * 
     * @param deleted True for deleted.
     */
    public final void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * findDriver is called from another function located in a service call on
     * the client layer.
     * 
     * @param users
     *            list of users.
     * @param category A category.
     * @return a String which will represent the selected driver of Whose Turn.
     */
    public final String findDriver(final List<User> users,
            final Category category) {

        /**
         * Driver name.
         */
        String driverName = "";
        
        /**
         * User.
         */
        User driver;
        
        /**
         * Strategy id.
         */
        Integer strategy = 0;
        
        /**
         * Strategy key string.
         */
        String strategyKeyString = category.getStrategyKeyString();

        switch (strategy) {
        case 0:
            driver = leastRecentlyGone(users, category);
            break;
        case 1:
            driver = lowestRatio(users, category);
            break;
        default:
            driver = chooseRandomUser(users);
            break;
        }

        return driverName;
    }

    /**
     * Algorithm which chooses a user based explicitly on the amount of times
     * they were selected/turnItems; the user with the least ratio will be
     * chosen to handle all driving responsibilities.
     * 
     * @param users
     *            list of users, as well as a category are passed into the
     *            lowestRatio method to handle and retrieve the amount of times
     *            the user participated in such a category.
     * @param category
     *            will represent a drive, chips & salsa, or ice cream
     * @return a User, which then will be used to access the a string
     *         representing the user name.
     */
    public final User lowestRatio(final List<User> users,
            final Category category) {
        
        List<Double> ratioList = new ArrayList<Double>();
        Set<String> turnItemsKeyStrings;
        List<TurnItem> turnItems = new ArrayList<TurnItem>();
        Double tempTurnCount;
        Double tempSelectedCount;
        PersistenceManager pm = PMF.get().getPersistenceManager();

        for (int i = 0; i < users.size(); i++) {
            tempTurnCount = 0.0;
            tempSelectedCount = 0.0;
            turnItemsKeyStrings = users.get(i).getTurnItems();

            for (int j = 0; j < turnItemsKeyStrings.size(); j++) {
                turnItems.add(pm.getObjectById(TurnItem.class, KeyFactory
                        .stringToKey(turnItemsKeyStrings.iterator().next())));
            }

            for (int k = 0; k < turnItems.size(); k++) {
                if (turnItems.get(k).getCategoryKeyString()
                        .equals(category.getKeyString())) {
                    tempTurnCount++;
                    if (turnItems.get(k).getSelected()) {
                        tempSelectedCount++;
                    }
                }
            }
            ratioList.add((Double) (tempSelectedCount / tempTurnCount));
        }

        Integer index = 0;
        Double tempCurrentRatio = ratioList.get(0);
        Double tempRatio;

        for (int i = 1; i < ratioList.size(); i++) {
            tempRatio = ratioList.get(i);

            if (tempRatio < tempCurrentRatio) {
                tempCurrentRatio = tempRatio;
                index = i;
            }
        }

        return users.get(index);
    }

    /**
     * Algorithm which chooses a user based explicitly on turnDateTime from
     * Turn.java, the user which has the oldest date will be selected to drive.
     * The Date objects will be compared using the predefined compareTo method,
     * which determines differences in milliseconds. Elementary comparisons
     * between a default currentTurnDate and a tempTurnDate, will be used to
     * determine which of the users has the earliest of Dates once the for loop
     * terminates.
     * 
     * @param users
     *            list of users, as well as a category are passed into the
     *            leastRecentlyGone method to handle and retrieve the amount of
     *            times the user participated in such a category.
     * @param category
     *            will represent a drive, chips & salsa, or ice cream
     * @return a User, which then will be used to access the a string
     *         representing the user name
     */
    public final User leastRecentlyGone(final List<User> users,
            final Category category) {
        Set<String> turnItemsKeyStrings;
        List<TurnItem> turnItems = new ArrayList<TurnItem>();
        List<Date> dateList = new ArrayList<Date>();
        Date tempTurnDate;
        
        /**
         * The current turn date.
         */
        Date currentTurnDate;
        
        /**
         * Temporary turn count.
         */
        Double tempTurnCount;
        
        /**
         * Temporary selected count.
         */
        Double tempSelectedCount;
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Turn tempTurn;

        for (int i = 0; i < users.size(); i++) {
            tempTurnCount = 0.0;
            tempSelectedCount = 0.0;
            turnItemsKeyStrings = users.get(i).getTurnItems();

            for (int j = 0; j < turnItemsKeyStrings.size(); j++) {
                turnItems.add(pm.getObjectById(TurnItem.class, KeyFactory
                        .stringToKey(turnItemsKeyStrings.iterator().next())));
            }
            for (int k = 0; k < turnItems.size(); k++) {
                tempTurn = pm.getObjectById(Turn.class, KeyFactory
                        .stringToKey(turnItems.get(k).getTurnKeyString()));
                dateList.add(tempTurn.getTurnDateTime());
            }
            pm.close();

        }

        Integer index = 0;
        Integer differenceOfDates;
        Date tempCurrentDate = dateList.get(0);

        for (int i = 1; i < dateList.size(); i++) {
            tempTurnDate = dateList.get(i);
            differenceOfDates = tempCurrentDate.compareTo(tempTurnDate);

            if (differenceOfDates < 0 || differenceOfDates == 0) {
                tempCurrentDate = tempTurnDate;
                index = i;
            }
        }

        return users.get(index);
    }

    /**
     * Algorithm which chooses a user based explicitly on the predefined random
     * generator The Random object will use the nextInt() method to generate an
     * integer value, which given a parameter of the the number of users will
     * choose a number in such 0 to n-1 range.
     * 
     * @param users
     *            list of users.
     * @return a User at the arbitrarily generated index, which then will be
     *         used to access a string representing the user name
     */
    public final User chooseRandomUser(final List<User> users) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(users.size());

        return users.get(randomIndex);
    }

    /**
     * Gets the strategy id.
     * 
     * @return The strategy id.
     */
    public final Integer getStrategyId() {
        return strategyId;
    }

    /**
     * Sets the strategy id.
     * 
     * @param strategyId The strategy id.
     */
    public final void setStrategyId(final Integer strategyId) {
        this.strategyId = strategyId;
    }
}
