package eus.fpsanturztilh.pag.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "produktu_mugimenduak")
public class Produktu_mugimenduak implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_produktua", nullable = false)
    //@JsonManagedReference("prod-mug")
    private Produktuak produktu;

    @ManyToOne
    @JoinColumn(name = "id_langilea", nullable = false)
    //@JsonBackReference("lang-mug")
    private Langileak langile;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(nullable = false)
    private LocalDateTime data;

    
    @Column(nullable = false)
    private Integer kopurua;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
}
