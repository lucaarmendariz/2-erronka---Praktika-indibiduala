package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "taldeak")
public class Taldeak implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    private String kodea;

    @Column(nullable = false)
    private String izena;
    
    @OneToMany(mappedBy = "taldea", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("taldeak-langileak")
    private List<Langileak> langileak;


    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;

}

