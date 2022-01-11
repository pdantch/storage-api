package br.com.storageapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Driver {
    
    /**
     * Id auto increment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * O código do motorista que está gravado no banco de dados.
     */
    @Column(name = "driver")
    private String driver;
    
    /**
     *  O arquivo a ser armazenado.
     */
    @Column(name = "document")
    private String document;

    /**
     * O arquivo será armazenado na estrutura:
     *  /stored/driver/:driver/
     */
    @Column(name = "localFile")
    private String localFile;
}
