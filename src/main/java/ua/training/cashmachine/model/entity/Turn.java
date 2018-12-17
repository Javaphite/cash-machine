package ua.training.cashmachine.model.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public class Turn {

    private int turnId;
    private User user;
    private long income;
    private LocalDateTime timeOpened;
    private LocalDateTime timeClosed;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (null == obj || getClass() != obj.getClass()) {
            return false;
        }

        Turn turn = (Turn) obj;

        return new EqualsBuilder()
                .append(turnId, turn.turnId)
                .append(timeOpened, turn.timeOpened)
                .append(timeClosed, turn.timeClosed)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(turnId)
                .append(timeOpened)
                .append(timeClosed)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("turnId", turnId)
                .append("user", user)
                .append("income", income)
                .append("timeOpened", timeOpened)
                .append("timeClosed", timeClosed)
                .toString();
    }

    public int getTurnId() {
        return turnId;
    }

    public void setTurnId(int turnId) {
        this.turnId = turnId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public LocalDateTime getTimeOpened() {
        return timeOpened;
    }

    public void setTimeOpened(LocalDateTime timeOpened) {
        this.timeOpened = timeOpened;
    }

    public LocalDateTime getTimeClosed() {
        return timeClosed;
    }

    public void setTimeClosed(LocalDateTime timeClosed) {
        this.timeClosed = timeClosed;
    }
}
