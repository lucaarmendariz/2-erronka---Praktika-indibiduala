package eus.fpsanturztilh.pag.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "zerbitzuak")
public class Zerbitzuak implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String izena;

    @Column(name = "etxeko_prezioa", nullable = false)
    private Double etxekoPrezioa;

    @Column(name = "kanpoko_prezioa", nullable = false)
    private Double kanpokoPrezioa;
    
    @ManyToOne
    @JoinColumn(name = "id_kategoria", nullable = false)
    @JsonBackReference
    private Zerbitzu_kategoria zerbitzuKategoria;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
}
