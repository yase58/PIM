package tr.com.yavuzduran.pim.eventscheduler.dblistener;

import javax.persistence.*;

public class EventDatabaseListener {

    @PrePersist
    void onPrePersist(Object entity) {
        System.out.println("onPrePersist");
    }

    @PostPersist
    void onPostPersist(Object entity) {
        System.out.println("onPostPersist");
    }

    @PostLoad
    void onPostLoad(Object entity) {
        System.out.println("onPostLoad");
    }

    @PreUpdate
    void onPreUpdate(Object entity) {
        System.out.println("onPreUpdate");
    }

    @PostUpdate
    void onPostUpdate(Object entity) {
        System.out.println("onPostUpdate");
    }

    @PreRemove
    void onPreRemove(Object entity) {
        System.out.println("onPreRemove");
    }

    @PostRemove
    void onPostRemove(Object entity) {
        System.out.println("onPostRemove");
    }

}
