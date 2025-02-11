package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "bezero_fitxak")
public class Bezero_fitxa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String izena;
    
    @Column(nullable = false)
    private String abizena;
    
    @Column(nullable = false)
    private String telefonoa;
    
    @Column(name="azal_sentikorra", nullable = false)
    private String azalSentikorra;
    
    @OneToMany(mappedBy = "bezeroa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("historial")
    private List<Kolore_historiala> historiala;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;

}

