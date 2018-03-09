package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @OneToMany(mappedBy = "payment")
    private List<HistoryEntity> history;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    public List<HistoryEntity> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEntity> history) {
        this.history = history;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
