package eus.fpsanturztilh.pag.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "material_maileguaK")
public class Material_mailegua implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_materiala", nullable = false)
    private Materialak materiala;

    @OneToOne
    @JoinColumn(name = "id_langilea", nullable = false)
    private Langileak langilea;
    
    @Column(name = "hasiera_data", nullable = false)
    private LocalDateTime hasieraData;
    
    @Column(name = "amaiera_data")
    private LocalDateTime amaieraData;

    @Column(name = "sortze_data", updatable = false)
    private LocalDateTime sortzeData = LocalDateTime.now();

    @Column(name = "eguneratze_data")
    private LocalDateTime eguneratzeData;

    @Column(name = "ezabatze_data")
    private LocalDateTime ezabatzeData;

}

