package at.ac.fhcampuswien.AZEApplication;

/**
 * a class for employee data to be displayed and 'get' for the timetable, time sheet.
 */
public class userData {
    String username,event_type, date, comment;
    public userData(String username, String event_type, String date, String comment){
        this.username = username;
        this.event_type = event_type;
        this.date= date;
        this.comment = comment;
    }

    /**
     * Gets the username.
     * @return the username.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Gets the event type.
     * @return the event type.
     */
    public String getEvent_type(){
        return event_type;
    }

    /**
     * Gets the date of the event.
     * @return the date.
     */
    public String getDate(){
        return date;
    }

    /**
     * Gets the comment about event.
     * @return the comment.
     */
    public String getComment(){
        return comment;
    }
}
