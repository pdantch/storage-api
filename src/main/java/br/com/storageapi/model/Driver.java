package br.com.storageapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
     * login do usuário solicitante.
     */
    @Column(name = "login", nullable = false)
    private String login;

    /**
     * O código do motorista que está gravado no banco de dados.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * O arquivo a ser armazenado.
     */
    @Lob
    @Column(name = "document", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] document;

    /**
     * O arquivo será armazenado na estrutura:
     * /stored/driver/:driver/
     */
    @Column(name = "localFile", nullable = false)
    private String localFile;

    /**
     * Registro de criação..
     */
    @Column(name = "dtCreate", nullable = false)
    private Date create;

    /**
     * Exclusão lógica.
     */
    @Column(name = "dtDelete")
    private Date delete;
}