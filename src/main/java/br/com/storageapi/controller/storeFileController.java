package br.com.storageapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.storageapi.component.FileComponent;

@RestController
@RequestMapping("/store-file")
public class storeFileController {

    /**
     * Constante usada para rotear a chamada e direcionar o file.
     */
    private static final String REQUEST_DRIVER = "/driver";

    /**
     * Constante usada para rotear a chamada e direcionar o file.
     */
    private static final String REQUEST_TARGET = "/target";

    @Autowired
    private FileComponent component;


    /**
     * O micro serviço deverá disponibilizar um endpoint para armazenar os arquivos
     * referentes aos motoristas.
     * 
     * Método: storeFileDriver
     * Utilizado para armazenar os arquivos referentes ao motorista
     * 
     * Parâmetros:
     * 1 -> id_motorista é o código do motorista que está gravado no banco de dados
     * 2 -> o arquivo a ser armazenado
     * 
     * @param archive
     * @param name
     */
    @PostMapping(REQUEST_DRIVER)
    public ResponseEntity<Object> driver(@RequestParam("archive") MultipartFile archive, @RequestParam("name") String name) {
        return this.component.saveFile(archive, name, REQUEST_DRIVER);
    }

    /**
     * O micro serviço deverá disponibilizar um endpoint para armazenar os arquivos
     * referente ao veículo.
     *
     * Método: storeFileTarget
     * Utilizado para armazenar os arquivos referente ao veículo.
     * 
     * Parâmetros:
     *  1 -> id_target -> é o código do veículo que está gravado no banco de dados
     *  2 -> o arquivo a ser armazenado
     * 
     * @param archive
     * @param name
     */
    @PostMapping(REQUEST_TARGET)
    public ResponseEntity<Object> target(@RequestParam("archive") MultipartFile archive, @RequestParam("name") String name) {
        return this.component.saveFile(archive, name, REQUEST_TARGET);
    }
}