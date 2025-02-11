package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ordutegiak")
public class Ordutegiak implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kodea", nullable = false)
    private Taldeak taldea;

    @Column(nullable = false)
    private Long eguna;

    @Column(name = "hasiera_data", nullable = false)
    private LocalDate hasieraData;

    @Column(name = "amaiera_data", nullable = false)
    private LocalDate amaieraData;

    @Column(name = "hasiera_ordua", nullable = false)
    private LocalTime hasieraOrdua;

    @Column(name = "amaiera_ordua", nullable = false)
    private LocalTime amaieraOrdua;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
    
    public boolean hasInvalidFields() {
        return taldea == null ||
               eguna == null || eguna < 1 || eguna > 7 || // Suponiendo que representa días de la semana (1-7)
               hasieraData == null ||
               amaieraData == null ||
               hasieraData.isAfter(amaieraData) || // La fecha de inicio no puede ser posterior a la de finalización
               hasieraOrdua == null ||
               amaieraOrdua == null ||
               hasieraOrdua.isAfter(amaieraOrdua); // La hora de inicio no puede ser posterior a la de finalización
    }

}

