package Entities;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "currency", length = 45)
    private String currency;

    @Column(name = "balance")
    private long balance;

    @Column(name = "is_opened")
    private int isOpened;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private UserEntity userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
