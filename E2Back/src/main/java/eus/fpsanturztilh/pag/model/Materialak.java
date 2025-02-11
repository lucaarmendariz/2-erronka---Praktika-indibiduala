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
@Table(name = "materialak")
public class Materialak implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String etiketa;
    
    @Column(nullable = false)
    private String izena;

    @ManyToOne
    @JoinColumn(name = "id_kategoria", nullable = false)
    @JsonBackReference("mat-kat")
    private Material_kategoria materialKategoria;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
    
    public Long getKategoriaId() {
        return materialKategoria != null ? materialKategoria.getId() : null;
    }
}
