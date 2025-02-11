package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ticket_lerroak")
public class Ticket_lerroa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_hitzordua")
    @JsonBackReference
    private Hitzorduak hitzordua;

    @OneToOne
    @JoinColumn(name = "id_zerbitzua")
    private Zerbitzuak zerbitzuak;

    @Column(nullable = false)
    private double prezioa;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;

}

