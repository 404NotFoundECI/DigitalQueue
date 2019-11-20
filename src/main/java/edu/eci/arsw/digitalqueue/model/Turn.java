package edu.eci.arsw.digitalqueue.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "turns")
public class Turn {

    private @Id String code;
    private String clientName;
    private Timestamp requestedDateTime;
    private Timestamp attendedDateTime;
    @ManyToOne
    private Queue queue;
    private Boolean attended;
    private Boolean cancelled;
    @ManyToOne
    private AttentionPoint attentionPoint;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Timestamp getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(Timestamp requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public Timestamp getAttendedDateTime() {
        return attendedDateTime;
    }

    public void setAttendedDateTime(Timestamp attendedDateTime) {
        this.attendedDateTime = attendedDateTime;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public AttentionPoint getAttentionPoint() {
        return attentionPoint;
    }

    public void setAttentionPoint(AttentionPoint attentionPoint) {
        this.attentionPoint = attentionPoint;
    }

}
