package net.ukr.lina_chen.model.entity;

import java.io.Serializable;
import java.time.LocalTime;

public class Master implements Serializable {
    private Long id;
    private User user;
    private LocalTime timeBegin;
    private LocalTime timeEnd;

    public Master() {
    }

    public Master(Long id, User user, LocalTime timeBegin, LocalTime timeEnd) {
        this.id = id;
        this.user = user;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", user=" + user +
                ", timeBegin=" + timeBegin +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public static class MasterBuilder {
        private Master master;

        private MasterBuilder() {
            master = new Master();
        }

        public MasterBuilder withId(Long id) {
            master.id = id;
            return this;
        }

        public MasterBuilder withUser(User user) {
            master.user = user;
            return this;
        }

        public MasterBuilder withTimeBegin(LocalTime timeBegin) {
            master.timeBegin = timeBegin;
            return this;
        }

        public MasterBuilder withTimeEnd(LocalTime timeEnd) {
            master.timeEnd = timeEnd;
            return this;
        }

        public static MasterBuilder master() {
            return new MasterBuilder();
        }

        public Master build() {
            return master;
        }
    }
}
