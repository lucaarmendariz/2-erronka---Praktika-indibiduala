package eus.fpsanturztilh.pag.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "zerbitzu_kategoria")
public class Zerbitzu_kategoria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String izena;

    @Column(name = "kolorea", nullable = false)
    private boolean kolorea;
    
    @Column(name = "extra", nullable = false)
    private boolean extra;
    
    @OneToMany(mappedBy = "zerbitzuKategoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Zerbitzuak> zerbitzuak;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
}
