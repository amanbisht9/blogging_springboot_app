package app.blogging.blogging_application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 60)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false, length = 200)
    private String about;

}
