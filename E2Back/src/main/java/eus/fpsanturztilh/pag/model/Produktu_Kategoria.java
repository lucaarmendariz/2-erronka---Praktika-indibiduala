package eus.fpsanturztilh.pag.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "produktu_kategoria")
public class Produktu_Kategoria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String izena;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
    
    @OneToMany(mappedBy = "produktuKategoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("kategoria")
    private List<Produktuak> produktuak;
}
