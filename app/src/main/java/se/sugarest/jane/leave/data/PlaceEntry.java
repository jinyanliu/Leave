package se.sugarest.jane.leave.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;

import static se.sugarest.jane.leave.utilities.Constants.TABLE_NAME;

/**
 * This class defines the schema of a table in {@link Room} for a single place's information.
 * The country column is used as an {@link Index} for fast lookup for the column.
 * <p>
 * Created by jane on 17-12-4.
 */
@Entity(tableName = TABLE_NAME, indices = {@Index(value = "placeCountry")})
public class PlaceEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String placeName;
    private String placeCity;
    private String placeCountry;
    private int category;
    private String specialties;
    private String reservation;
    private String comments;
    private String facebook;
    private String website;
    private String location;

    // Constructor used by Room to create PlaceEntries
    public PlaceEntry(int id, String placeName, String placeCity, String placeCountry, int category, String specialties,
                      String reservation, String comments, String facebook, String website,
                      String location) {
        this.id = id;
        this.placeName = placeName;
        this.placeCity = placeCity;
        this.placeCountry = placeCountry;
        this.category = category;
        this.specialties = specialties;
        this.reservation = reservation;
        this.comments = comments;
        this.facebook = facebook;
        this.website = website;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public String getPlaceCountry() {
        return placeCountry;
    }

    public int getCategory() {
        return category;
    }

    public String getSpecialties() {
        return specialties;
    }

    public String getReservation() {
        return reservation;
    }

    public String getComments() {
        return comments;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }
}
