package at.ac.fhcampuswien.AZEApplication;

public class userData {
    String username,event_type, date, comment;
    public userData(String username, String event_type, String date, String comment){
        this.username = username;
        this.event_type = event_type;
        this.date= date;
        this.comment = comment;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getEvent_type(){
        return event_type;
    }

    public void setEvent_type(String event_type){
        this.event_type = event_type;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
}
