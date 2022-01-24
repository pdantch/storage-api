package br.com.storageapi.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.storageapi.model.Driver;
import br.com.storageapi.model.Target;
import br.com.storageapi.repository.DriverRepository;
import br.com.storageapi.repository.TargetRepository;

@Component
public class FileComponent {

    @Value("${contato.file.root}")
    private String root;

    @Value("${contato.file.driver}")
    private String dirDriver;

    @Value("${contato.file.target}")
    private String dirTarget;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private TargetRepository targetRepository;

    /**
     * Método cria no disco a pasta para salvar os arquivos recebidos.
     *
     * @param archive
     * @param name
     * @param controller
     */
    public ResponseEntity<Object> saveFile(MultipartFile archive, String name, String request) {

        HttpStatus httpStatus = HttpStatus.OK;
        try {

            Path uniqueFilename = Paths.get("");
            if (dirDriver.equals(request)) {

                uniqueFilename = makeLocalFile(archive, name, dirDriver);
                saveDriver(archive, name, uniqueFilename);
            } else if (dirTarget.equals(request)) {

                uniqueFilename = makeLocalFile(archive, name, dirTarget);
                saveTarget(archive, name, uniqueFilename);
            }

            archive.transferTo(uniqueFilename.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Object>("Salvo com sucesso!", httpStatus);
    }

    /**
     * Método monta o caminho absoluto para receber o aquivo.
     * 
     * @param archive
     * @param name
     * @param file
     * @return
     * @throws IOException
     */
    private Path makeLocalFile(MultipartFile archive, String name, String file) throws IOException {
        Path directoryPath = Paths.get(this.root.concat("/").concat(file), name);
        String originalFilename = archive.getOriginalFilename();

        Files.createDirectories(directoryPath);
        Path uniqueFilename = createUniqueFilename(directoryPath.resolve(originalFilename),
                fileNameWithoutExt(originalFilename),
                getFileExtension(originalFilename));
        return uniqueFilename;
    }

    /**
     * Método monta e salva o objeto Target.
     * 
     * @param archive
     * @param name
     * @param uniqueFilename
     */
    private void saveTarget(MultipartFile archive, String name, Path uniqueFilename) {
        try {
            Target target = new Target();
            target.setLogin("login...");
            target.setName(name);
            target.setLocalFile(uniqueFilename.toString());
            target.setDocument(archive.getBytes());
            target.setCreate(new Date());
            this.targetRepository.save(target);
        } catch (IOException | JDBCException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método monta e salva o objeto Driver.
     * 
     * @param archive
     * @param name
     * @param uniqueFilename
     */
    private void saveDriver(MultipartFile archive, String name, Path uniqueFilename) {
        try {
            Driver driver = new Driver();
            driver.setLogin("login...");
            driver.setName(name);
            driver.setLocalFile(uniqueFilename.toString());
            driver.setDocument(archive.getBytes());
            driver.setCreate(new Date());
            this.driverRepository.save(driver);
        } catch (IOException | JDBCException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método renomeia os arquivos com nome existente no disco.
     * 
     * @param controllerFile
     * @param filename
     * @param extension
     * @return
     */
    public Path createUniqueFilename(Path controllerFile, String filename, String extension) {
        Path updatedcontrollerFile = controllerFile;
        for (int i = 1; Files.exists(updatedcontrollerFile); i++) {
            String newFilename = String.format("%s(%d).%s", filename, i, extension);
            updatedcontrollerFile = controllerFile.resolveSibling(newFilename);
        }
        System.out.println();
        return updatedcontrollerFile;
    }

    /**
     * Método separa o nome do arquivo.
     * 
     * @param fileName
     * @return
     */
    public static String fileNameWithoutExt(String fileName) {
        return Optional.of(fileName.lastIndexOf(".")).filter(i -> i >= 0).map(i -> fileName.substring(0, i))
                .orElse(fileName);
    }

    /**
     * Método separa a extensão do arquivo.
     * 
     * @param filename
     * @return
     */
    public static String getFileExtension(String filename) {
        return filename.contains(".") ? filename.substring(filename.lastIndexOf(".") + 1) : "";
    }
}