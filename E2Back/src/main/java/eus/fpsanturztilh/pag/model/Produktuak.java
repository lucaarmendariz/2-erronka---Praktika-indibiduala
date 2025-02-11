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
@Table(name = "produktuak")
public class Produktuak implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String izena;

    @Column
    private String deskribapena;
    
    @ManyToOne
    @JoinColumn(name = "id_kategoria", nullable = false)
    @JsonBackReference("kategoria")
    private Produktu_Kategoria produktuKategoria;

   // @OneToMany(mappedBy = "produktu")
    //@JsonBackReference("prod-mug")
    //private List<Produktu_mugimenduak> mugimenduak;

    @Column(nullable = false)
    private String marka;
    
    @Column(nullable = false)
    private Integer stock;
    
    @Column(name = "stock_alerta", nullable = false)
    private Integer stockAlerta;
    
    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;
}
