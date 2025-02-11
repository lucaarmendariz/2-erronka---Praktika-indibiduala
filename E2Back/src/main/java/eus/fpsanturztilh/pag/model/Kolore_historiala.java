package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "kolore_historialak")
public class Kolore_historiala implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_bezeroa", nullable = false)
    @JsonBackReference("historial")
    private Bezero_fitxa bezeroa;

    //@OneToOne
    //@JoinColumn(name = "id_produktua")
    //@JsonManagedReference("kolore")
  //  @JsonIgnore
   // private Produktuak produktua;
    
    @Column(name = "id_produktua", nullable = false)
    private Long id_produktua;
    
    @Column(nullable = false)
    private LocalDate data;
    
    @Column(nullable = false)
    private double kantitatea;
    
    @Column(nullable = false)
    private String bolumena;
    
    @Column(nullable = true)
    private String oharrak;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
    
   // public Long getid_produktua() {
     //   return produktua != null ? produktua.getId() : null;
   // }

}

